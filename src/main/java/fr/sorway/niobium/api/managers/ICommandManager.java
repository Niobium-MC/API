package fr.sorway.niobium.api.managers;

import fr.sorway.niobium.api.commands.Command;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;

public interface ICommandManager {
    void register(JavaPlugin plugin, Command command);
    Set<Command> getCommands();
}
