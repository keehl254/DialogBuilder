package me.keehl.dialogbuilder.paper;

import io.papermc.paper.dialog.DialogResponseView;
import me.keehl.dialogbuilder.api.IDialogPayload;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.UUID;

@SuppressWarnings("UnstableApiUsage")
public record PaperDialogPayload(UUID owner, boolean isQuit, @Nullable DialogResponseView view) implements IDialogPayload {
    @Override
    public @Nullable String textValue(String key) {
        if (this.view == null)
            return null;
        return this.view.getText(key);
    }

    @Override
    public @Nullable Boolean booleanValue(String key) {
        if (this.view == null)
            return null;
        return this.view.getBoolean(key);
    }

    @Override
    public @Nullable Number numberValue(String key) {
        if (this.view == null)
            return null;
        return this.view.getFloat(key);
    }

    @Override
    public Map<String, String> map() {
        if (this.view == null)
            return Map.of();
        return PaperDialogPayloadMap.convertDialogResponseToMap(this.view);
    }
}
