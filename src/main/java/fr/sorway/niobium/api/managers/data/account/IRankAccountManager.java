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
     * Removes a specific rank from a player's account.
     *
     * @param account the player account from which the rank will be removed.
     * @param rank the rank to remove from the player account.
     */
    void removeRank(IPlayerAccount account, IAccountRank rank);
}
