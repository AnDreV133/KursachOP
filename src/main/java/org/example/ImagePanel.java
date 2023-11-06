package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ImagePanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {
    private final ImageIcon imageIcon; // изображение
    private final Point imagePlace = new Point();
    private final Point mousePlace = new Point();
    private double scale = 1.0; // масштабирование

    public ImagePanel() {
        imageIcon = SettingsPanel.getImageIcon();

        addMouseListener(this); // добавляем слушателей событий мыши
        addMouseMotionListener(this);
        addMouseWheelListener(this);

        imagePlace.setXY(
                (Main.WIDTH_IMAGE - imageIcon.getIconWidth()) / 2,
                (Main.HEIGHT_IMAGE - imageIcon.getIconHeight()) / 2
        );
    }

    public void paintComponent(Graphics g) { // переопределяем метод отрисовки компонента
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g; // используем 2D графику
        g2d.scale(scale, scale); // масштабируем изображение
        g2d.drawImage(
                imageIcon.getImage(),
                (int) (imagePlace.getX() / scale),
                (int) (imagePlace.getY() / scale),
                this
        );
    }

    public void mouseClicked(MouseEvent e) {
    } // оставляем пустыми, так как не используем эти события

    public void mousePressed(MouseEvent e) { // запоминаем координаты мыши при нажатии
        mousePlace.setXY(e.getX(), e.getY());
    }

    public void mouseReleased(MouseEvent e) {
    } // оставляем пустыми

    public void mouseEntered(MouseEvent e) {
    } // оставляем пустыми

    public void mouseExited(MouseEvent e) {
    } // оставляем пустыми

    public void mouseDragged(MouseEvent e) { // перемещаем изображение при перетаскивании мыши
        imagePlace.setXY(
                imagePlace.getX() + e.getX() - mousePlace.getX(),
                imagePlace.getY() + e.getY() - mousePlace.getY()
        );

        repaint();

        mousePlace.setXY(e.getX(), e.getY());
    }

    public void mouseMoved(MouseEvent e) {
    } // оставляем пустыми

    public void mouseWheelMoved(MouseWheelEvent e) { // масштабируем изображение при вращении колесика мыши
        int notches = e.getWheelRotation();
        if (notches < 0) {
            scale *= 1.1; // увеличиваем масштаб на 10%
        } else if (scale >= 0.1) {
            scale /= 1.1; // уменьшаем масштаб на 10%
        }

        repaint(); // перерисовываем компонент
    }
}