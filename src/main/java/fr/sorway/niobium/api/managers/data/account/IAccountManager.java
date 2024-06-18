package fr.sorway.niobium.api.managers.data.account;

import fr.sorway.niobium.api.data.accounts.IPlayerAccount;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface IAccountManager {

    /**
     * Handles actions to be performed when a player logs in.
     *
     * @param player the player who logged in
     */
    void onLogin(Player player);

    /**
     * Handles actions to be performed when a player logs out.
     *
     * @param player the player who logged out
     */
    void onLogout(Player player);

    /**
     * Retrieves the player account associated with the specified UUID.
     *
     * @param player The player whose account is to be loaded.
     * @return The player account associated with the given UUID, or null if no such account exists.
     */
    IPlayerAccount loadAccount(Player player);

    /**
     * Creates a new account for the specified player.
     *
     * @param player the player for whom the account will be created
     */
    void createAccount(Player player);

    /**
     * Deletes the account of the specified player.
     *
     * @param player the player whose account will be deleted
     */
    void deleteAccount(Player player);

    /**
     * Updates the account of the specified player.
     *
     * @param player the player whose account will be updated
     */
    void updateAccount(Player player);

    /**
     * Checks if the specified player's account exists in the database.
     *
     * @param uuid the UUID of the player to check
     * @return true if the player's account is in the database, false otherwise
     */
    boolean inDatabase(UUID uuid);

    /**
     * Gets the account of the specified player.
     *
     * @param player the player whose account to retrieve
     * @return the player's account, or null if not found
     */
    IPlayerAccount getAccount(Player player);
}
