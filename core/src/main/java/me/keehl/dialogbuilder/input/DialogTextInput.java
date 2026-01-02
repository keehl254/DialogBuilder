package me.keehl.dialogbuilder.input;

import me.keehl.dialogbuilder.api.input.IDialogTextInput;
import org.jetbrains.annotations.Nullable;

public class DialogTextInput extends DialogInput implements IDialogTextInput {

    private @Nullable String label;
    private @Nullable String initial;
    private int width = DEFAULT_WIDTH;
    private @Nullable Integer height;
    private int maxLength = DEFAULT_MAX_LENGTH;
    private @Nullable Integer maxLines;

    public DialogTextInput(String key) {
        super(key);
    }

    @Override
    public IDialogTextInput label(@Nullable String label) {
        this.label = label;
        return this;
    }

    @Override
    public IDialogTextInput width(@Nullable Integer width) {
        this.width = width == null ? DEFAULT_WIDTH : width;
        return this;
    }

    @Override
    public IDialogTextInput initial(String initial) {
        this.initial = initial;
        return this;
    }

    @Override
    public IDialogTextInput maxLength(@Nullable Integer maxLength) {
        this.maxLength = maxLength == null ? DEFAULT_MAX_LENGTH : maxLength;
        return this;
    }

    @Override
    public IDialogTextInput maxLines(@Nullable Integer maxLines) {
        this.maxLines = maxLines;
        return this;
    }

    @Override
    public IDialogTextInput height(@Nullable Integer height) {
        this.height = height;
        return this;
    }

    public @Nullable String getLabel() {
        return this.label;
    }

    public @Nullable String getInitial() {
        return this.initial;
    }

    public int getWidth() {
        return this.width;
    }

    public @Nullable Integer getHeight() {
        return this.height;
    }

    public int getMaxLength() {
        return this.maxLength;
    }

    public @Nullable Integer getMaxLines() {
        return this.maxLines;
    }
}
