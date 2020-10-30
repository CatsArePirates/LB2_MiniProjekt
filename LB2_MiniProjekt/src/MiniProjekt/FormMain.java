package MiniProjekt;

import Backtracker.Backtracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class FormMain extends JFrame {
    private JPanel mainPanel;
    private JButton btnUploadLabyrinth; // Click button to upload image
    private JPanel panelImage;
    private JLabel labelImage; // Contains the image of the labyrinth
    private JButton btnStart; // Click button to start the solving-process

    private Labyrinth labyrinth = null;
    private Backtracker<Labyrinth> lBacktracker = new Backtracker<Labyrinth>();

    public FormMain(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // The application exits when the form is closed
        this.setContentPane(mainPanel);
        this.pack();

        // Actionlistener for button btnUploadLabyrinth
        btnUploadLabyrinth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open folder to upload a file
                ReadFile();
            }
        });

        // Actionlistener for button btnStart
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Solve labyrinth
                SolveLabyrinth();
            }
        });
    }

    // Read in labyrinth-file and create labyrinth
    private void ReadFile() {
        FileDialog fileDialog = new FileDialog(this, "Upload a labyrinth", FileDialog.LOAD);
        fileDialog.setFile("*.png");
        fileDialog.setDirectory("C:\\");
        fileDialog.setVisible(true);
        String path = fileDialog.getDirectory() + fileDialog.getFile();

        if (fileDialog.getFile() != null) {
            File labyrinthFile = new File(path);
            labyrinth = new Labyrinth(labyrinthFile.toString());

            // Display the image
            ImageIcon icon = new ImageIcon(path);
            Image scaledImage = resizeImage(icon.getImage(), panelImage.getWidth(), panelImage.getHeight());
            icon.setImage(scaledImage);
            labelImage.setIcon(icon);

            btnUploadLabyrinth.setEnabled(false);
            btnStart.setEnabled(true);
        }
    }

    private Image resizeImage(Image image, int pWidth, int pHeight) {
        // Calculate the factor needed to resize image
        int iWidth = image.getWidth(null);
        int iHeight = image.getHeight(null);
        int scaleFactor = 0;
        int scaleFactorW = pWidth / iWidth;
        int scaleFactorH = pHeight / iHeight;

        if (scaleFactorW <= scaleFactorH) {
            scaleFactor = scaleFactorW;
        } else {
            scaleFactor = scaleFactorH;
        }

        iWidth = iWidth * scaleFactor;
        iHeight = iHeight * scaleFactor;

        // Resize the image
        BufferedImage resizedImage = new BufferedImage(iWidth, iHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImage.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(image, 0, 0, iWidth, iHeight, null);
        g2.dispose();

        return resizedImage;
    }

    // Solve the labyrinth
    private void SolveLabyrinth() {
        if (labyrinth == null)
            return;
        btnStart.setEnabled(false);

        // TODO: Solve

    }
}