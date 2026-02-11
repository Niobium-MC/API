package fr.sorway.niobium.api.data.accounts.rank;

import java.util.Set;

public interface IPlayerRank {

    /**
     * Sets the active rank of an account.
     *
     * @param rank the new active rank of the account
     */
    void setActiveRank(IAccountRank rank);

    /**
     * Gets the active rank of the player.
     *
     * @return the active account rank
     */
    IAccountRank getActiveRank();

    /**
     * Gets all ranks associated with the player.
     *
     * @return a set of all account ranks
     */
    Set<IAccountRank> getRanks();
}
