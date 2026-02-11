package fr.sorway.niobium.api.message.parser;

import fr.sorway.niobium.api.message.StyleState;
import net.kyori.adventure.text.format.TextColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HexColorParser implements MessageTokenParser {
    private static final Pattern HEX_PATTERN = Pattern.compile("<#([a-fA-F0-9]{6})>");

    @Override
    public ParseResult tryParse(String input, int position, StyleState state) {
        final Matcher matcher = HEX_PATTERN.matcher(input.substring(position));
        if (!matcher.lookingAt()) return null;

        state.color = TextColor.fromHexString("#" + matcher.group(1));
        return new ParseResult(null, position + matcher.end());
    }
}

