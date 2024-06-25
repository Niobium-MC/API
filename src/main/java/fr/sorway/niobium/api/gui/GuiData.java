package fr.sorway.niobium.api.gui;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class GuiData {
    private final int size;
    private final String title;
    private final Collection<GuiItem> items;
    private final Collection<IClickable> clickables;
    private Inventory inventory;

    public GuiData(int size, String title) {
        this.size = size;
        this.title = title;
        this.items = new ArrayList<>();
        this.clickables = new ArrayList<>();
    }

    public static void open(IGuiManager guiManager, GuiBuilder builder, Player player) {
        final GuiData guiData = builder.getData();
        builder.init(guiData, player);
        player.openInventory(guiData.build());
        guiManager.getGuis().put(player.getUniqueId(), guiData);
    }

    public void addItem(GuiItem guiItem) {
        this.items.add(guiItem);
    }

    public void addItems(GuiItem... guiItems) {
        this.items.addAll(Arrays.asList(guiItems));
    }

    public void addItem(int slot, ItemStack itemStack) {
        this.items.add(new GuiItem(slot, itemStack));
    }

    public void addItem(ItemStack itemStack) {
        addItem(nextItem(), itemStack);
    }

    public void addItems(ItemStack... itemStacks) {
        for (ItemStack itemStack : itemStacks)
            addItem(nextItem(), itemStack);
    }

    public void removeItem(int slot) {
        Optional<GuiItem> itemFound = this.items.stream().filter(inventoryItem -> (inventoryItem.slot() == slot)).findFirst();
        Objects.requireNonNull(this.items);
        itemFound.ifPresent(this.items::remove);
        update();
    }

    public void replaceSlot(int slot, ItemStack item) {
        Optional<GuiItem> itemFound = this.items.stream().filter(inventoryItem1 -> (inventoryItem1.slot() == slot)).findFirst();
        Objects.requireNonNull(this.items);
        itemFound.ifPresent(this.items::remove);
        this.items.add(new GuiItem(slot, item));
        update();
    }

    public int nextItem() {
        boolean itemExist = false;
        int i = 0;
        while (!itemExist) {
            if (i > this.size)
                return -1;
            int finalI = i;
            if (this.items.stream().anyMatch(inventoryItem -> (inventoryItem.slot() == finalI))) {
                i++;
                continue;
            }
            itemExist = true;
        }
        return i;
    }

    public void addItemClickable(int slot, ItemStack item, IClickable clickable) {
        this.items.add(new GuiItem(slot, item));
        ItemClickable itemClickable = new ItemClickable();
        itemClickable.addClickable(slot, clickable);
        this.clickables.add(itemClickable);
    }

    public void addClickable(IClickable clickable) {
        this.clickables.add(clickable);
    }

    public void addClickables(IClickable... clickables) {
        this.clickables.addAll(Arrays.asList(clickables));
    }

    public void clickOnInventory(InventoryClickEvent event) {
        this.clickables.forEach(clickable -> clickable.click(event));
    }

    public Inventory build() {
        this.inventory = Bukkit.createInventory(null, this.size, MiniMessage.miniMessage().deserialize(this.title));
        this.items.forEach(inventoryItem -> this.inventory.setItem(inventoryItem.slot(), inventoryItem.itemStack()));
        return this.inventory;
    }

    public void update() {
        if (this.inventory != null)
            this.items.forEach(inventoryItem -> this.inventory.setItem(inventoryItem.slot(), inventoryItem.itemStack()));
    }
}
