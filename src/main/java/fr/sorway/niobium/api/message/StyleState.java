package fr.sorway.niobium.api.message;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class StyleState {
    public TextColor color = NamedTextColor.WHITE;
    public boolean bold = false, italic = false, underlined = false, strikethrough = false, obfuscated = false;

    public StyleState copy() {
        StyleState copy = new StyleState();
        copy.color = this.color;
        copy.bold = this.bold;
        copy.italic = this.italic;
        copy.underlined = this.underlined;
        copy.strikethrough = this.strikethrough;
        copy.obfuscated = this.obfuscated;
        return copy;
    }

    public void reset() {
        this.color = NamedTextColor.WHITE;
        this.bold = this.italic = this.underlined = this.strikethrough = this.obfuscated = false;
    }

    public Component applyTo(String content) {
        return Component.text(content)
                .color(color)
                .decoration(TextDecoration.BOLD, bold)
                .decoration(TextDecoration.ITALIC, italic)
                .decoration(TextDecoration.UNDERLINED, underlined)
                .decoration(TextDecoration.STRIKETHROUGH, strikethrough)
                .decoration(TextDecoration.OBFUSCATED, obfuscated);
    }
}
