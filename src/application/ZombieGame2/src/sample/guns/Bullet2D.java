package sample.guns;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import sample.GameObject;
import sample.Moveable;

import static sample.guns.Bullet2D.Bullet2DBuilder.buildBullet;

public class Bullet2D extends Moveable {

    private int speed;
    private int damage;
    private Point2D direction;

    public Bullet2D(int speed, int damage) {
        this.speed = speed;
        this.damage = damage;
        this.getChildren().add(buildBullet());
    }

    public int getDamage() {
        return damage;
    }

    public void setDirection(Point2D point2D) {
        direction = point2D;
    }

    public Point2D getDirection() {
        return direction;
    }

    public Point2D getTrajectory() {
        return new Point2D(direction.getX() * speed, direction.getY() * speed);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        return false;
    }

    static class Bullet2DBuilder {

        private static final int bulletLength = 3;
        private static final Color bulletColor = Color.RED;
        private static final int bulletWidth = 2;

        public static Line buildBullet() {
            Line bullet = new Line(0, 0, 0, bulletLength);
            bullet.setStroke(bulletColor);
            bullet.setStrokeWidth(bulletWidth);
            return bullet;
        }

    }

}
