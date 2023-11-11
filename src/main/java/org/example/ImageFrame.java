package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class ImageFrame extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {
    private final ImageIcon imageIcon = new ImageIcon();
    private final Point imagePlace = new Point();
    private final Point mousePlace = new Point();
    private double scale = 1.0;

    public ImageFrame(int width, int height) {
        updateImage(PainterLandscape.getEmptyImage());

        addMouseListener(this); // добавляем слушателей событий мыши
        addMouseMotionListener(this);
        addMouseWheelListener(this);
        addKeyListener(new KeyCheckerForMode());
        addKeyListener(new KeyCheckerForObjects());

        requestFocus();

//        JLabel objectType

        imagePlace.setXY(
            (width - imageIcon.getIconWidth()) / 2,
            (height - imageIcon.getIconHeight()) / 2
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
        requestFocusInWindow();
    }

    public void mousePressed(MouseEvent e) { // запоминаем координаты мыши при нажатии
        if (KeyCheckerForMode.isAltPressed)
            mousePlace.setXY(e.getX(), e.getY());
    }

    public void mouseReleased(MouseEvent e) {
    } // оставляем пустыми

    public void mouseEntered(MouseEvent e) {
    } // оставляем пустыми

    public void mouseExited(MouseEvent e) {
    } // оставляем пустыми

    public void mouseDragged(MouseEvent e) { // перемещаем изображение при перетаскивании мыши
        if (KeyCheckerForMode.isAltPressed) {
            imagePlace.setXY(
                imagePlace.getX() + e.getX() - mousePlace.getX(),
                imagePlace.getY() + e.getY() - mousePlace.getY()
            );

            repaint();

            mousePlace.setXY(e.getX(), e.getY());
        }
    }

    public void mouseMoved(MouseEvent e) {
    } // оставляем пустыми

    public void mouseWheelMoved(MouseWheelEvent e) { // масштабируем изображение при вращении колесика мыши
        if (KeyCheckerForMode.isAltPressed) {
            int notches = e.getWheelRotation();
            if (notches < 0) {
                scale *= 1.1; // увеличиваем масштаб на 10%
            } else if (scale >= 0.1) {
                scale /= 1.1; // уменьшаем масштаб на 10%
            }

            repaint(); // перерисовываем компонент
        }
    }

    public void updateImage(BufferedImage image) {
        imageIcon.setImage(image);
        repaint();
    }
}