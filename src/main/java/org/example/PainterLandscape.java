package org.example;

import org.example.Assets.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PainterLandscape {
    static private final int sizeCell = 10;
    static private final BufferedImage image;
    static private final Graphics2D graphics;
    static private final NoiseGenerator generator;

    static {
        generator = new NoiseGenerator(30, 30);
        image = new BufferedImage(
            sizeCell * generator.getWidthInCell(),
            sizeCell * generator.getHeightInCell(),
            BufferedImage.TYPE_INT_ARGB
        );
        graphics = image.createGraphics();
    }

    static private void addObject(Asset a, int x, int y) {
        graphics.drawImage(a.getMapObject(), x * sizeCell, y * sizeCell, null);
    }

    static public BufferedImage getResizeImage(int newWidthInCell, int newHeightInCell, float blockFreq) {
        generator.resize(newWidthInCell, newHeightInCell);
        return getImage(blockFreq);
    }

    static public BufferedImage getInterpolatedImage(float blockFreq) {
        generator.interpolate();
        return getImage(blockFreq);
    }

    static public BufferedImage getRandomDotsImage(float blockFreq) {
        generator.getRandomMatrix();
        return getImage(blockFreq);
    }

    static public BufferedImage getImage(float blockFreq) {
        ArrayList<ArrayList<Float>> matrix = generator.getMap();

        for (int y = 0; y < generator.getHeightInCell(); y++) {
            for (int x = 0; x < generator.getWidthInCell(); x++) {
                if (matrix.get(y).get(x) > blockFreq)
                    addObject(new RectangleCover(sizeCell), x, y);
                else
                    addObject(new Cell(sizeCell), x, y);
            }
        }

        return image;
    }

    static public void addObjectsByBoolMatrix(Asset asset, ArrayList<ArrayList<Boolean>> mask,
                                              boolean[][] objectChecker) {
        for (int i = 0; i < mask.size(); i++)
            for (int j = 0; j < mask.get(0).size(); j++)
                if (objectChecker != null) {
                    if (mask.get(i).get(j) && !objectChecker[i][j]) {
                        objectChecker[i][j] = true;
                        addObject(asset, i, j);
                    }
                } else if (mask.get(i).get(j)) {
                    addObject(asset, i, j);
                }
    }

    // gr.dispose
}
