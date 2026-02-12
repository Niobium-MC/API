package fr.sorway.niobium.api.commands;

public class CommandArgument<T> {
    private final String name;
    private final Argument<T> argument;
    private final boolean optional;

    public CommandArgument(String name, ArgumentType type) {
        this(name, type, false);
    }

    public CommandArgument(String name, ArgumentType type, boolean optional) {
        this.name = name;
        this.argument = (Argument<T>) type.create();
        this.optional = optional;
    }

    public String getName() {
        return name;
    }

    public Argument<T> getArgument() {
        return argument;
    }

    public boolean isOptional() {
        return optional;
    }

    public String getUsage() {
        return optional ? "[" + name + "]" : "<" + name + ">";
    }
}
