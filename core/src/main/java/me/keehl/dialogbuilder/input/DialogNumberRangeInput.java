package me.keehl.dialogbuilder.input;

import me.keehl.dialogbuilder.api.input.IDialogNumberRangeInput;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

public class DialogNumberRangeInput extends DialogInput implements IDialogNumberRangeInput {

    private String label;
    @Range(from = 1, to = 1024) private int width = DEFAULT_WIDTH;
    private String labelFormat = DEFAULT_LABEL_FORMAT;
    private float start;
    private float end;
    private @Nullable Float initial;
    private @Nullable Float step;

    public DialogNumberRangeInput(String key) {
        super(key);
    }

    @Override
    public IDialogNumberRangeInput width(@Nullable @Range(from = 1, to = 1024) Integer width) {
        this.width = width == null || width <= 0 ? DEFAULT_WIDTH : width;
        return this;
    }

    @Override
    public IDialogNumberRangeInput label(@Nullable String label) {
        this.label = label;
        return this;
    }

    @Override
    public IDialogNumberRangeInput labelFormat(@Nullable String labelFormat) {
        this.labelFormat = labelFormat == null ? DEFAULT_LABEL_FORMAT : labelFormat;
        return this;
    }

    @Override
    public IDialogNumberRangeInput start(float start) {
        this.start = start;
        return this;
    }

    @Override
    public IDialogNumberRangeInput end(float end) {
        this.end = end;
        return this;
    }

    @Override
    public IDialogNumberRangeInput initial(@Nullable Float initial) {
        this.initial = initial;
        return this;
    }

    @Override
    public IDialogNumberRangeInput step(@Nullable Float step) {
        this.step = step;
        return this;
    }

    public String getLabel() {
        return this.label;
    }

    public int getWidth() {
        return this.width;
    }

    public String getLabelFormat() {
        return this.labelFormat;
    }

    public float getStart() {
        return this.start;
    }

    public float getEnd() {
        return this.end;
    }

    public @Nullable Float getInitial() {
        return this.initial;
    }

    public @Nullable Float getStep() {
        return this.step;
    }
}
