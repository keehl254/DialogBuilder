package me.keehl.dialogbuilder.api.dialog;

import me.keehl.dialogbuilder.api.IDialogOpener;
import me.keehl.dialogbuilder.api.body.IDialogBodyBuilder;
import me.keehl.dialogbuilder.api.input.IDialogInputBuilder;

import java.util.Collection;
import java.util.Map;
import java.util.function.Consumer;

public interface IDialog<T extends IDialog<T>> {

    /**
     * Set the title of the dialog
     *
     * @param title the title of the dialog
     * @return the dialog itself for method chaining
     */
    IDialog<T> title(String title);

    /**
     * Set the external title of the dialog, which is used for displaying the dialog in a multi-dialog dialog
     *
     * @param externalTitle the external title of the dialog
     * @return the dialog itself for method chaining
     */
    IDialog<T> externalTitle(String externalTitle);

    /**
     * Set the option to allow the dialog to be closed with the escape key
     *
     * @param canCloseWithEscape whether the dialog can be closed with the escape key
     * @return the dialog itself for method chaining
     */
    IDialog<T> canCloseWithEscape(boolean canCloseWithEscape);

    /**
     * Set the option to pause the game while the dialog is open
     *
     * @param pause whether the dialog should pause the game
     * @return the dialog itself for method chaining
     */
    IDialog<T> pause(boolean pause);

    /**
     * Set the action to be performed after an action is taken in the dialog
     *
     * @param afterAction the action to be performed after an action is taken
     * @return the dialog itself for method chaining
     */
    IDialog<T> afterAction(AfterAction afterAction);

    /**
     * Set the body of the dialog
     *
     * @param bodyBuilder the body builder for the dialog
     * @return the dialog itself for method chaining
     */
    IDialog<T> body(Consumer<IDialogBodyBuilder> bodyBuilder);

    /**
     * Set the input for the dialog
     *
     * @param key          the key for the input
     * @param inputBuilder the input builder for the dialog
     * @return the dialog itself for method chaining
     */
    IDialog<T> input(String key, Consumer<IDialogInputBuilder> inputBuilder);

    /**
     * Set the body of the dialog using a collection of body builders
     *
     * @param bodyBuilders the collection of body builders for the dialog
     * @return the dialog itself for method chaining
     */
    IDialog<T> body(Collection<Consumer<IDialogBodyBuilder>> bodyBuilders);

    /**
     * Set the input for the dialog using a map of input builders
     *
     * @param inputBuilders the map of input builders for the dialog
     * @return the dialog itself for method chaining
     */
    IDialog<T> input(Map<String, Consumer<IDialogInputBuilder>> inputBuilders);



    /**
     * Create an opener for the dialog
     *
     * @return the dialog opener
     */
    IDialogOpener opener();

}
