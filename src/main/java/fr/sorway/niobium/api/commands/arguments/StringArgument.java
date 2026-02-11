package fr.sorway.niobium.api.commands.arguments;

import fr.sorway.niobium.api.NiobiumAPI;
import fr.sorway.niobium.api.commands.Argument;
import fr.sorway.niobium.api.commands.ArgumentResult;
import org.bukkit.command.CommandSender;

public class StringArgument implements Argument<String> {

    @Override
    public boolean check(NiobiumAPI api, CommandSender sender, String input) {
        return input != null && !input.trim().isEmpty();
    }

    @Override
    public ArgumentResult<String> parse(NiobiumAPI api, CommandSender sender, String input) {
        return new ArgumentResult<>(input, true);
    }
}
