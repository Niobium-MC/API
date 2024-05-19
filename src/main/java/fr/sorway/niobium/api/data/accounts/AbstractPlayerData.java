package fr.sorway.niobium.api.data.accounts;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public abstract class AbstractPlayerData {
    public UUID uuid;

    public String getUUID() {
        return this.uuid.toString();
    }

    Player getPlayer() {
        return Bukkit.getPlayer(this.uuid);
    }
}
