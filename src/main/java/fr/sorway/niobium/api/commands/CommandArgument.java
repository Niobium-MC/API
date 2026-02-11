package fr.sorway.niobium.api.commands;

public class CommandArgument<T> {
    private final String name;
    private final Argument<T> argument;

    public CommandArgument(String name, ArgumentType type) {
        this.name = name;
        this.argument = (Argument<T>) type.create();
    }

    public String getName() {
        return name;
    }

    public Argument<T> getArgument() {
        return argument;
    }

    public String getUsage() {
        return "<" + name + ">";
    }
}
