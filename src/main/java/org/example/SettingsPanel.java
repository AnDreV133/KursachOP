package org.example;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class SettingsPanel extends JPanel {
    static private final ImagePanel imagePanel = new ImagePanel();
    static private final ImageIcon imageIcon = new ImageIcon();
    static private final JSpinner widthLandscape = new JSpinner();
    static private final JSpinner heightLandscape = new JSpinner();
    static private final JSlider blockFreq = new JSlider(0, 1000, 500);


    public SettingsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setSize(Main.WIDTH_SETTINGS, Main.HEIGHT_IMAGE);

        // Задание размеров поля
        panel.add(new JLabel("Sizes"));
        widthLandscape.setModel(new SpinnerNumberModel(30, 10, 100, 1));
        panel.add(widthLandscape);

        heightLandscape.setModel(new SpinnerNumberModel(30, 10, 100, 1));
        panel.add(heightLandscape);

        JButton btnResize = new JButton();
        btnResize.addActionListener((l) -> resize());
        btnResize.setText("Resize");
        panel.add(btnResize);

        panel.add(new JLabel("Block density"));
        blockFreq.addChangeListener((l)->draw());
        panel.add(blockFreq);

        JButton btnRandomDots = new JButton();
        btnRandomDots.addActionListener((l) -> randomiseDots());
        btnRandomDots.setText("Interpolate");
        panel.add(btnRandomDots);

        JButton btnInterpolate_ = new JButton();
        btnInterpolate_.addActionListener((l) -> resize());
        btnInterpolate_.setText("Interpolate");
        panel.add(btnInterpolate_);

        add(panel);
    }

    static private void updateImage(BufferedImage image) {
        imageIcon.setImage(image);
        imagePanel.repaint();
    }

    private void randomiseDots() {
        updateImage(PainterLandscape.getRandomDotsImage(blockFreq.getValue() / 1000f));
    }

    static private void resize() {
        updateImage(PainterLandscape.getResizeImage(
            (Integer) widthLandscape.getValue(),
            (Integer) heightLandscape.getValue(),
            blockFreq.getValue() / 1000f
        ));
    }

    static private void interpolate() {
        updateImage(PainterLandscape.getInterpolatedImage(blockFreq.getValue() / 1000f));
    }

    static private void draw() {
        updateImage(PainterLandscape.getImage(blockFreq.getValue() / 1000f));
    }

    static public ImageIcon getImageIcon() {
        return imageIcon;
    }
}
