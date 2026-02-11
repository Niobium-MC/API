package fr.sorway.niobium.api.includes;

import fr.sorway.niobium.api.NiobiumAPI;
import fr.sorway.niobium.api.includes.cuboid.Cuboid;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.List;
import java.util.logging.Level;

public class ChunkLoader {
    private final NiobiumAPI api;
    private final World world;

    public ChunkLoader(NiobiumAPI api, World world) {
        this.api = api;
        this.world = world;
    }

    /**
     * Charge tous les chunks dans un rayon autour d'une position donnée.
     *
     * @param centerX La coordonnée X de la position centrale.
     * @param centerZ La coordonnée Z de la position centrale.
     * @param radius Le rayon dans lequel charger les chunks.
     */
    public void loadChunksAroundPosition(int centerX, int centerZ, int radius) {
        for (int x = centerX - radius; x <= centerX + radius; x += 16) {
            for (int z = centerZ - radius; z <= centerZ + radius; z += 16) {
                loadChunk(x, z);
            }
        }
        api.log(Level.INFO, "Chunks chargés dans un rayon de " + radius + " autour de (" + centerX + ", " + centerZ + ").");
    }

    /**
     * Charge tous les chunks d'une zone rectangulaire définie par deux coordonnées.
     *
     * @param x1 La coordonnée X du coin supérieur gauche de la zone.
     * @param z1 La coordonnée Z du coin supérieur gauche de la zone.
     * @param x2 La coordonnée X du coin inférieur droit de la zone.
     * @param z2 La coordonnée Z du coin inférieur droit de la zone.
     */
    public void loadChunksInArea(int x1, int z1, int x2, int z2) {
        for (int x = x1; x <= x2; x += 16) {
            for (int z = z1; z <= z2; z += 16) {
                loadChunk(x, z);
            }
        }
        api.log(Level.INFO, "Chunks chargés dans la zone (" + x1 + ", " + z1 + ") -> (" + x2 + ", " + z2 + ").");
    }

    /**
     * Charge tous les chunks spécifiés dans une liste.
     *
     * @param chunks La liste de chunks à charger.
     */
    public void loadChunksFromList(List<Chunk> chunks) {
        chunks.forEach(this::loadChunk);
        api.log(Level.INFO, "Tous les chunks spécifiés ont été chargés.");
    }

    /**
     * Charge un chunk spécifique aux coordonnées données.
     *
     * @param x La coordonnée X du chunk à charger.
     * @param z La coordonnée Z du chunk à charger.
     */
    public void loadChunk(int x, int z) {
        loadChunk(world.getChunkAt(x / 16, z / 16));
    }

    /**
     * Charge un chunk spécifique.
     *
     * @param chunk Le chunk à charger.
     */
    public void loadChunk(Chunk chunk) {
        if (!chunk.isLoaded())
            world.loadChunk(chunk);
    }

    /**
     * Charge tous les chunks autour d'un bloc spécifique (en utilisant un rayon).
     *
     * @param block Le bloc autour duquel charger les chunks.
     * @param radius Le rayon autour du bloc.
     */
    public void loadChunksAroundBlock(Block block, int radius) {
        loadChunksAroundPosition(block.getX(), block.getZ(), radius);
    }

    /**
     * Charge tous les chunks d'une ligne de chunks entre deux points définis par des coordonnées.
     *
     * @param x1 La coordonnée X du point de départ.
     * @param z1 La coordonnée Z du point de départ.
     * @param x2 La coordonnée X du point d'arrivée.
     * @param z2 La coordonnée Z du point d'arrivée.
     */
    public void loadChunksBetweenPoints(int x1, int z1, int x2, int z2) {
        int stepX = x1 < x2 ? 16 : -16;
        int stepZ = z1 < z2 ? 16 : -16;
        for (int x = x1; x != x2; x += stepX) {
            for (int z = z1; z != z2; z += stepZ) {
                loadChunk(x, z);
            }
        }

        api.log(Level.INFO, "Chunks chargés entre les points (" + x1 + ", " + z1 + ") et (" + x2 + ", " + z2 + ").");
    }

    /**
     * Charge un chunk spécifique à partir de ses coordonnées de bloc.
     *
     * @param block Le bloc à partir duquel charger le chunk.
     */
    public void loadChunkFromBlock(Block block) {
        loadChunk(block.getX() / 16, block.getZ() / 16);
    }

    /**
     * Charge tous les chunks couvrant un cuboïde donné.
     *
     * Cette méthode utilise le cuboïde pour récupérer la liste des chunks qui couvrent toute
     * la zone délimitée par les coordonnées du cuboïde. Ensuite, elle charge chaque chunk
     * dans le monde, afin qu'ils soient prêts à être utilisés.
     *
     * @param cuboid Le cuboïde qui définit la zone pour laquelle les chunks seront chargés.
     *                Un cuboïde est une zone rectangulaire dans le monde, définie par deux coins opposés.
     *                Les chunks couvrant cette zone seront chargés dans le monde.
     *
     * @see Cuboid#getChunks() Pour récupérer la liste des chunks couvrant le cuboïde.
     * @see ChunkLoader#loadChunk(Chunk) Pour charger un chunk spécifique.
     */
    public void loadChunksFromCuboid(Cuboid cuboid) {
        cuboid.getChunks().forEach(this::loadChunk);
        api.log(Level.INFO, "Tous les chunks du cuboïde ont été chargés.");
    }
}

