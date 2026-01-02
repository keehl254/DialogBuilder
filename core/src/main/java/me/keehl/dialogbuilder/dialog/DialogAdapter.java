package me.keehl.dialogbuilder.dialog;

import me.keehl.dialogbuilder.api.IDialogAdapter;
import me.keehl.dialogbuilder.api.IDialogPayload;
import org.bukkit.NamespacedKey;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public abstract class DialogAdapter<A, T, Z, D> implements IDialogAdapter<A, T, Z, D> {

    protected final Map<NamespacedKey, Consumer<IDialogPayload>> customActions = new HashMap<>();

    private final String defaultNamespace;

    public DialogAdapter(String defaultNamespace) {
        this.defaultNamespace = defaultNamespace;
    }

    @Override
    public void registerCustomAction(String id, Consumer<IDialogPayload> action) {
        registerCustomAction(this.defaultNamespace, id, action);
    }

    @Override
    public void registerCustomAction(String namespace, String id, Consumer<IDialogPayload> action) {
        this.customActions.put(new NamespacedKey(namespace, id), action);
    }

    public void registerCustomAction(String id, BiConsumer<UUID, Map<String, String>> action) {
        this.registerCustomAction(id, payload -> action.accept(payload.owner(), payload.map()));
    }

    public void registerCustomAction(String namespace, String id, BiConsumer<UUID, Map<String, String>> action) {
        this.registerCustomAction(namespace, id, payload -> action.accept(payload.owner(), payload.map()));
    }

    @Override
    public void unregisterCustomAction(String id) {
        this.unregisterCustomAction(this.defaultNamespace, id);
    }

    @Override
    public void unregisterCustomAction(String namespace, String id) {
        this.customActions.remove(new NamespacedKey(namespace, id));
    }

    @Override
    public void unregisterAllCustomActions() {
        this.customActions.clear();
    }
}
