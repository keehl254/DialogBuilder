package me.keehl.dialogbuilder.dialog.action.actions;

import me.keehl.dialogbuilder.api.dialog.action.actions.IDynamicCustomAction;

public record DynamicCustomAction(String namespace, String ID) implements IDynamicCustomAction {

    @Override
    public String getType() {
        return "dynamic_custom";
    }
}
