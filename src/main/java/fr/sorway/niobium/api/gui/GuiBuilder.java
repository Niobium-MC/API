package fr.sorway.niobium.api.gui;

import org.bukkit.entity.Player;

public abstract class GuiBuilder {
    private final int size;
    private final String title;

    public GuiBuilder(int size, String title) {
        this.size = size;
        this.title = title;
    }

    public abstract void init(GuiData data, Player player);

    public GuiData getData() {
        return new GuiData(this.size, this.title);
    }
}
