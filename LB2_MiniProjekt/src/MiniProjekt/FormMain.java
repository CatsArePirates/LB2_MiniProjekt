package MiniProjekt;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormMain extends JFrame {
    private JPanel mainPanel;
    private JButton btnUploadLabyrinth;

    public FormMain(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        btnUploadLabyrinth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open folder to upload a file
                
            }
        });
    }
}
