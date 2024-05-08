package fr.sorway.niobium.api.data.accounts.data;

import fr.sorway.niobium.api.data.accounts.AbstractAccountData;
import fr.sorway.niobium.api.data.ranks.IRank;

import java.util.UUID;

public class RankData extends AbstractAccountData {
    private IRank rank;

    public RankData(UUID uuid) {
        this.uuid = uuid;
    }

    public IRank getRank() {
        return rank;
    }

    public void setRank(IRank rank) {
        this.rank = rank;
    }
}
