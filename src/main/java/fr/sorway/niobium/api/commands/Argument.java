package fr.sorway.niobium.api.commands;

import fr.sorway.niobium.api.NiobiumAPI;
import org.bukkit.command.CommandSender;

import java.util.List;

public interface Argument<T> {
    boolean check(NiobiumAPI api, CommandSender sender, String input);

    ArgumentResult<T> parse(NiobiumAPI api, CommandSender sender, String input);

    default List<String> autoComplete(NiobiumAPI api, CommandSender sender, String input) {
        return List.of();
    }
}
