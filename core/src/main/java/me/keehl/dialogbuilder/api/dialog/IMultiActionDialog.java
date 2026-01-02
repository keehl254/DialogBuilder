package me.keehl.dialogbuilder.api.dialog;

import me.keehl.dialogbuilder.api.dialog.action.IDialogActionHolder;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public interface IMultiActionDialog extends IDialog<IMultiActionDialog> {
    int DEFAULT_COLUMNS = 2;

    /**
     * Set the number of columns for the dialog
     *
     * @param columns the number of columns to set
     * @return the dialog itself for method chaining
     */
    IMultiActionDialog columns(Integer columns);

    /**
     * Add an action to the dialog
     *
     * @param action the action to be performed
     * @return the dialog itself for method chaining
     */
    IMultiActionDialog action(Consumer<IDialogActionHolder> action);

    /**
     * Set the exit action for the dialog
     *
     * @param action the action to be performed when exiting the dialog
     * @return the dialog itself for method chaining
     */
    IMultiActionDialog exitAction(@Nullable Consumer<IDialogActionHolder> action);


}
