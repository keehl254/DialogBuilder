package me.keehl.dialogbuilder.spigot;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import me.keehl.dialogbuilder.api.IDialogPayload;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public record SpigotDialogPayload(UUID owner, boolean isQuit, @Nullable JsonElement jsonData) implements IDialogPayload {
    private @Nullable JsonObject getAsJsonObject() {
        if (this.jsonData == null || !this.jsonData.isJsonObject())
            return null;
        return this.jsonData.getAsJsonObject();
    }

    private @Nullable JsonPrimitive getElement(String key) {
        JsonObject jsonObject = getAsJsonObject();
        if (jsonObject == null)
            return null;
        JsonElement element = jsonObject.get(key);
        if (element == null || !element.isJsonPrimitive())
            return null;
        return element.getAsJsonPrimitive();
    }

    @Override
    public @Nullable String textValue(String key) {
        JsonPrimitive element = getElement(key);
        if (element == null || !element.isString())
            return null;
        return element.getAsString();
    }

    @Override
    public @Nullable Boolean booleanValue(String key) {
        JsonPrimitive element = getElement(key);
        if (element == null || !element.isBoolean())
            return null;
        return element.getAsBoolean();
    }

    @Override
    public @Nullable Number numberValue(String key) {
        JsonPrimitive element = getElement(key);
        if (element == null || !element.isNumber())
            return null;
        return element.getAsNumber();
    }

    @Override
    public Map<String, String> map() {
        JsonObject jsonObject = getAsJsonObject();
        if (jsonObject == null)
            return Map.of();
        return jsonObject.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().getAsString(),
                        (a, b) -> b)
                );
    }
}
