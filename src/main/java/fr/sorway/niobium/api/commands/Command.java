package fr.sorway.niobium.api.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import java.util.ArrayList;
import java.util.List;

public abstract class Command {
    public final String parent;
    public final String name;
    public Executor executor = Executor.ALL;

    public Command(String parent) {
        this.parent = parent;
        this.name = "";
    }

    public Command(String parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    public Command(String parent, String name, Executor executor) {
        this.parent = parent;
        this.name = name;
        this.executor = executor;
    }

    public abstract void execute(CommandExecutor executor);

    public List<String> autoComplete(CommandExecutor executor) {
        return new ArrayList<>();
    }

    public boolean hasPermission(CommandSender sender, String permission) {
        if (sender instanceof ConsoleCommandSender || sender.isOp())
            return true;
        if (!sender.hasPermission(permission)) {
            sender.sendMessage(ChatColor.RED + "Vous n'avez pas les autorisations npour effectuer cette action.");
            return false;
        }
        return true;
    }

    public enum Executor {
        PLAYER, CONSOLE, OPERATORS, ALL;
    }
}
