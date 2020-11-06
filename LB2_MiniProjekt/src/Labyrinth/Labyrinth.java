package Labyrinth;

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

    private LabyrinthField currentField = null;

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

                if (field != null) {
                    // Draw field
                    g2.setColor(field.GetColor());

                    g2.fillRect(iPosX, iPosY, field.GetLength(), field.GetLength());
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
        if (currentField == null) {
            currentField = fields[0][0];
            currentField.SetVisited(true);
            currentField.SetColor(Color.BLUE); // Change the color of the field
            stack.add(currentField);
        } else {
            currentField = stack.remove(stack.size()-1);
            currentField.SetColor(Color.BLUE); // Change the color of the field

            ArrayList<LabyrinthField> neighbourFields = GetUnvisitedNeighbours(currentField);
            ArrayList<LabyrinthField> tmpList = new ArrayList<LabyrinthField>();
            for (LabyrinthField f : neighbourFields) {
                int count = GetSurroundingVisitedFields(f).size();

                if (count > 2) {
                    tmpList.add(f);
                }
            }

            for (LabyrinthField f : tmpList) {
                neighbourFields.remove(f);
            }

            if (neighbourFields.size() > 0) {
                LabyrinthField neighbour = neighbourFields.get(new Random().nextInt(neighbourFields.size()));

                stack.add(currentField);
                neighbour.SetVisited(true);
                neighbour.SetColor(Color.BLUE); // Change the color of the field
                stack.add(neighbour);
            } else {
                currentField.SetColor(Color.WHITE);
            }
        }

        DrawImage(5);
        return image;
    }

    private ArrayList<LabyrinthField> GetUnvisitedNeighbours(LabyrinthField currField) {
        ArrayList<LabyrinthField> neighbours = new ArrayList<>();
        LabyrinthField neighbour;
        int [] posArr = FindElement(currField);

        if (posArr != null) {
            int width = posArr[0];
            int height = posArr[1];

            if (height-1 >= 0) {
                // Above
                neighbour = fields[width][height-1];

                if (neighbour != null && !neighbour.GetVisited()) {
                    neighbours.add(neighbour);
                }
            }

            if (width+1 <= fields.length-1) {
                // Right
                neighbour = fields[width+1][height];

                if (neighbour != null && !neighbour.GetVisited()) {
                    neighbours.add(neighbour);
                }
            }

            if (height+1 <= fields[0].length-1) {
                // Below
                neighbour = fields[width][height+1];

                if (neighbour != null && !neighbour.GetVisited()) {
                    neighbours.add(neighbour);
                }
            }

            if (width-1 >= 0) {
                // Left
                neighbour = fields[width-1][height];

                if (neighbour != null && !neighbour.GetVisited()) {
                    neighbours.add(neighbour);
                }
            }
        }

        return neighbours;
    }

    private ArrayList<LabyrinthField> GetSurroundingVisitedFields(LabyrinthField field) {
        ArrayList<LabyrinthField> neighbours = new ArrayList<>();
        LabyrinthField neighbour;
        int[] posXY = FindElement(field);

        if (posXY != null) {
            int width = posXY[0];
            int height = posXY[1];

            if (height-1 >= 0) {
                // Above
                neighbour = fields[width][height-1];

                if (neighbour != null && neighbour.GetVisited()) {
                    neighbours.add(neighbour);
                }
            }

            if (width+1 < fields.length) {
                // Right
                neighbour = fields[width+1][height];

                if (neighbour != null && neighbour.GetVisited()) {
                    neighbours.add(neighbour);
                }
            }

            if (height+1 < fields[0].length) {
                // Below
                neighbour = fields[width][height+1];

                if (neighbour != null && neighbour.GetVisited()) {
                    neighbours.add(neighbour);
                }
            }

            if (width-1 >= 0) {
                // Left
                neighbour = fields[width-1][height];

                if (neighbour != null && neighbour.GetVisited()) {
                    neighbours.add(neighbour);
                }
            }

            if (width+1 < fields.length && height-1 >= 0) {
                // Above-Right
                neighbour = fields[width + 1][height - 1];

                if (neighbour != null && neighbour.GetVisited()) {
                    neighbours.add(neighbour);
                }
            }

            if (width-1 > 0 && height-1 >= 0) {
                // Above-Left
                neighbour = fields[width - 1][height - 1];

                if (neighbour != null && neighbour.GetVisited()) {
                    neighbours.add(neighbour);
                }
            }

            if (width+1 < fields.length && height+1 < fields[0].length) {
                // Below-Right
                neighbour = fields[width + 1][height + 1];

                if (neighbour != null && neighbour.GetVisited()) {
                    neighbours.add(neighbour);
                }
            }

            if (width-1 >= 0 && height+1 < fields[0].length) {
                // Below-Left
                neighbour = fields[width - 1][height + 1];

                if (neighbour != null && neighbour.GetVisited()) {
                    neighbours.add(neighbour);
                }
            }
        }

        return neighbours;
    }

    private int[] FindElement(LabyrinthField currField) {
        for (int i = 0; i < fields[0].length; i++) {
            for (int j = 0; j < fields.length; j++) {
                LabyrinthField field = fields[i][j];
                if (field != null && field.equals(currField)) {
                    return new int[] { i, j };
                }
            }
        }

        return null;
    }

    public boolean HasNext() {
        if (!stack.isEmpty() || currentField == null) {
            return true;
        }
        return false;
    }
}
