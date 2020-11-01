package Labyrinth;

import java.awt.*;

public class LabyrinthField {
    /* Member/Fields */

    private int length;
    private Color color = new Color(255, 255, 255, 255);
    private Walls walls = new Walls();

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

    public Color GetColor() {
        return color;
    }

    public void SetColor(Color color) {
        this.color = color;
    }

    public Walls GetWalls() {
        return walls;
    }

    public void SetWalls(Walls walls) {
        this.walls = walls;
    }

    /* Methods */
}
