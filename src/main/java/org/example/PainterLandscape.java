package org.example;

import org.example.Assets.Asset;
import org.example.Assets.Cell;
import org.example.Assets.CircleCover;
import org.example.Assets.RectangleCover;

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

        // Создаём классы объектов
        Asset cell = new Cell(sizeCell);
        Asset rectangleCover = new RectangleCover(sizeCell);
        Asset circleCover = new CircleCover(sizeCell);

        // Создаём матрицу рандомных значений
        ArrayList<ArrayList<Float>> matrix = NoiseGenerator.getFloatGridEasyVersion(widthLandscape, heightLandscape);

        // Расставляем еденичные объекты согласно матрице
        for (int i = 0; i < heightLandscape; i++) {
            for (int j = 0; j < widthLandscape; j++) {
                if (matrix.get(i).get(j) < groundFreq) {
                    break;
                } else if (matrix.get(i).get(j) < groundFreq + rectangleCoverFreq) {
                    addObject(rectangleCover, i, j);
                } else if (matrix.get(i).get(j) < groundFreq + rectangleCoverFreq + circleCoverFreq) {
                    addObject(circleCover, i, j);
                }
            }
        }

        imageIcon.setImage(map);
        imagePanel.repaint();

        graphics.dispose();
    }
}
