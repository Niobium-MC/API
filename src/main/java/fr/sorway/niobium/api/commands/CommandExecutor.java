package fr.sorway.niobium.api.commands;

import fr.sorway.niobium.api.NiobiumAPI;
import org.bukkit.command.CommandSender;

import java.util.List;

public record CommandExecutor(NiobiumAPI api, CommandSender sender, String[] args, Command.Executor executor, List<ArgumentResult<?>> parsedArguments) {

    @SuppressWarnings("unchecked")
    public <T> T getArgument(int index) {
        if (index < 0 || index >= this.parsedArguments.size())
            return null;

        Object value = this.parsedArguments.get(index);

        if (value == null)
            return null;

        return (T) ((ArgumentResult<?>) value).get();
    }
}
