package me.keehl.dialogbuilder.dialog.action.actions;

import me.keehl.dialogbuilder.api.dialog.action.actions.ICopyToClipboardAction;

public record CopyToClipboardAction(String value) implements ICopyToClipboardAction {

    @Override
    public String getType() {
        return "copy_to_clipboard";
    }
}
