package MiniProjekt;

import Backtracker.Backtracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FormMain extends JFrame {
    private JPanel mainPanel;
    private JButton btnUploadLabyrinth; // Click button to upload image
    private JPanel panelImage; // Contains the image of the labyrinth
    private JButton btnStart;

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
        String filename = fileDialog.getDirectory() + fileDialog.getFile();

        if (fileDialog.getFile() != null) {
            File labyrinthFile = new File(filename);
            labyrinth = new Labyrinth(labyrinthFile.toString());

            // TODO: Display image of labyrinth in panelImage

            btnUploadLabyrinth.setEnabled(false);
            btnStart.setEnabled(true);
        }
    }

    // Solve the labyrinth
    private void SolveLabyrinth() {
        if (labyrinth == null)
            return;
        btnStart.setEnabled(false);

        // TODO: Solve

    }
}