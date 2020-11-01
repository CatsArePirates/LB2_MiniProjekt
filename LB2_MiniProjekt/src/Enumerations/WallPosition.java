package Enumerations;

public enum WallPosition {
    Above, Right, Below, Left;

    private WallPosition opposite;

    static {
        Above.opposite = Below;
        Right.opposite = Left;
        Below.opposite = Above;
        Left.opposite = Right;
    }

    public WallPosition GetOppositePosition() {
        return opposite;
    }
}
