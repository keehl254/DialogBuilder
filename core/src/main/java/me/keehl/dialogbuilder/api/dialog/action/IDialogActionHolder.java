package me.keehl.dialogbuilder.api.dialog.action;

import me.keehl.dialogbuilder.api.IDialogOpener;
import me.keehl.dialogbuilder.api.IDialogPayload;
import me.keehl.dialogbuilder.api.dialog.DialogActionResult;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.function.Consumer;
import java.util.function.Function;

public interface IDialogActionHolder {
    int DEFAULT_WIDTH = 150;

    /**
     * Set the label for the dialog action
     *
     * @param label the label to set
     * @return the current instance of the builder for method chaining
     */
    IDialogActionHolder label(String label);

    /**
     * Set the tooltip for the dialog action
     *
     * @param toolTip the tooltip to set, can be null
     * @return the current instance of the builder for method chaining
     */
    IDialogActionHolder tooltip(String toolTip);

    /**
     * Set the width for the dialog action
     *
     * @param width the width to set
     * @return the current instance of the builder for method chaining
     */
    @Range(from = 1, to = 1024) IDialogActionHolder width(Integer width);

    @Nullable <T extends IDialogAction<T>> IDialogActionHolder action(IDialogAction<T> action);


    /**
     * Set the action to be a copy-to-clipboard action
     *
     * @param value the value to copy to the clipboard
     * @return the current instance of the builder for method chaining
     */
    IDialogActionHolder copyToClipboard(String value);

    /**
     * Set the action to be a dynamic custom action
     *
     * @param action the action to be called.
     * @return the current instance of the builder for method chaining
     */
    IDialogActionHolder dynamicCustom(Function<IDialogPayload, DialogActionResult> action);

    /**
     * Set the action to be a dynamic custom action
     *
     * @param id the identifier for the dynamic custom action
     * @return the current instance of the builder for method chaining
     */
    IDialogActionHolder dynamicCustom(String id);

    /**
     * Set the action to be a dynamic custom action
     *
     * @param id the identifier for the dynamic custom action
     * @param payload   the optional payload for the dynamic custom action
     * @return the current instance of the builder for method chaining
     */
    IDialogActionHolder dynamicCustom(String id, Object payload);

    /**
     * Set the action to be a dynamic custom action with a namespace
     *
     * @param namespace the namespace for the dynamic custom action
     * @param id        the identifier for the dynamic custom action
     * @return the current instance of the builder for method chaining
     */
    IDialogActionHolder dynamicCustom(String namespace, String id);

    /**
     * Set the action to be a dynamic custom action with a namespace
     *
     * @param namespace the namespace for the dynamic custom action
     * @param id        the identifier for the dynamic custom action
     * @param payload   the optional payload for the dynamic custom action
     * @return the current instance of the builder for method chaining
     */
    IDialogActionHolder dynamicCustom(String namespace, String id, Object payload);

    /**
     * Set the action to be a dynamic run command action
     *
     * @param template the command template to run
     * @return the current instance of the builder for method chaining
     */
    IDialogActionHolder dynamicRunCommand(String template);

    /**
     * Set the action to be an open-URL action
     *
     * @param url the URL to open
     * @return the current instance of the builder for method chaining
     */
    IDialogActionHolder openUrl(String url);

    /**
     * Set the action to be a run command action
     *
     * @param command the command to run
     * @return the current instance of the builder for method chaining
     */
    IDialogActionHolder runCommand(String command);

    /**
     * Set the action to be a suggest command action
     *
     * @param command the command to suggest
     * @return the current instance of the builder for method chaining
     */
    IDialogActionHolder suggestCommand(String command);

    /**
     * Set the action to be a show dialog action
     *
     * @param dialog the dialog to show
     * @return the current instance of the builder for method chaining
     */
    IDialogActionHolder showDialog(Object dialog);

    /**
     * Set the action to be a show dialog action
     *
     * @param namespace the namespace of the dialog
     * @param dialogId  the identifier of the dialog to show
     * @return the current instance of the builder for method chaining
     */
    IDialogActionHolder showDialog(String namespace, String dialogId);

    /**
     * Set the action to be a show dialog action
     *
     * @param dialogOpener the dialog opener to use for showing the dialog
     * @return the current instance of the builder for method chaining
     */
    IDialogActionHolder showDialog(IDialogOpener dialogOpener);

}
