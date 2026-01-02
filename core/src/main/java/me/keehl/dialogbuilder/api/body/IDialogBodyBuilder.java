package me.keehl.dialogbuilder.api.body;

public interface IDialogBodyBuilder {

    /**
     * Create an item body
     *
     * @return the instance of the item body
     */
    IDialogItemBody item();

    /**
     * Create a text body
     *
     * @return the instance of the text body
     */
    IDialogTextBody text();
}
