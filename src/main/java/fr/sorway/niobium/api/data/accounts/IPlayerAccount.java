package fr.sorway.niobium.api.data.accounts;

import fr.sorway.niobium.api.data.accounts.data.IPlayerRank;

import java.net.Inet4Address;

public interface IPlayerAccount {
    boolean isNewPlayer();
    Inet4Address getIpAddress();
    long getShopMoney();
    long getCreatedAt();
    long getUpdatedAt();
    IPlayerRank getPlayerRank();
}
