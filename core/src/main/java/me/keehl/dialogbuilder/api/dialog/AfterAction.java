package me.keehl.dialogbuilder.api.dialog;

public enum AfterAction {
    /**
     * Close the dialog after the action is taken.
     */
    CLOSE("close"),
    /**
     * Take no action after the action is taken.
     */
    NONE("none"),
    /**
     * Wait for a response after the action is taken.
     */
    WAIT_FOR_RESPONSE("wait_for_response");

    private final String key;

    AfterAction(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }
}