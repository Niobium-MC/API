package fr.sorway.niobium.api.data.accounts;

import fr.sorway.niobium.api.data.accounts.data.IPlayerRank;

import java.util.UUID;

public interface IPlayerAccount {

    /**
     * Retrieves the unique identifier of the player's account.
     * <p>
     * This ID is typically used to uniquely distinguish the account
     * in the system. It can be used for various purposes such as
     * tracking, querying, and managing player accounts.
     * </p>
     *
     * @return the unique identifier of the player's account as an integer
     */
    int getId();

    /**
     * Retrieves the universally unique identifier (UUID) of the player's account.
     * <p>
     * The UUID is a 128-bit value used to uniquely identify the player's account
     * across different systems and platforms. Unlike a simple numeric ID, a UUID
     * provides a high degree of uniqueness and can be used to ensure that the
     * player's account is globally unique.
     * </p>
     * <p>
     * The UUID is commonly used in scenarios where systems need to integrate or
     * communicate with each other, ensuring consistent identification of the
     * player's account without the risk of collision.
     * </p>
     *
     * @return the UUID of the player's account as a {@link java.util.UUID} object
     */
    UUID getUUID();

    /**
     * Gets the IP address of the player.
     *
     * @return the player's IP address
     */
    String getIpAddress();

    /**
     * Sets the amount of money available in the shop.
     *
     * @param money the new amount of money in the shop
     */
    void setShopMoney(long money);

    /**
     * Gets the amount of shop money the player has.
     *
     * @return the player's shop money
     */
    long getShopMoney();

    /**
     * Gets the account creation time.
     *
     * @return the creation time as LocalDateTime
     */
    long getCreatedAt();

    /**
     * Gets the player's rank information.
     *
     * @return the player's rank
     */
    IPlayerRank getPlayerRank();
}
