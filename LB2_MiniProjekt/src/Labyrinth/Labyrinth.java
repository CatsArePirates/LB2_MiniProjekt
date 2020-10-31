package Labyrinth;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Labyrinth {
    private BufferedImage image = null;
    private LabyrinthField[][] fields = null;

    public Labyrinth(int fieldsInWidht, int fieldsInHeight, int lengthOfField) {
        int width = fieldsInWidht * lengthOfField;
        int height = fieldsInHeight * lengthOfField;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        fields = new LabyrinthField[fieldsInWidht][fieldsInHeight];

        InitializePicture();

        if (lengthOfField != 5) {
            InitializeFields(lengthOfField);
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    private void InitializePicture() {
        // Draw black borders
        int width = image.getWidth();
        int height = image.getHeight();
        int border = 1;

        BufferedImage framedImage = new BufferedImage(width + 2*border, height + 2*border, image.getType());

        Graphics2D g2 = framedImage.createGraphics();
        g2.setColor(Color.black);
        g2.fill(new Rectangle(0, 0, width + 2*border, height + 2*border));
        g2.drawImage(image, border, border, null);
        g2.dispose();

        image = framedImage;
    }

    private void InitializeFields(int length) {
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[0].length; j++) {
                LabyrinthField field = fields[i][j];
                field.length = length;
            }
        }
    }

    public BufferedImage SolveNext() {


        return image;
    }

    public BufferedImage SolveAll() {
        return image;
    }

    private void DrawImage() {
        // Draw field into image
        for (int i = 0; i < fields[0].length; i++) {
            for (int j = 0; j < fields.length; j++) {
                LabyrinthField field = fields[j][i];

                // Draw field into image
                BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
                Graphics2D g2 = image.createGraphics();
                g2.setColor(field.color);
                // TODO: find correct position in image
            }
        }
    }

    public boolean HasNext() {
        // TODO: test if another step is needed
        return true;
    }
}
