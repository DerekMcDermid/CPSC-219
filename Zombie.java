import java.util.*;
import java.io.*;

public final class Zombie {

    private final String name;
    private int hitPoints;
    private final int minDamage;
    private final int maxDamage;
    private final static Random random = new Random();
    private final static Set<Integer> zombiesSeen = new HashSet<Integer>();
    private final static int NUM_ZOMBIES = 3;

    public static Zombie newRandomInstance() {
        if (zombiesSeen.size() == NUM_ZOMBIES) {
            zombiesSeen.clear();
        }
        int i;
        do {
            i = random.nextInt(NUM_ZOMBIES);
        } while (zombiesSeen.contains(i));
        zombiesSeen.add(i);

        if (i == 0) {
            return new Zombie("Harpy", 40, 8, 12);
        } else if (i == 1) {
            return new Zombie("Gargoyle", 26, 4, 6);
        } else {
            return new Zombie("Hobgoblin", 18, 1, 2);
        }
    }

    public static Zombie newBossInstance() {
        return new Zombie("Dragon", 60, 10, 20);
    }

    private Zombie(String name, int hitPoints, int minDamage, int maxDamage) {
        this.name = name;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.hitPoints = hitPoints;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getStatus() {
        return "Zombie HP: " + hitPoints;
    }

    public int attack() {
        return random.nextInt(maxDamage - minDamage + 1) + minDamage;
    }

    public void defend(Player player) {
        int attackStrength = player.attack();
        hitPoints = (hitPoints > attackStrength) ? hitPoints - attackStrength : 0;
        System.out.printf("  %s hits %s for %d HP of damage (%s)\n", player, name, attackStrength,
                getStatus());
        if (hitPoints == 0) {
            System.out.println("  " + player + " transforms the skull of " + name
                    + " into a red pancake with his stone hammer");
        }
    }

    public boolean isAlive() {
        return hitPoints > 0;
    }

}
