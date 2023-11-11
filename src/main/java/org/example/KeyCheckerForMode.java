package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.*;
import static java.awt.event.KeyEvent.VK_9;

public class KeyCheckerForMode implements KeyListener {
    public static boolean isAltPressed;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.isAltDown()) {
            isAltPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.isAltDown())
            isAltPressed = false;
    }
}
