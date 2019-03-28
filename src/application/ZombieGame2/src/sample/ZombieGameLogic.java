package sample;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import sample.characters.Player2D;
import sample.characters.Zombie2D;
import sample.displays.Display;
import sample.displays.PermanentDisplay;
import sample.guns.Bullet2D;
import sample.guns.handguns.M1911;
import sample.guns.rifles.M4;
import sample.guns.submachineguns.Mp5;
import sample.maps.ZombieMap;

import java.util.HashSet;
import java.util.Set;

import static sample.ZombiesPlayerController.handleKeyPresses;

public class ZombieGameLogic {

    private static final int maxZombiesPerRound = 100;
    private static final int zombieReward = 60;
    private static final int hitReward = 10;

    public static Zombie2D spawnZombie(ZombieMap map) {
        Zombie2D zombie2D = new Zombie2D();
        zombie2D.move(map.getZombieSpawn(), map.getObstacles());
        return zombie2D;
    }

    public static Player2D spawnPlayer(ZombieMap map) {
        Player2D player2D = new Player2D(new Mp5());
        player2D.move(map.getPlayerSpawn(), map.getObstacles());
        return player2D;
    }

    public static int getNumberOfZombiesForRound(int round) {
        if(round + 8 < maxZombiesPerRound) return round + 8;
        return maxZombiesPerRound;
    }

    public static boolean chase(Set<Zombie2D> zombies, Player2D player2D, ZombieMap map) {
        for(Zombie2D z : zombies) {
            z.chase(player2D.getLocation(), map.getObstacles());
            if(z.isInsideBoundsOf(player2D)) {
                // need to delay hits
                player2D.takeDamage(z.hit());
                if(!player2D.isAlive()) return true;
            }
        }
        return false;
    }

    public static Set<Zombie2D> damageZombies(ZombieGame game) {
        Set<Zombie2D> killedZombies = new HashSet<>();
        Set<Bullet2D> hits = new HashSet<>();

        for(Zombie2D z : game.getZombies()) {
            for(Bullet2D b : game.getBullets()) {
                if(b.isInsideBoundsOf(z)) {
                    game.getPlayer2D().addMoney(hitReward);
                    z.takeDamage(b.getDamage());
                    hits.add(b);
                    if(!z.isAlive()) killedZombies.add(z);
                }
            }
        }
        game.removeBullets(hits);
        game.getPlayer2D().addMoney(zombieReward * killedZombies.size());
        return killedZombies;
    }

    public static Set<Bullet2D> propagateBullets(Set<Bullet2D> bullets, ZombieMap map) {
        Set<Bullet2D> outOfBoundsBullets = new HashSet<>();
        for(Bullet2D b : bullets) {
            // move bullet along its path
            if(!b.move(b.getTrajectory(), map.getObstacles())) {
                outOfBoundsBullets.add(b);
            } else if (!b.isInsideBoundsOf(map)) {
                outOfBoundsBullets.add(b);
            }
        }
        return outOfBoundsBullets;
    }

    public static void updateDisplay(Set<PermanentDisplay> displays) {
        for(Display d : displays) {
            d.update();
        }
    }

}