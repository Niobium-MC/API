package fr.sorway.niobium.api.commands;

import fr.sorway.niobium.api.commands.arguments.*;

import java.util.function.Supplier;

public enum ArgumentType {
    STRING(StringArgument::new),
    INTEGER(IntegerArgument::new),
    DOUBLE(DoubleArgument::new),
    LONG(LongArgument::new),
    BOOLEAN(BooleanArgument::new),
    PLAYER(PlayerArgument::new),
    OFFLINE_PLAYER(OfflinePlayerArgument::new),
    GAMEMODE(GameModeArgument::new),
    RANK(RankArgument::new);

    private final Supplier<Argument<?>> supplier;

    ArgumentType(Supplier<Argument<?>> supplier) {
        this.supplier = supplier;
    }

    public Argument<?> create() {
        return supplier.get();
    }
}
