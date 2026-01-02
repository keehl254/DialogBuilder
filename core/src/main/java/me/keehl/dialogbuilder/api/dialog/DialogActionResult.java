package me.keehl.dialogbuilder.api.dialog;

public enum DialogActionResult {

    /**
     * Keep the action in memory. This is useful if the Dialog is reusable rather than regenerated each time.
     */
    PERSIST,
    /**
     * Unregister the action from memory. If this is a unique dialog that will only be used once, then this is the correct
     * option to choose.
     */
    UNREGISTER

}
