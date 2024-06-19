package fr.sorway.niobium.api.managers.data.account;

import fr.sorway.niobium.api.data.accounts.IPlayerAccount;
import fr.sorway.niobium.api.data.accounts.data.IAccountRank;

public interface IRankAccountManager {

    /**
     * Assigns a specific rank to a player's account.
     *
     * @param account the player account to which the rank will be assigned.
     * @param rank the rank to assign to the player account.
     */
    void assignRank(IPlayerAccount account, IAccountRank rank);

    /**
     * Removes the specified rank from the given player account.
     *
     * @param account the player account from which the rank will be removed.
     *                Must not be null.
     * @param rank the rank to be removed from the player account.
     *             Must not be null.
     */
    void unassignRank(IPlayerAccount account, IAccountRank rank);

    /**
     * Adds a rank to the specified player account.
     *
     * @param account the player account to which the rank will be added
     * @param rank the rank to be added to the player's account
     */
    void addRank(IPlayerAccount account, IAccountRank rank);

    /**
     * Removes a specific rank from a player's account.
     *
     * @param account the player account from which the rank will be removed.
     * @param rank the rank to remove from the player account.
     */
    void removeRank(IPlayerAccount account, IAccountRank rank);
}
