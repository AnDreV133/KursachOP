package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ImagePanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {
    private ImageIcon imageIcon = new ImageIcon(); // изображение
    private int x, y; // координаты изображения
    private int xMouse, yMouse; // координаты изображения
    private double scale = 1.0; // масштабирование

    public ImagePanel() {
        new PainterLandscape(this, imageIcon);
        x = (Main.WIDTH_IMAGE - imageIcon.getIconWidth()) / 2; // начальные координаты изображения по центру панели
        y = (Main.HEIGHT - imageIcon.getIconHeight()) / 2;
        addMouseListener(this); // добавляем слушателей событий мыши
        addMouseMotionListener(this);
        addMouseWheelListener(this);
    }

    public void paintComponent(Graphics g) { // переопределяем метод отрисовки компонента
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g; // используем 2D графику
        g2d.scale(scale, scale); // масштабируем изображение
        g2d.drawImage(imageIcon.getImage(), (int) (x / scale), (int) (y / scale), this); // отрисовываем изображение
    }

    public void mouseClicked(MouseEvent e) {
    } // оставляем пустыми, так как не используем эти события

    public void mousePressed(MouseEvent e) { // запоминаем координаты мыши при нажатии
        xMouse = e.getX();
        yMouse = e.getY();
    }

    public void mouseReleased(MouseEvent e) {
    } // оставляем пустыми

    public void mouseEntered(MouseEvent e) {
    } // оставляем пустыми

    public void mouseExited(MouseEvent e) {
    } // оставляем пустыми

    public void mouseDragged(MouseEvent e) { // перемещаем изображение при перетаскивании мыши
        int dx = e.getX() - xMouse; // смещение по x
        int dy = e.getY() - yMouse; // смещение по y
        x += dx;
        y += dy;
        repaint();// перерисовываем компонент
        xMouse = e.getX();
        yMouse = e.getY();
    }

    public void mouseMoved(MouseEvent e) {
    } // оставляем пустыми

    public void mouseWheelMoved(MouseWheelEvent e) { // масштабируем изображение при вращении колесика мыши
        int notches = e.getWheelRotation();
        if (notches < 0) {
            scale *= 1.1; // увеличиваем масштаб на 10%
        } else {
            scale /= 1.1; // уменьшаем масштаб на 10%
        }

        if (scale < 0.1)
            scale = 0.1;

        repaint(); // перерисовываем компонент
    }
}