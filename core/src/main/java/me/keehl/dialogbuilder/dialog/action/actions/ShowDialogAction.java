package me.keehl.dialogbuilder.dialog.action.actions;

import me.keehl.dialogbuilder.api.dialog.action.actions.IShowDialogAction;

public record ShowDialogAction(Object dialog) implements IShowDialogAction {

    @Override
    public String getType() {
        return "show_dialog";
    }
}
