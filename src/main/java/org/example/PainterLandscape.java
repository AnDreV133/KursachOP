package org.example;

import org.w3c.dom.DOMImplementation;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
public class PainterLandscape {
    public PainterLandscape() {
        System.out.println("help");
    }

    public void drawGrid(Integer height_, Integer weight_) {
        int size = 10; // размер сетки
        int cellSize = 20; // размер ячейки
        int width = size * cellSize; // ширина svg-изображения
        int height = size * cellSize; // высота svg-изображения

        // создаем документ SVG
        DOMImplementation domImpl = SVGDOMImplementation.getDOMImplementation();
        Document doc = domImpl.createDocument(SVGDOMImplementation.SVG_NAMESPACE_URI, "svg", null);
        Element svgRoot = doc.getDocumentElement();
        svgRoot.setAttributeNS(null, "width", Integer.toString(width));
        svgRoot.setAttributeNS(null, "height", Integer.toString(height));

        // создаем объект для рисования на SVG
        SVGGraphics2D g2d = new SVGGraphics2D(doc);

        // рисуем сетку
        g2d.setColor(Color.BLACK);
        for (int i = 0; i <= size; i++) {
            int x = i * cellSize;
            g2d.drawLine(x, 0, x, height);
            g2d.drawLine(0, x, width, x);
        }

        // сохраняем SVG-изображение в файл
        FileWriter writer = new FileWriter(new File("grid.svg"));
        g2d.stream(svgRoot, writer);
        writer.close();
    }

    public void generate(Integer heightLandscape,
                         Integer weightLandscape,
                         Float groundFreq,
                         Float coverFreq,
                         Float areaFreq,
                         Integer areaMaxRadius,
                         Integer areaAmountObjects,
                         Integer riverAmount,
                         Integer riverWidth,
                         Float difficultTerrainFreq,
                         Integer difficultTerrainRadius) {

    }
}
