package sample.characters;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import sample.Moveable;
import sample.maps.ZombieMap;
import sample.maps.obstacles.Obstacle;

import java.util.Set;

import static sample.characters.Zombie2D.Zombie2DBuilder.buildZombieHead;
import static sample.characters.Zombie2D.Zombie2DBuilder.buildZombieLeftArm;
import static sample.characters.Zombie2D.Zombie2DBuilder.buildZombieRightArm;

public class Zombie2D extends Moveable {

    private static final int maxHealth = 100;
    private static final int hitDamage = 25;
    private int health;
    private double walkSpeed;
    private Circle head;

    public Zombie2D() {
        this.head = buildZombieHead();
        this.getChildren().add(head);
        this.getChildren().add(buildZombieLeftArm());
        this.getChildren().add(buildZombieRightArm());

        health = maxHealth;
        walkSpeed = 0.5;
    }

    public int hit(){
        return hitDamage;
    }

    public void takeDamage(int damage) {
        health -= damage;
    }

    public boolean isAlive() {
        if(health > 0) return true;
        return false;
    }

    public void chase(Point2D playerLocation, Set<Obstacle> obstacles) {
        double xDistance = playerLocation.getX() - getLocation().getX();
        double yDistance = playerLocation.getY() - getLocation().getY();

        double xSquared = Math.pow(xDistance, 2);
        double ySquared = Math.pow(yDistance, 2);
        double magnitude = Math.sqrt(xSquared + ySquared);

        double xStep = xDistance / magnitude * walkSpeed;
        double yStep = yDistance / magnitude * walkSpeed;

        move(new Point2D(xStep, yStep), obstacles);

    }

    @Override
    public Point2D getLocation() {
        Bounds bounds = head.localToScene(head.getBoundsInLocal());
        double x = (bounds.getMinX() + bounds.getMaxX()) / 2.0;
        double y = (bounds.getMinY() + bounds.getMaxY()) / 2.0;
        return new Point2D(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        return false;
    }

    public static class Zombie2DBuilder {

        private static final int radius = 15;
        private static final int armLength = 30;
        private static final int armWidth = 2;

        public static Circle buildZombieHead() {
            Circle head = new Circle(0, 0, radius);
            head.setFill(Color.GREEN);
            head.setStroke(Color.BLACK);
            head.setStrokeWidth(5);
            return head;
        }

        public static Line buildZombieLeftArm() {
            Line leftArm = new Line(-radius, 0, -radius, armLength);
            leftArm.setStrokeWidth(armWidth);
            return leftArm;
        }

        public static Line buildZombieRightArm() {
            Line rightArm = new Line(radius, 0, radius, armLength);
            rightArm.setStrokeWidth(armWidth);
            return rightArm;
        }
    }


}