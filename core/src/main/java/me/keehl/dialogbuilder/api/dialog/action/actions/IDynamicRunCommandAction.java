package me.keehl.dialogbuilder.api.dialog.action.actions;

import me.keehl.dialogbuilder.api.dialog.action.IDialogAction;
import me.keehl.dialogbuilder.dialog.action.actions.RunCommandAction;

public interface IDynamicRunCommandAction extends IDialogAction<RunCommandAction> {

    String getType();

    String template();

}
