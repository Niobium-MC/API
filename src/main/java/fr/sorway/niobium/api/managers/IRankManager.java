package fr.sorway.niobium.api.managers;

import fr.sorway.niobium.api.data.ranks.IRank;

import java.util.Set;

public interface IRankManager {
    Set<IRank> getRanks();
    void createRank(IRank rank);
    void deleteRank(IRank rank);
    void loadRanks();
    IRank getRank(String name);
}
