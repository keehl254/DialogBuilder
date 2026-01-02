package me.keehl.dialogbuilder.dialog;

import me.keehl.dialogbuilder.api.IDialogAdapter;
import me.keehl.dialogbuilder.api.dialog.IServerLinksDialog;
import me.keehl.dialogbuilder.api.dialog.action.IDialogActionHolder;
import me.keehl.dialogbuilder.dialog.action.DialogActionHolder;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.function.Consumer;

public class ServerLinksDialog extends Dialog<IServerLinksDialog> implements IServerLinksDialog {

    private @Nullable DialogActionHolder exitAction;
    private int buttonWidth = DEFAULT_BUTTON_WIDTH;
    private int columns = DEFAULT_COLUMNS;

    public ServerLinksDialog(IDialogAdapter<?,?,?,?> adapter, String namespace) {
        super(adapter, namespace);
    }

    @Override
    protected ServerLinksDialog self() {
        return this;
    }

    @Override
    public IServerLinksDialog columns(@Nullable Integer columns) {
        this.columns = columns == null ? DEFAULT_COLUMNS : columns;
        return this;
    }

    @Override
    public IServerLinksDialog buttonWidth(@Nullable @Range(from = 1, to = 1024) Integer buttonWidth) {
        this.buttonWidth = buttonWidth == null ? DEFAULT_BUTTON_WIDTH : buttonWidth;
        return this;
    }

    @Override
    public IServerLinksDialog exitAction(@Nullable Consumer<IDialogActionHolder> action) {
        if(action == null) {
            this.exitAction = null;
            return this;
        }

        DialogActionHolder holder = new DialogActionHolder(this.adapter);
        action.accept(holder);

        this.exitAction = holder;
        return this;
    }

    public int getColumns() {
        return this.columns;
    }

    public int getButtonWidth() {
        return this.buttonWidth;
    }

    public @Nullable DialogActionHolder getExitAction() {
        return this.exitAction;
    }
}
