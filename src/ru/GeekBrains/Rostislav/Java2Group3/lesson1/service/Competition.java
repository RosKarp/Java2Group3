package ru.GeekBrains.Rostislav.Java2Group3.lesson1.service;

import ru.GeekBrains.Rostislav.Java2Group3.lesson1.interfaces.ParticipantsAction;
import ru.GeekBrains.Rostislav.Java2Group3.lesson1.model.*;

import java.util.Random;

public class Competition {

    private final Object[] obstacles;
    private final ParticipantsAction[] participantsActions;
    private final Random r;

    private static final int QUANTITY_OF_PARTICIPANTS = 6;
    private static final int QUANTITY_OF_OBSTACLES = 4;

    public Competition() {
        obstacles = fillObstacles();
        participantsActions = fillParticipantsAction();
        r = new Random();
    }

    public void competitionWithoutObstaclesImpl() {
        for (ParticipantsAction participantsAction : participantsActions) {
            for (int j = 0; j < obstacles.length; j += 2) {
                jumpMayBe(participantsAction);
                runMayBe(participantsAction);
            }
        }
    }

    public void competitionWithObstaclesImpl() {
        for (ParticipantsAction participantsAction : participantsActions) {
            for (int j = 0; j < obstacles.length; j++) {
                if (obstacles[j] instanceof Wall) {
                    Wall wall = new Wall();
                    if (participantsAction.getHeightLimitation() > wall.getHeight()) {
                        jump(participantsAction);
                    } else {
                        System.out.println(participantsAction.getName() + " dropped from the wall № " + j);
                        break;
                    }
                } else if (obstacles[j] instanceof Treadmill) {
                    Treadmill treadmill = new Treadmill();
                    if (participantsAction.getLengthLimitation() > treadmill.getLength()) {
                        run(participantsAction);
                    } else {
                        System.out.println(participantsAction.getName() + " dropped from the treadmill № " + j);
                        break;
                    }
                } else {
                    System.out.println("Not have this obstacles type");
                    System.exit(0);
                }
            }
        }
    }

    private Object[] fillObstacles() {
        Object[] o = new Object[QUANTITY_OF_OBSTACLES];
        for (int i = 0; i < QUANTITY_OF_OBSTACLES; i += 2) {
            o[i] = new Wall();
        }
        for (int i = 1; i < QUANTITY_OF_OBSTACLES; i += 2) {
            o[i] = new Treadmill();
        }
        return o;
    }

    private ParticipantsAction[] fillParticipantsAction() {
        ParticipantsAction[] p = new ParticipantsAction[QUANTITY_OF_PARTICIPANTS];
        for (int i = 0; i < QUANTITY_OF_PARTICIPANTS; i += 3) {
            p[i] = new Cat("Cat" + i);
        }
        for (int i = 1; i < QUANTITY_OF_PARTICIPANTS; i += 3) {
            p[i] = new Human("Human" + i);
        }
        for (int i = 2; i < QUANTITY_OF_PARTICIPANTS; i += 3) {
            p[i] = new Robot("Robot" + i);
        }
        return p;
    }

    private boolean canOvercomeObstacle() {
        return r.nextInt(2) == 1;
    }

    private void jump(ParticipantsAction a) {
        a.jump();
    }

    private void run(ParticipantsAction a) {
        a.run();
    }

    private void jumpMayBe(ParticipantsAction a) {
        a.jump(canOvercomeObstacle());
    }

    private void runMayBe(ParticipantsAction a) {
        a.run(canOvercomeObstacle());
    }
}
