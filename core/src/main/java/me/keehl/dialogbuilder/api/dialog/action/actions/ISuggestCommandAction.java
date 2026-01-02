package me.keehl.dialogbuilder.api.dialog.action.actions;

import me.keehl.dialogbuilder.api.dialog.action.IDialogAction;
import me.keehl.dialogbuilder.dialog.action.actions.SuggestCommandAction;

public interface ISuggestCommandAction extends IDialogAction<SuggestCommandAction> {

    String getType();

    String command();

}
