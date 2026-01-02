package me.keehl.dialogbuilder.dialog.action.actions;

import me.keehl.dialogbuilder.api.dialog.action.actions.IDynamicRunCommandAction;

public record DynamicRunCommandAction(String template) implements IDynamicRunCommandAction {

    @Override
    public String getType() {
        return "run_command";
    }
}
