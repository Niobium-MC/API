package fr.sorway.niobium.api.includes;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import fr.sorway.niobium.api.message.MessageParser;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.*;

public class ItemBuilder {
    private final ItemStack itemStack;
    private final ItemMeta itemMeta;

    private Component displayName;
    private List<Component> lore = new ArrayList<>();
    private final Map<Enchantment, Integer> enchantments = new HashMap<>();
    private final List<ItemFlag> flags = new ArrayList<>();
    private boolean unbreakable = false;
    private int damage = 0;
    private String customSkullTexture;

    public ItemBuilder(Material material) {
        this.itemStack = new ItemStack(material);
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(ItemStack item) {
        this.itemStack = item;
        this.itemMeta = itemStack.getItemMeta();
        this.displayName = itemMeta.hasDisplayName() ? itemMeta.displayName() : null;
        this.lore = itemMeta.lore() != null ? new ArrayList<>(itemMeta.lore()) : new ArrayList<>();
        this.unbreakable = itemMeta.isUnbreakable();
        this.flags.addAll(itemMeta.getItemFlags());

        if (itemMeta instanceof Damageable damageable)
            this.damage = damageable.getDamage();

        this.enchantments.putAll(itemMeta.getEnchants());
    }

    public ItemBuilder amount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder displayName(String displayName) {
        this.displayName = MessageParser.parse(displayName);
        return this;
    }

    public ItemBuilder displayName(Component displayName) {
        this.displayName = displayName;
        return this;
    }

    public ItemBuilder lore(String... lore) {
        Arrays.stream(lore).forEach(line -> this.lore.add(MessageParser.parse(line)));
        return this;
    }

    public ItemBuilder lore(List<Component> lore) {
        this.lore = lore;
        return this;
    }

    public ItemBuilder lore(Component... lore) {
        this.lore.addAll(List.of(lore));
        return this;
    }

    public ItemBuilder addLoreLine(String line) {
        this.lore.add(MessageParser.parse(line));
        return this;
    }

    public ItemBuilder addLoreLine(Component line) {
        this.lore.add(line);
        return this;
    }

    public ItemBuilder clearLore() {
        this.lore.clear();
        return this;
    }

    public ItemBuilder enchant(Enchantment enchantment, int level) {
        enchantments.put(enchantment, level);
        return this;
    }

    public ItemBuilder enchant(Map<Enchantment, Integer> enchants) {
        enchantments.putAll(enchants);
        return this;
    }

    public ItemBuilder flag(ItemFlag flag) {
        this.flags.add(flag);
        return this;
    }

    public ItemBuilder flag(ItemFlag... flags) {
        this.flags.addAll(Arrays.asList(flags));
        return this;
    }

    public ItemBuilder unbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
        return this;
    }

    public ItemBuilder glow() {
        enchant(Enchantment.DURABILITY, 1);
        flag(ItemFlag.HIDE_ENCHANTS);
        return this;
    }

    public ItemBuilder damage(int damage) {
        this.damage = damage;
        return this;
    }

    public ItemBuilder customSkull(String base64Texture) {
        this.customSkullTexture = base64Texture;
        return this;
    }

    public ItemBuilder hideAllAttributes() {
        this.flag(ItemFlag.values());
        return this;
    }

    public ItemBuilder customModelData(int customModelData) {
        this.itemMeta.setCustomModelData(customModelData);
        return this;
    }

    public ItemStack build() {
        if (displayName != null) {
            if (displayName.decoration(TextDecoration.ITALIC) == TextDecoration.State.NOT_SET)
                displayName = displayName.decoration(TextDecoration.ITALIC, false);
            itemMeta.displayName(displayName);
        }

        if (!lore.isEmpty()) {
            List<Component> loreProcessed = lore.stream()
                    .map(line -> line.decoration(TextDecoration.ITALIC) == TextDecoration.State.NOT_SET
                            ? line.decoration(TextDecoration.ITALIC, false)
                            : line
                    )
                    .toList();
            itemMeta.lore(loreProcessed);
        }

        itemMeta.setUnbreakable(unbreakable);
        flags.forEach(itemMeta::addItemFlags);
        enchantments.forEach((enchantment, lvl) -> itemMeta.addEnchant(enchantment, lvl, true));
        if (itemMeta instanceof Damageable damageable)
            damageable.setDamage(damage);

        itemStack.setItemMeta(itemMeta);

        if (itemStack.getType() == Material.PLAYER_HEAD && customSkullTexture != null) {
            this.itemStack.editMeta(SkullMeta.class, (skullMeta) -> {
                final UUID uuid = UUID.randomUUID();
                final PlayerProfile playerProfile = Bukkit.createProfile(uuid, uuid.toString().substring(0, 16));

                playerProfile.setProperty(new ProfileProperty("textures", customSkullTexture));
                skullMeta.setPlayerProfile(playerProfile);
            });
        }

        return itemStack;
    }
}

