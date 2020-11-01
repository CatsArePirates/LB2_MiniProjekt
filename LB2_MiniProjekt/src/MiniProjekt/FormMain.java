package MiniProjekt;

import Labyrinth.Labyrinth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class FormMain extends JFrame {
    private JPanel mainPanel;
    private JPanel panelImage;
    private JLabel labelImage; // Contains the image of the labyrinth
    private JTextField tbFieldLength;
    private JTextField tbHeight;
    private JTextField tbWidth;
    private JButton btnSolve; // Click button to start solving the labyrinth
    private JButton btnBeDone; // Click button to jump to the end of the solving process

    private Labyrinth labyrinth = null;

    public FormMain(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // The application exits when the form is closed
        this.setContentPane(mainPanel);
        this.pack();

        // Action-listener for button btnSolve
        btnSolve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Start solving the labyrinth
                StartSolving();
            }
        });

        // Action-listener for button btnBeDone
        btnBeDone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Jump to end of solving the labyrinth
                BeDone();
            }
        });

        // Set input verifier for text-fields
        tbWidth.setInputVerifier(new InputVerifier());
        tbHeight.setInputVerifier(new InputVerifier());
        tbFieldLength.setInputVerifier(new InputVerifier());
    }

    // Read in labyrinth-file and create labyrinth
    private void StartSolving() {
        // Sanity check
        if (tbWidth.getText().length() <= 0 || tbHeight.getText().length() <= 0 || tbFieldLength.getText().length() <= 0)
            return;

        btnSolve.setEnabled(false);
        btnBeDone.setEnabled(true);

        // Initialize labyrinth
        labyrinth = new Labyrinth(Integer.parseInt(tbWidth.getText()), Integer.parseInt(tbHeight.getText()), Integer.parseInt(tbFieldLength.getText()));

        ImageIcon icon = new ImageIcon();
        icon.setImage(labyrinth.getImage());
        labelImage.setIcon(icon);

        Solve();
    }

    // Solve the labyrinth
    private void BeDone() {
        btnBeDone.setEnabled(false);

        // TODO: Finish backtracking immediately (without the thread.sleep(500))

        //btnSolve.setEnabled(true);
    }

    private void Solve() {
        ImageIcon icon = new ImageIcon();

        /*while (labyrinth.HasNext()) {
            icon.setImage(labyrinth.SolveNext());
            labelImage.setIcon(icon);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) { }
        }*/

        icon.setImage(labyrinth.SolveNext());
        labelImage.setIcon(icon);
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
}