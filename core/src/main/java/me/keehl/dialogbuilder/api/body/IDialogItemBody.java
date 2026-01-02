package me.keehl.dialogbuilder.api.body;

import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public interface IDialogItemBody extends IDialogBody {
    int DEFAULT_WIDTH = 16;
    int DEFAULT_HEIGHT = 16;

    /**
     * Set the item for the body
     *
     * @param item the item to set
     * @return the instance of the item body for method chaining
     */
    <T> IDialogItemBody item(T item);

    /**
     * Set the description for the body
     *
     * @param descriptionBuilder a consumer to build the description
     * @return the instance of the item body for method chaining
     */
    IDialogItemBody description(@Nullable Consumer<IDialogTextBody> descriptionBuilder);

    /**
     * Set the option to show decorations in the body
     *
     * @param showDecorations whether to show decorations
     * @return the instance of the item body for method chaining
     */
    IDialogItemBody showDecorations(boolean showDecorations);

    /**
     * Set the option to show a tooltip in the body
     *
     * @param showTooltip whether to show a tooltip
     * @return the instance of the item body for method chaining
     */
    IDialogItemBody showTooltip(boolean showTooltip);

    /**
     * Set the width of the item body
     *
     * @param width the width to set
     * @return the instance of the item body for method chaining
     */
    IDialogItemBody width(@Nullable Integer width);

    /**
     * Set the height of the item body
     *
     * @param height the height to set
     * @return the instance of the item body for method chaining
     */
    IDialogItemBody height(@Nullable Integer height);
}
