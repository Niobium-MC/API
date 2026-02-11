package fr.sorway.niobium.api.gui;

import fr.sorway.niobium.api.includes.ItemBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class GuiData {
    private final int size;
    private final String title;
    private final List<GuiItem> items;
    private final Collection<IClickable> clickables;
    private final Map<GuiItem, IClickable> itemClickables = new HashMap<>();
    private Inventory inventory;

    private boolean isPagined = false;
    private int page = 0;

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

    public static int getSize(List<?> items) {
        return Math.min(54, ((items.size() + 8) / 9 + (items.size() % 9 == 0 ? 1 : 0)) * 9);
    }

    public static int getSizeWithBackButton(List<?> items) {
        int size = items.size() + 1;
        return Math.min(54, ((size + 8) / 9 + (size % 9 == 0 ? 1 : 0)) * 9);
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
        for (int i = 0; i < size; i++) {
            final int slot = i;
            if (items.stream().noneMatch(it -> it.slot() == slot)) {
                return slot;
            }
        }
        return -1;
    }

    public int getLastSlot() {
        return size - 1;
    }

    public void addItemClickable(int slot, ItemStack item, IClickable clickable) {
        GuiItem guiItem = new GuiItem(slot, item);
        this.items.add(guiItem);
        this.itemClickables.put(guiItem, clickable);
    }

    public void addClickable(IClickable clickable) {
        this.clickables.add(clickable);
    }

    public void addClickables(IClickable... clickables) {
        this.clickables.addAll(Arrays.asList(clickables));
    }

    public void clickOnInventory(InventoryClickEvent event) {
        final List<IClickable> clickablesCopy = new ArrayList<>(this.clickables);
        clickablesCopy.forEach(clickable -> clickable.click(event));
    }

    public IClickable removeItemOnClick(IClickable customAction) {
        return event -> {
            int clickedSlot = event.getSlot();

            if (customAction != null)
                customAction.click(event);

            if (!isPagined) {
                this.items.removeIf(guiItem -> guiItem.slot() == clickedSlot);
            } else {
                int indexInPage = clickedSlot;
                int globalIndex = page * getItemsPerPage() + indexInPage;

                if (globalIndex >= 0 && globalIndex < items.size()) {
                    GuiItem toRemove = items.get(globalIndex);
                    items.remove(globalIndex);
                    itemClickables.remove(toRemove);
                }
            }

            this.update();
        };
    }

    public void removeItem(InventoryClickEvent event) {
        int clickedSlot = event.getSlot();

        if (!isPagined) {
            items.removeIf(guiItem -> guiItem.slot() == clickedSlot);
        } else {
            int indexInPage = clickedSlot;
            int globalIndex = page * getItemsPerPage() + indexInPage;

            if (globalIndex >= 0 && globalIndex < items.size()) {
                GuiItem toRemove = items.get(globalIndex);
                items.remove(globalIndex);
                itemClickables.remove(toRemove);
            }
        }

        update();
    }


    public Inventory build() {
        this.inventory = Bukkit.createInventory(null, this.size, MiniMessage.miniMessage().deserialize(this.title));
        update();
        return this.inventory;
    }

    public void update() {
        if (this.inventory == null)
            return;
        this.inventory.clear();
        this.clickables.clear();

        if (!isPagined) {
            for (GuiItem item : items) {
                int slot = item.slot();
                if (slot >= 0 && slot < size) {
                    inventory.setItem(slot, item.itemStack());

                    if (itemClickables.containsKey(item)) {
                        ItemClickable ic = new ItemClickable();
                        ic.addClickable(slot, itemClickables.get(item));
                        clickables.add(ic);
                    }
                }
            }
        } else {
            int start = page * getItemsPerPage();
            int end = Math.min(start + getItemsPerPage(), items.size());
            int visualSlot = 0;

            for (int i = start; i < end && visualSlot < getItemsPerPage(); i++) {
                GuiItem item = items.get(i);
                inventory.setItem(visualSlot, item.itemStack());

                if (itemClickables.containsKey(item)) {
                    ItemClickable ic = new ItemClickable();
                    ic.addClickable(visualSlot, itemClickables.get(item));
                    clickables.add(ic);
                }

                visualSlot++;
            }

            // Flèche précédente
            if (hasPreviousPage() && size >= 54) {
                int slot = size - 8;
                ItemStack item = createArrowItem("⬅ Page précédente");
                inventory.setItem(slot, item);

                ItemClickable backClickable = new ItemClickable();
                backClickable.addClickable(slot, e -> previousPage());
                clickables.add(backClickable);
            }

            // Flèche suivante
            if (hasNextPage() && size >= 54) {
                int slot = size - 2;
                ItemStack item = createArrowItem("Page suivante ➡");
                inventory.setItem(slot, item);

                ItemClickable nextClickable = new ItemClickable();
                nextClickable.addClickable(slot, e -> nextPage());
                clickables.add(nextClickable);
            }
        }
    }

    private ItemStack createArrowItem(String name) {
        return new ItemBuilder(Material.ARROW)
                .displayName(Component.text(name).color(TextColor.fromHexString("#fdcb6e")))
                .build();
    }


    public boolean isPagined() {
        return isPagined;
    }

    public void setPagined(boolean pagined) {
        if (size >= 54)
            this.isPagined = pagined;
    }

    public int getPage() {
        return page;
    }

    public void nextPage() {
        if (hasNextPage()) {
            page++;
            update();
        }
    }

    public void previousPage() {
        if (hasPreviousPage()) {
            page--;
            update();
        }
    }

    private int getItemsPerPage() {
        return size - 9;
    }

    private int getMaxPage() {
        long counted = items.stream()
                .filter(item -> isPagined || item.slot() >= 0)
                .count();
        return (int) Math.ceil((double) counted / getItemsPerPage());
    }

    public boolean hasNextPage() {
        return page + 1 < getMaxPage();
    }

    public boolean hasPreviousPage() {
        return page > 0;
    }
}

