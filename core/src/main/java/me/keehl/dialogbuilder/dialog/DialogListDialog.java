package me.keehl.dialogbuilder.dialog;

import me.keehl.dialogbuilder.api.AdapterType;
import me.keehl.dialogbuilder.api.IDialogAdapter;
import me.keehl.dialogbuilder.api.IDialogOpener;
import me.keehl.dialogbuilder.api.dialog.IDialogListDialog;
import me.keehl.dialogbuilder.api.dialog.WrappedDialog;
import me.keehl.dialogbuilder.api.dialog.action.IDialogActionHolder;
import me.keehl.dialogbuilder.dialog.action.DialogActionHolder;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class DialogListDialog extends Dialog<IDialogListDialog> implements IDialogListDialog {

    private final List<Object> dialogs = new ArrayList<>();
    private @Nullable DialogActionHolder exitAction;
    private int buttonWidth = DEFAULT_BUTTON_WIDTH;
    private int columns = DEFAULT_COLUMNS;

    public DialogListDialog(IDialogAdapter<?,?,?,?> adapter, String namespace) {
        super(adapter, namespace);
    }

    @Override
    protected IDialogListDialog self() {
        return this;
    }

    @Override
    public IDialogListDialog dialog(String namespace, String dialogID) {

        if(this.adapter.getAdapterType() == AdapterType.SPIGOT)
            throw new UnsupportedOperationException("DialogListDialog does not support dialog by namespace and ID in Bungee/Spigot.");

        this.dialogs.add(new WrappedDialog(namespace, dialogID));
        return this;
    }

    @Override
    public IDialogListDialog dialog(Object dialog) {
        this.dialogs.add(dialog);
        return this;
    }

    @Override
    public IDialogListDialog dialog(IDialogOpener dialogOpener) {
        this.dialogs.add(dialogOpener);
        return this;
    }

    @Override
    public IDialogListDialog columns(@Nullable Integer columns) {
        this.columns = columns == null ? DEFAULT_COLUMNS : columns;
        return this;
    }

    @Override
    public IDialogListDialog buttonWidth(@Nullable @Range(from = 1, to = 1024) Integer buttonWidth) {
        this.buttonWidth = buttonWidth == null ? DEFAULT_BUTTON_WIDTH : buttonWidth;
        return this;
    }
    @Override
    public IDialogListDialog exitAction(@Nullable Consumer<IDialogActionHolder> action) {
        if(action == null) {
            this.exitAction = null;
            return this;
        }

        DialogActionHolder holder = new DialogActionHolder(this.adapter);
        action.accept(holder);

        this.exitAction = holder;
        return this;
    }

    public int getColumns() {
        return this.columns;
    }

    public int getButtonWidth() {
        return this.buttonWidth;
    }

    public List<Object> getDialogs() {
        return this.dialogs;
    }

    public @Nullable DialogActionHolder getExitAction() {
        return this.exitAction;
    }
}
