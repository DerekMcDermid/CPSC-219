import java.util.*;
import java.io.*;

public final class Battle {

    public Battle(Player player, Zombie zombie) throws IOException {
        System.out.println("You encounter " + zombie + ": " + "\n");
        System.out.println("Battle with " + zombie + " starts (" + player.getStatus() + " / "
                + zombie.getStatus() + ")");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (player.isAlive() && zombie.isAlive()) {
            System.out.print("Attack (a) or heal (h)? ");
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
