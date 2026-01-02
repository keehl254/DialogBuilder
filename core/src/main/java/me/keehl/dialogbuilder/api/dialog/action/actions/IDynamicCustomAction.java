package me.keehl.dialogbuilder.api.dialog.action.actions;

import me.keehl.dialogbuilder.api.dialog.action.IDialogAction;
import me.keehl.dialogbuilder.dialog.action.actions.CustomAction;

public interface IDynamicCustomAction extends IDialogAction<CustomAction> {

    String getType();

    String namespace();

    String ID();

}
