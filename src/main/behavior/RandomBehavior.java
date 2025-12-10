package main.behavior;

import main.model.Boid;
import main.simulation.Forces;
import main.simulation.Vector2D;

import java.util.List;
import java.util.Random;

public class RandomBehavior implements BehaviorStrategy {
private static final double MAX_TURN_ANGLE = Math.toRadians(15); 
    private static final double MAX_FORCE = 0.03;                    

    private final Random random = new Random();

    @Override
    public Forces calculateForces(Boid boid, List<Boid> neighbors) {
        double vx = boid.getVx();
        double vy = boid.getVy();

        double speed = Math.sqrt(vx * vx + vy * vy);
        if (speed == 0) {
           
            speed = 1.0;
            vx = speed;
            vy = 0.0;
        }

        double angle = Math.atan2(vy, vx);

        
        double delta = (random.nextDouble() * 2.0 - 1.0) * MAX_TURN_ANGLE;
        double newAngle = angle + delta;

        double desiredVx = Math.cos(newAngle) * speed;
        double desiredVy = Math.sin(newAngle) * speed;

      
        double steerX = desiredVx - vx;
        double steerY = desiredVy - vy;

        double mag = Math.sqrt(steerX * steerX + steerY * steerY);
        if (mag > MAX_FORCE) {
            steerX = steerX / mag * MAX_FORCE;
            steerY = steerY / mag * MAX_FORCE;
        }

        Vector2D randomSteer = new Vector2D(steerX, steerY);


        return new Forces(randomSteer, Vector2D.ZERO, Vector2D.ZERO);
    }
}