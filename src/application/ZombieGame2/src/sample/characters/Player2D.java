package sample.characters;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import sample.Moveable;
import sample.guns.Bullet2D;
import sample.guns.Gun;
import sample.maps.obstacles.Obstacle;

import java.util.Random;
import java.util.Set;

import static sample.characters.Player2D.Player2DBuilder.*;

public class Player2D extends Moveable {

    private static final int maxhealth = 100;
    private int health;
    private Gun gun;
    private Circle head;
    private int money;

    public Player2D(Gun gun) {
        this.head = buildPlayerHead();
        this.money = 0;
        this.getChildren().add(head);
        this.getChildren().add(buildPlayerLeftArm());
        this.getChildren().add(buildPlayerRightArm());
        setGun(gun);

        health = maxhealth;
    }

    public void takeDamage(int amount) {
        health -= amount;
    }

    public boolean isAlive() {
        if(health > 0) return true;
        return false;
    }

    public void addMoney(int amount) {
        money += amount;
    }

    public int getMoney() {
        return money;
    }

    public int spendMoney(int amount) {
        if(amount > money) return 0;
        money -= amount;
        return amount;
    }

    public void setGun(Gun gun) {
        if(gun == null) return;
        if(this.gun == gun) {
            this.gun.replenishReserveAmmo();
            return;
        }
        if(this.gun != null) {
            getChildren().remove(this.gun);
        }
        this.gun = gun;
        this.getChildren().add(this.gun);
        equipGun();
    }

    public Gun getGun() {
        return gun;
    }

    public void equipGun() {
        gun.place(new Point2D(0, armLength), 0);
    }

    public Bullet2D shoot() {
        Bullet2D bullet2D = gun.shoot();
        if(bullet2D == null) return bullet2D;

        // set bullet direction
        Point2D direction = aim();
        bullet2D.setDirection(direction);

        // move bullet to end of barrel
        double lengthToBullet = armLength + gun.getGunLength();

        // need to add center of player to both
        double playerX = getLocation().getX();
        double playerY = getLocation().getY();

        double bulletX = lengthToBullet * direction.getX() + playerX;
        double bulletY = lengthToBullet * direction.getY() + playerY;
        Point2D startingPoint = new Point2D(bulletX, bulletY);
        bullet2D.place(startingPoint, 0);

        return bullet2D;
    }

    public Point2D aim() {
        Random rand = new Random();
        int offset = rand.nextInt(gun.getRecoil());
        double angle;
        if((System.currentTimeMillis() & 1) == 0) {
            angle = Math.toRadians(getAngle() + offset);
        } else  {
            angle = Math.toRadians(getAngle() - offset);
        }
        return new Point2D(-Math.sin(angle),Math.cos(angle));
    }

    public void reload() {
        gun.reload();
    }

    @Override
    public boolean move(Point2D point2D, Set<Obstacle> obstacles) {
        double x = point2D.getX() * gun.getGunRunSpeed();
        double y = point2D.getY() * gun.getGunRunSpeed();
        return super.move(new Point2D(x, y), obstacles);
    }

    @Override
    public Point2D getLocation() {
        Bounds bounds = head.localToScene(head.getBoundsInLocal());
        double x = (bounds.getMinX() + bounds.getMaxX()) / 2.0;
        double y = (bounds.getMinY() + bounds.getMaxY()) / 2.0;
        return new Point2D(x, y);
    }

    public static class Player2DBuilder {

        private static final double radius = 15;
        public static final double armLength = 30;
        private static final double armWidth = 2;

        public static Circle buildPlayerHead() {
            Circle head = new Circle(0, 0, radius);
            head.setFill(Color.TRANSPARENT);
            head.setStroke(Color.BLACK);
            head.setStrokeWidth(5);
            return head;
        }

        public static Line buildPlayerLeftArm() {
            Line leftArm = new Line(-radius, 0, 0, armLength);
            leftArm.setStrokeWidth(armWidth);
            return leftArm;
        }

        public static Line buildPlayerRightArm() {
            Line rightArm = new Line(radius, 0, 0, armLength);
            rightArm.setStrokeWidth(armWidth);
            return rightArm;
        }

    }

}