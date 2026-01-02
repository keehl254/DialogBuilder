package me.keehl.dialogbuilder.dialog.action.actions;

import me.keehl.dialogbuilder.api.IDialogPayload;
import me.keehl.dialogbuilder.api.dialog.DialogActionResult;
import me.keehl.dialogbuilder.api.dialog.action.actions.IDialogBuilderAction;

import java.util.function.Function;

public record DialogBuilderAction(Function<IDialogPayload, DialogActionResult> action) implements IDialogBuilderAction {

    @Override
    public String getType() {
        return "custom";
    }
}
