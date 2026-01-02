package me.keehl.dialogbuilder.api;

import me.keehl.dialogbuilder.api.body.IDialogBody;
import me.keehl.dialogbuilder.api.dialog.IDialog;
import me.keehl.dialogbuilder.api.input.IDialogInput;
import me.keehl.dialogbuilder.body.DialogItemBody;
import me.keehl.dialogbuilder.body.DialogTextBody;
import me.keehl.dialogbuilder.input.DialogBooleanInput;
import me.keehl.dialogbuilder.input.DialogNumberRangeInput;
import me.keehl.dialogbuilder.input.DialogSingleOptionInput;
import me.keehl.dialogbuilder.input.DialogTextInput;
import org.bukkit.event.Listener;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface IDialogAdapter<A, T, Z, D> extends Listener {

    AdapterType getAdapterType();

    D convertDialogTextBody(DialogTextBody body);

    D convertDialogItemBody(DialogItemBody body);

    D convertDialogBody(IDialogBody body);

    Z convertDialogTextInput(DialogTextInput input);

    Z convertDialogBooleanInput(DialogBooleanInput input);

    Z convertDialogNumberRangeInput(DialogNumberRangeInput input);

    Z convertDialogSingleOptionInput(DialogSingleOptionInput input);

    Z convertDialogInput(IDialogInput input);

    Optional<A> convertDialog(IDialog<?> dialog);


    /**
     * Register a custom action with a unique identifier.
     *
     * @param id     the unique identifier for the custom action
     * @param action the action to be executed, taking a payload
     */
    void registerCustomAction(String id, Consumer<IDialogPayload> action);

    /**
     * Register a custom action with a unique identifier.
     *
     * @param id     the unique identifier for the custom action
     * @param action the action to be executed, taking a payload
     */
    void registerCustomAction(String namespace, String id, Consumer<IDialogPayload> action);

    /**
     * Register a custom action with a unique identifier.
     *
     * @param id     the unique identifier for the custom action
     * @param action the action to be executed, taking a UUID and a map of parameters
     */
    void registerCustomAction(String id, BiConsumer<UUID, Map<String, String>> action);

    /**
     * Register a custom action with a namespace and a unique identifier.
     *
     * @param namespace the namespace for the custom action
     * @param id        the unique identifier for the custom action
     * @param action    the action to be executed, taking a UUID and a map of parameters
     */
    void registerCustomAction(String namespace, String id, BiConsumer<UUID, Map<String, String>> action);

    /**
     * Unregister a custom action by its unique identifier.
     *
     * @param id the unique identifier of the custom action to be unregistered
     */
    void unregisterCustomAction(String id);

    /**
     * Unregister a custom action by its namespace and unique identifier.
     *
     * @param namespace the namespace of the custom action
     * @param id        the unique identifier of the custom action to be unregistered
     */
    void unregisterCustomAction(String namespace, String id);

    /**
     * Unregister all custom actions.
     */
    void unregisterAllCustomActions();

    /**
     * Clear the current dialog associated with the given player UUID.
     *
     * @param uuid the unique identifier of the player whose dialog should be cleared
     * @return true if the dialog was successfully cleared, false otherwise
     */
    boolean clearDialog(UUID uuid);

    IDialogOpener opener(IDialog<?> dialog);


}
