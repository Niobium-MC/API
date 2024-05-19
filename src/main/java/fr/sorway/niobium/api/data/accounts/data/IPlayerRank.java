package fr.sorway.niobium.api.data.accounts.data;

import fr.sorway.niobium.api.data.ranks.IRank;

import java.util.Set;

public interface IPlayerRank {
    IRank getActiveRank();
    Set<IRank> getRanks();
}
