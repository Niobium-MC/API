package fr.sorway.niobium.api.events;

import fr.sorway.niobium.api.data.accounts.IPlayerAccount;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerAccountLoadEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final IPlayerAccount account;

    public PlayerAccountLoadEvent(Player player, IPlayerAccount account) {
        this.player = player;
        this.account = account;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Player getPlayer() {
        return player;
    }

    public IPlayerAccount getAccount() {
        return account;
    }
}
