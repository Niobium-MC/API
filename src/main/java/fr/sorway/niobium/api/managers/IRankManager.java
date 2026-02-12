package fr.sorway.niobium.api.managers;

import fr.sorway.niobium.api.data.ranks.IRank;

import java.util.Optional;
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
     * @param name the name of the rank to search for
     * @return an {@link Optional} containing the found {@link IRank}
     *         if a rank with the given name exists,
     *         or {@link Optional#empty()} if no rank matches
     * @throws IllegalArgumentException if name is null or empty
     */
    Optional<IRank> getRank(String name);

    /**
     * Retrieves a rank by its unique identifier.
     *
     * @param id the unique identifier of the rank
     * @return an {@link Optional} containing the found {@link IRank}
     *         if a rank with the given id exists,
     *         or {@link Optional#empty()} if no rank matches
     */
    Optional<IRank> getRank(int id);
}
