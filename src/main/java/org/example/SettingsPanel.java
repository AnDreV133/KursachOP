package org.example;

import javax.swing.*;

public class SettingsPanel {
    public static final int WIDTH_SETTINGS_SPACE = 300;
    public static final int WIDTH_IMAGE_SPACE = 600;
    public static final int HEIGHT_SPACE = 500;

    static private final JSpinner widthLandscape = new JSpinner();
    static private final JSpinner heightLandscape = new JSpinner();
    static private final JSlider blockFreq = new JSlider(0, 1000, 500);

    private final ImageFrame imageFrame = new ImageFrame(WIDTH_IMAGE_SPACE, HEIGHT_SPACE);

    public SettingsPanel() {
        JPanel settingsFrame = new JPanel();
        settingsFrame.setLayout(new BoxLayout(settingsFrame, BoxLayout.Y_AXIS));
        settingsFrame.setSize(WIDTH_SETTINGS_SPACE, HEIGHT_SPACE);

        // Задание размеров поля
        settingsFrame.add(new JLabel("Sizes"));
        widthLandscape.setModel(new SpinnerNumberModel(30, 10, 100, 1));
        settingsFrame.add(widthLandscape);

        heightLandscape.setModel(new SpinnerNumberModel(30, 10, 100, 1));
        settingsFrame.add(heightLandscape);

        JButton btnResize = new JButton();
        btnResize.addActionListener((l) -> resize());
        btnResize.setText("Resize");
        settingsFrame.add(btnResize);

        settingsFrame.add(new JLabel("Block density"));
        blockFreq.addChangeListener((l) -> draw());
        settingsFrame.add(blockFreq);

        JButton btnRandomDots = new JButton();
        btnRandomDots.addActionListener((l) -> randomiseDots());
        btnRandomDots.setText("RandomDots");
        settingsFrame.add(btnRandomDots);

        JButton btnInterpolate = new JButton();
        btnInterpolate.addActionListener((l) -> interpolate());
        btnInterpolate.setText("Interpolate");
        settingsFrame.add(btnInterpolate);

        JFrame mainFrame = new JFrame("Generator"); // создаем окно с заголовком "Image Panel"

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, imageFrame, settingsFrame);
        splitPane.setDividerLocation(WIDTH_IMAGE_SPACE);

        mainFrame.add(splitPane); // добавляем панель на окно

        mainFrame.setSize(WIDTH_IMAGE_SPACE + WIDTH_SETTINGS_SPACE, HEIGHT_SPACE); // задаем размеры окна
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // закрываем окно при нажатии на крестик
        mainFrame.setVisible(true); // делаем окно видимым
    }

    private void randomiseDots() {
        imageFrame.updateImage(PainterLandscape.getRandomDotsImage(blockFreq.getValue() / 1000f));
    }

    private void resize() {
        imageFrame.updateImage(PainterLandscape.getResizeImage(
            (Integer) widthLandscape.getValue(),
            (Integer) heightLandscape.getValue(),
            blockFreq.getValue() / 1000f
        ));
    }

    private void interpolate() {
        imageFrame.updateImage(PainterLandscape.getInterpolatedImage(blockFreq.getValue() / 1000f));
    }

    private void draw() {
        imageFrame.updateImage(PainterLandscape.getImageFromMap(blockFreq.getValue() / 1000f));
    }
}
