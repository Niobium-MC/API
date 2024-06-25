package fr.sorway.niobium.api.message;

import fr.sorway.niobium.api.NiobiumAPI;
import fr.sorway.niobium.api.data.accounts.IPlayerAccount;
import fr.sorway.niobium.api.data.ranks.IRank;
import org.bukkit.entity.Player;

public class TextFormatter {

    public static String playerName(NiobiumAPI api, Player player) {
        final IPlayerAccount account = api.getAccountManager().getAccount(player);
        final IRank rank = account.getPlayerRank().getActiveRank().getRank();

        return String.format("%s %s%s %s", rank.getPrefix(), rank.getColor(), player.getName(), rank.getSuffix());
    }
}
