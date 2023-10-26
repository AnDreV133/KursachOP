package org.example;

import javax.swing.*;
import java.awt.*;

public class PanelPainter {

//    Graphics graphic = getGraphics();

    public PanelPainter() {
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    private final ImageIcon imageIcon = new ImageIcon(); // изображение
    private final Point imagePlace = new Point(
            (Main.WIDTH_IMAGE - imageIcon.getIconWidth()) / 2,
            (Main.HEIGHT_IMAGE - imageIcon.getIconHeight()) / 2
    );
    private double scale = 1.0; // масштабирование

    public double getScale() {
        return scale;
    }

    public void updateImage(Graphics g) {
        Graphics2D g2d = (Graphics2D) g; // используем 2D графику
        g2d.scale(scale, scale); // масштабируем изображение
        g2d.drawImage(
                imageIcon.getImage(),
                (int) (imagePlace.getX() / scale),
                (int) (imagePlace.getY() / scale),
                null
        ); // отрисовываем изображение
    }

    public void transferImage(Point point) {
        imagePlace.setX(imagePlace.getX() + point.getX());
        imagePlace.setX(imagePlace.getY() + point.getY());
    }

    public void resizeOnCoefficient(double coefficient) {
        scale *= coefficient;
    }
}
