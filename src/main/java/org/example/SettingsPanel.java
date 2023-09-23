package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SettingsPanel extends JPanel {
    private JSpinner heightLandscape = new JSpinner();
    private JSpinner weightLandscape = new JSpinner();
    private JSlider groundFreq = new JSlider(0, 1000, 1000);
    private JSlider coverFreq = new JSlider(0, 1000, 0);
    private JSpinner riverAmount = new JSpinner();
    private JSpinner riverWidth = new JSpinner();
    private JSlider difficultTerrainFreq = new JSlider(0, 1000, 0);
    private JSpinner difficultTerrainRadius = new JSpinner();
    private JSlider areaFreq = new JSlider();
    private JSpinner areaMaxRadius = new JSpinner();
    private JSpinner areaAmountObject = new JSpinner();
    private int sumFreq = groundFreq.getValue();

    PainterLandscape painter = new PainterLandscape();

    public SettingsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Задание размеров поля
        panel.add(new JLabel("Zone"));
        SpinnerNumberModel model = new SpinnerNumberModel(5, 5, 35, 1);
        heightLandscape.setModel(model);
        weightLandscape.setModel(model);
        heightLandscape.addChangeListener((e) -> painter.drawGrid((Integer) heightLandscape.getValue(), (Integer) weightLandscape.getValue()));
        weightLandscape.addChangeListener((e) -> painter.drawGrid((Integer) heightLandscape.getValue(), (Integer) weightLandscape.getValue()));
        panel.add(heightLandscape);
        panel.add(weightLandscape);

        // Частота появления свободных полей
        groundFreq.setMajorTickSpacing(10);
        groundFreq.setPaintTicks(true);
        groundFreq.addChangeListener(e -> balanceFreq(groundFreq));
        panel.add(new JLabel("Ground"));
        panel.add(groundFreq);

        // Частота появления укрытий
        coverFreq.addChangeListener(e -> balanceFreq(coverFreq));
        panel.add(new JLabel("Cover"));
        panel.add(coverFreq);

        // Частота появления областей
        areaFreq.addChangeListener(e -> balanceFreq(areaFreq));
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
        difficultTerrainFreq.addChangeListener(e -> balanceFreq(difficultTerrainFreq));
        panel.add(difficultTerrainFreq);

        // Радиус труднопроходимой местностности
        panel.add(difficultTerrainRadius);

        JPanel panelBtnGen = new JPanel();

        JButton btnGenerate = new JButton();
        btnGenerate.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                painter.generate(
                    (Integer) heightLandscape.getValue(),
                    (Integer) weightLandscape.getValue(),
                    groundFreq.getValue() / 1000f,
                    coverFreq.getValue() / 1000f,
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

    public void balanceFreq(JSlider slider) {
        sumFreq = groundFreq.getValue() + coverFreq.getValue()
            + difficultTerrainFreq.getValue() + areaFreq.getValue();

        if (sumFreq <= 1000)
            return;

        int delta = (sumFreq - 1000) / 4;

        if (!slider.equals(groundFreq)) groundFreq.setValue(groundFreq.getValue() - delta);
        if (!slider.equals(coverFreq)) coverFreq.setValue(coverFreq.getValue() - delta);
        if (!slider.equals(difficultTerrainFreq))
            difficultTerrainFreq.setValue(difficultTerrainFreq.getValue() - delta);
        if (!slider.equals(areaFreq)) areaFreq.setValue(areaFreq.getValue() - delta);

        System.out.println(sumFreq);
    }

    public void finalBalanceFreqForGenerated() {

    }

    // сделать создание поля с автоматическим обновлением картинки.
}
