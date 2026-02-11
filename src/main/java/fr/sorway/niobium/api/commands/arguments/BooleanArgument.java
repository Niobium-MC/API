package fr.sorway.niobium.api.commands.arguments;

import fr.sorway.niobium.api.NiobiumAPI;
import fr.sorway.niobium.api.commands.Argument;
import fr.sorway.niobium.api.commands.ArgumentResult;
import fr.sorway.niobium.api.message.MessageHelper;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class BooleanArgument implements Argument<Boolean> {

    @Override
    public boolean check(NiobiumAPI api, CommandSender sender, String input) {
        return input != null && (input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false"));
    }

    @Override
    public ArgumentResult<Boolean> parse(NiobiumAPI api, CommandSender sender, String input) {
        try {
            return new ArgumentResult<>(Boolean.parseBoolean(input), true);
        } catch (NumberFormatException e) {
            MessageHelper.sendError(sender,
                    Component.text("'")
                            .append(Component.text(input).color(NamedTextColor.WHITE))
                            .append(Component.text("' n'est pas un boolean valide."))
            );
            return new ArgumentResult<>(null, false);
        }
    }

    @Override
    public List<String> autoComplete(NiobiumAPI api, CommandSender sender, String input) {
        return List.of("true", "false");
    }
}
