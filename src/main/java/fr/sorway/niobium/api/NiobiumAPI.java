package fr.sorway.niobium.api;

import fr.sorway.niobium.api.database.sql.IDatabaseManager;
import fr.sorway.niobium.api.gui.IGuiManager;
import fr.sorway.niobium.api.managers.accounts.IAccountManager;
import fr.sorway.niobium.api.managers.ICommandManager;
import fr.sorway.niobium.api.managers.IRankManager;
import fr.sorway.niobium.api.utils.Servers;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
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
     * Retrieves a message object of type MiniMessage.
     * <p>
     * This method is used to obtain the current MiniMessage.
     * It might be used for various purposes such as displaying
     * the message content or processing it further.
     * </p>
     *
     * @return a {@code MiniMessage} object containing the message details.
     */
    MiniMessage getMessage();

    /**
     * Logs a message with the specified log level.
     *
     * @param level   the log level
     * @param message the message to log
     */
    default void log(Level level, String message) {
        getPlugin().getLogger().log(level, message);
    }

    default Servers getServer() {
        return Servers.getServer(PlainTextComponentSerializer.plainText().serialize(getPlugin().getServer().motd()).replace("[", "").replace("]", ""));
    }
}
