import java.util.*;
import java.io.*;

public final class Game {

    private final Player player = Player.newInstance();

    public void play() throws IOException {
        System.out.println("You are " + player + " " + player.getDescription());
        Town.newInstance().startQuest(player);
    }

    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.play();
    }

}
