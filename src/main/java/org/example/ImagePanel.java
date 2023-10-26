package org.example;

import org.example.MouseController;
import org.example.PainterLandscape;
import org.example.PanelPainter;

import javax.swing.*;
import java.awt.*;


public class ImagePanel extends JPanel {
    private PanelPainter panelPainter = new PanelPainter();

    public ImagePanel() {
        new PainterLandscape(panelPainter, panelPainter.getImageIcon());
        this.addMouseListener(new MouseController());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        panelPainter.updateImage(g);
    }
}
