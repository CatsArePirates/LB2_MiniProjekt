package Labyrinth;

import Enumerations.WallPosition;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Labyrinth {
    /* Member/Fields */

    private BufferedImage image = null;
    private LabyrinthField[][] fields = null; // Labyrinth is stored as a matrix
    private int fieldLength;
    private ArrayList<LabyrinthField> stack = new ArrayList<>();

    private WallPosition wallPosition;

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
                if (field.GetWalls().above) {
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

    // Backtrack the way through the labyrinth
    public BufferedImage SolveNext() {
        LabyrinthField currentField = fields[0][0];
        currentField.SetVisited(true);
        currentField.SetColor(Color.BLUE); // Change the color of the field
        stack.add(currentField);

        while (!stack.isEmpty()) {
            currentField = stack.remove(stack.size()-1);
            LabyrinthField neighbour = GetRandomUnvisitedNeighbour(currentField);
            currentField.SetColor(Color.WHITE); // Change the color of the field

            if (neighbour != null) {
                stack.add(currentField);
                RemoveWallBetween(currentField, neighbour);
                neighbour.SetVisited(true);
                neighbour.SetColor(Color.BLUE); // Change the color of the field
                stack.add(neighbour);
            }
        }

        DrawImage(5);
        return image;
    }

    // Return next unvisited neighbour
    private LabyrinthField GetRandomUnvisitedNeighbour(LabyrinthField currField) {
        ArrayList<LabyrinthField> neighbours = new ArrayList<LabyrinthField>();
        LabyrinthField neighbour;
        int [] posArr = FindElement(currField);

        if (posArr != null) {
            int width = posArr[0];
            int height = posArr[1];

            if (height-1 >= 0) {
                // Above
                neighbour = fields[width][height-1];

                if (!neighbour.GetVisited()) {
                    neighbours.add(neighbour);
                    //wallPosition = WallPosition.Above;
                }
            }

            if (width+1 <= fields.length-1) {
                // Right
                neighbour = fields[width+1][height];

                if (!neighbour.GetVisited()) {
                    neighbours.add(neighbour);
                    //wallPosition = WallPosition.Right;
                }
            }

            if (height+1 <= fields[0].length-1) {
                // Below
                neighbour = fields[width][height+1];

                if (!neighbour.GetVisited()) {
                    neighbours.add(neighbour);
                    //wallPosition = WallPosition.Below;
                }
            }

            if (width-1 >= 0) {
                // Left
                neighbour = fields[width-1][height];

                if (!neighbour.GetVisited()) {
                    neighbours.add(neighbour);
                    //wallPosition = WallPosition.Left;
                }
            }
        }

        Random rand = new Random();

        if (neighbours.size() == 0) {
            return null;
        } else {
            int r = rand.nextInt(neighbours.size());
            // TODO: set WallPosition
            return neighbours.get(r);
        }
    }

    // Remove wall between currentField and neighbour
    private void RemoveWallBetween(LabyrinthField currField, LabyrinthField neighbour) {
        currField.SetSingleWall(wallPosition, false);
        neighbour.SetSingleWall(wallPosition.GetOppositePosition(), false);
    }

    private int[] FindElement(LabyrinthField currField) {
        for (int i = 0; i < fields[0].length; i++) {
            for (int j = 0; j < fields.length; j++) {
                LabyrinthField field = fields[i][j];
                if (field.equals(currField)) {
                    return new int[] { i, j };
                }
            }
        }

        return null;
    }

    public boolean HasNext() {
        // TODO: test if another step is needed
        return true;
    }
}
