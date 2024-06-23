package fr.sorway.niobium.api.data.ranks;

import java.util.List;

public interface IRank {
    /**
     * Gets the unique identifier of the rank.
     *
     * @return the rank ID
     */
    int getId();

    /**
     * Gets the name of the rank.
     *
     * @return the rank name
     */
    String getName();

    /**
     * Gets the prefix of the rank.
     *
     * @return the rank prefix
     */
    String getPrefix();

    /**
     * Gets the suffix of the rank.
     *
     * @return the rank suffix
     */
    String getSuffix();

    /**
     * Gets the color associated with the rank.
     *
     * @return the rank color
     */
    String getColor();

    /**
     * Checks if the maintenance access mode is enabled.
     *
     * This method returns {@code true} if the system is currently in maintenance mode,
     * meaning that only users with maintenance privileges have access. Otherwise,
     * it returns {@code false}, indicating that the system is in normal operation mode.
     *
     * @return {@code true} if maintenance access is enabled, {@code false} otherwise.
     */
    boolean isMaintenanceAccess();

    /**
     * Gets the list of permissions associated with the rank.
     * The returned list should be immutable to prevent external modifications.
     *
     * @return an immutable list of permissions
     */
    List<String> getPermissions();
}
