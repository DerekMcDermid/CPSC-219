
import java.util.*;

/**
 * Developed with reference to https://codereview.stackexchange.com/questions/9999/text-based-rpg-in-java, under the
 * "Monster" class.
 * Creates and updates the instance of the Zombie during gameplay.
 */
public final class Zombie {

    private final String name;
    private int hitPoints;
    private final int minDamage;
    private final int maxDamage;
    private final static Random random = new Random();
    private final static Set<Integer> zombiesSeen = new HashSet<Integer>();
    private final static int NUM_ZOMBIES = 6;
    
    /**
     * Constructs Zombie with given parameters.
     * @param name
     * @param hitPoints
     * @param minDamage a zombie can inflict on a player.
     * @param maxDamage a zombie can inflict on a player.
     */
    public Zombie(String name, int hitPoints, int minDamage, int maxDamage) {
        this.name = name;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.hitPoints = hitPoints;
    }
    
    /**
     * @return zombie identifier.
     */
    @Override
    public String toString() {
        return name;
    }

    public String getStatus() {
        return "Zombie HP: " + hitPoints;
    }
    
    public int getHitPoints() {
    	return hitPoints;
    }
    
    /**
     * Generates a new zombie in rooms, as long as the player has not seen that zombie before.
     * @return Zombie to appear in combat mode.
     */
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
            return new Zombie("Creeper", 40, 8, 12);
        } else if (i == 1) {
            return new Zombie("Rotter", 26, 4, 6);
        } else if (i == 2) {
        	return new Zombie("Tainter", 30, 3, 5);
        } else if (i == 3) {
        	return new Zombie("Chubber", 35, 3, 5);
        } else if (i == 4) {
        	return new Zombie("Squirmer", 21, 3, 6);
        } else {
            return new Zombie("Gnawer", 18, 1, 2);
        }
    }
    
    /**
     * Final zombie to appear and be generated.
     * @return Zombie to appear in combat mode.
     */
    public static Zombie newBossInstance() {
        return new Zombie("Dragon", 60, 10, 20);
    }
    
    /**
     * @return a random, positive integer to be used as an attack value.
     */
    public int attack() {
        return random.nextInt(maxDamage - minDamage + 1) + minDamage;
    }
    
    /**
     * Updates zombie hitPoints following player attack.
     * @param player currently in combat with the zombie.
     */
    public void defend(Player player) {
        int attackStrength = player.attack();
        hitPoints = (hitPoints > attackStrength) ? hitPoints - attackStrength : 0;
        System.out.printf("%s hits %s for %d HP of damage (%s)\n", player, name, attackStrength,
                getStatus());
        if (hitPoints == 0) {
            int outcome = random.nextInt(3 - 1 + 1) + 1;
            System.out.println(FightPrint.printOutcome(outcome, player, name));
        }
    }
    
    /**
     * @return boolean, true if zombie hitPoints are above zero, false otherwise.
     */
    public boolean isAlive() {
        return hitPoints > 0;
    }
}
