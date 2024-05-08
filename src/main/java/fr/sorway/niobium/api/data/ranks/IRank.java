package fr.sorway.niobium.api.data.ranks;

import java.util.List;

public interface IRank {
    String getName();
    String getPrefix();
    String getColor();
    List<String> getPermissions();
}
