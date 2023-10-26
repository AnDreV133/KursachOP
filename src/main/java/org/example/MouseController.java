package org.example;

import java.awt.event.*;

public class MouseController implements MouseListener, MouseMotionListener, MouseWheelListener {
    private final Point mousePlace = new Point();

    private final PanelPainter painter = new PanelPainter();

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
        painter.transferImage(
                new Point(
                        e.getX() - mousePlace.getX(),
                        e.getY() - mousePlace.getY()
                )
        );
        painter.repaint();// перерисовываем компонент
        mousePlace.setXY(e.getX(), e.getY());
    }

    public void mouseMoved(MouseEvent e) {
    } // оставляем пустыми

    public void mouseWheelMoved(MouseWheelEvent e) { // масштабируем изображение при вращении колесика мыши
        int notches = e.getWheelRotation();
        if (notches < 0) {
            painter.resizeOnCoefficient(1.1);
        } else if (painter.getScale() > 0.1) {
            painter.resizeOnCoefficient(0.9);
        }
        // todo проверить ресайз
        painter.repaint(); // перерисовываем компонент
    }
}