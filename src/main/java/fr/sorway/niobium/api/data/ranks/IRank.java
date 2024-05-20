package fr.sorway.niobium.api.data.ranks;

import org.bukkit.ChatColor;

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
    ChatColor getColor();

    /**
     * Gets the list of permissions associated with the rank.
     * The returned list should be immutable to prevent external modifications.
     *
     * @return an immutable list of permissions
     */
    List<String> getPermissions();
}
