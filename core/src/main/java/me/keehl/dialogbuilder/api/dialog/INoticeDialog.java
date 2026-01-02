package me.keehl.dialogbuilder.api.dialog;

import me.keehl.dialogbuilder.api.dialog.action.IDialogActionHolder;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.function.Consumer;

public interface INoticeDialog extends IDialog<INoticeDialog> {
    /**
     * Set the exit action for the dialog
     *
     * @param action the action to be performed when exiting the dialog
     * @return the server links dialog itself for method chaining
     */
    INoticeDialog action(@Nullable Consumer<IDialogActionHolder> action);
}
