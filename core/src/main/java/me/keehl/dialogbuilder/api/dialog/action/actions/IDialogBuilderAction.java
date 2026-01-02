package me.keehl.dialogbuilder.api.dialog.action.actions;

import me.keehl.dialogbuilder.api.IDialogPayload;
import me.keehl.dialogbuilder.api.dialog.DialogActionResult;
import me.keehl.dialogbuilder.api.dialog.action.IDialogAction;
import me.keehl.dialogbuilder.dialog.action.actions.CustomAction;

import java.util.function.Consumer;
import java.util.function.Function;

public interface IDialogBuilderAction extends IDialogAction<CustomAction> {

    String getType();

    Function<IDialogPayload, DialogActionResult> action();

}
