package me.keehl.dialogbuilder.dialog;

import me.keehl.dialogbuilder.api.IDialogAdapter;
import me.keehl.dialogbuilder.api.dialog.INoticeDialog;
import me.keehl.dialogbuilder.api.dialog.action.IDialogActionHolder;
import me.keehl.dialogbuilder.dialog.action.DialogActionHolder;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class NoticeDialog extends Dialog<INoticeDialog> implements INoticeDialog {

    private @Nullable DialogActionHolder action;


    public NoticeDialog(IDialogAdapter<?,?,?,?> adapter, String namespace) {
        super(adapter, namespace);
    }

    @Override
    protected INoticeDialog self() {
        return this;
    }

    @Override
    public INoticeDialog action(@Nullable Consumer<IDialogActionHolder> action) {
        if(action == null) {
            this.action = null;
            return this;
        }

        DialogActionHolder holder = new DialogActionHolder(this.adapter);
        action.accept(holder);

        this.action = holder;
        return this;
    }

    public @Nullable DialogActionHolder getAction() {
        return this.action;
    }
}
