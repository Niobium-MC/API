package fr.sorway.niobium.api.data.accounts;

import fr.sorway.niobium.api.data.accounts.data.IPlayerRank;

public interface IPlayerAccount {
    boolean isNewPlayer();
    String getIpAddress();
    long getShopMoney();
    long getCreatedAt();
    long getUpdatedAt();
    IPlayerRank getPlayerRank();
}
