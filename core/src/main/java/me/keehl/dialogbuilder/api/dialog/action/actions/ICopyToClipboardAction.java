package me.keehl.dialogbuilder.api.dialog.action.actions;

import me.keehl.dialogbuilder.api.dialog.action.IDialogAction;
import me.keehl.dialogbuilder.dialog.action.actions.CopyToClipboardAction;

public interface ICopyToClipboardAction extends IDialogAction<CopyToClipboardAction> {

    String getType();

    String value();
}
