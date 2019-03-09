import java.util.*;
import java.io.*;

//CITATION: https://codereview.stackexchange.com/questions/9999/text-based-rpg-in-java under the "Game" code section

public final class Game {
    private final Player player = Player.newInstance();

    public void play() throws IOException {
        System.out.println("You are " + player + " " + player.getDescription() + "\n\n");
        Town.newInstance().startQuest(player);
    }

    public static void main(String[] args) {
        Game game = new Game();
        try{
          game.play();
        } catch (IOException e){
            System.out.println("Something went wrong... please restart..");
        }
    }
}
