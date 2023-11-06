package org.example;

import javax.swing.*;

public class ThreadPanel extends Thread {
    @Override
    public void run() {
        JFrame frame = new JFrame("Image Panel"); // создаем окно с заголовком "Image Panel"

        SettingsPanel settingsPanel = new SettingsPanel();

        ImagePanel imagePanel = new ImagePanel(); // создаем панель с изображением

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, imagePanel, settingsPanel);
        splitPane.setDividerLocation(Main.WIDTH_IMAGE);


        frame.add(splitPane); // добавляем панель на окно

        frame.setSize(Main.WIDTH_IMAGE+Main.WIDTH_SETTINGS, Main.HEIGHT_IMAGE); // задаем размеры окна
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // закрываем окно при нажатии на крестик
        frame.setVisible(true); // делаем окно видимым
    }
}
