package org.example;

import java.util.ArrayList;

public class NoiseGenerator {
    private final ArrayList<ArrayList<Float>> map = new ArrayList<>();
    private int heightInCell, widthInCell;

    public NoiseGenerator(int heightInCell, int widthInCell) {
        this.heightInCell = heightInCell;
        this.widthInCell = widthInCell;
    }

    static private void checkFreq(float freq) {
        if (!(freq >= 0 && freq <= 1)) {
            System.err.println("freq is too large or less");
        }
    }

    public ArrayList<ArrayList<Float>> getMap() {

        return map;
    }

    public int getHeightInCell() {
        return heightInCell;
    }

    public int getWidthInCell() {
        return widthInCell;
    }

    public void resize(int newWidthInCell, int newHeightInCell) {
        for (int i = 0; i < newWidthInCell - widthInCell; ++i)
            for (int y = 0; y < heightInCell; ++y)
                map.get(y).add(
                    (map.get(y != 0 ? y - 1 : 0).get(widthInCell + i - 1)
                        + map.get(y).get(widthInCell + i - 1)
                        + map.get(y != heightInCell - 1 ? y + 1 : heightInCell - 1).get(widthInCell + i - 1)) / 3
                );

        for (int i = 0; i < newHeightInCell - heightInCell; ++i) {
            ArrayList<Float> buf = new ArrayList<>();
            for (int x = 0; x < newWidthInCell; x++) {
                buf.add(
                    (map.get(heightInCell + i - 1).get(x != 0 ? x - 1 : 0)
                        + map.get(heightInCell + i - 1).get(x)
                        + map.get(heightInCell + i - 1).get(x != newWidthInCell - 1 ? x + 1 : widthInCell - 1)) / 3
                );
            }
            map.add(buf);
        }

        widthInCell = newWidthInCell;
        heightInCell = newHeightInCell;
    }

    public ArrayList<ArrayList<Float>> getEmptyMatrix() {
        for (int y = 0; y < heightInCell; y++) {
            ArrayList<Float> buf = new ArrayList<>();
            for (int x = 0; x < widthInCell; x++) {
                buf.add(0.0f);
            }
            addOrUpdateStrokeOfMap(y, buf);
        }

        return map;
    }

    public ArrayList<ArrayList<Float>> getRandomMatrix() {
        for (int y = 0; y < heightInCell; y++) {
            ArrayList<Float> buf = new ArrayList<>();
            for (int x = 0; x < widthInCell; x++) {
                buf.add((float) Math.random());
            }
            addOrUpdateStrokeOfMap(y, buf);
        }

        return map;
    }

    public ArrayList<ArrayList<Float>> getInterpolateMatrix() {
        float[][] tempMap = new float[heightInCell][widthInCell];
        for (int x = 1; x < widthInCell - 1; ++x)
            for (int y = 1; y < heightInCell - 1; ++y) {
                tempMap[y][x] = (map.get(y).get(x - 1)
                    + map.get(y - 1).get(x - 1) + map.get(y - 1).get(x)
                    + map.get(y - 1).get(x + 1) + map.get(y).get(x + 1)
                    + map.get(y + 1).get(x + 1) + map.get(y + 1).get(x)
                    + map.get(y + 1).get(x - 1) + map.get(y).get(x)) / 9;
            }

        for (int y = 0; y < heightInCell; y++) {
            ArrayList<Float> buf = new ArrayList<>();
            for (int x = 0; x < widthInCell; x++) {
                buf.add(tempMap[y][x]);
            }
            addOrUpdateStrokeOfMap(y, buf);
        }

        return map;
    }

    static ArrayList<ArrayList<Boolean>> getMatrixByMathRandomWithRandomIndentation(int width, int height, float freq,
                                                                                    float surroundingFreq) { // todo: delete this peace of code
        boolean[][] rangeMask = new boolean[height][width];
        ArrayList<ArrayList<Boolean>> res = new ArrayList<>(height);
        for (int i = 0; i < height; i++) {
            ArrayList<Boolean> temp = new ArrayList<>(width);
            for (int j = 0; j < width; j++) {
                boolean isAdded = Math.random() < freq && !rangeMask[i][j];
                temp.add(isAdded);
                if (!isAdded)
                    continue;

                for (int subI = (i != 0 ? i - 1 : i); subI < (i != height - 1 ? i + 2 : i + 1); subI++)
                    for (int subJ = (j != 0 ? j - 1 : j); subJ < (j != width - 1 ? j + 2 : j + 1); subJ++)
                        rangeMask[subI][subJ] = Math.random() < surroundingFreq;
            }
            res.add(temp);
        }

        return res;
    }

    private void addOrUpdateStrokeOfMap(int index, ArrayList<Float> val) {
        if (index < map.size()) {
            map.set(index, val);
        } else {
            map.add(val);
        }
    }
}
