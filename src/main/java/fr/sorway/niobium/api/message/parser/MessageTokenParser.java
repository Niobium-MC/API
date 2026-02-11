package fr.sorway.niobium.api.message.parser;

import fr.sorway.niobium.api.message.StyleState;
import net.kyori.adventure.text.Component;

public interface MessageTokenParser {
    /**
     * Tente de parser un token à partir de l'entrée.
     * @param input Le texte complet.
     * @param position Position actuelle du curseur.
     * @param state L'état courant du style.
     * @return Un résultat contenant un composant (ou null) et la nouvelle position.
     */
    ParseResult tryParse(String input, int position, StyleState state);

    record ParseResult(Component component, int newPosition) {}
}
