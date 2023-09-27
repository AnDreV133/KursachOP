package org.example.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CircleCover extends Asset{
    public CircleCover(int sideSize) {
        super(sideSize);
    }

    @Override
    public void drawMapObject() {
        g.setColor(Color.black);
        g.fillOval(0, 0, getSideSize(), getSideSize());
    }
}
