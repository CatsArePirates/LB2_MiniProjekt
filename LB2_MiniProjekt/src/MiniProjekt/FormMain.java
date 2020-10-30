package MiniProjekt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FormMain extends JFrame {
    private JPanel mainPanel;
    private JButton btnUploadLabyrinth;

    public FormMain(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // The application exits when the form is closed
        this.setContentPane(mainPanel);
        this.pack();

        btnUploadLabyrinth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open folder to upload a file
                ReadFile();
            }
        });
    }

    private void ReadFile() {
        FileDialog fileDialog = new FileDialog(this, "Upload a labyrinth", FileDialog.LOAD);
        fileDialog.setVisible(true);
        String filename = fileDialog.getFile();

        if (filename != null) {
            File labyrinthFile = new File(filename);

            // Create labyrinth from file
        }
    }
}
