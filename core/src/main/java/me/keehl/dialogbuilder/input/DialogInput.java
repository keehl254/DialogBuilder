package me.keehl.dialogbuilder.input;

import me.keehl.dialogbuilder.api.input.IDialogInput;

public abstract class DialogInput implements IDialogInput {

    protected final String key;

    public DialogInput(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

}
