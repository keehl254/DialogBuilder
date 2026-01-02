package me.keehl.dialogbuilder;

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
import me.keehl.dialogbuilder.spigot.SpigotDialogOpener;
import me.keehl.dialogbuilder.spigot.SpigotDialogPayload;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.dialog.Dialog;
import net.md_5.bungee.api.dialog.DialogBase;
import net.md_5.bungee.api.dialog.action.Action;
import net.md_5.bungee.api.dialog.action.ActionButton;
import net.md_5.bungee.api.dialog.action.CustomClickAction;
import net.md_5.bungee.api.dialog.action.StaticAction;
import net.md_5.bungee.api.dialog.body.DialogBody;
import net.md_5.bungee.api.dialog.body.PlainMessageBody;
import net.md_5.bungee.api.dialog.chat.ShowDialogClickEvent;
import net.md_5.bungee.api.dialog.input.*;
import net.md_5.bungee.api.dialog.input.DialogInput;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCustomClickEvent;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

@SuppressWarnings("UnstableApiUsage")
public class SpigotDialogAdapter extends DialogAdapter<Dialog, DialogBase, DialogInput, DialogBody> {

    public SpigotDialogAdapter(String defaultNamespace) {
        super(defaultNamespace);
    }

    @Override
    public AdapterType getAdapterType() {
        return AdapterType.SPIGOT;
    }

    public BaseComponent convertStringToComponent(String message) {
        if (message == null)
            return new TextComponent();
        return TextComponent.fromLegacy(message);
    }

    @EventHandler
    public void onCustomClick(PlayerCustomClickEvent event) {
        NamespacedKey key = event.getId();
        Consumer<IDialogPayload> action = this.customActions.get(key);
        if (action == null) return;

        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        action.accept(new SpigotDialogPayload(uuid, false, event.getData()));
    }

    @Override
    public DialogBody convertDialogTextBody(DialogTextBody body) {
        return new PlainMessageBody(convertStringToComponent(body.getText()), body.getWidth());
    }

    @Override
    public DialogBody convertDialogItemBody(DialogItemBody body) {
        return new PlainMessageBody(convertStringToComponent(ChatColor.translateAlternateColorCodes('&', "&cThis item body is not supported in Spigot!")));
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

        return new TextInput(input.getKey(),
                input.getWidth(),
                convertStringToComponent(input.getLabel()),
                input.getLabel() != null,
                input.getInitial() != null ? input.getInitial() : "",
                input.getMaxLength(),
                input.getMaxLines() == null && input.getHeight() == null ? null : new TextInput.Multiline(input.getMaxLines(), input.getHeight())
        );
    }

    @Override
    public DialogInput convertDialogBooleanInput(DialogBooleanInput input) {

        return new BooleanInput(input.getKey(),
                convertStringToComponent(input.getLabel()),
                input.getInitial(),
                input.getOnTrue() != null ? input.getOnTrue() : "true",
                input.getOnFalse() != null ? input.getOnFalse() : "false"
        );
    }

