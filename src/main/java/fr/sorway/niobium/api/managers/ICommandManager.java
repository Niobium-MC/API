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
     * Récupère un ensemble de toutes les commandes disponibles.
     * Cette méthode retourne un {@link Set} contenant toutes les commandes accessibles,
     * indépendamment des plugins spécifiques.
     *
     * @return un {@link Set} contenant toutes les commandes disponibles. Si aucune commande n'est
     *         disponible, une collection vide est retournée.
     */
    Set<Command> getCommands();
}
