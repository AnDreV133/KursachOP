package org.example.Assets;

import java.awt.image.BufferedImage;

public abstract class Asset {
    private final int sideSize;

    public Asset(int sideSize) {
        this.sideSize = sideSize;
    }


    public abstract BufferedImage getImage();

    public int getSideSize() {
        return sideSize;
    }
}
