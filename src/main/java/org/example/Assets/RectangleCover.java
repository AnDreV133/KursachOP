package org.example.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RectangleCover extends Asset {

    public RectangleCover(int sideSize) {
        super(sideSize);
    }

    @Override
    public BufferedImage getImage() {
        BufferedImage image = new BufferedImage(getSideSize(), getSideSize(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, getSideSize(), getSideSize());

        return image;
    }
}
