package fr.sorway.niobium.api.commands;

import fr.sorway.niobium.api.NiobiumAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public record CommandExecutor(NiobiumAPI api, CommandSender sender, String[] args) {

    public void send(CommandSender sender, String message) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
