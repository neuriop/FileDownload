package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ManipImage {
    public static void addWatermark(String imagePath, String watermark){
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            BufferedImage watermarked = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = (Graphics2D) watermarked.getGraphics();
            g2d.drawImage(image, 0, 0, null);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            g2d.setFont(g2d.getFont().deriveFont(100f));
            g2d.drawString(watermark, 50, 100);
            g2d.dispose();
            ImageIO.write(watermarked, "jpg", new File(imagePath));
        } catch (IOException e){
            System.out.println("Unknown problem reading file: " + e.getMessage());
        }
    }
}
