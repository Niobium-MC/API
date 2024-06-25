package fr.sorway.niobium.api.message;

import fr.sorway.niobium.api.NiobiumAPI;
import fr.sorway.niobium.api.data.accounts.IPlayerAccount;
import fr.sorway.niobium.api.data.ranks.IRank;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageHelper {
    public static final String PREFIX = "<#3d61f1><bold>Niobium</bold> <gray>» <reset>";

    /**
     * Récupère le nom complet du joueur, incluant son préfixe, son suffixe, et sa couleur de rang.
     *
     * @param api L'instance de NiobiumAPI utilisée pour accéder aux données des comptes.
     * @param player L'instance du joueur dont on veut récupérer le nom complet.
     * @return Une chaîne de caractères représentant le nom complet du joueur avec son rang.
     */
    public static String playerName(NiobiumAPI api, Player player) {
        IPlayerAccount account = api.getAccountManager().getAccount(player);
        IRank rank = account.getPlayerRank().getActiveRank().getRank();

        return rank.getSuffix() == null || rank.getSuffix().isEmpty()
                ? String.format("%s %s%s", rank.getPrefix(), rank.getColor(), player.getName())
                : String.format("%s %s%s %s", rank.getPrefix(), rank.getColor(), player.getName(), rank.getSuffix());
    }
}
