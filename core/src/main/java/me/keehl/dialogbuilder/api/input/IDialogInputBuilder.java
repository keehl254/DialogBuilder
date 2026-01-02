package me.keehl.dialogbuilder.api.input;

import me.keehl.dialogbuilder.input.DialogBooleanInput;
import me.keehl.dialogbuilder.input.DialogNumberRangeInput;
import me.keehl.dialogbuilder.input.DialogSingleOptionInput;
import me.keehl.dialogbuilder.input.DialogTextInput;

public interface IDialogInputBuilder {
    /**
     * Create a boolean input
     *
     * @return a new instance of a boolean input
     */
     IDialogBooleanInput booleanInput();

    /**
     * Create a text input
     *
     * @return a new instance of a text input
     */
    IDialogTextInput textInput();

    /**
     * Create a single option input
     *
     * @return a new instance of a single option input
     */
    IDialogSingleOptionInput singleOptionInput();

    /**
     * Create a number range input
     *
     * @return a new instance of a number range input
     */
    IDialogNumberRangeInput numberRangeInput();
}
