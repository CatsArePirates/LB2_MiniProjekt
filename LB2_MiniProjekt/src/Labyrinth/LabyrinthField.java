package Labyrinth;

import java.awt.*;

public class LabyrinthField {
    /* Member/Fields */

    private int length;
    private Color color = Color.BLACK;
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

    public Color GetColor() {
        return color;
    }

    public void SetColor(Color color) {
        this.color = color;
    }

    public boolean GetVisited() {
        return visited;
    }

    public void SetVisited(boolean visited) {
        this.visited = visited;
    }

    /* Methods */

}
