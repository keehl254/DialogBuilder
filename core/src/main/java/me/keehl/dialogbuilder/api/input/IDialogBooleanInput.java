package me.keehl.dialogbuilder.api.input;

import org.jetbrains.annotations.Nullable;

public interface IDialogBooleanInput extends IDialogInput {

    /**
     * Set the label for the input.
     *
     * @param label the label to display for the input
     * @return the current instance for method chaining
     */
    IDialogBooleanInput label(@Nullable String label);

    /**
     * Set the initial value of the boolean input.
     *
     * @param initial the initial value (true or false)
     * @return the current instance for method chaining
     */
    IDialogBooleanInput initial(boolean initial);

    /**
     * Set the text to be sent when the input is true.
     *
     * @param onTrue the text to be sent when the input is true
     * @return the current instance for method chaining
     */
    IDialogBooleanInput onTrue(@Nullable String onTrue);

    /**
     * Set the text to be sent when the input is false.
     *
     * @param onFalse the text to be sent when the input is false
     * @return the current instance for method chaining
     */
    IDialogBooleanInput onFalse(@Nullable String onFalse);

}
