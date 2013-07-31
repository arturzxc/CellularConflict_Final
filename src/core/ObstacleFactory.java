/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import controllers.Settings;
import core.physics.Physics;
import java.util.Random;

/**
 * Creates and adds obstacles to the physics object.
 *
 * @author Artur Krzynowek
 */
public class ObstacleFactory {

    private Random gen;
    private Physics physics;

    public ObstacleFactory() {
        physics = Physics.getInstance();
        gen = new Random();

    }

    public void generateObstacles(int n) {
        for (int i = 0; i < n; i++) {
            physics.add(new Obstacle(physics.nextIndex(),
                    gen.nextInt(Settings.getInstance().getGridWidth() - 50) + 20,
                    gen.nextInt(Settings.getInstance().getGridHeight() - 50) + 20,
                    gen.nextInt(20) + 20,
                    gen.nextInt(20) + 20));
        }


    }

    public void generateSinglObstacle(int x, int y, int w, int h) {
        physics.add(new Obstacle(physics.nextIndex(),
                x,
                y,
                w,
                h));


    }
}
