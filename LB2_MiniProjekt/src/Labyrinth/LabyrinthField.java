package Labyrinth;

import java.awt.*;

public class LabyrinthField {
    // A field is by standard 5x5 pixel
    int length = 5;
    // Standard color is white
    Color color = new Color(255, 255, 255, 255);

    public LabyrinthField() { }
    public LabyrinthField(int length) {
        this.length = length;
    }

    public Color getColor() {
        return color;
    }

    public void SetColor(Color color) {
        this.color = color;
    }
}
