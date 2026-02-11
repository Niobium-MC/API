package fr.sorway.niobium.api.helpers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationHelper {

    /**
     * Parse une chaîne de caractères en une instance de {@link Location}.
     * Le format attendu est : WORLD;X;Y;Z;YAW;PITCH
     * Exemple : "world:100:64:200:0.0:0.0"
     *
     * @param locationString La chaîne de caractères représentant la location.
     * @return L'objet {@link Location} correspondant à la chaîne donnée.
     * @throws IllegalArgumentException Si la chaîne est mal formatée ou si le monde est introuvable.
     */
    public static Location parseLocation(String locationString) {
        String[] parts = locationString.split(":");
        if (parts.length != 6) {
            throw new IllegalArgumentException("Invalid location format. Expected format: WORLD:X:Y:Z:YAW:PITCH");
        }

        World world = Bukkit.getWorld(parts[0]);
        if (world == null) {
            throw new IllegalArgumentException("World not found: " + parts[0]);
        }

        try {
            double x = Double.parseDouble(parts[1]);
            double y = Double.parseDouble(parts[2]);
            double z = Double.parseDouble(parts[3]);
            float yaw = Float.parseFloat(parts[4]);
            float pitch = Float.parseFloat(parts[5]);

            return new Location(world, x, y, z, yaw, pitch);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format in location string: " + locationString, e);
        }
    }

    /**
     * Convertit une {@link Location} en une chaîne de caractères.
     * Le format est : WORLD:X:Y:Z:YAW:PITCH
     * Exemple : "world:100:64:200:0.0:0.0"
     *
     * @param location La {@link Location} à convertir.
     * @return La chaîne de caractères représentant la location.
     * @throws IllegalArgumentException Si la location ou le monde est null.
     */
    public static String convertLocation(Location location) {
        if (location == null || location.getWorld() == null) {
            throw new IllegalArgumentException("Location or world is null");
        }

        return String.format("%s:%d:%d:%d:%.2f:%.2f",
                location.getWorld().getName(),
                location.getBlockX(),
                location.getBlockY(),
                location.getBlockZ(),
                location.getYaw(),
                location.getPitch());
    }
}
