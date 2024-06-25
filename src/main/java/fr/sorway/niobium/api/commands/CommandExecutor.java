package fr.sorway.niobium.api.commands;

import fr.sorway.niobium.api.NiobiumAPI;
import fr.sorway.niobium.api.message.TextFormatter;
import org.bukkit.command.CommandSender;

public record CommandExecutor(NiobiumAPI api, CommandSender sender, String[] args, Command.Executor executor) {

    public void send(CommandSender sender, String message) {
        sender.sendMessage(TextFormatter.translateColors(message));
    }
}
