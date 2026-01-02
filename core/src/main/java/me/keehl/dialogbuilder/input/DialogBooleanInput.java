package me.keehl.dialogbuilder.input;

import me.keehl.dialogbuilder.api.input.IDialogBooleanInput;
import org.jetbrains.annotations.Nullable;

public class DialogBooleanInput extends DialogInput implements IDialogBooleanInput {

    private @Nullable String label;
    private boolean initial;
    private @Nullable String onTrue;
    private @Nullable String onFalse;

    public DialogBooleanInput(String key) {
        super(key);
    }

    public IDialogBooleanInput label(@Nullable String label) {
        this.label = label;
        return this;
    }

    public IDialogBooleanInput initial(boolean initial) {
        this.initial = initial;
        return this;
    }

    public IDialogBooleanInput onTrue(@Nullable String onTrue) {
        this.onTrue = onTrue;
        return this;
    }

    public IDialogBooleanInput onFalse(@Nullable String onFalse) {
        this.onFalse = onFalse;
        return this;
    }

    public @Nullable String getLabel() {
        return this.label;
    }

    public boolean getInitial() {
        return this.initial;
    }

    public @Nullable String getOnTrue() {
        return this.onTrue;
    }

    public @Nullable String getOnFalse() {
        return this.onFalse;
    }

}
