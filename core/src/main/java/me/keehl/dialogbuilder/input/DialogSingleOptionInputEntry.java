package me.keehl.dialogbuilder.input;

import me.keehl.dialogbuilder.api.input.IDialogSingleOptionInputEntry;
import org.jetbrains.annotations.Nullable;

public class DialogSingleOptionInputEntry implements IDialogSingleOptionInputEntry {

    private String id;
    private String display;
    private boolean isDefault;

    public DialogSingleOptionInputEntry(String id, String display, boolean isDefault) {
        this.id = id;
        this.display = display;
        this.isDefault = isDefault;
    }

    @Override
    public IDialogSingleOptionInputEntry id(String id) {
        this.id = id;
        return this;
    }

    @Override
    public @Nullable IDialogSingleOptionInputEntry display(String display) {
        this.display = display;
        return this;
    }

    @Override
    public IDialogSingleOptionInputEntry isDefault(boolean isDefault) {
        this.isDefault = isDefault;
        return this;
    }

    public String getID() {
        return this.id;
    }

    public @Nullable String getDisplay() {
        return this.display;
    }

    public boolean getIsDefault() {
        return this.isDefault;
    }
}
