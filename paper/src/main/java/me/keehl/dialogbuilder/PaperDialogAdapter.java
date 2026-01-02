package me.keehl.dialogbuilder;

import io.papermc.paper.connection.PlayerConfigurationConnection;
import io.papermc.paper.connection.PlayerGameConnection;
import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.event.player.PlayerCustomClickEvent;
import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.data.dialog.ActionButton;
import io.papermc.paper.registry.data.dialog.DialogBase;
import io.papermc.paper.registry.data.dialog.DialogRegistryEntry;
import io.papermc.paper.registry.data.dialog.action.DialogAction;
import io.papermc.paper.registry.data.dialog.body.DialogBody;
import io.papermc.paper.registry.data.dialog.body.PlainMessageDialogBody;
import io.papermc.paper.registry.data.dialog.input.DialogInput;
import io.papermc.paper.registry.data.dialog.input.SingleOptionDialogInput;
import io.papermc.paper.registry.data.dialog.input.TextDialogInput;
import io.papermc.paper.registry.data.dialog.type.DialogType;
import io.papermc.paper.registry.set.RegistrySet;
import me.keehl.dialogbuilder.api.AdapterType;
import me.keehl.dialogbuilder.api.IDialogOpener;
import me.keehl.dialogbuilder.api.IDialogPayload;
import me.keehl.dialogbuilder.api.body.IDialogBody;
import me.keehl.dialogbuilder.api.dialog.DialogActionResult;
import me.keehl.dialogbuilder.api.dialog.IDialog;
import me.keehl.dialogbuilder.api.dialog.WrappedDialog;
import me.keehl.dialogbuilder.api.dialog.action.IDialogAction;
import me.keehl.dialogbuilder.api.dialog.action.IDialogActionHolder;
import me.keehl.dialogbuilder.api.input.IDialogInput;
import me.keehl.dialogbuilder.api.input.IDialogSingleOptionInputEntry;
import me.keehl.dialogbuilder.body.DialogItemBody;
import me.keehl.dialogbuilder.body.DialogTextBody;
import me.keehl.dialogbuilder.dialog.*;
import me.keehl.dialogbuilder.dialog.action.DialogActionHolder;
import me.keehl.dialogbuilder.dialog.action.actions.*;
import me.keehl.dialogbuilder.input.*;
import me.keehl.dialogbuilder.paper.PaperDialogOpener;
import me.keehl.dialogbuilder.paper.PaperDialogPayload;
import net.kyori.adventure.dialog.DialogLike;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.KeyPattern;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

@SuppressWarnings("UnstableApiUsage")
public class PaperDialogAdapter extends DialogAdapter<Dialog, DialogBase, DialogInput, DialogBody> {

    public PaperDialogAdapter(String defaultNamespace) {
        super(defaultNamespace);
    }

    @Override
    public AdapterType getAdapterType() {
        return AdapterType.PAPER;
    }

    public Component convertStringToComponent(String message) {
        if (message == null)
            return Component.empty();
        if(message.contains("§")) {
            message = MiniMessage.miniMessage().serialize(LegacyComponentSerializer.legacySection().deserialize(message));
        }
        return MiniMessage.miniMessage().deserialize(message);
    }

    @EventHandler
    public void onCustomClick(PlayerCustomClickEvent event) {
        Consumer<IDialogPayload> action = this.customActions.get(new NamespacedKey(event.getIdentifier().namespace(), event.getIdentifier().value()));
        if (action == null)
            return;

        UUID uuid = switch (event.getCommonConnection()) {
            case PlayerGameConnection pgc -> pgc.getPlayer().getUniqueId();
            case PlayerConfigurationConnection pcc -> pcc.getProfile().getId();
            default -> null;
        };
        if (uuid == null)
            return;

        action.accept(new PaperDialogPayload(uuid, false, event.getDialogResponseView()));
    }

    @Override
    public DialogBody convertDialogTextBody(DialogTextBody body) {
        return DialogBody.plainMessage(convertStringToComponent(body.getText()), body.getWidth());
    }

    @Override
    public DialogBody convertDialogItemBody(DialogItemBody body) {

        if (!(body.getItem() instanceof ItemStack itemStack))
            return null;

        PlainMessageDialogBody description = null;
        if (body.getDescription() instanceof DialogTextBody textBody) {
            description = (PlainMessageDialogBody) convertDialogTextBody(textBody);
        }

        return DialogBody.item(itemStack, description, body.getShowDecorations(), body.getShowTooltip(), body.getWidth(), body.getHeight());
    }

    @Override
    public DialogBody convertDialogBody(IDialogBody body) {

        if (body instanceof DialogTextBody textBody)
            return this.convertDialogTextBody(textBody);
        if (body instanceof DialogItemBody itemBody)
            return this.convertDialogItemBody(itemBody);

        return null;
    }

