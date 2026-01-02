package me.keehl.dialogbuilder.api.input;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

public interface IDialogTextInput extends IDialogInput {
    int DEFAULT_WIDTH = 200;
    int DEFAULT_MAX_LENGTH = 32;

    /**
     * Set the label for the input
     *
     * @param label the label text
     * @return the current instance for method chaining
     */
    IDialogTextInput label(@Nullable String label);

    /**
     * Set the width of the input
     *
     * @param width the width
     * @return the current instance for method chaining
     */
    IDialogTextInput width(@Nullable @Range(from = 1, to = 1024) Integer width);

    /**
     * Set the initial value for the input
     *
     * @param initial the initial text
     * @return the current instance for method chaining
     */
    IDialogTextInput initial(String initial);

    /**
     * Set the maximum length of the input text
     *
     * @param maxLength the maximum number of characters allowed
     * @return the current instance for method chaining
     */
    IDialogTextInput maxLength(@Nullable Integer maxLength);

    /**
     * Set the maximum number of lines for the input
     *
     * @param maxLines the maximum number of lines allowed
     * @return the current instance for method chaining
     */
    IDialogTextInput maxLines(@Nullable Integer maxLines);

    /**
     * Set the height of the input
     *
     * @param height the height
     * @return the current instance for method chaining
     */
    IDialogTextInput height(@Nullable Integer height);

}
