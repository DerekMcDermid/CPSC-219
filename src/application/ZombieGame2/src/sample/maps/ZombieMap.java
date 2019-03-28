package sample.maps;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import sample.maps.buyables.BuyableObject;
import sample.maps.obstacles.Obstacle;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ZombieMap extends Map {

    private int width;
    private int height;
    private Point2D playerSpawn;
    private List<Point2D> zombieSpawns;
    private Set<Obstacle> obstacles;
    private Set<BuyableObject> buyables;

    public ZombieMap(int width,
                     int height,
                     Point2D playerSpawn,
                     List<Point2D> zombieSpawns
    ) {
        this.width = width;
        this.height = height;
        this.playerSpawn = playerSpawn;
        this.zombieSpawns = zombieSpawns;
        this.obstacles = new HashSet<>();
        this.buyables = new HashSet<>();
    }

    public Point2D getPlayerSpawn() {
        return playerSpawn;
    }

    public Point2D getZombieSpawn() {
        if(zombieSpawns.size() == 0) return null;
        Random rand = new Random();
        int spawnIndex = rand.nextInt(zombieSpawns.size());
        return zombieSpawns.get(spawnIndex);
    }

    public Set<Obstacle> getObstacles() {
        return obstacles;
    }

    public void setObstacles(Set<Obstacle> obstacles) {
        this.obstacles = obstacles;
        getChildren().addAll(obstacles);
    }

    public void addObstacle(Obstacle obstacle, Point2D position) {
        this.obstacles.add(obstacle);
        getChildren().add(obstacle);
        obstacle.place(position, 90);
    }

    public void addBuyable(BuyableObject buyable, Point2D position) {
        this.buyables.add(buyable);
        getChildren().add(buyable);
        buyable.place(position, 0);
    }

    public Set<BuyableObject> getBuyables() {
        return buyables;
    }

    @Override
    public Bounds getBounds() {
        return new BoundingBox(0, 0, 0, width, height, 0);
    }
}