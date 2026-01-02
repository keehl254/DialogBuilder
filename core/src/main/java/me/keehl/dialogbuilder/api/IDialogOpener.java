package me.keehl.dialogbuilder.api;

import java.util.UUID;

public interface IDialogOpener {

    /**
     * Open the dialog for the given player UUID
     *
     * @param uuid the UUID of the player to open the dialog for
     * @return true if the dialog was opened successfully, false otherwise
     */
    boolean open(UUID uuid);

}
