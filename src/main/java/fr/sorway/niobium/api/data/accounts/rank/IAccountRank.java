package fr.sorway.niobium.api.data.accounts.rank;

import fr.sorway.niobium.api.data.ranks.IRank;

public interface IAccountRank {

    /**
     * Gets the rank.
     *
     * @return the rank
     */
    IRank rank();

    /**
     * Gets the end time of the rank.
     *
     * @return the end time as LocalDateTime
     */
    long endTime();
}
