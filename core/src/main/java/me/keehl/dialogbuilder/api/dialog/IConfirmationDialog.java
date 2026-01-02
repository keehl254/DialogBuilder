package me.keehl.dialogbuilder.api.dialog;

import me.keehl.dialogbuilder.api.dialog.action.IDialogActionHolder;
import me.keehl.dialogbuilder.dialog.action.DialogActionHolder;

import java.util.function.Consumer;

public interface IConfirmationDialog extends IDialog<IConfirmationDialog> {

    IConfirmationDialog yesAction(Consumer<IDialogActionHolder> action);
    IConfirmationDialog noAction(Consumer<IDialogActionHolder> action);

}
