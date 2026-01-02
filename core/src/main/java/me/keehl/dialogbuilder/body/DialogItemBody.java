package me.keehl.dialogbuilder.body;

import me.keehl.dialogbuilder.api.body.IDialogBody;
import me.keehl.dialogbuilder.api.body.IDialogItemBody;
import me.keehl.dialogbuilder.api.body.IDialogTextBody;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class DialogItemBody extends DialogBody implements IDialogItemBody {

    private Object item;
    private @Nullable IDialogTextBody description;
    private boolean showDecorations = true;
    private boolean showTooltip = true;
    private int width = DEFAULT_WIDTH;
    private int height = DEFAULT_HEIGHT;

    @Override
    public <T> IDialogItemBody item(T item) {
        this.item = item;
        return this;
    }

    @Override
    public IDialogItemBody description(@Nullable Consumer<IDialogTextBody> descriptionBuilder) {
        if (descriptionBuilder == null) {
            this.description = null;
        } else {
            DialogTextBody textBody = new DialogTextBody();
            descriptionBuilder.accept(textBody);
            this.description = textBody;
        }
        return this;
    }

    @Override
    public IDialogItemBody showDecorations(boolean showDecorations) {
        this.showDecorations = showDecorations;
        return this;
    }

    @Override
    public IDialogItemBody showTooltip(boolean showTooltip) {
        this.showTooltip = showTooltip;
        return this;
    }

    @Override
    public IDialogItemBody width(@Nullable Integer width) {
        this.width = width == null ? DEFAULT_WIDTH : width;
        return this;
    }

    @Override
    public IDialogItemBody height(@Nullable Integer height) {
        this.height = height == null ? DEFAULT_HEIGHT : height;
        return this;
    }

    public Object getItem() {
        return this.item;
    }

    public @Nullable IDialogTextBody getDescription() {
        return this.description;
    }

    public boolean getShowDecorations() {
        return this.showDecorations;
    }

    public boolean getShowTooltip() {
        return this.showTooltip;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
