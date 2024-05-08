package fr.sorway.niobium.api;

import fr.sorway.niobium.api.managers.IAccountManager;
import fr.sorway.niobium.api.managers.ICommandManager;
import fr.sorway.niobium.api.managers.IRankManager;
import org.bukkit.plugin.java.JavaPlugin;

public interface NiobiumAPI {
    JavaPlugin getPlugin();
    ICommandManager getCommandManager();
    IAccountManager getAccountManager();
    IRankManager getRankManager();
}
