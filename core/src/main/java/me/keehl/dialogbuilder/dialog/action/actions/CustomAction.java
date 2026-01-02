package me.keehl.dialogbuilder.dialog.action.actions;

import me.keehl.dialogbuilder.api.dialog.action.actions.ICustomAction;

public record CustomAction(String namespace, String id, Object payload) implements ICustomAction {

    public CustomAction(String namespace, String id) {
        this(namespace, id, null);
    }

    public CustomAction(String id) {
        this(null, id, null);
    }

    public CustomAction(String id, Object payload) {
        this(null, id, payload);
    }

    @Override
    public String getType() {
        return "custom";
    }
}
