package me.keehl.dialogbuilder.dialog;

import me.keehl.dialogbuilder.api.IDialogAdapter;
import me.keehl.dialogbuilder.api.dialog.IMultiActionDialog;
import me.keehl.dialogbuilder.api.dialog.action.IDialogActionHolder;
import me.keehl.dialogbuilder.dialog.action.DialogActionHolder;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MultiActionDialog extends Dialog<IMultiActionDialog> implements IMultiActionDialog {

    private final List<DialogActionHolder> actions = new ArrayList<>();
    private @Nullable DialogActionHolder exitAction;
    private int columns = DEFAULT_COLUMNS;


    public MultiActionDialog(IDialogAdapter<?,?,?,?> adapter, String namespace) {
        super(adapter, namespace);
    }

    @Override
    protected IMultiActionDialog self() {
        return this;
    }

    @Override
    public IMultiActionDialog columns(@Nullable Integer columns) {
        this.columns = columns == null ? DEFAULT_COLUMNS : columns;
        return this;
    }

    @Override
    public IMultiActionDialog action(Consumer<IDialogActionHolder> action) {
        DialogActionHolder holder = new DialogActionHolder(this.adapter);
        action.accept(holder);

        this.actions.add(holder);
        return this;
    }

    @Override
    public IMultiActionDialog exitAction(@Nullable Consumer<IDialogActionHolder> action) {
        if(action == null) {
            this.exitAction = null;
            return this;
        }

        DialogActionHolder holder = new DialogActionHolder(this.adapter);
        action.accept(holder);

        this.exitAction = holder;
        return this;
    }

    public List<DialogActionHolder> getActions() {
        return this.actions;
    }

    public int getColumns() {
        return this.columns;
    }

    public @Nullable DialogActionHolder getExitAction() {
        return this.exitAction;
    }
}
