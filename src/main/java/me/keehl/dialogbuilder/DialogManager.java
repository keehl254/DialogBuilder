package me.keehl.dialogbuilder;

import me.keehl.dialogbuilder.api.IDialogAdapter;
import me.keehl.dialogbuilder.api.dialog.*;
import me.keehl.dialogbuilder.dialog.*;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

public class DialogManager {

    private final Plugin plugin;
    private final String defaultNamespace;

    private final IDialogAdapter<?, ?, ?, ?> adapter;

    public DialogManager(Plugin plugin, String defaultNamespace) {
        this.plugin = plugin;
        this.defaultNamespace = defaultNamespace;

        ImplementationType implementationType = ImplementationType.UNKNOWN;
        for (ImplementationType type : ImplementationType.values()) {
            if (type.selfCheck()) {
                implementationType = type;
                break;
            }
        }

        String path = this.getClass().getCanonicalName().substring(0, this.getClass().getCanonicalName().indexOf(this.getClass().getSimpleName()));
        String className = path + implementationType.getImplementationClassName();
        try {
            Class<?> clazz = Class.forName(className);
            this.adapter = (IDialogAdapter<?, ?, ?, ?>) clazz.getConstructor(String.class).newInstance(this.defaultNamespace);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public DialogManager(Plugin plugin) {
        this(plugin, plugin.getName().replace("[^a-zA-Z0-9]", "_").toLowerCase(Locale.ROOT));
    }

    public void register() {
        this.plugin.getServer().getPluginManager().registerEvents(this.adapter, this.plugin);
    }

    public void unregister() {
        HandlerList.unregisterAll(this.adapter);
        this.adapter.unregisterAllCustomActions();
    }

    public IConfirmationDialog createConfirmationDialog() {
        return new ConfirmationDialog(this.adapter, this.defaultNamespace);
    }

    public IMultiActionDialog createMultiActionDialog() {
        return new MultiActionDialog(this.adapter, this.defaultNamespace);
    }

    public IServerLinksDialog createServerLinksDialog() {
        return new ServerLinksDialog(this.adapter, this.defaultNamespace);
    }

    public INoticeDialog createNoticeDialog() {
        return new NoticeDialog(this.adapter, this.defaultNamespace);
    }

    public IDialogListDialog createDialogListDialog() {
        return new DialogListDialog(this.adapter, this.defaultNamespace);
    }

}
