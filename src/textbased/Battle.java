import java.io.*;

/**
 * Developed with reference to https://codereview.stackexchange.com/questions/9999/text-based-rpg-in-java, under the
 * "Battle" class.
 * Manages the battle scenes based on player input.
 */
public final class Battle { 
	
    /**
     * Keeps battles ongoing by repeatedly asking for player input and executing appropriate attack or
     * defend methods for both the zombie and player.
     * @param player
     * @param zombie
     * @throws IOException
     */
    public Battle(Player player, Zombie zombie) throws IOException {
        System.out.println("You encounter " + zombie + ": " + "\n");
        System.out.println("Battle with " + zombie + " starts (" + player.getStatus() + " / "
                + zombie.getStatus() + ")");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (player.isAlive() && zombie.isAlive()) {
            System.out.print("Attack (a) or heal (h)? \n\n");
            String action = in.readLine();
            if (action.equals("h")) {
                player.heal();
            } else {
                zombie.defend(player);
            }
            if (zombie.isAlive()) {
                player.defend(zombie);
            }
        }
    }

}
