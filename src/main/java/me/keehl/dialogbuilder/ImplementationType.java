package me.keehl.dialogbuilder;

public enum ImplementationType {
    PAPER("PaperDialogAdapter", "com.destroystokyo.paper.PaperConfig", "io.papermc.paper.configuration.Configuration"),
    SPIGOT("SpigotDialogAdapter", "org.spigotmc.SpigotConfig"),
    UNKNOWN("UnsupportedImplementation");

    private final String implementationClassName;
    private final String[] classNames;

    ImplementationType(String implementationClassName, String... classNames) {
        this.implementationClassName = implementationClassName;
        this.classNames = classNames;
    }

    public String getImplementationClassName() {
        return this.implementationClassName;
    }

    public boolean selfCheck() {

        for(String className : this.classNames) {
            try {
                Class.forName(className);
                return true;
            } catch (ClassNotFoundException var7) {
            }
        }

        return false;
    }
}