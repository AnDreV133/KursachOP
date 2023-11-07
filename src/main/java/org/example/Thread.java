package org.example;

public class Thread extends java.lang.Thread {
    @Override
    public void run() {
        new SettingsPanel();
    }
}
