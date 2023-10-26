package org.example;


public class Main {
    public static final int WIDTH_SETTINGS = 300;
    public static final int WIDTH_IMAGE = 600;
    public static final int HEIGHT_IMAGE = 500;

    public static void main(String[] args) {
        ThreadPanel tp = new ThreadPanel();
        tp.start();
    }
}
