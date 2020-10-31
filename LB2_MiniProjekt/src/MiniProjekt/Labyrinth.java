package MiniProjekt;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Labyrinth {
    private BufferedImage image = null;

    public Labyrinth(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void DrawWhite() {
        // Draw all transparent pixel white
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                // Test if pixel is transparent
                if (image.getRGB(i, j)>>24 == 0x00) {
                    image.setRGB(i, j, new Color(255, 255, 255, 255).getRGB());
                }
            }
        }
    }

    private void ConvertToCode() {
        // TODO: Convert labyrinth from image into code
        
    }
}
