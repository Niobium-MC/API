package fr.sorway.niobium.api.events;

import fr.sorway.niobium.api.data.accounts.PlayerAccount;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerAccountUnloadEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final PlayerAccount account;

    public PlayerAccountUnloadEvent(Player player, PlayerAccount account) {
        this.player = player;
        this.account = account;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerAccount getAccount() {
        return account;
    }
}
