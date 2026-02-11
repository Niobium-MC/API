package fr.sorway.niobium.api.includes;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryTools {

    /**
     * Déplace un item depuis un slot source vers le premier slot disponible (vide) dans l'inventaire.
     *
     * @param inventory l'inventaire sur lequel opérer
     * @param sourceSlot l'indice du slot source dont on veut déplacer l'item
     * @return {@code true} si le déplacement a réussi, {@code false} sinon (slot source vide, pas de slot libre, slot invalide)
     * @throws IllegalArgumentException si {@code sourceSlot} est hors des limites de l'inventaire
     */
    public static boolean moveItemToAvailableSlot(Inventory inventory, int sourceSlot) {
        int size = inventory.getSize();

        if (sourceSlot < 0 || sourceSlot >= size)
            throw new IllegalArgumentException("Slot source invalide : " + sourceSlot);
        ItemStack itemToMove = inventory.getItem(sourceSlot);

        if (itemToMove == null || itemToMove.isEmpty())
            return false;

        for (int i = 0; i < size; i++) {
            if (i == sourceSlot)
                continue;

            ItemStack current = inventory.getItem(i);
            if (current == null || current.isEmpty()) {
                inventory.setItem(i, itemToMove);
                inventory.setItem(sourceSlot, new ItemStack(Material.AIR));
                return true;
            }
        }

        return false;
    }

    /**
     * Vérifie si le joueur a un ItemStack identique à celui donné.
     * Compare le Material, le ItemMeta (nom, lore, etc.) et la quantité.
     * @param player Le joueur à vérifier.
     * @param target L'ItemStack recherché.
     * @return true si le joueur a un ItemStack identique, false sinon.
     */
    public static boolean hasExactItemStack(Player player, ItemStack target) {
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.equals(target)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Vérifie si le joueur a au moins un item du type spécifié.
     * @param player Le joueur à vérifier.
     * @param material Le type de l'item (Material).
     * @return true si le joueur a cet item, false sinon.
     */
    public static boolean hasItem(Player player, Material material) {
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.getType() == material) {
                return true;
            }
        }
        return false;
    }

    /**
     * Vérifie si le joueur a au moins la quantité spécifiée d'un item.
     * @param player Le joueur à vérifier.
     * @param material Le type de l'item.
     * @param amount Quantité minimum requise.
     * @return true si le joueur a au moins cette quantité, false sinon.
     */
    public static boolean hasItemAmount(Player player, Material material, int amount) {
        int count = 0;
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.getType() == material) {
                count += item.getAmount();
                if (count >= amount) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Compare deux ItemStack pour vérifier s'ils sont identiques.
     * Compare le Material, ItemMeta (nom, lore, enchantements...) et la quantité.
     * @param first Premier ItemStack
     * @param second Deuxième ItemStack
     * @return true si les ItemStack sont identiques, false sinon
     */
    public static boolean areItemStacksIdentical(ItemStack first, ItemStack second) {
        if (first == null || second == null) {
            return false;
        }

        // Vérifier le type
        if (first.getType() != second.getType()) {
            return false;
        }

        // Vérifier la quantité
        if (first.getAmount() != second.getAmount()) {
            return false;
        }

        // Vérifier le ItemMeta
        ItemMeta firstMeta = first.getItemMeta();
        ItemMeta secondMeta = second.getItemMeta();

        if (firstMeta == null && secondMeta == null) {
            return true; // Les deux n'ont pas de meta
        }

        if (firstMeta == null || secondMeta == null) {
            return false; // L'un a une meta et pas l'autre
        }

        // Comparer les meta
        return firstMeta.equals(secondMeta);
    }
}
