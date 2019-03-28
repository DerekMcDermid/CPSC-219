package sample;


import javafx.scene.Group;
import sample.characters.Player2D;
import sample.characters.Zombie2D;
import sample.displays.PermanentDisplay;
import sample.drops.Drop;
import sample.guns.Bullet2D;
import sample.maps.ZombieMap;

import java.util.HashSet;
import java.util.Set;

import static sample.ZombieGameLogic.getNumberOfZombiesForRound;

public class ZombieGame {

    private Group root;

    private int round;
    private int zombiesForRound;
    private int spawnedZombies;

    private Set<PermanentDisplay> displays;
    private ZombieMap map;
    private Player2D player2D;
    private Set<Zombie2D> zombies;
    private Set<Zombie2D> deadZombies;
    private Set<Drop> drops;
    private Set<Bullet2D> bullets;

    public ZombieGame() {
        root = new Group();
        zombies = new HashSet<>();
        deadZombies = new HashSet<>();
        drops = new HashSet<>();
        bullets = new HashSet<>();
        displays = new HashSet<PermanentDisplay>();
        round = 0;
        spawnedZombies = 0;
    }

    public void addPermanentDisplay(PermanentDisplay display) {
        displays.add(display);
        root.getChildren().add(display);
    }

    public Set<PermanentDisplay> getPermanentDisplays() {
        return displays;
    }

    public void addPlayer(Player2D player2D) {
        root.getChildren().add(player2D);
        this.player2D = player2D;
    }

    public Player2D getPlayer2D() {
        return player2D;
    }

    public void addMap(ZombieMap map) {
        root.getChildren().add(map);
        this.map = map;
    }

    public ZombieMap getMap() {
        return map;
    }

    public int getSpawnedZombies() {
        return spawnedZombies;
    }

    public void addZombie(Zombie2D zombie2D) {
        root.getChildren().add(zombie2D);
        zombies.add(zombie2D);
        spawnedZombies += 1;
    }

    public void removeZombie(Zombie2D zombie2D) {
        root.getChildren().remove(zombie2D);
        zombies.remove(zombie2D);
    }

    public void removeZombies(Set<Zombie2D> zombies) {
        root.getChildren().removeAll(zombies);
        this.zombies.removeAll(zombies);
    }

    public void addDeadZombies(Set<Zombie2D> zombies) {
        root.getChildren().removeAll(zombies);
        this.zombies.removeAll(zombies);
        this.deadZombies.addAll(zombies);
    }

    public Set<Zombie2D> getDeadZombies() {
        return deadZombies;
    }

    public Set<Zombie2D> getZombies() {
        return zombies;
    }

    public void addDrop(Drop drop) {
        root.getChildren().add(drop);
        drops.add(drop);
    }

    public void removeDrop(Drop drop) {
        root.getChildren().remove(drop);
        drops.remove(drop);
    }

    public void addBullet(Bullet2D bullet2D) {
        root.getChildren().add(bullet2D);
        bullets.add(bullet2D);
    }

    public void removeBullet(Bullet2D bullet2D) {
        root.getChildren().remove(bullet2D);
        bullets.remove(bullet2D);
    }

    public Set<Bullet2D> getBullets() {
        return bullets;
    }

    public void setBullets(Set<Bullet2D> bullets) {
        this.bullets = bullets;
    }

    public void removeBullets(Set<Bullet2D> bullets) {
        root.getChildren().removeAll(bullets);
        this.bullets.removeAll(bullets);
    }

    public int getRound() {
        return round;
    }

    public void nextRound() {
        round += 1;
        zombiesForRound = getNumberOfZombiesForRound(round);
        spawnedZombies = 0;
        root.getChildren().removeAll(zombies);
        zombies.clear();
        deadZombies.clear();
    }

    public Group getRoot() {
        return root;
    }
}