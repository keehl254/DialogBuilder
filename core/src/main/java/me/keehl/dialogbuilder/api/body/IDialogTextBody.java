package me.keehl.dialogbuilder.api.body;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

public interface IDialogTextBody extends IDialogBody {
    int DEFAULT_WIDTH = 200;

    /**
     * Set the text for the body
     *
     * @param text the text to set
     * @return the instance of the text body for method chaining
     */
    IDialogTextBody text(String text);

    /**
     * Set the width of the text body
     *
     * @param width the width to set
     * @return the instance of the text body for method chaining
     */
    IDialogTextBody width(@Nullable @Range(from = 1, to = 1024) Integer width);
}
