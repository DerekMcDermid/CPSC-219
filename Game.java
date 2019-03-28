import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

// image credits:
// Zombie GIF: https://wifflegif.com/tags/2505-zombie-gifs?page=79
// Player Sprites:
public class Game extends Application {

    Random rnd = new Random();

    Pane playfieldLayer;
    Pane scoreLayer;

    Image playerImage;
    Image zombieImage;
    Image levelImage;

    List<Player> players = new ArrayList<>();
    List<Zombie> zombies = new ArrayList<>();

    Text collisionText = new Text();
    boolean collision = false;

    Scene scene;

    @Override
    public void start(Stage primaryStage) {
    	
        Group root = new Group();

        // create layers
        playfieldLayer = new Pane();
        scoreLayer = new Pane();

        root.getChildren().add(playfieldLayer);
        root.getChildren().add(scoreLayer);
        

        scene = new Scene(root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
        levelImage = new Image(getClass().getResource("level.png").toExternalForm());
        
       
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene( scene);
        primaryStage.show();

        loadGame();
        createPlayers();

        AnimationTimer gameLoop = new AnimationTimer() {

            @Override
            public void handle(long now) {

                // player input
                players.forEach(sprite -> sprite.processInput());

                // add random zombies
                spawnEnemies(true);

                // movement
                players.forEach(sprite -> sprite.move());
                zombies.forEach(sprite -> sprite.move());

                // check collisions
                checkCollisions();

                // update sprites in scene
                players.forEach(sprite -> sprite.updateUI());
                zombies.forEach(sprite -> sprite.updateUI());

                // check if sprite can be removed
                zombies.forEach(sprite -> sprite.checkRemovability());

                // remove removables from list, layer, etc
                removeSprites( zombies);
            }

        };
        gameLoop.start();

    }

    // Retrieves game images for player and zombie.
    private void loadGame() {
        playerImage = new Image( getClass().getResource("player.png").toExternalForm());
        zombieImage = new Image( getClass().getResource("enemy.png").toExternalForm());
    }
    
    
    private void createPlayers() {

        // player input
        Input input = new Input( scene);

        // register input listeners
        input.addListeners(); 

        Image image = playerImage;

        // center horizontally, position at 70% vertically, so the player doesn't spawn directly 
        // in front of zombies.
        double x = (Settings.SCENE_WIDTH - image.getWidth()) / 2.0;
        double y = Settings.SCENE_HEIGHT * 0.7;

        // create player
        Player player = new Player(playfieldLayer, image, x, y, 0, 0, Settings.PLAYER_HEALTH, 0, Settings.PLAYER_SPEED, input);

        // register player
        players.add( player);

    }

    /*
     * Randomly places enemies on the map
     */
    private void spawnEnemies( boolean random) {

        if( random && rnd.nextInt(Settings.ENEMY_SPAWN_RANDOMNESS) != 0) {
            return;
        }

        // image
        Image image = zombieImage;

        // random speed
        double speed = rnd.nextDouble() * 1.0 + 2.0;

        // x position range: zombie is always fully inside the screen, no part of it is outside
        // y position: right on top of the view, so that it becomes visible with the next game iteration
        double x = rnd.nextDouble() * (Settings.SCENE_WIDTH - image.getWidth());
        double y = -image.getHeight();

        // create a sprite
        Zombie zombie = new Zombie(playfieldLayer, image, x, y, 0, speed, 1,1);

        // manage sprite
        zombies.add(zombie);
    }

    /*
     * Clears sprites from screen and sprite list.
     */
    private void removeSprites(List<? extends SpriteBase> spriteList) {
        Iterator<? extends SpriteBase> iter = spriteList.iterator();
        while( iter.hasNext()) {
            SpriteBase sprite = iter.next();

            if( sprite.isRemovable()) {

                // remove from layer
                sprite.removeFromLayer();

                // remove from list
                iter.remove();
            }
        }
    }

    /*
     * Checks for collisions between sprites.
     */
    private void checkCollisions() {

        collision = false;

        for( Player player: players) {
            for( Zombie zombie: zombies) {
                if( player.collidesWith(zombie)) {
                    collision = true;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
