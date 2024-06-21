package fr.sorway.niobium.api.data.accounts;

import fr.sorway.niobium.api.data.accounts.data.IPlayerRank;

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
