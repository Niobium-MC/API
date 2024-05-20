package fr.sorway.niobium.api.data.accounts;

import fr.sorway.niobium.api.data.accounts.data.IPlayerRank;

public interface IPlayerAccount {

    /**
     * Checks if the player is a new player.
     *
     * @return true if the player is new, false otherwise
     */
    boolean isNewPlayer();

    /**
     * Gets the IP address of the player.
     *
     * @return the player's IP address
     */
    String getIpAddress();

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
     * Gets the last update time of the account.
     *
     * @return the last update time as LocalDateTime
     */
    long getUpdatedAt();

    /**
     * Gets the player's rank information.
     *
     * @return the player's rank
     */
    IPlayerRank getPlayerRank();
}
