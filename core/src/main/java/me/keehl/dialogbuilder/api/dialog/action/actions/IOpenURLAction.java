package me.keehl.dialogbuilder.api.dialog.action.actions;

import me.keehl.dialogbuilder.api.dialog.action.IDialogAction;
import me.keehl.dialogbuilder.dialog.action.actions.OpenURLAction;

public interface IOpenURLAction extends IDialogAction<OpenURLAction> {

    String getType();

    String url();

}
