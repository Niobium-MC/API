package fr.sorway.niobium.api.commands.arguments;

import fr.sorway.niobium.api.NiobiumAPI;
import fr.sorway.niobium.api.commands.Argument;
import fr.sorway.niobium.api.commands.ArgumentResult;
import fr.sorway.niobium.api.message.MessageHelper;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class PlayerArgument implements Argument<Player> {

    @Override
    public boolean check(NiobiumAPI api, CommandSender sender, String input) {
        final boolean result = Bukkit.getPlayer(input) != null;
        if (!result) {
            MessageHelper.sendError(sender,
                    Component.text("Le joueur '")
                            .append(Component.text(input).color(NamedTextColor.WHITE))
                            .append(Component.text("' est introuvable."))
            );
        }
        return result;
    }

    @Override
    public ArgumentResult<Player> parse(NiobiumAPI api, CommandSender sender, String input) {
        final Player player = Bukkit.getPlayer(input);
        if (player == null) {
            MessageHelper.sendError(sender,
                    Component.text("Le joueur '")
                            .append(Component.text(input).color(NamedTextColor.WHITE))
                            .append(Component.text("' est introuvable."))
            );
            return new ArgumentResult<>(null, false);
        }

        return new ArgumentResult<>(player, true);
    }

    @Override
    public List<String> autoComplete(NiobiumAPI api, CommandSender sender, String input) {
        return Bukkit.getOnlinePlayers().stream().map(Player::getName).filter(s -> s.startsWith(input)).toList();
    }
}
