package me.keehl.dialogbuilder.dialog.action.actions;

import me.keehl.dialogbuilder.api.dialog.action.actions.IOpenURLAction;

public record OpenURLAction(String url) implements IOpenURLAction {

    @Override
    public String getType() {
        return "open_url";
    }
}
