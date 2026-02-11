package fr.sorway.niobium.api.includes;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ItemStackSerializer {

    /**
     * Sérialise un inventaire de joueur en deux chaînes Base64 : contenu principal et armure.
     *
     * @param playerInventory inventaire du joueur.
     * @return tableau contenant [contenu, armure].
     */
    public static String[] playerInventoryToBase64(PlayerInventory playerInventory) {
        return new String[] {
                toBase64(playerInventory),
                itemStackArrayToBase64(playerInventory.getArmorContents())
        };
    }

    /**
     * Sérialise un tableau de {@link ItemStack} en Base64.
     *
     * @param items tableau d'objets à sérialiser.
     * @return chaîne Base64.
     */
    public static String itemStackArrayToBase64(ItemStack[] items) {
        return writeObjects(out -> {
            out.writeInt(items.length);
            for (ItemStack item : items) {
                out.writeObject(item);
            }
        });
    }

    /**
     * Sérialise un {@link Inventory} en Base64.
     *
     * @param inventory inventaire à sérialiser.
     * @return chaîne Base64.
     */
    public static String toBase64(Inventory inventory) {
        return writeObjects(out -> {
            out.writeInt(inventory.getSize());
            for (int i = 0; i < inventory.getSize(); i++) {
                out.writeObject(inventory.getItem(i));
            }
        });
    }

    /**
     * Désérialise une chaîne Base64 en {@link Inventory}.
     *
     * @param data chaîne Base64.
     * @return inventaire reconstruit.
     * @throws IOException si une erreur survient.
     */
    public static Inventory fromBase64(String data) throws IOException {
        try (ByteArrayInputStream input = new ByteArrayInputStream(Base64Coder.decodeLines(data));
             BukkitObjectInputStream in = new BukkitObjectInputStream(input)) {

            Inventory inventory = Bukkit.createInventory(null, in.readInt());
            for (int i = 0; i < inventory.getSize(); i++) {
                inventory.setItem(i, (ItemStack) in.readObject());
            }

            return inventory;
        } catch (ClassNotFoundException e) {
            throw new IOException("Type d'objet inconnu lors de la désérialisation.", e);
        }
    }

    /**
     * Désérialise une chaîne Base64 en tableau de {@link ItemStack}.
     *
     * @param data chaîne Base64.
     * @return tableau d'ItemStack.
     * @throws IOException si une erreur survient.
     */
    public static ItemStack[] itemStackArrayFromBase64(String data) throws IOException {
        try (ByteArrayInputStream input = new ByteArrayInputStream(Base64Coder.decodeLines(data));
             BukkitObjectInputStream in = new BukkitObjectInputStream(input)) {

            ItemStack[] items = new ItemStack[in.readInt()];
            for (int i = 0; i < items.length; i++) {
                items[i] = (ItemStack) in.readObject();
            }

            return items;
        } catch (ClassNotFoundException e) {
            throw new IOException("Type d'objet inconnu lors de la désérialisation.", e);
        }
    }

    /**
     * Sérialise un {@link ItemStack} unique en Base64.
     *
     * @param item objet à sérialiser.
     * @return chaîne Base64.
     */
    public static String itemStackToBase64(ItemStack item) {
        return writeObjects(out -> out.writeObject(item));
    }

    /**
     * Désérialise une chaîne Base64 en {@link ItemStack}.
     *
     * @param data chaîne Base64.
     * @return ItemStack désérialisé.
     * @throws IOException si une erreur survient.
     */
    public static ItemStack itemStackFromBase64(String data) throws IOException {
        try (ByteArrayInputStream input = new ByteArrayInputStream(Base64Coder.decodeLines(data));
             BukkitObjectInputStream in = new BukkitObjectInputStream(input)) {

            return (ItemStack) in.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException("Type d'objet inconnu lors de la désérialisation.", e);
        }
    }

    // ------------------------------------------------------

    /**
     * Méthode utilitaire générique pour écrire des objets via BukkitObjectOutputStream.
     *
     * @param writer code à exécuter pour écrire les objets.
     * @return chaîne Base64.
     */
    private static String writeObjects(ThrowingConsumer<BukkitObjectOutputStream> writer) {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream();
             BukkitObjectOutputStream out = new BukkitObjectOutputStream(output)) {

            writer.accept(out);
            return Base64Coder.encodeLines(output.toByteArray());
        } catch (Exception e) {
            throw new IllegalStateException("Erreur de sérialisation.", e);
        }
    }

    @FunctionalInterface
    private interface ThrowingConsumer<T> {
        void accept(T t) throws Exception;
    }
}


