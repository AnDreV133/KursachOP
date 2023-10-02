package org.example;

import org.example.Assets.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PainterLandscape {
    static private final int sizeCell = 60;
    static private BufferedImage map;
    static private ImagePanel imagePanel;
    static private ImageIcon imageIcon;
    static private Graphics2D graphics;

    public PainterLandscape(ImagePanel imagePanel, ImageIcon imageIcon) {
        PainterLandscape.imagePanel = imagePanel;
        PainterLandscape.imageIcon = imageIcon;
        drawGrid(5, 5);
    }

    static private void addObject(Asset a, int x, int y) {
        graphics.drawImage(a.getMapObject(), x * sizeCell, y * sizeCell, null);
    }

    static public void drawGrid(Integer widthByCells, Integer heightByCells) {
        map = new BufferedImage(sizeCell * widthByCells, sizeCell * heightByCells, BufferedImage.TYPE_INT_ARGB);
        graphics = map.createGraphics();

        Asset cell = new Cell(sizeCell);

        for (int x = 0; x < widthByCells; x++)
            for (int y = 0; y < heightByCells; y++)
                addObject(cell, x, y);

        imageIcon.setImage(map);
        imagePanel.repaint(); // сбрасывается генерация
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

    static public void generate(Integer widthLandscape,
                                Integer heightLandscape,
                                Float groundFreq,
                                Float rectangleCoverFreq,
                                Float circleCoverFreq,
                                Float areaFreq,
                                Integer areaMaxRadius,
                                Integer areaAmountObjects,
                                Integer riverAmount,
                                Integer riverWidth,
                                Float difficultTerrainFreq,
                                Integer difficultTerrainRadius) {
        // Заново отрисовываем поле
        drawGrid(widthLandscape, heightLandscape);

        boolean[][] mainObjectChecker = new boolean[heightLandscape][widthLandscape];

        // Создаём матрицу рандомных значений
        addObjectsByBoolMatrix(new RectangleCover(sizeCell),
                NoiseGenerator.getMatrixByMathRandomWithRandomIndentation(
                        widthLandscape, heightLandscape, rectangleCoverFreq, 0.8f),
                mainObjectChecker);

        addObjectsByBoolMatrix(new CircleCover(sizeCell),
                NoiseGenerator.getMatrixByMathRandomWithRandomIndentation(
                        widthLandscape, heightLandscape, circleCoverFreq, 1.0f),
                mainObjectChecker);

        addObjectsByBoolMatrix(new Area(sizeCell),
                NoiseGenerator.getMatrixByMathRandomWithRandomIndentation(
                        widthLandscape, heightLandscape, areaFreq, 0.3f),
                null);


        imageIcon.setImage(map);
        imagePanel.repaint();

        graphics.dispose();
    }
}
