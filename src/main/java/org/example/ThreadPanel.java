package org.example;

import javax.swing.*;

public class ThreadPanel extends Thread {
    @Override
    public void run() {
        JFrame frame = new JFrame("Image Panel"); // создаем окно с заголовком "Image Panel"

        ImageIcon image = new ImageIcon("D:\\projects_by_java\\FinalWorkOPnOA2023\\src\\main\\java\\org\\example\\img.png"); // загружаем изображение из файла "image.jpg"
        ImagePanel imagePanel = new ImagePanel(image); // создаем панель с изображением

        SettingsPanel settingsPanel = new SettingsPanel();

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, imagePanel, settingsPanel);
        splitPane.setDividerLocation(Main.WIDTH * 2 / 3);


        frame.add(splitPane); // добавляем панель на окно

        frame.setSize(Main.WIDTH, Main.HEIGHT); // задаем размеры окна
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // закрываем окно при нажатии на крестик
        frame.setVisible(true); // делаем окно видимым
    }
}
