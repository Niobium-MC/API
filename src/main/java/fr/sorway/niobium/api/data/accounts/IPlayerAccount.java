package fr.sorway.niobium.api.data.accounts;

import fr.sorway.niobium.api.data.accounts.data.IPlayerRank;

public interface IPlayerAccount {
    boolean isNewPlayer();
    long getCreatedAt();
    long getUpdatedAt();
    IPlayerRank getPlayerRank();
}
