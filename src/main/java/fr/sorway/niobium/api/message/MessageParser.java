package fr.sorway.niobium.api.message;

import fr.sorway.niobium.api.message.parser.AmpersandColorParser;
import fr.sorway.niobium.api.message.parser.HexColorParser;
import fr.sorway.niobium.api.message.parser.MessageTokenParser;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;

import java.util.List;

public class MessageParser {

    private static final List<MessageTokenParser> PARSERS = List.of(
            new AmpersandColorParser(),
            new HexColorParser()
    );

    /**
     * Analyse le texte d'entrée et le transforme en un {@link Component} formaté.
     *
     * @param input Le texte à analyser.
     * @return Le composant résultant après parsing.
     */
    public static Component parse(String input) {
        TextComponent.Builder builder = Component.text();
        StyleState state = new StyleState();
        int index = 0;

        while (index < input.length()) {
            int nextSpecial = findNextSpecialChar(input, index);

            if (nextSpecial == input.length()) {
                builder.append(state.applyTo(input.substring(index)));
                break;
            }

            builder.append(state.applyTo(input.substring(index, nextSpecial)));

            boolean parsed = false;
            for (MessageTokenParser parser : PARSERS) {
                MessageTokenParser.ParseResult result = parser.tryParse(input, nextSpecial, state);
                if (result != null) {
                    if (result.component() != null) {
                        builder.append(result.component());
                    }
                    index = result.newPosition();
                    parsed = true;
                    break;
                }
            }

            if (!parsed) {
                index = nextSpecial + 1;
            }
        }

        return builder.build();
    }

    /**
     * Trouve la position de la prochaine occurrence d'un caractère spécial (& ou <#).
     *
     * @param input La chaîne d'entrée.
     * @param start La position de départ pour la recherche.
     * @return La position du prochain caractère spécial ou la fin de la chaîne.
     */
    private static int findNextSpecialChar(String input, int start) {
        int nextAmp = input.indexOf('&', start);
        int nextHex = input.indexOf("<#", start);

        if (nextAmp == -1 && nextHex == -1) {
            return input.length();
        }

        if (nextAmp == -1) {
            return nextHex;
        }
        if (nextHex == -1) {
            return nextAmp;
        }

        return Math.min(nextAmp, nextHex);
    }
}

