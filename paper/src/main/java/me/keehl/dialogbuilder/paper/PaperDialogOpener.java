package me.keehl.dialogbuilder.paper;

import io.papermc.paper.dialog.Dialog;
import me.keehl.dialogbuilder.api.IDialogOpener;
import org.bukkit.Bukkit;

import java.util.Optional;
import java.util.UUID;

public record PaperDialogOpener(Dialog dialog) implements IDialogOpener {

    @Override
    public boolean open(UUID uuid) {
        Optional.ofNullable(Bukkit.getPlayer(uuid)).ifPresent(player -> player.showDialog(this.dialog));
        return false;
    }
}
