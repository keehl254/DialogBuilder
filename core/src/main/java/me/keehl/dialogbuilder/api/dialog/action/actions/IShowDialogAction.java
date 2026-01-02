package me.keehl.dialogbuilder.api.dialog.action.actions;

import me.keehl.dialogbuilder.api.dialog.action.IDialogAction;
import me.keehl.dialogbuilder.dialog.action.actions.ShowDialogAction;

public interface IShowDialogAction extends IDialogAction<ShowDialogAction> {

    String getType();

    Object dialog();

}
