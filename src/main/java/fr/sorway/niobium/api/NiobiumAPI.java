package fr.sorway.niobium.api;

import fr.sorway.niobium.api.database.sql.IDatabaseManager;
import fr.sorway.niobium.api.gui.IGuiManager;
import fr.sorway.niobium.api.managers.data.account.IAccountManager;
import fr.sorway.niobium.api.managers.ICommandManager;
import fr.sorway.niobium.api.managers.data.rank.IRankManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public interface NiobiumAPI {

    /**
     * Gets the plugin instance.
     *
     * @return the plugin instance
     */
    JavaPlugin getPlugin();

    /**
     * Gets the command manager.
     *
     * @return the command manager
     */
    ICommandManager getCommandManager();

    /**
     * Gets the account manager.
     *
     * @return the account manager
     */
    IAccountManager getAccountManager();

    /**
     * Gets the rank manager.
     *
     * @return the rank manager
     */
    IRankManager getRankManager();

    /**
     * Gets the database manager.
     *
     * @return the database manager
     */
    IDatabaseManager getDatabaseManager();

    /**
     * Retrieves the {@link IGuiManager} instance associated with the current context.
     *
     * This method provides access to the graphical user interface (GUI) manager
     * which is responsible for managing the GUI elements and their behavior.
     *
     * @return the {@link IGuiManager} instance currently being used.
     *
     * @see IGuiManager
     */
    IGuiManager getGuiManager();

    /**
     * Logs a message with the specified log level.
     *
     * @param level   the log level
     * @param message the message to log
     */
    default void log(Level level, String message) {
        getPlugin().getLogger().log(level, message);
    }
}
