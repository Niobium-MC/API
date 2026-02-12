package fr.sorway.niobium.api.managers;

import fr.sorway.niobium.api.data.ranks.IRank;
import org.bukkit.entity.Player;

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
     * Applique le classement (rank) spécifié au joueur donné,
     * en mettant à jour son affichage dans la tablist.
     *
     * <p>Cette méthode modifie généralement :
     * <ul>
     *     <li>Le préfixe/suffixe affiché dans la tablist</li>
     *     <li>L'ordre du joueur dans la liste</li>
     *     <li>Les éventuels paramètres liés au rang (couleur, priorité, etc.)</li>
     * </ul>
     *
     * @param player le joueur auquel appliquer le rank
     * @param rank le rank à appliquer au joueur
     */
    void applyRankTab(Player player, IRank rank);

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
