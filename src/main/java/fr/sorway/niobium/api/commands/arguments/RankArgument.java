package fr.sorway.niobium.api.commands.arguments;

import fr.sorway.niobium.api.NiobiumAPI;
import fr.sorway.niobium.api.commands.Argument;
import fr.sorway.niobium.api.commands.ArgumentResult;
import fr.sorway.niobium.api.data.ranks.IRank;
import fr.sorway.niobium.api.message.MessageColor;
import fr.sorway.niobium.api.message.MessageHelper;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class RankArgument implements Argument<IRank> {

    @Override
    public boolean check(NiobiumAPI api, CommandSender sender, String input) {
        boolean result = api.getRankManager().getRank(input).isPresent();
        if (!result) {
            MessageHelper.sendMessage(sender, Component.text("Ce grade n'existe pas. ")
                    .color(MessageColor.ERROR_COLOR)
                    .append(Component.text("(").color(NamedTextColor.GRAY))
                    .append(Component.text( input.replaceAll("_", " ")).color(NamedTextColor.WHITE))
                    .append(Component.text(")").color(NamedTextColor.GRAY))
            );
        }
        return result;
    }

    @Override
    public ArgumentResult<IRank> parse(NiobiumAPI api, CommandSender sender, String input) {
        return new ArgumentResult<>(api.getRankManager().getRank(input).get(), true);
    }

    @Override
    public List<String> autoComplete(NiobiumAPI api, CommandSender sender, String input) {
        return api.getRankManager().getRanks().stream().map(IRank::name).toList();
    }
}
