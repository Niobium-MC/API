package fr.sorway.niobium.api.helpers;

import org.bukkit.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextColors {

    private static final Pattern HEX_PATTERN = Pattern.compile("#[a-fA-F0-9]{6}");

    public static String translateColors(String message) {
        Matcher matcher = HEX_PATTERN.matcher(message);
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
