package ru.GeekBrains.Rostislav.Java2Group3.lesson1.model;

import java.util.Random;

public class Wall {

    private final double height;

    public Wall() {
        Random r = new Random();
        this.height = r.nextDouble();
    }

    public double getHeight() {
        return height;
    }
}
