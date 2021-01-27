package ru.GeekBrains.Rostislav.Java2Group3.lesson1;

import ru.GeekBrains.Rostislav.Java2Group3.lesson1.service.Competition;

public class AppRun {

    public static void main(String[] args) {
        Competition service = new Competition();
        System.out.println("Skill test.");
        System.out.println();
        service.competitionWithoutObstaclesImpl();
        System.out.println();
        System.out.println();
        System.out.println("Championship.");
        System.out.println();
        service.competitionWithObstaclesImpl();
    }
}