    @Override
    public DialogInput convertDialogTextInput(DialogTextInput input) {
        return DialogInput.text(
                input.getKey(),
                input.getWidth(),
                convertStringToComponent(input.getLabel()),
                input.getLabel() != null,
                input.getInitial() != null ? input.getInitial() : "",
                input.getMaxLength(),
                input.getMaxLines() == null && input.getHeight() == null ? null : TextDialogInput.MultilineOptions.create(input.getMaxLines(), input.getHeight())
        );
    }

    @Override
    public DialogInput convertDialogBooleanInput(DialogBooleanInput input) {
        return DialogInput.bool(
                input.getKey(),
                convertStringToComponent(input.getLabel()),
                input.getInitial(),
                input.getOnTrue() != null ? input.getOnTrue() : "true",
                input.getOnFalse() != null ? input.getOnFalse() : "false"
        );
    }

    @Override
    public DialogInput convertDialogNumberRangeInput(DialogNumberRangeInput input) {
        return DialogInput.numberRange(
                input.getKey(),
                input.getWidth(),
                convertStringToComponent(input.getLabel()),
                input.getLabelFormat(),
                input.getStart(),
                input.getEnd(),
                input.getInitial(),
                input.getStep()
        );
    }

    @Override
    public DialogInput convertDialogSingleOptionInput(DialogSingleOptionInput input) {
        List<SingleOptionDialogInput.OptionEntry> options = new ArrayList<>();
        if (input.getOptions() != null) {
            for (IDialogSingleOptionInputEntry apiOption : input.getOptions()) {
                DialogSingleOptionInputEntry option = (DialogSingleOptionInputEntry) apiOption;
                options.add(SingleOptionDialogInput.OptionEntry.create(option.getID(), this.convertStringToComponent(option.getDisplay()), option.getIsDefault()));
            }
        }

        return DialogInput.singleOption(
                input.getKey(),
                input.getWidth(),
                options,
                convertStringToComponent(input.getLabel()),
                input.getLabel() != null
        );
    }

    @Override
    public DialogInput convertDialogInput(IDialogInput input) {

        if (input instanceof DialogTextInput textInput)
            return this.convertDialogTextInput(textInput);
        if (input instanceof DialogBooleanInput booleanInput)
            return this.convertDialogBooleanInput(booleanInput);
        if (input instanceof DialogNumberRangeInput numberRangeInput)
            return this.convertDialogNumberRangeInput(numberRangeInput);
        if (input instanceof DialogSingleOptionInput singleOptionInput)
            return this.convertDialogSingleOptionInput(singleOptionInput);

        return null;
    }

    public DialogBase getDialogBase(IDialog<?> apiDialog) {
        me.keehl.dialogbuilder.dialog.Dialog<?> dialog = (me.keehl.dialogbuilder.dialog.Dialog<?>) apiDialog;

        Optional<DialogBase.DialogAfterAction> afterActionOpt = Arrays.stream(DialogBase.DialogAfterAction.values())
                .filter(x -> x.name().equalsIgnoreCase(dialog.getAfterAction().getKey()))
                .findFirst();

        List<DialogBody> body = dialog.getBody().stream().map(this::convertDialogBody).toList();
        List<DialogInput> input = dialog.getInput().stream().map(this::convertDialogInput).toList();

        return DialogBase.create(
                convertStringToComponent(dialog.getTitle()),
                dialog.getExternalTitle() == null ? null : convertStringToComponent(dialog.getExternalTitle()),
                dialog.getCanCloseWithEscape(),
                dialog.canPause(),
                afterActionOpt.orElse(DialogBase.DialogAfterAction.CLOSE),
                body,
                input
        );
    }

    private Dialog convertDialogObjectToDialog(Object dialogObject) {
        if (dialogObject instanceof WrappedDialog(String namespace, String dialogID)) {
            return RegistryAccess.registryAccess().getRegistry(RegistryKey.DIALOG).getOrThrow(Key.key(namespace, dialogID));
        } else if (dialogObject instanceof PaperDialogOpener(Dialog dialog)) {
            return dialog;
        } else if (dialogObject instanceof Dialog dialog) {
            return dialog;
        }

        return null;
    }

    private DialogAction createActionFromDialogAction(IDialogAction<?> apiDialogAction) {
        if (apiDialogAction instanceof CopyToClipboardAction(String value))
            return DialogAction.staticAction(ClickEvent.copyToClipboard(value));
        if (apiDialogAction instanceof OpenURLAction(String url))
            return DialogAction.staticAction(ClickEvent.openUrl(url));
        if (apiDialogAction instanceof RunCommandAction(String command))
            return DialogAction.staticAction(ClickEvent.runCommand(command));
        if (apiDialogAction instanceof SuggestCommandAction(String command))
            return DialogAction.staticAction(ClickEvent.suggestCommand(command));
        if (apiDialogAction instanceof DynamicCustomAction(String namespace, String ID))
            return DialogAction.customClick(Key.key(namespace, ID), null);
        if (apiDialogAction instanceof DialogBuilderAction(Function<IDialogPayload, DialogActionResult> action)) {
            String key = UUID.randomUUID().toString();

            Consumer<IDialogPayload> payloadConsumer = (payload) -> {
                if(action.apply(payload) == DialogActionResult.UNREGISTER) {
                    this.unregisterCustomAction("dialog-builder", key);
                }
            };

            this.registerCustomAction("dialog-builder", key, payloadConsumer);
            return DialogAction.customClick(Key.key("dialog-builder", key), null);
        }
        if (apiDialogAction instanceof ShowDialogAction(Object dialogObject)) {

            DialogLike dialog = this.convertDialogObjectToDialog(dialogObject);
            if (dialog != null)
                return DialogAction.staticAction(ClickEvent.showDialog(dialog));

            return null;
        }

        return null;
    }

