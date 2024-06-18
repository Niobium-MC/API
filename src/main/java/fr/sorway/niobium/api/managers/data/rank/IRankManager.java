package fr.sorway.niobium.api.managers.data.rank;

import fr.sorway.niobium.api.data.ranks.IRank;

import java.util.Set;

public interface IRankManager {

    /**
     * Retrieves all available ranks.
     *
     * @return a set of all ranks
     */
    Set<IRank> getRanks();

    /**
     * Creates a new rank.
     *
     * @param rank the rank to be created
     */
    void createRank(IRank rank);

    /**
     * Deletes an existing rank.
     *
     * @param rank the rank to be deleted
     */
    void deleteRank(IRank rank);

    /**
     * Loads all ranks from the data source.
     */
    void loadRanks();

    /**
     * Retrieves a rank by its name.
     *
     * @param name the name of the rank
     * @return the rank with the specified name, or null if not found
     */
    IRank getRank(String name);
}
