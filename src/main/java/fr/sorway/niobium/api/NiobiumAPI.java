package fr.sorway.niobium.api;

import fr.sorway.niobium.api.database.sql.IDatabaseManager;
import fr.sorway.niobium.api.managers.IAccountManager;
import fr.sorway.niobium.api.managers.ICommandManager;
import fr.sorway.niobium.api.managers.IRankManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public interface NiobiumAPI {
    JavaPlugin getPlugin();
    ICommandManager getCommandManager();
    IAccountManager getAccountManager();
    IRankManager getRankManager();
    IDatabaseManager getDatabaseManager();

    default void log(Level level, String message) {
        getPlugin().getLogger().log(level, message);
    }
}
