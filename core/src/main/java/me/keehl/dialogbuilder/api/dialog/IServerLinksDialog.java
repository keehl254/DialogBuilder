package me.keehl.dialogbuilder.api.dialog;

import me.keehl.dialogbuilder.api.dialog.action.IDialogActionHolder;
import me.keehl.dialogbuilder.dialog.action.DialogActionHolder;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.function.Consumer;

public interface IServerLinksDialog extends IDialog<IServerLinksDialog> {
    int DEFAULT_COLUMNS = 2;
    int DEFAULT_BUTTON_WIDTH = 150;
    /**
     * Set the exit action for the dialog
     *
     * @param action the action to be performed when exiting the dialog
     * @return the server links dialog itself for method chaining
     */
    IServerLinksDialog exitAction(@Nullable Consumer<IDialogActionHolder> action);

    /**
     * Set the number of columns for the dialog
     *
     * @param columns the number of columns to set
     * @return the server links dialog itself for method chaining
     */
    IServerLinksDialog columns(@Nullable Integer columns);

    /**
     * Set the width of the buttons in the dialog
     *
     * @param buttonWidth the width of the buttons
     * @return the server links dialog itself for method chaining
     */
    IServerLinksDialog buttonWidth(@Nullable @Range(from = 1, to = 1024)  Integer buttonWidth);
}
