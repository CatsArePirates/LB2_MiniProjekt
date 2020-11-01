package Labyrinth;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Labyrinth {
    /* Member/Fields */

    private BufferedImage image = null;
    private LabyrinthField[][] fields = null; // Labyrinth is stored as a matrix
    private int fieldLength;

    /* Constructor */

    public Labyrinth(int fieldsInWidht, int fieldsInHeight, int lengthOfField) {
        this.fieldLength = lengthOfField;
        int width = fieldsInWidht * lengthOfField;
        int height = fieldsInHeight * lengthOfField;
        image = new BufferedImage(width+1, height+1, BufferedImage.TYPE_INT_ARGB);
        fields = new LabyrinthField[fieldsInWidht][fieldsInHeight];

        InitializeFields(lengthOfField);
        DrawImage(5);
    }

    /* Getter/Setter */

    public BufferedImage getImage() {
        return image;
    }

    /* Methods */

    // Initialize field-matrix
    private void InitializeFields(int length) {
        for (int i = 0; i < fields[0].length; i++) {
            for (int j = 0; j < fields.length; j++) {
                fields[j][i] = new LabyrinthField(length);
            }
        }
    }

    // Draw fields into image
    private void DrawImage(int border) {
        Graphics2D g2 = image.createGraphics();
        g2.setStroke(new BasicStroke(border));

        int iPosX = 0; // width
        int iPosY = 0; // height

        for (int i = 0; i < fields[0].length; i++) {
            for (int j = 0; j < fields.length; j++) {
                LabyrinthField field = fields[i][j];

                // Draw field
                g2.setColor(field.GetColor());
                g2.fillRect(iPosX+border, iPosY+border, field.GetLength()-border, field.GetLength()-border);

                g2.setColor(Color.BLACK);

                // Draw walls around field
                if (field.GetWalls().top) {
                    g2.drawLine(iPosX, iPosY, iPosX+fieldLength, iPosY);
                }

                if (field.GetWalls().right) {
                    g2.drawLine(iPosX+fieldLength, iPosY, iPosX+fieldLength, iPosY+fieldLength);
                }

                if (field.GetWalls().below) {
                    g2.drawLine(iPosX, iPosY+fieldLength, iPosX+fieldLength, iPosY+fieldLength);
                }

                if (field.GetWalls().left) {
                    g2.drawLine(iPosX, iPosY, iPosX, iPosY+fieldLength);
                }

                iPosX += fieldLength;
            }
            iPosY += fieldLength;
            iPosX = 0;
        }
        g2.dispose();
    }

    public BufferedImage SolveNext() {
        // TODO: Backtrack the way throught the labyrinth
        return image;
    }

    public BufferedImage SolveAll() {
        // TODO: Finish backtracking
        return image;
    }

    public boolean HasNext() {
        // TODO: test if another step is needed
        return true;
    }
}
