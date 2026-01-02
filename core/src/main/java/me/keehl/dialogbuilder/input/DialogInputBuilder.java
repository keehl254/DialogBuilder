package me.keehl.dialogbuilder.input;

import me.keehl.dialogbuilder.api.input.*;

public class DialogInputBuilder extends DialogInput implements IDialogInputBuilder {

    private IDialogInput current;

    public DialogInputBuilder(String key) {
        super(key);
    }

    /**
     * Create a boolean input
     *
     * @return a new instance of a boolean input
     */
    public IDialogBooleanInput booleanInput() {
        DialogBooleanInput input = new DialogBooleanInput(this.key);
        this.current = input;
        return input;
    }

    /**
     * Create a text input
     *
     * @return a new instance of a text input
     */
    public IDialogTextInput textInput() {
        DialogTextInput input = new DialogTextInput(this.key);
        this.current = input;
        return input;
    }

    /**
     * Create a single option input
     *
     * @return a new instance of a single option input
     */
    public IDialogSingleOptionInput singleOptionInput() {
        DialogSingleOptionInput input = new DialogSingleOptionInput(this.key);
        this.current = input;
        return input;
    }

    /**
     * Create a number range input
     *
     * @return a new instance of a number range input
     */
    public IDialogNumberRangeInput numberRangeInput() {
        DialogNumberRangeInput input = new DialogNumberRangeInput(this.key);
        this.current = input;
        return input;
    }

    public IDialogInput getDialogInput() {
        if (this.current == null)
            throw new IllegalStateException("No dialog input has been created yet.");

        return this.current;
    }
}
