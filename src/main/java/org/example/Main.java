package org.example;


public class Main {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 500;

    public static void main(String[] args) {
        ThreadPanel tp = new ThreadPanel();
        tp.start();
    }
}
