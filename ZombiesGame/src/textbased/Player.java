import java.util.*;
import java.io.*;

/**
 * Developed with reference to https://codereview.stackexchange.com/questions/9999/text-based-rpg-in-java, under the
 * "Player" class.
 * Creates and updates the instance of the Player during gameplay.
 */
public final class Player {

    private final String name;
    private final String description;
    private final int maxHitPoints;
    private int hitPoints;
    private int numBandages;
    private final int minDamage;
    private final int maxDamage;
    private final Random random = new Random();
    
    /**
     * 
     * @param name
     * @param description
     * @param maxHitPoints
     * @param minDamage the player can inflict on a zombie.
     * @param maxDamage the player can inflict on a zombie.
     * @param numBandages
     */
    public Player(String name, String description, int maxHitPoints, int minDamage, int maxDamage,
            int numBandages) {
        this.name = name;
        this.description = description;
        this.maxHitPoints = maxHitPoints;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.numBandages = numBandages;
        this.hitPoints = maxHitPoints;
    }
    
    public String getStatus() {
        return "Player HP: " + hitPoints;
    }
    
    /**
     * For testing the methods heal, defend, and isAlive, makes hitPoints manipulatable in PlayerTest.
     * @param amount to set player health.
     */
    public void setStatus(int amount) {
    	hitPoints = amount;
    }
    

    public int getHitPoints() {
    	return hitPoints;
    }
    

    public int getNumBandages() {
    	return numBandages;
    }
    
    /**
     * @return name inputed by user.
     */
    @Override
    public String toString() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    
    /**
     * @return a random, positive integer to be used as an attack value.
     */
    public int attack() {
        return random.nextInt(maxDamage - minDamage + 1) + minDamage;
    }
    
    /**
     * Updates player hitPoints following zombie attack.
     * @param zombie currently in combat with the player.
     */
    public void defend(Zombie zombie) {
        int attackStrength = zombie.attack();
        hitPoints = (hitPoints > attackStrength) ? hitPoints - attackStrength : 0;
        System.out.printf(name + " is hit for %d HP of damage (%s)\n\n", attackStrength,
                getStatus());
        if (hitPoints == 0) {
            System.out.println(name + " has been defeated\n\n");
        }
    }
    
    /**
     * Increases player hitPoints when bandages are "applied", as long as there are bandages available and healing
     * won't increase hitPoints above the character class' max hitPoint value.
     */
    public void heal() {
        if (numBandages > 0) {
            hitPoints = Math.min(maxHitPoints, hitPoints + 20);
            System.out.printf("%s applies healing bandage (%s, %d Bandages left)\n\n", name,
                    getStatus(), --numBandages);
        } else {
            System.out.println("You are out of Bandages!");
        }
    }

    /**
     * @return boolean, true if player hitPoints are above zero, false otherwise.
     */
    public boolean isAlive() {
        return hitPoints > 0;
    }

    /**
     * Using user input to create the requested player type.
     * @return Player 
     */
    public static Player newInstance(){
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try{
        System.out.print("What is your character's name? \n");
            String name = in.readLine();
        System.out.println("Which class will your character be? Scout (high damage, low health), Bruiser (low damage, high health), or Survivalist (balanced)?");
            String choice = in.readLine();
        if (choice.equals("Scout")){
            return new Player(name, "the Scout", 25, 15, 25, 6);
        }
        if (choice.equals("Bruiser")){
            return new Player(name, "the Bruiser", 65, 5, 12, 12);
        }
        if (choice.equals("Survivalist")){
            return new Player(name, "the Survivalist", 40, 6, 17, 9);
        }
      } catch (IOException e){
          System.out.println("Something went wrong...");
      }
      return new Player("the Mighty Thor!",
              "A musclebound hulk intent on crushing all evil that stands in his way!", 200, 100, 150, 200);
    }
}