    private ActionButton createActionButtonFromDialogActionHolder(IDialogActionHolder apiActionHolder) {
        DialogActionHolder actionHolder = (DialogActionHolder) apiActionHolder;
        Component toolTip = actionHolder.getTooltip() == null || actionHolder.getTooltip().isBlank() ? null : this.convertStringToComponent(actionHolder.getTooltip());

        return ActionButton
                .builder(this.convertStringToComponent(actionHolder.getLabel()))
                .tooltip(toolTip)
                .width(actionHolder.getWidth())
                .action(this.createActionFromDialogAction(actionHolder.getAction()))
                .build();
    }

    private DialogType getConfirmationDialogType(ConfirmationDialog confirmationDialog) {
        return DialogType.confirmation(
                this.createActionButtonFromDialogActionHolder(confirmationDialog.getYesAction()),
                this.createActionButtonFromDialogActionHolder(confirmationDialog.getNoAction())
        );
    }

    private DialogType getMultiActionDialogType(MultiActionDialog dialog) {
        List<ActionButton> buttons = dialog.getActions().stream().map(this::createActionButtonFromDialogActionHolder).toList();
        return DialogType.multiAction(
                buttons,
                this.createActionButtonFromDialogActionHolder(dialog.getExitAction()),
                dialog.getColumns()
        );
    }

    private DialogType getServerLinksDialogType(ServerLinksDialog dialog) {
        return DialogType.serverLinks(
                this.createActionButtonFromDialogActionHolder(dialog.getExitAction()),
                dialog.getColumns(),
                dialog.getButtonWidth()
        );
    }

    private DialogType getNoticeDialogType(NoticeDialog dialog) {
        return DialogType.notice(
                this.createActionButtonFromDialogActionHolder(dialog.getAction())
        );
    }

    private DialogType getDialogListDialog(DialogListDialog dialog) {

        List<Dialog> dialogList = new ArrayList<>();
        for (Object dialogObject : dialog.getDialogs()) {
            Dialog dialogLike = convertDialogObjectToDialog(dialogObject);
            if (dialogLike != null) {
                dialogList.add(dialogLike);
            }
        }

        return DialogType.dialogList(
                RegistrySet.valueSet(RegistryKey.DIALOG, dialogList),
                this.createActionButtonFromDialogActionHolder(dialog.getExitAction()),
                dialog.getColumns(),
                dialog.getButtonWidth()
        );
    }

    public DialogType getDialogType(IDialog<?> dialog) {

        if (dialog instanceof ConfirmationDialog confirmationDialog)
            return this.getConfirmationDialogType(confirmationDialog);
        if (dialog instanceof MultiActionDialog multiActionDialog)
            return this.getMultiActionDialogType(multiActionDialog);
        if (dialog instanceof ServerLinksDialog serverLinksDialog)
            return this.getServerLinksDialogType(serverLinksDialog);
        if (dialog instanceof NoticeDialog noticeDialog)
            return this.getNoticeDialogType(noticeDialog);
        if (dialog instanceof DialogListDialog dialogListDialog)
            return this.getDialogListDialog(dialogListDialog);

        return null;
    }

    public final Consumer<DialogRegistryEntry.Builder> getDialogBuilder(IDialog<?> dialog) {

        DialogBase dialogBase = this.getDialogBase(dialog);
        if (dialogBase == null)
            return null;

        DialogType dialogType = this.getDialogType(dialog);
        if (dialogType == null)
            return null;

        return builder -> builder.base(dialogBase).type(dialogType);
    }

    @Override
    public Optional<Dialog> convertDialog(IDialog<?> apiDialog) {

        Consumer<DialogRegistryEntry.Builder> builderConsumer = this.getDialogBuilder(apiDialog);
        if (builderConsumer == null)
            return Optional.empty();

        Dialog dialog = Dialog.create(factory -> {
            builderConsumer.accept(factory.empty());
        });
        return Optional.of(dialog);
    }

    @Override
    public boolean clearDialog(UUID uuid) {
        Player player = Bukkit.getServer().getPlayer(uuid);
        if (player == null)
            return false;

        try {
            player.closeDialog();
        } catch (Throwable e) {
            player.closeInventory();
        }

        return true;
    }

    @Override
    public IDialogOpener opener(IDialog<?> apiDialog) {
        Optional<Dialog> dialog = this.convertDialog(apiDialog);
        return dialog.map(PaperDialogOpener::new).orElse(null);
    }
}
