package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SettingsPanel extends JPanel {
    private JSpinner widthLandscape = new JSpinner();
    private JSpinner heightLandscape = new JSpinner();
    private JSlider groundFreq = new JSlider(0, 1000, 1000);
    private JSlider rectangleCoverFreq = new JSlider(0, 1000, 0);
    private JSlider circleCoverFreq = new JSlider(0, 1000, 0);
    private JSpinner riverAmount = new JSpinner();
    private JSpinner riverWidth = new JSpinner();
    private JSlider difficultTerrainFreq = new JSlider(0, 1000, 0);
    private JSpinner difficultTerrainRadius = new JSpinner();
    private JSlider areaFreq = new JSlider(0, 1000, 0);
    private JSpinner areaMaxRadius = new JSpinner();
    private JSpinner areaAmountObject = new JSpinner();

    public SettingsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setSize(Main.WIDTH_SETTINGS, Main.HEIGHT);

        // Задание размеров поля
        panel.add(new JLabel("Zone"));
        widthLandscape.setModel(new SpinnerNumberModel(5, 5, 35, 1));
        heightLandscape.setModel(new SpinnerNumberModel(5, 5, 35, 1));
        widthLandscape.addChangeListener((e) -> PainterLandscape.drawGrid((Integer) widthLandscape.getValue(), (Integer) heightLandscape.getValue()));
        heightLandscape.addChangeListener((e) -> PainterLandscape.drawGrid((Integer) widthLandscape.getValue(), (Integer) heightLandscape.getValue()));
        panel.add(widthLandscape);
        panel.add(heightLandscape);

        // Частота появления свободных полей
        groundFreq.setMajorTickSpacing(100);
        groundFreq.setPaintTicks(true);
        panel.add(new JLabel("Ground"));
        panel.add(groundFreq);

        // Частота появления укрытий
        panel.add(new JLabel("Rectangle Cover"));
        panel.add(rectangleCoverFreq);

        // Частота появления укрытий
        panel.add(new JLabel("Circle Cover"));
        panel.add(circleCoverFreq);

        // Частота появления областей
        panel.add(new JLabel("Area"));
        panel.add(areaFreq);

        // Реки
        panel.add(new JLabel("River"));

        // Количество рек
        panel.add(riverAmount);

        // Ширина рек
        panel.add(riverWidth);

        // труднопроходимые местности
        panel.add(new JLabel("Difficult terrain"));

        // Частота появления труднопроходимых местностей
        panel.add(difficultTerrainFreq);

        // Радиус труднопроходимой местностности
        panel.add(difficultTerrainRadius);

        JPanel panelBtnGen = new JPanel();

        JButton btnGenerate = new JButton();
        btnGenerate.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PainterLandscape.generate(
                        (Integer) widthLandscape.getValue(),
                        (Integer) heightLandscape.getValue(),
                        groundFreq.getValue() / 1000f,
                        rectangleCoverFreq.getValue() / 1000f,
                        circleCoverFreq.getValue() / 1000f,
                        areaFreq.getValue() / 1000f,
                        (Integer) areaMaxRadius.getValue(),
                        (Integer) areaAmountObject.getValue(),
                        (Integer) riverAmount.getValue(),
                        (Integer) riverWidth.getValue(),
                        difficultTerrainFreq.getValue() / 1000f,
                        (Integer) difficultTerrainRadius.getValue());
            }
        });
        btnGenerate.setText("Generate");
        panelBtnGen.add(btnGenerate);

        panel.add(panelBtnGen);
        add(panel);
    }

//    public void balanceFreq(JSlider slider) {
//        int sumFreq = groundFreq.getValue()
//                + rectangleCoverFreq.getValue()
//                + circleCoverFreq.getValue()
//                + areaFreq.getValue()
//                + difficultTerrainFreq.getValue();
//
//        if (sumFreq <= 1000)
//            return;
//
//        int delta = (sumFreq - 1000) / 5;
//
//        if (!slider.equals(groundFreq)) groundFreq.setValue(groundFreq.getValue() - delta);
//        if (!slider.equals(rectangleCoverFreq)) rectangleCoverFreq.setValue(rectangleCoverFreq.getValue() - delta);
//        if (!slider.equals(circleCoverFreq)) circleCoverFreq.setValue(circleCoverFreq.getValue() - delta);
//        if (!slider.equals(areaFreq)) areaFreq.setValue(areaFreq.getValue() - delta);
//        if (!slider.equals(difficultTerrainFreq))
//            difficultTerrainFreq.setValue(difficultTerrainFreq.getValue() - delta);
//    }


    // сделать создание поля с автоматическим обновлением картинки.
}
