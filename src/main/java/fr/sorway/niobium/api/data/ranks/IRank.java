package fr.sorway.niobium.api.data.ranks;

import org.bukkit.ChatColor;

import java.util.List;

public interface IRank {
    int getId();
    String getName();
    String getPrefix();
    String getSuffix();
    ChatColor getColor();
    List<String> getPermissions();
}
