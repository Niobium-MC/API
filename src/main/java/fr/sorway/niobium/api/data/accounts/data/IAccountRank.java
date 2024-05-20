package fr.sorway.niobium.api.data.accounts.data;

import fr.sorway.niobium.api.data.ranks.IRank;

public interface IAccountRank {

    /**
     * Gets the rank.
     *
     * @return the rank
     */
    IRank getRank();

    /**
     * Gets the end time of the rank.
     *
     * @return the end time as LocalDateTime
     */
    long getEndTime();

    /**
     * Checks if this rank is the default rank.
     *
     * @return true if it is the default rank, false otherwise
     */
    boolean isDefault();
}
