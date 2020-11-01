package Labyrinth;

import Enumerations.WallPosition;

import java.awt.*;

public class LabyrinthField {
    /* Member/Fields */

    private int length;
    private Walls walls = new Walls();
    private boolean visited = false;

    /* Constructor */

    public LabyrinthField(int length) {
        this.length = length;
    }

    /* Getter/Setter */

    public int GetLength() {
        return length;
    }

    public void SetLength(int length) {
        this.length = length;
    }

    public Walls GetWalls() {
        return walls;
    }

    public void SetWalls(Walls walls) {
        this.walls = walls;
    }

    public boolean GetVisited() {
        return visited;
    }

    public void SetVisited(boolean visited) {
        this.visited = visited;
    }

    /* Methods */

    public void SetSingleWall(WallPosition wallPosition, boolean value) {
        switch (wallPosition) {
            case Above:
                walls.above = value;
                break;
            case Right:
                walls.right = value;
                break;
            case Below:
                walls.below = value;
                break;
            case Left:
                walls.left = value;
                break;
        }
    }
}
