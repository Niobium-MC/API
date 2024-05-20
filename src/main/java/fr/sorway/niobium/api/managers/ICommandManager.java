package fr.sorway.niobium.api.managers;

import fr.sorway.niobium.api.commands.Command;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;

public interface ICommandManager {

    /**
     * Registers a command with the specified plugin.
     *
     * @param plugin  the plugin with which the command is registered
     * @param command the command to register
     */
    void register(JavaPlugin plugin, Command command);

    /**
     * Retrieves the set of registered commands.
     *
     * @return a set of registered commands
     */
    Set<Command> getCommands();
}
