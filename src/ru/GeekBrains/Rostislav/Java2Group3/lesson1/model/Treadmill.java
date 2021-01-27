package ru.GeekBrains.Rostislav.Java2Group3.lesson1.model;

import java.util.Random;

public class Treadmill {

    private final double length;

    public Treadmill() {
        Random r = new Random();
        this.length = r.nextDouble();
    }

    public double getLength() {
        return length;
    }
}