    @Override
    public DialogInput convertDialogNumberRangeInput(DialogNumberRangeInput input) {

        return new NumberRangeInput(input.getKey(),
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
        List<InputOption> options = new ArrayList<>();
        if (input.getOptions() != null) {
            for (IDialogSingleOptionInputEntry apiOption : input.getOptions()) {
                DialogSingleOptionInputEntry option = (DialogSingleOptionInputEntry) apiOption;
                options.add(new InputOption(option.getID(), this.convertStringToComponent(option.getDisplay()), option.getIsDefault()));
            }
        }

        return new SingleOptionInput(input.getKey(),
                input.getWidth(),
                convertStringToComponent(input.getLabel()),
                input.getLabel() != null,
                options
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

        Optional<DialogBase.AfterAction> afterActionOpt = Arrays.stream(DialogBase.AfterAction.values())
                .filter(x -> x.name().equalsIgnoreCase(dialog.getAfterAction().getKey()))
                .findFirst();

        List<DialogBody> body = dialog.getBody().stream().map(this::convertDialogBody).toList();
        List<DialogInput> input = dialog.getInput().stream().map(this::convertDialogInput).toList();

        return new DialogBase(
                convertStringToComponent(dialog.getTitle()),
                dialog.getExternalTitle() == null ? null : convertStringToComponent(dialog.getExternalTitle()),
                input,
                body,
                dialog.getCanCloseWithEscape(),
                dialog.canPause(),
                afterActionOpt.orElse(DialogBase.AfterAction.CLOSE)
        );
    }

    private Action createActionFromDialogAction(IDialogAction<?> apiDialogAction) {
        if (apiDialogAction instanceof CopyToClipboardAction(String value))
            return new StaticAction(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, value));
        if (apiDialogAction instanceof OpenURLAction(String url))
            return new StaticAction(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
        if (apiDialogAction instanceof RunCommandAction(String command))
            return new StaticAction(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
        if (apiDialogAction instanceof SuggestCommandAction(String command))
            return new StaticAction(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, command));
        if (apiDialogAction instanceof DynamicCustomAction(String namespace, String ID))
            return new CustomClickAction(namespace + ":" + ID);

        if (apiDialogAction instanceof DialogBuilderAction(Function<IDialogPayload, DialogActionResult> action)) {
            String key = UUID.randomUUID().toString();

            Consumer<IDialogPayload> payloadConsumer = (payload) -> {
                if(action.apply(payload) == DialogActionResult.UNREGISTER) {
                    this.unregisterCustomAction("dialog-builder", key);
                }
            };

            this.registerCustomAction("dialog-builder", key, payloadConsumer);
            return new CustomClickAction("dialog-builder:" + key);
        }
        if (apiDialogAction instanceof ShowDialogAction(Object dialogObject)) {
            if (dialogObject instanceof WrappedDialog(String namespace, String dialogID)) {
                return new StaticAction(new ShowDialogClickEvent(namespace + ":" + dialogID));
            } else if (dialogObject instanceof Dialog dialog) {
                return new StaticAction(new ShowDialogClickEvent(dialog));
            } else if (dialogObject instanceof SpigotDialogOpener(Dialog dialog)) {
                return new StaticAction(new ShowDialogClickEvent(dialog));
            }
            return null;
        }

        return null;
    }

    private ActionButton createActionButtonFromDialogActionHolder(IDialogActionHolder apiActionHolder) {
        DialogActionHolder actionHolder = (DialogActionHolder) apiActionHolder;
        BaseComponent toolTip = actionHolder.getTooltip() == null || actionHolder.getTooltip().isBlank() ? null : this.convertStringToComponent(actionHolder.getTooltip());

        return new ActionButton(this.convertStringToComponent(actionHolder.getLabel()),
                toolTip,
                actionHolder.getWidth(),
                this.createActionFromDialogAction(actionHolder.getAction())
        );
    }

    private Dialog getConfirmationDialogType(ConfirmationDialog confirmationDialog, DialogBase dialogBase) {
        return new net.md_5.bungee.api.dialog.ConfirmationDialog(
                dialogBase,
                this.createActionButtonFromDialogActionHolder(confirmationDialog.getYesAction()),
                this.createActionButtonFromDialogActionHolder(confirmationDialog.getNoAction())
        );
    }

    private Dialog getMultiActionDialogType(MultiActionDialog dialog, DialogBase dialogBase) {
        List<ActionButton> buttons = dialog.getActions().stream().map(this::createActionButtonFromDialogActionHolder).toList();

        return new net.md_5.bungee.api.dialog.MultiActionDialog(dialogBase,
                buttons,
                dialog.getColumns(),
                this.createActionButtonFromDialogActionHolder(dialog.getExitAction())
        );
    }

    private Dialog getServerLinksDialogType(ServerLinksDialog dialog, DialogBase dialogBase) {
        return new net.md_5.bungee.api.dialog.ServerLinksDialog(dialogBase,
                this.createActionButtonFromDialogActionHolder(dialog.getExitAction()),
                dialog.getColumns(),
                dialog.getButtonWidth()
        );
    }

    private Dialog getNoticeDialogType(NoticeDialog dialog, DialogBase dialogBase) {

        return new net.md_5.bungee.api.dialog.NoticeDialog(dialogBase,
                this.createActionButtonFromDialogActionHolder(dialog.getAction())
        );
    }

    private Dialog getDialogListDialog(DialogListDialog dialogListDialog, DialogBase dialogBase) {

        List<Dialog> dialogList = new ArrayList<>();
        for (Object dialogObject : dialogListDialog.getDialogs()) {
            if (dialogObject instanceof Dialog dialogLike) {
                dialogList.add(dialogLike);
            } else if (dialogObject instanceof SpigotDialogOpener(Dialog dialog)) {
                dialogList.add(dialog);
            }
        }

        return new net.md_5.bungee.api.dialog.DialogListDialog(dialogBase,
                dialogList,
                this.createActionButtonFromDialogActionHolder(dialogListDialog.getExitAction()),
                dialogListDialog.getColumns(),
                dialogListDialog.getButtonWidth()
        );
    }

    public Dialog createDialog(IDialog<?> dialog, DialogBase dialogBase) {

        if (dialog instanceof ConfirmationDialog confirmationDialog)
            return this.getConfirmationDialogType(confirmationDialog, dialogBase);
        if(dialog instanceof MultiActionDialog multiActionDialog)
            return this.getMultiActionDialogType(multiActionDialog, dialogBase);
        if(dialog instanceof ServerLinksDialog serverLinksDialog)
            return this.getServerLinksDialogType(serverLinksDialog, dialogBase);
        if(dialog instanceof NoticeDialog noticeDialog)
            return this.getNoticeDialogType(noticeDialog, dialogBase);
        if(dialog instanceof DialogListDialog dialogListDialog)
            return this.getDialogListDialog(dialogListDialog, dialogBase);

        return null;
    }

    @Override
    public Optional<Dialog> convertDialog(IDialog<?> apiDialog) {

        DialogBase dialogBase = this.getDialogBase(apiDialog);
        if (dialogBase == null)
            return Optional.empty();

        return Optional.of(this.createDialog(apiDialog, dialogBase));
    }

    @Override
    public boolean clearDialog(UUID uuid) {
        Player player = Bukkit.getServer().getPlayer(uuid);
        if (player == null)
            return false;
        player.clearDialog();
        return true;
    }

    @Override
    public IDialogOpener opener(IDialog<?> apiDialog) {
        Optional<Dialog> dialog = this.convertDialog(apiDialog);
        return dialog.map(SpigotDialogOpener::new).orElse(null);
    }

}
