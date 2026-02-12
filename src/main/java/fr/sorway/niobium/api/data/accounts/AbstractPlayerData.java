package fr.sorway.niobium.api.data.accounts;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public abstract class AbstractPlayerData {
    private final UUID uuid;

    protected AbstractPlayerData(UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * Gets the UUID of the player.
     *
     * @return the UUID of the player
     */
    public UUID uuid() {
        return this.uuid;
    }

    /**
     * Gets the UUID of the player as a String.
     *
     * @return the UUID of the player
     */
    public String uuidAsString() {
        return this.uuid.toString();
    }

    /**
     * Gets the Player object associated with this UUID.
     *
     * @return the Player object or null if the player is not online
     */
    Player player() {
        return Bukkit.getPlayer(this.uuid);
    }
}
