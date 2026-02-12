package fr.sorway.niobium.api.commands.arguments;

import fr.sorway.niobium.api.NiobiumAPI;
import fr.sorway.niobium.api.commands.Argument;
import fr.sorway.niobium.api.commands.ArgumentResult;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class GameModeArgument implements Argument<GameMode> {

    @Override
    public boolean check(NiobiumAPI api, CommandSender sender, String input) {
        return Arrays.stream(GameMode.values())
                .anyMatch(gameMode -> gameMode.name().equalsIgnoreCase(input.toUpperCase()));
    }

    @Override
    public ArgumentResult<GameMode> parse(NiobiumAPI api, CommandSender sender, String input) {
        return new ArgumentResult<>(GameMode.valueOf(input.toUpperCase()), true);
    }

    @Override
    public List<String> autoComplete(NiobiumAPI api, CommandSender sender, String input) {
        return Arrays.stream(GameMode.values())
                .map(GameMode::name)
                .map(String::toLowerCase)
                .toList();
    }
}
