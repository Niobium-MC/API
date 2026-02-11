package fr.sorway.niobium.api.commands.arguments;

import fr.sorway.niobium.api.NiobiumAPI;
import fr.sorway.niobium.api.commands.Argument;
import fr.sorway.niobium.api.commands.ArgumentResult;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class OfflinePlayerArgument implements Argument<OfflinePlayer> {

    @Override
    public boolean check(NiobiumAPI api, CommandSender sender, String input) {
        return true;
    }

    @Override
    public ArgumentResult<OfflinePlayer> parse(NiobiumAPI api, CommandSender sender, String input) {
        return new ArgumentResult<>(Bukkit.getOfflinePlayer(input), true);
    }

    @Override
    public List<String> autoComplete(NiobiumAPI api, CommandSender sender, String input) {
        return Arrays.stream(Bukkit.getOfflinePlayers()).map(OfflinePlayer::getName).filter(Objects::nonNull).filter(s -> s.startsWith(input)).toList();
    }
}
