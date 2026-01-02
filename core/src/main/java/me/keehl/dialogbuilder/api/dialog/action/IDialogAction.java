package me.keehl.dialogbuilder.api.dialog.action;

public interface IDialogAction<Z extends IDialogAction<Z>> {

    String getType();

}
