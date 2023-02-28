package com.example.finalproject;

public class RecModel {
    String appName;
    int image;

    public RecModel(String appName, int image) {
        this.appName = appName;
        this.image = image;
    }

    public String getAppName() {
        return appName;
    }

    public int getImage() {
        return image;
    }
}
