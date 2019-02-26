import java.util.*;
import java.io.*;

public final class Zombie {

    private final String name;
    private int hitPoints;
    private final int minDamage;
    private final int maxDamage;
    private final static Random random = new Random();
    private final static Set<Integer> zombiesSeen = new HashSet<Integer>();
    private final static int NUM_ZOMBIES = 6;

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
        System.out.printf("    %s hits %s for %d HP of damage (%s)\n", player, name, attackStrength,
                getStatus());
        if (hitPoints == 0) {
            int outcome = random.nextInt(3 - 1 + 1) + 1;
            
            if (player.getDescription() == "a hard hitting but lightly protected scout"){
                if (outcome == 1){
                    System.out.println("    " + player + " lands a direct shot on the " + name + " with their shotgun balsting it apart");
                }
                if (outcome == 2){
                    System.out.println("    " + player + " hits the " + name + " with the butt end of their shotgun creating an audible crunch as inpacts");
                }
                if (outcome == 3){
                    System.out.println("    " + player + " Shoots the " + name + " in the head destroying its skull ");
                }
            }

            if (player.getDescription() == "a large brute capable of taking harder hits than you can give out"){
                if (outcome == 1){
                    System.out.println("    " + player + " kicks the " + name + "hard in the chest sending the" + name + "across the room");
                }
                if (outcome == 2){
                    System.out.println("    " + player + " grabs the " + name + " and slams it onto the foor so hard that the boards crack beneath the zombie");
                }
                if (outcome == 3){
                    System.out.println("    " + player + " kicksthe zombie's legs out causing the " + name + "to fall over then " + player + " Stomps on the zombie");
                }
            }

            if (player.getDescription() == "a well rounded fighter"){
                if (outcome == 1){
                    System.out.println("    " + player + " swings their woodcutter's axe over their head then brings it down on the zombie's head");
                }
                if (outcome == 2){
                    System.out.println("    " + player + " swings their axe hiting the " + name + "in the side and chopping it in half");
                }
                if (outcome == 3){
                    System.out.println("    " + player + " hits the zombie in the jaw with the backside of their axe breifly launching it in the air"); 
                }
            }
            if (player.getDescription() == "a musclebound hulk intent on crushing all evil in his way"){
                System.out.println("    " + player + " incerates the zombie with a lightning strike");
            }
        }
    }

    public boolean isAlive() {
        return hitPoints > 0;
    }

}
