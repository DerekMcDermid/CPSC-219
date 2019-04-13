import java.io.*;

/**
 * Developed with reference to https://codereview.stackexchange.com/questions/9999/text-based-rpg-in-java, under the
 * "Game" class.
 * Initializes and starts up the game.
 */
public final class Game {
    private final Player player = Player.newInstance();

    /**
     * Sets up town map and starts gameplay.
     * @throws IOException
     */
    public void play() throws IOException {
        System.out.println("You are " + player + " " + player.getDescription() + "\n\n");
        Town.newInstance().startQuest(player);
    }

    public static void main(String[] args) {
        Game game = new Game();
        try{
          game.play();
        } catch (IOException ioe){
            System.out.println("There was an error in starting the game. Please try running again.");
            System.exit(0);
        }
    }
}
