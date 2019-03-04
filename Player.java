
import java.util.*;
import java.io.*;

public final class Player {

    private final String name;
    private final String description;
    private final int maxHitPoints;
    private int hitPoints;
    private int numBandages;
    private final int minDamage;
    private final int maxDamage;
    private final Random random = new Random();

    private Player(String name, String description, int maxHitPoints, int minDamage, int maxDamage,
            int numBandages) {
        this.name = name;
        this.description = description;
        this.maxHitPoints = maxHitPoints;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.numBandages = numBandages;
        this.hitPoints = maxHitPoints;
    }

    public int attack() {
        return random.nextInt(maxDamage - minDamage + 1) + minDamage;
    }

    public void defend(Zombie zombie) {
        int attackStrength = zombie.attack();
        hitPoints = (hitPoints > attackStrength) ? hitPoints - attackStrength : 0;
        System.out.printf("  " + name + " is hit for %d HP of damage (%s)\n", attackStrength,
                getStatus());
        if (hitPoints == 0) {
            System.out.println("  " + name + " has been defeated");
        }
    }

    public void heal() {
        if (numBandages > 0) {
            hitPoints = Math.min(maxHitPoints, hitPoints + 20);
            System.out.printf("  %s applies healing bandage (%s, %d Bandages left)\n", name,
                    getStatus(), --numBandages);
        } else {
            System.out.println(" You are out of Bandages!");
        }
    }

    public boolean isAlive() {
        return hitPoints > 0;
    }

    public String getStatus() {
        return "Player HP: " + hitPoints;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public static Player newInstance(){
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try{
        System.out.print("What is your character's name? ");
            String name = in.readLine();
        System.out.println("Which class will your character be? Scout (high damage, low health and number of bandages), Bruiser (High health and number of bandages, low damage), or Survivalist (balanced)?");
            String choice = in.readLine();
        if (choice.equals("Scout")){
            return new Player(name, "A glass cannon who can dish out a lot more damage to their foes than they can take themselves.", 25, 15, 25, 6);
        }
        if (choice.equals("Bruiser")){
            return new Player(name, "A fistfighter who protects himself from the zombies with homemade armour, though this does hamer their physical abilities somewhat.", 65, 5, 12, 12);
        }
        if (choice.equals("Survivalist")){
            return new Player(name, "A jack of all trades who values being able to kill the zombies they encounter, along with possessing the durability to survive them.", 40, 6, 17, 9);
        }
      } catch (IOException e){
          System.out.println("Something went wrong...");
      }
      return new Player("Mighty Thor",
              "a musclebound hulk intent on crushing all evil in his way", 200, 100, 150, 200);
    }
}