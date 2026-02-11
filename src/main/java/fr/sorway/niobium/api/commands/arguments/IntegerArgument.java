package fr.sorway.niobium.api.commands.arguments;

import fr.sorway.niobium.api.NiobiumAPI;
import fr.sorway.niobium.api.commands.Argument;
import fr.sorway.niobium.api.commands.ArgumentResult;
import fr.sorway.niobium.api.message.MessageHelper;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;

public class IntegerArgument implements Argument<Integer> {

    @Override
    public boolean check(NiobiumAPI api, CommandSender sender, String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public ArgumentResult<Integer> parse(NiobiumAPI api,CommandSender sender, String input) {
        try {
            return new ArgumentResult<>(Integer.parseInt(input), true);
        } catch (NumberFormatException e) {
            MessageHelper.sendError(sender,
                    Component.text("'")
                            .append(Component.text(input).color(NamedTextColor.WHITE))
                            .append(Component.text("' n'est pas un nombre valide."))
            );
            return new ArgumentResult<>(null, false);
        }
    }
}
