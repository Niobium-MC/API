package fr.sorway.niobium.api.message;

import fr.sorway.niobium.api.NiobiumAPI;
import fr.sorway.niobium.api.data.accounts.IPlayerAccount;
import fr.sorway.niobium.api.data.ranks.IRank;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageHelper {
    public static final String PREFIX = translateColors("#3d61f1§lNiobium §7» §r");

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

    /**
     * Translates color codes in a message to their corresponding color values.
     *
     * @param message The input message containing color codes (e.g., "&6Hello").
     * @return The message with color codes replaced by actual colors.
     */
    public static String translateColors(String message) {
        Matcher matcher = Pattern.compile("#[a-fA-F0-9]{6}").matcher(message);
        StringBuilder result = new StringBuilder();

        int lastEnd = 0;

        while (matcher.find()) {
            result.append(message, lastEnd, matcher.start());

            String hexCode = matcher.group();
            result.append('&').append('x');
            for (int i = 1; i < hexCode.length(); i++) {
                result.append('&').append(hexCode.charAt(i));
            }

            lastEnd = matcher.end();
        }

        result.append(message.substring(lastEnd));

        return ChatColor.translateAlternateColorCodes('&', result.toString());
    }
}
