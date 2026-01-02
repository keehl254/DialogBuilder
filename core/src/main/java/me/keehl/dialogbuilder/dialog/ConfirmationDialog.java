package me.keehl.dialogbuilder.dialog;

import me.keehl.dialogbuilder.api.IDialogAdapter;
import me.keehl.dialogbuilder.api.dialog.IConfirmationDialog;
import me.keehl.dialogbuilder.api.dialog.action.IDialogActionHolder;
import me.keehl.dialogbuilder.dialog.action.DialogActionHolder;

import java.util.function.Consumer;

public class ConfirmationDialog extends Dialog<IConfirmationDialog> implements IConfirmationDialog {

    private IDialogActionHolder yesAction;
    private IDialogActionHolder noAction;

    public ConfirmationDialog(IDialogAdapter<?,?,?,?> adapter, String namespace) {
        super(adapter, namespace);

        this.yesAction = new DialogActionHolder(adapter, "Yes", null, 150, null);
        this.noAction = new DialogActionHolder(adapter, "No", null, 150, null);
    }

    @Override
    protected ConfirmationDialog self() {
        return this;
    }

    @Override
    public IConfirmationDialog yesAction(Consumer<IDialogActionHolder> action) {
        DialogActionHolder holder = new DialogActionHolder(this.adapter);
        action.accept(holder);

        this.yesAction = holder;
        return this;
    }

    @Override
    public IConfirmationDialog noAction(Consumer<IDialogActionHolder> action) {
        DialogActionHolder holder = new DialogActionHolder(this.adapter);
        action.accept(holder);

        this.noAction = holder;
        return this;
    }

    public IDialogActionHolder getYesAction() {
        return this.yesAction;
    }

    public IDialogActionHolder getNoAction() {
        return this.noAction;
    }
}
