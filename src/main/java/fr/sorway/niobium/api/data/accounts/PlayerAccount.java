package fr.sorway.niobium.api.data.accounts;

import fr.sorway.niobium.api.data.accounts.data.RankData;
import org.bukkit.entity.Player;

public class PlayerAccount extends AbstractAccountData {
    private final boolean newPlayer;
    private final RankData rankData;

    public PlayerAccount(Player player, boolean newPlayer) {
        this.uuid = player.getUniqueId();
        this.newPlayer = newPlayer;
        this.rankData = new RankData(this.uuid);
    }

    public boolean isNewPlayer() {
        return newPlayer;
    }

    public RankData getRankData() {
        return rankData;
    }
}
