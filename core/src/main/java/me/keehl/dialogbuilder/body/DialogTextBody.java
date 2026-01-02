package me.keehl.dialogbuilder.body;

import me.keehl.dialogbuilder.api.body.IDialogTextBody;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

public class DialogTextBody extends DialogBody implements IDialogTextBody {

    private String text;
    @Range(from = 1, to = 1024) private int width = DEFAULT_WIDTH;

    @Override
    public IDialogTextBody text(String text) {
        this.text = text;
        return this;
    }

    @Override
    public IDialogTextBody width(@Nullable @Range(from = 1, to = 1024) Integer width) {
        this.width = width == null ? DEFAULT_WIDTH : width;
        return this;
    }

    public String getText() {
        return this.text;
    }

    public int getWidth() {
        return this.width;
    }
}
