package me.keehl.dialogbuilder.input;

import me.keehl.dialogbuilder.api.input.IDialogSingleOptionInput;
import me.keehl.dialogbuilder.api.input.IDialogSingleOptionInputEntry;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.ArrayList;
import java.util.List;

public class DialogSingleOptionInput extends DialogInput implements IDialogSingleOptionInput {

    private @Nullable String label;
    @Range(from = 1, to = 1024) private int width = DEFAULT_WIDTH;
    private List<IDialogSingleOptionInputEntry> options;


    public DialogSingleOptionInput(String key) {
        super(key);
    }

    @Override
    public IDialogSingleOptionInput width(@Nullable @Range(from = 1, to = 1024) Integer width) {
        this.width = width == null || width <= 0 ? DEFAULT_WIDTH : width;
        return this;
    }

    @Override
    public IDialogSingleOptionInput label(@Nullable String label) {
        this.label = label;
        return this;
    }

    @Override
    public IDialogSingleOptionInput option(String id, String display, boolean isDefault) {
        if (this.options == null) {
            this.options = new ArrayList<>();
        }

        IDialogSingleOptionInputEntry entry = new DialogSingleOptionInputEntry(id, display, isDefault);
        this.options.add(entry);
        return null;
    }


    public @Nullable String getLabel() {
        return this.label;
    }

    public int getWidth() {
        return this.width;
    }

    public List<IDialogSingleOptionInputEntry> getOptions() {
        return this.options;
    }
}
