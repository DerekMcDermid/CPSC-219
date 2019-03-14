import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Following code is based off of Jewelsea's character movemnt
 * Hold down one of the WASD keys to have your hero move around the screen.
 * Hold down the shift key to have the hero run.
 */
public class CharacterMovement extends Application {

    // Create a window
    private static final double W = 600, H = 400;


    // get icons for game objects and attach them to nodes
    private static final String HERO_IMAGE_LOC =
            "http://icons.iconarchive.com/icons/raindropmemory/legendora/64/Hero-icon.png";

    private Image heroImage;
    private Node  hero;

    private static final String ZOMBIE_IMAGE_LOC =
            "http://icons.iconarchive.com/icons/papirus-team/papirus-apps/48/blobby-icon.png";

    private Image zombieImage;
    private Node  zombie;

     private static final String DOOR_IMAGE_LOC =
            "http://icons.iconarchive.com/icons/google/noto-emoji-objects/96/62991-door-icon.png";

    private Image doorImage;
    private Node  door;


    //Movement variables
    boolean running, goNorth, goSouth, goEast, goWest, runningZ, goNorthZ, goSouthZ, goEastZ, goWestZ;

    //create a Stage with the game objects on it
    @Override
    public void start(Stage stage) throws Exception {
        heroImage = new Image(HERO_IMAGE_LOC);
        zombieImage = new Image(ZOMBIE_IMAGE_LOC);
        doorImage = new Image(DOOR_IMAGE_LOC);
        door = new ImageView(doorImage);
        hero = new ImageView(heroImage);
        zombie = new ImageView(zombieImage);

        Group dungeon = new Group(door, zombie, hero);

        moveHeroTo(W / 2, H / 2);

        Scene scene = new Scene(dungeon, W, H, Color.FORESTGREEN);

        //Create Movement keys for moving objects
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W: goNorth = true; break;
                    case S: goSouth = true; break;
                    case A: goWest  = true; break;
                    case D: goEast  = true; break;
                    case SHIFT: running = true; break;
                    
                    case UP: goNorthZ = true; break;
                    case DOWN: goSouthZ = true; break;
                    case LEFT: goWestZ  = true; break;
                    case RIGHT: goEastZ  = true; break;
                    case CONTROL: runningZ = true; break;
                }
            }
        });

        //Stop movement when chracter key is relased 
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W: goNorth = false; break;
                    case S: goSouth = false; break;
                    case A: goWest  = false; break;
                    case D: goEast  = false; break;
                    case SHIFT: running = false; break;

                    case UP:    goNorthZ = false; break;
                    case DOWN:  goSouthZ = false; break;
                    case LEFT:  goWestZ  = false; break;
                    case RIGHT: goEastZ  = false; break;
                    case CONTROL: runningZ = false; break;
                }
            }
        });

        //show Scene 
        stage.setScene(scene);
        stage.show();

        //move the coords of the objects when the movent variables are true 
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                int dx = 0, dy = 0, dxz = 0, dyz = 0;

                if (goNorth) dy -= 1;
                if (goSouth) dy += 1;
                if (goEast)  dx += 1;
                if (goWest)  dx -= 1;
                if (running) { dx *= 3; dy *= 3; }
                moveHeroBy(dx, dy);

                if (goNorthZ) dyz -= 1;
                if (goSouthZ) dyz += 1;
                if (goEastZ)  dxz += 1;
                if (goWestZ)  dxz -= 1;
                if (runningZ) { dxz *= 3; dyz *= 3; }
                moveZombieBy(dxz, dyz);
            }
        };
        timer.start();
    }

    // Method that moves icon of the Hero by set amounts 
    private void moveHeroBy(int dx, int dy) {
        if (dx == 0 && dy == 0) return;

        final double cx = hero.getBoundsInLocal().getWidth()  / 2;
        final double cy = hero.getBoundsInLocal().getHeight() / 2;

        double x = cx + hero.getLayoutX() + dx;
        double y = cy + hero.getLayoutY() + dy;

        moveHeroTo(x, y);
    }

    // Does not allow the hero to move out of the window, and determines the direction in which the hero moves
    private void moveHeroTo(double x, double y) {
        final double cx = hero.getBoundsInLocal().getWidth()  / 2;
        final double cy = hero.getBoundsInLocal().getHeight() / 2;

        if (x - cx >= 0 &&
            x + cx <= W &&
            y - cy >= 0 &&
            y + cy <= H) {
            hero.relocate(x - cx, y - cy);
        }
    }

    //movet the zombie a set amount depending of if it is running or not
    private void moveZombieBy(int dx, int dy) {
        if (dx == 0 && dy == 0) return;

        final double cx = zombie.getBoundsInLocal().getWidth()  / 2;
        final double cy = zombie.getBoundsInLocal().getHeight() / 2;

        double x = cx + zombie.getLayoutX() + dx;
        double y = cy + zombie.getLayoutY() + dy;

        moveZombieTo(x, y);
        
    }

    // prevents the zombie from leaving the window
    private void moveZombieTo(double x, double y) {
        final double cx = zombie.getBoundsInLocal().getWidth()  / 2;
        final double cy = zombie.getBoundsInLocal().getHeight() / 2;

        if (x - cx >= 0 &&
            x + cx <= W &&
            y - cy >= 0 &&
            y + cy <= H) {
            zombie.relocate(x - cx, y - cy);
        }
    }

    public static void main(String[] args) { launch(args); }
}