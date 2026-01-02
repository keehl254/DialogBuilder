package me.keehl.dialogbuilder.dialog;

import me.keehl.dialogbuilder.api.IDialogAdapter;
import me.keehl.dialogbuilder.api.IDialogOpener;
import me.keehl.dialogbuilder.api.body.IDialogBody;
import me.keehl.dialogbuilder.api.body.IDialogBodyBuilder;
import me.keehl.dialogbuilder.api.dialog.AfterAction;
import me.keehl.dialogbuilder.api.dialog.IDialog;
import me.keehl.dialogbuilder.api.input.IDialogInput;
import me.keehl.dialogbuilder.api.input.IDialogInputBuilder;
import me.keehl.dialogbuilder.body.DialogBodyBuilder;
import me.keehl.dialogbuilder.input.DialogInputBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public abstract class Dialog<T extends IDialog<T>> implements IDialog<T> {

    protected final IDialogAdapter<?,?,?,?> adapter;
    private final String namespace;

    private String title;
    private String externalTitle;
    private boolean canCloseWithEscape;
    private boolean pause;
    private AfterAction afterAction;

    private List<IDialogInput> input;
    private List<IDialogBody> body;

    public Dialog(IDialogAdapter<?,?,?,?> adapter, String namespace) {
        this.adapter = adapter;
        this.namespace = namespace;
    }

    protected abstract T self();

    @Override
    public T title(String title) {
        this.title = title;
        return this.self();
    }

    @Override
    public T externalTitle(String externalTitle) {
        this.externalTitle = externalTitle;
        return this.self();
    }

    @Override
    public T canCloseWithEscape(boolean canCloseWithEscape) {
        this.canCloseWithEscape = canCloseWithEscape;
        return this.self();
    }

    @Override
    public T pause(boolean pause) {
        this.pause = pause;
        return this.self();
    }

    @Override
    public T afterAction(AfterAction afterAction) {
        this.afterAction = afterAction;
        return this.self();
    }

    @Override
    public T body(Consumer<IDialogBodyBuilder> bodyBuilder) {
        if (this.body == null) {
            this.body = new ArrayList<>();
        }
        DialogBodyBuilder builder = new DialogBodyBuilder();
        bodyBuilder.accept(builder);
        this.body.add(builder.getDialogBody());
        return this.self();
    }

    @Override
    public T input(String key, Consumer<IDialogInputBuilder> inputBuilder) {
        if (this.input == null) {
            this.input = new ArrayList<>();
        }
        DialogInputBuilder builder = new DialogInputBuilder(key);
        inputBuilder.accept(builder);
        this.input.add(builder.getDialogInput());
        return this.self();
    }

    @Override
    public T body(Collection<Consumer<IDialogBodyBuilder>> bodyBuilders) {
        for (Consumer<IDialogBodyBuilder> bodyBuilder : bodyBuilders) {
            this.body(bodyBuilder);
        }
        return this.self();
    }

    @Override
    public T input(Map<String, Consumer<IDialogInputBuilder>> inputBuilders) {
        for (Map.Entry<String, Consumer<IDialogInputBuilder>> entry : inputBuilders.entrySet()) {
            this.input(entry.getKey(), entry.getValue());
        }
        return this.self();
    }

    public IDialogOpener opener() {
        return this.adapter.opener(this);
    }

    public String getTitle() {
        return this.title;
    }

    public String getExternalTitle() {
        return this.externalTitle;
    }

    public boolean getCanCloseWithEscape() {
        return this.canCloseWithEscape;
    }

    public boolean canPause() {
        return this.pause;
    }

    public AfterAction getAfterAction() {
        return this.afterAction;
    }

    public List<IDialogBody> getBody() {
        return this.body;
    }

    public List<IDialogInput> getInput() {
        return this.input;
    }

    public String getNamespace() {
        return this.namespace;
    }
}
