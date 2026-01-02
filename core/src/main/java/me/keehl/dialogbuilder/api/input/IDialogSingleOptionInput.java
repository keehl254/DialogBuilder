package me.keehl.dialogbuilder.api.input;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

public interface IDialogSingleOptionInput extends IDialogInput {
    int DEFAULT_WIDTH = 200;

    /**
     * Set the width of the input
     *
     * @param width the width
     * @return the current instance for method chaining
     */
    IDialogSingleOptionInput width(@Nullable @Range(from = 1, to = 1024) Integer width);

    /**
     * Set the label for the input
     *
     * @param label the label text
     * @return the current instance for method chaining
     */
    IDialogSingleOptionInput label(@Nullable String label);

    /**
     * Add an option to the input
     *
     * @param id        the identifier for the option
     * @param display   the display text for the option
     * @param isDefault whether this option is the default selection
     * @return the current instance for method chaining
     */
    IDialogSingleOptionInput option(String id, String display, boolean isDefault);

    /**
     * Add an option to the input
     *
     * @param id      the identifier for the option
     * @param display the display text for the option
     * @return the current instance for method chaining
     */
    default IDialogSingleOptionInput option(String id, String display) {
        return option(id, display, false);
    }

}
