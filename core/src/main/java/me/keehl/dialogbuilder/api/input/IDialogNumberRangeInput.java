package me.keehl.dialogbuilder.api.input;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

public interface IDialogNumberRangeInput extends IDialogInput {
    int DEFAULT_WIDTH = 200;
    String DEFAULT_LABEL_FORMAT = "options.generic_value";

    /**
     * Set the width of the input
     *
     * @param width the width
     * @return the current instance for method chaining
     */
    IDialogNumberRangeInput width(@Nullable @Range(from = 1, to = 1024) Integer width);

    /**
     * Set the label for the input
     *
     * @param label the label text
     * @return the current instance for method chaining
     */
    IDialogNumberRangeInput label(@Nullable String label);

    /**
     * Set the format for the label
     *
     * @param labelFormat the format string for the label
     * @return the current instance for method chaining
     */
    IDialogNumberRangeInput labelFormat(@Nullable String labelFormat);

    /**
     * Set the start value of the range
     *
     * @param start the start value
     * @return the current instance for method chaining
     */
    IDialogNumberRangeInput start(float start);

    /**
     * Set the end value of the range
     *
     * @param end the end value
     * @return the current instance for method chaining
     */
    IDialogNumberRangeInput end(float end);

    /**
     * Set the initial value of the range
     *
     * @param initial the initial value, can be null
     * @return the current instance for method chaining
     */
    IDialogNumberRangeInput initial(@Nullable Float initial);

    /**
     * Set the step value for the range
     *
     * @param step the step value, can be null
     * @return the current instance for method chaining
     */
    IDialogNumberRangeInput step(@Nullable Float step);
}
