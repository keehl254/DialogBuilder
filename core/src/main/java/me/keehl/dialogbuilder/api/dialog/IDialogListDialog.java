package me.keehl.dialogbuilder.api.dialog;

import me.keehl.dialogbuilder.api.IDialogOpener;
import me.keehl.dialogbuilder.api.dialog.action.IDialogActionHolder;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.function.Consumer;

public interface IDialogListDialog extends IDialog<IDialogListDialog> {
    int DEFAULT_COLUMNS = 2;
    int DEFAULT_BUTTON_WIDTH = 150;

    /**
     * Add a dialog to the list of dialogs in this dialog
     *
     * @param dialog the dialog to add
     * @return the dialog itself for method chaining
     */
    IDialogListDialog dialog(Object dialog);

    /**
     * Add a dialog to the list of dialogs in this dialog
     *
     * @param dialogOpener the dialog opener to use for showing the dialog
     * @return the dialog itself for method chaining
     */
    IDialogListDialog dialog(IDialogOpener dialogOpener);


    /**
     * Add a dialog to the list of dialogs in this dialog
     *
     * @param namespace the namespace of the dialog
     * @param dialogID  the ID of the dialog
     * @return the dialog itself for method chaining
     */
    IDialogListDialog dialog(String namespace, String dialogID);

    /**
     * Set the exit action for the dialog
     *
     * @param action the action to be performed when exiting the dialog
     * @return the server links dialog itself for method chaining
     */
    IDialogListDialog exitAction(@Nullable Consumer<IDialogActionHolder> action);

    /**
     * Set the number of columns for the dialog
     *
     * @param columns the number of columns to set
     * @return the server links dialog itself for method chaining
     */
    IDialogListDialog columns(@Nullable Integer columns);

    /**
     * Set the width of the buttons in the dialog
     *
     * @param buttonWidth the width of the buttons
     * @return the server links dialog itself for method chaining
     */
    IDialogListDialog buttonWidth(@Nullable @Range(from = 1, to = 1024)  Integer buttonWidth);
}
