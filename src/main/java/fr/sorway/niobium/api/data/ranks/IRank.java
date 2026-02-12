package fr.sorway.niobium.api.data.ranks;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import java.util.List;

/**
 * Represents a rank within the system.
 *
 * A rank defines identification details, display formatting (prefix/suffix/colors),
 * access restrictions (maintenance mode), and the permissions granted to users
 * assigned to this rank.
 */
public interface IRank {

    /**
     * Gets the unique identifier of the rank.
     *
     * @return the rank ID
     */
    int id();

    /**
     * Gets the tab list order identifier of the rank.
     *
     * This value is typically used to determine the sorting position
     * of the rank in the tab list. Lower values usually indicate
     * higher priority and will appear above ranks with higher values.
     *
     * @return the tab list sorting identifier
     */
    int tabId();

    /**
     * Gets the name of the rank.
     *
     * @return the rank name
     */
    String name();

    /**
     * Gets the prefix of the rank.
     *
     * The prefix is typically displayed before a user's name
     * (e.g., in chat or player lists).
     *
     * @return the rank prefix
     */
    Component prefix();

    /**
     * Gets the suffix of the rank.
     *
     * The suffix is typically displayed after a user's name.
     *
     * @return the rank suffix
     */
    Component suffix();

    /**
     * Gets the color used for displaying the rank in the tab list.
     *
     * @return the tab list color representation
     */
    NamedTextColor tabColor();

    /**
     * Gets the color used for displaying the rank in chat.
     *
     * @return the chat color representation
     */
    String chatColor();

    /**
     * Checks whether this rank has maintenance access enabled.
     *
     * When maintenance access is enabled, users with this rank
     * are allowed to join or interact with the system while it
     * is in maintenance mode.
     *
     * @return {@code true} if this rank has maintenance access,
     *         {@code false} otherwise
     */
    boolean isMaintenanceAccess();

    /**
     * Gets the list of permissions associated with the rank.
     *
     * The returned list should be immutable to prevent
     * external modifications.
     *
     * @return an immutable list of permissions
     */
    List<String> getPermissions();
}
