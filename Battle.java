import java.util.*;
import java.io.*;

//CITATION: https://codereview.stackexchange.com/questions/9999/text-based-rpg-in-java under "Battle Class" section

public final class Battle {

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
