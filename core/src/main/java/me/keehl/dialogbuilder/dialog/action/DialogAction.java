package me.keehl.dialogbuilder.dialog.action;

import me.keehl.dialogbuilder.api.IDialogPayload;
import me.keehl.dialogbuilder.api.dialog.DialogActionResult;
import me.keehl.dialogbuilder.api.dialog.WrappedDialog;
import me.keehl.dialogbuilder.dialog.action.actions.*;

import java.util.function.Consumer;
import java.util.function.Function;

public class DialogAction {

    public static CopyToClipboardAction copyToClipboard(String value) {
        return new CopyToClipboardAction(value);
    }

    public static DialogBuilderAction custom(Function<IDialogPayload, DialogActionResult> action) {
        return new DialogBuilderAction(action);
    }

    public static CustomAction custom(String id) {
        return new CustomAction(id);
    }

    public static CustomAction custom(String namespace, String id) {
        return new CustomAction(namespace, id);
    }

    public static CustomAction custom(String id, Object payload) {
        return new CustomAction(id, payload);
    }

    public static CustomAction custom(String namespace, String id, Object payload) {
        return new CustomAction(namespace, id, payload);
    }

    public static DynamicRunCommandAction dynamicCommand(String template) {
        return new DynamicRunCommandAction(template);
    }


    public static OpenURLAction openURL(String url) {
        return new OpenURLAction(url);
    }

    public static RunCommandAction runCommand(String command) {
        return new RunCommandAction(command);
    }

    public static ShowDialogAction showDialog(Object dialog) {
        return new ShowDialogAction(dialog);
    }

    public static ShowDialogAction showDialog(String namespace, String dialogID) {
        return new ShowDialogAction(new WrappedDialog(namespace, dialogID));
    }

    public static SuggestCommandAction suggestCommand(String command) {
        return new SuggestCommandAction(command);
    }

}
