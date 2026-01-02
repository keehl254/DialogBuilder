package me.keehl.dialogbuilder.body;

import me.keehl.dialogbuilder.api.body.IDialogBody;
import me.keehl.dialogbuilder.api.body.IDialogBodyBuilder;
import me.keehl.dialogbuilder.api.body.IDialogItemBody;
import me.keehl.dialogbuilder.api.body.IDialogTextBody;

public class DialogBodyBuilder implements IDialogBodyBuilder {

    private IDialogBody current;

    @Override
    public IDialogItemBody item() {
        DialogItemBody itemBody = new DialogItemBody();
        this.current = itemBody;
        return itemBody;
    }

    @Override
    public IDialogTextBody text() {
        DialogTextBody textBody = new DialogTextBody();
        this.current = textBody;
        return textBody;
    }

    public IDialogBody getDialogBody() {
        if (this.current == null)
            throw new IllegalStateException("No dialog body has been created yet.");

        return this.current;
    }
}
