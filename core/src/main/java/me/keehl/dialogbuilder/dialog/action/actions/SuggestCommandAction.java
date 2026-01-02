package me.keehl.dialogbuilder.dialog.action.actions;

import me.keehl.dialogbuilder.api.dialog.action.actions.ISuggestCommandAction;

public record SuggestCommandAction(String command) implements ISuggestCommandAction {

    @Override
    public String getType() {
        return "suggest_command";
    }
}
