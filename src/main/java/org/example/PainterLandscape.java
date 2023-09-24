package org.example;

import org.example.Assets.Asset;
import org.example.Assets.Cell;
import org.example.Assets.RectangleCover;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PainterLandscape {
    static private final int sizeCell = 60;
    static private BufferedImage image;
    static private ImagePanel imagePanel;
    static private ImageIcon imageIcon;
    static private Integer widthByCells, heightByCells = 0;
    static private Graphics2D graphics;

    public PainterLandscape(ImagePanel imagePanel, ImageIcon imageIcon) {
        PainterLandscape.imagePanel = imagePanel;
        PainterLandscape.imageIcon = imageIcon;
        drawGrid(5, 5);
    }

    static private void addObject(Asset a, int x, int y) {
        graphics.drawImage(a.getImage(), x * sizeCell, y * sizeCell, null);
    }

    static public void drawGrid(Integer widthByCells, Integer heightByCells) {
        image = new BufferedImage(sizeCell * widthByCells, sizeCell * heightByCells, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();

        Asset cell = new Cell(sizeCell);

        for (int x = 0; x < widthByCells; x++)
            for (int y = 0; y < heightByCells; y++)
                addObject(cell, x, y);

        graphics.dispose();

        PainterLandscape.widthByCells = widthByCells;
        PainterLandscape.heightByCells = heightByCells;

        imageIcon.setImage(image);
        imagePanel.repaint(); // сбрасывается генерация
    }

    static public void generate(Integer heightLandscape,
                                Integer weightLandscape,
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
        graphics = image.createGraphics();
        Asset rectangleCover = new RectangleCover(sizeCell);
        addObject(rectangleCover, 1, 1); // TODO: 24.09.2023

        imageIcon.setImage(image);
        imagePanel.repaint();
    }
}
