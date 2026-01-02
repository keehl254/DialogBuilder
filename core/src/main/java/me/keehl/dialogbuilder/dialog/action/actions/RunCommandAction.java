package me.keehl.dialogbuilder.dialog.action.actions;

import me.keehl.dialogbuilder.api.dialog.action.actions.IRunCommandAction;

public record RunCommandAction(String command) implements IRunCommandAction {

    @Override
    public String getType() {
        return "run_command";
    }
}
