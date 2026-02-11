package fr.sorway.niobium.api.includes.cuboid;

public enum CuboidDirection {
    North,
    East,
    South,
    West,
    Up,
    Down,
    Horizontal,
    Vertical,
    Both,
    Unknown;

    public CuboidDirection opposite() {
        return switch (this) {
            case North -> South;
            case East -> West;
            case South -> North;
            case West -> East;
            case Horizontal -> Vertical;
            case Vertical -> Horizontal;
            case Up -> Down;
            case Down -> Up;
            case Both -> Both;
            default -> Unknown;
        };
    }
}
