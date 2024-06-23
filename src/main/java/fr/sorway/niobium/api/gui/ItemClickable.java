package fr.sorway.niobium.api.gui;

import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.HashMap;
import java.util.Map;

public class ItemClickable implements IClickable {
    private final Map<Integer, IClickable> clickableMap = new HashMap<>();

    public void click(InventoryClickEvent event) {
        if (!this.clickableMap.containsKey(event.getSlot()))
            return;
        clickableMap.get(event.getSlot()).click(event);
    }

    public void addClickable(int slot, IClickable clickable) {
        if (!this.clickableMap.containsKey(slot))
            this.clickableMap.put(slot, clickable);
    }
}
