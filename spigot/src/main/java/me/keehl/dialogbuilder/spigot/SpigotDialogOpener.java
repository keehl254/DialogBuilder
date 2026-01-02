package me.keehl.dialogbuilder.spigot;

import me.keehl.dialogbuilder.api.IDialogOpener;
import net.md_5.bungee.api.dialog.Dialog;
import org.bukkit.Bukkit;

import java.util.Optional;
import java.util.UUID;

public record SpigotDialogOpener(Dialog dialog) implements IDialogOpener {

    @Override
    public boolean open(UUID uuid) {
        Optional.ofNullable(Bukkit.getPlayer(uuid)).ifPresent(player -> player.showDialog(this.dialog));
        return false;
    }
}
