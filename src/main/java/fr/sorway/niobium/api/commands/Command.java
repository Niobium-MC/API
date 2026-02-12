package fr.sorway.niobium.api.commands;

import fr.sorway.niobium.api.commands.arguments.DescriptionArgument;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Command {
    public final String parent;
    public final String name;
    public final List<String> aliases;
    public List<CommandArgument<?>> arguments = new ArrayList<>();
    public Executor executor = Executor.ALL;

    public Command(String parent) {
        this(parent, "", Collections.emptyList(), Executor.ALL);
    }

    public Command(String parent, String name) {
        this(parent, name, Collections.emptyList(), Executor.ALL);
    }

    public Command(String parent, String name, Executor executor) {
        this(parent, name, Collections.emptyList(), executor);
    }

    public Command(String parent, String name, List<String> aliases, Executor executor) {
        this.parent = parent;
        this.name = name;
        this.aliases = new ArrayList<>(aliases);
        this.executor = executor;
    }

    public Command addArgument(CommandArgument<?>... arguments) {
        boolean foundOptional = false;

        for (int i = 0; i < arguments.length; i++) {
            final CommandArgument<?> argument = arguments[i];

            if (argument.getArgument() instanceof DescriptionArgument && i != arguments.length - 1)
                throw new IllegalArgumentException("L'argument DESCRIPTION doit être le dernier dans la liste des arguments.");

            if (foundOptional && !argument.isOptional())
                throw new IllegalArgumentException("Un argument obligatoire ne peut pas venir après un argument optionnel.");

            if (argument.isOptional())
                foundOptional = true;
        }

        this.arguments = List.of(arguments);
        return this;
    }

    public boolean matches(String input) {
        return this.parent.equalsIgnoreCase(input) || aliases.stream().anyMatch(alias -> alias.equalsIgnoreCase(input));
    }

    public abstract void execute(CommandExecutor executor);

    public List<String> autoComplete(CommandExecutor executor) {
        return new ArrayList<>();
    }

    public boolean hasPermission(CommandSender sender, String permission) {
        if (sender instanceof ConsoleCommandSender || sender.isOp())
            return true;
        return sender.hasPermission(permission);
    }

    public String getUsage() {
        StringBuilder builder = new StringBuilder("/").append(parent);
        if (!name.isBlank()) builder.append(" ").append(name);

        for (CommandArgument<?> arg : arguments)
            builder.append(" ").append(arg.getUsage());

        return builder.toString();
    }

    public enum Executor {
        PLAYER, CONSOLE, OPERATORS, ALL;
    }
}
