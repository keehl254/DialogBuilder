package me.keehl.dialogbuilder.api.input;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

public interface IDialogSingleOptionInputEntry {

    @Contract(pure = true)
    IDialogSingleOptionInputEntry id(String ID);

    @Contract(pure = true)
    @Nullable IDialogSingleOptionInputEntry display(String display);

    @Contract(pure = true)
    IDialogSingleOptionInputEntry isDefault(boolean isDefault);

}
