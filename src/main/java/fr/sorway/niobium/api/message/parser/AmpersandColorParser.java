package fr.sorway.niobium.api.message.parser;

import fr.sorway.niobium.api.message.StyleState;
import net.kyori.adventure.text.format.NamedTextColor;

public class AmpersandColorParser implements MessageTokenParser {

    @Override
    public ParseResult tryParse(String input, int position, StyleState state) {
        if (input.length() <= position + 1 || input.charAt(position) != '&') return null;

        char code = Character.toLowerCase(input.charAt(position + 1));
        switch (code) {
            case '0' -> state.color = NamedTextColor.BLACK;
            case '1' -> state.color = NamedTextColor.DARK_BLUE;
            case '2' -> state.color = NamedTextColor.DARK_GREEN;
            case '3' -> state.color = NamedTextColor.DARK_AQUA;
            case '4' -> state.color = NamedTextColor.DARK_RED;
            case '5' -> state.color = NamedTextColor.DARK_PURPLE;
            case '6' -> state.color = NamedTextColor.GOLD;
            case '7' -> state.color = NamedTextColor.GRAY;
            case '8' -> state.color = NamedTextColor.DARK_GRAY;
            case '9' -> state.color = NamedTextColor.BLUE;
            case 'a' -> state.color = NamedTextColor.GREEN;
            case 'b' -> state.color = NamedTextColor.AQUA;
            case 'c' -> state.color = NamedTextColor.RED;
            case 'd' -> state.color = NamedTextColor.LIGHT_PURPLE;
            case 'e' -> state.color = NamedTextColor.YELLOW;
            case 'f' -> state.color = NamedTextColor.WHITE;
            case 'l' -> state.bold = true;
            case 'o' -> state.italic = true;
            case 'n' -> state.underlined = true;
            case 'm' -> state.strikethrough = true;
            case 'k' -> state.obfuscated = true;
            case 'r' -> state.reset();
            default -> { return null; }
        }

        return new ParseResult(null, position + 2);
    }
}
