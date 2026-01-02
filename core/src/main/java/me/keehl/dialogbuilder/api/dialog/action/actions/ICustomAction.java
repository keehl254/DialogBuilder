package me.keehl.dialogbuilder.api.dialog.action.actions;

import me.keehl.dialogbuilder.api.dialog.action.IDialogAction;
import me.keehl.dialogbuilder.dialog.action.actions.CustomAction;

public interface ICustomAction extends IDialogAction<CustomAction> {

    String getType();

    Object payload();

    String id();

}
