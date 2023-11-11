package org.example;

import org.example.Assets.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PainterLandscape {
    static private final int SIZE_CELL = 12;
    static private final NoiseGenerator generator = new NoiseGenerator(SettingsPanel.DEFAULT_SIZE_MAP, SettingsPanel.DEFAULT_SIZE_MAP);
    static private BufferedImage image;
    static private Graphics2D graphics;

    static private void addObject(Asset a, int x, int y) {
        graphics.drawImage(a.getMapObject(), x * SIZE_CELL, y * SIZE_CELL, null);
    }

    static public BufferedImage getResizeImage(int newWidthInCell, int newHeightInCell, float blockFreq) {
        generator.resize(newWidthInCell, newHeightInCell);
        return getImageFromMap(blockFreq);
    }

    static public BufferedImage getInterpolatedImage(float blockFreq) {
        generator.getInterpolateMatrix();
        return getImageFromMap(blockFreq);
    }

    static public BufferedImage getRandomDotsImage(float blockFreq) {
        generator.getRandomMatrix();
        return getImageFromMap(blockFreq);
    }

    static public BufferedImage getEmptyImage() {
        generator.getEmptyMatrix();
        return getImageFromMap(0.1f);
    }

    static public BufferedImage getImageFromMap(float blockFreq) {
        image = new BufferedImage(
            SIZE_CELL * generator.getWidthInCell(),
            SIZE_CELL * generator.getHeightInCell(),
            BufferedImage.TYPE_INT_ARGB
        );
        graphics = image.createGraphics();

        ArrayList<ArrayList<Float>> matrix = generator.getMap();

        for (int y = 0; y < generator.getHeightInCell(); y++) {
            for (int x = 0; x < generator.getWidthInCell(); x++) {
                if (matrix.get(y).get(x) < blockFreq)
                    addObject(new ShadedCell(SIZE_CELL), x, y);
                else
                    addObject(new Cell(SIZE_CELL), x, y);
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

    static public BufferedImage getImage() {
        return image;
    }
}