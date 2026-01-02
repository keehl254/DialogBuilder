package me.keehl.dialogbuilder.dialog.action;

import me.keehl.dialogbuilder.api.IDialogAdapter;
import me.keehl.dialogbuilder.api.IDialogOpener;
import me.keehl.dialogbuilder.api.IDialogPayload;
import me.keehl.dialogbuilder.api.dialog.DialogActionResult;
import me.keehl.dialogbuilder.api.dialog.action.IDialogAction;
import me.keehl.dialogbuilder.api.dialog.action.IDialogActionHolder;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.function.Consumer;
import java.util.function.Function;

public class DialogActionHolder implements IDialogActionHolder {

    private final IDialogAdapter<?,?,?,?> adapter;

    private String label;
    private String tooltip;
    private int width = DEFAULT_WIDTH;
    private IDialogAction<?> action;

    public DialogActionHolder(IDialogAdapter<?,?,?,?> adapter) {
        this.adapter = adapter;
    }

    public DialogActionHolder(IDialogAdapter<?,?,?,?> adapter, String label, String tooltip, Integer width, IDialogAction<?> action) {
        this.adapter = adapter;
        this.label = label;
        this.tooltip = tooltip;
        this.width = width == null ? DEFAULT_WIDTH : width;
        this.action = action;
    }

    @Override
    public IDialogActionHolder label(String label) {
        this.label = label;
        return this;
    }

    @Override
    public IDialogActionHolder tooltip(String toolTip) {
        this.tooltip = toolTip;
        return this;
    }

    @Override
    public IDialogActionHolder width(@Nullable @Range(from = 1, to = 1024) Integer width) {
        this.width = width == null ? DEFAULT_WIDTH : width;
        return this;
    }

    @Override
    public @Nullable <T extends IDialogAction<T>> IDialogActionHolder action(IDialogAction<T> action) {
        this.action = action;
        return this;
    }

    @Override
    public IDialogActionHolder copyToClipboard(String value) {
        return this.action(DialogAction.copyToClipboard(value));
    }

    @Override
    public IDialogActionHolder dynamicCustom(String id) {
        return this.action(DialogAction.custom(id));
    }

    @Override
    public IDialogActionHolder dynamicCustom(String id, Object payload) {
        return this.action(DialogAction.custom(id, payload));
    }

    @Override
    public IDialogActionHolder dynamicCustom(String namespace, String id) {
        return this.action(DialogAction.custom(namespace, id));
    }

    @Override
    public IDialogActionHolder dynamicCustom(String namespace, String id, Object payload) {
        return this.action(DialogAction.custom(namespace, id, payload));
    }

    @Override
    public IDialogActionHolder dynamicCustom(Function<IDialogPayload, DialogActionResult> action) {
        return this.action(DialogAction.custom(action));
    }

    @Override
    public IDialogActionHolder dynamicRunCommand(String template) {
        return this.action(DialogAction.dynamicCommand(template));
    }

    @Override
    public IDialogActionHolder openUrl(String url) {
        return this.action(DialogAction.openURL(url));
    }

    @Override
    public IDialogActionHolder runCommand(String command) {
        return this.action(DialogAction.runCommand(command));
    }

    @Override
    public IDialogActionHolder suggestCommand(String command) {
        return this.action(DialogAction.suggestCommand(command));
    }

    @Override
    public IDialogActionHolder showDialog(Object dialog) {
        return this.action(DialogAction.showDialog(dialog));
    }

    @Override
    public IDialogActionHolder showDialog(String namespace, String dialogId) {
        return this.action(DialogAction.showDialog(namespace, dialogId));
    }

    @Override
    public IDialogActionHolder showDialog(IDialogOpener dialogOpener) {
        return this.action(DialogAction.showDialog(dialogOpener));
    }

    public String getLabel() {
        return this.label;
    }

    public String getTooltip() {
        return this.tooltip;
    }

    public int getWidth() {
        return this.width;
    }

    public IDialogAction<?> getAction() {
        return this.action;
    }
}
