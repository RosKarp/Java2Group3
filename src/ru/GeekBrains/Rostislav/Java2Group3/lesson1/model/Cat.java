package ru.GeekBrains.Rostislav.Java2Group3.lesson1.model;

import ru.GeekBrains.Rostislav.Java2Group3.lesson1.interfaces.ParticipantsAction;

import java.util.Random;

public class Cat implements ParticipantsAction {

    private final String name;
    private final double lengthLimitation;
    private final double heightLimitation;

    public Cat(String name) {
        Random r = new Random();
        this.name = name;
        this.lengthLimitation = r.nextDouble();
        this.heightLimitation = r.nextDouble();
    }

    public String getName() {
        return name;
    }

    public double getLengthLimitation() {
        return lengthLimitation;
    }

    public double getHeightLimitation() {
        return heightLimitation;
    }

    public void jump(boolean b) {
        if (b) {
            System.out.println(name + " jump");
        } else {
            System.out.println(name + " not jump");
        }
    }

    public void jump() {
        System.out.println(name + " jump");
    }

    public void run(boolean b) {
        if (b) {
            System.out.println(name + " run");
        } else {
            System.out.println(name + " not run");
        }
    }

    public void run() {
        System.out.println(name + " run");
    }
}
