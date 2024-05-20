package fr.sorway.niobium.api.data.accounts.data;

import java.util.Set;

public interface IPlayerRank {

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
