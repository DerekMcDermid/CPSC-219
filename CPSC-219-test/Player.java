import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Player extends SpriteBase {

    double playerPlayerMinX;
    double playerPlayerMaxX;
    double playerPlayerMinY;
    double playerPlayerMaxY;

    Input input;

    double speed;

    public Player(Pane layer, Image image, double x, double y,  double dx, double dy,double health, double damage, double speed, Input input) {

        super(layer, image, x, y, dx, dy, health, damage);

        this.speed = speed;
        this.input = input;

        init();
    }

    
    /*
     * Initializes and calculates movement bounds of the player
     */
    private void init() {
        playerPlayerMinX = 0 - image.getWidth() / 2.0;
        playerPlayerMaxX = Settings.SCENE_WIDTH - image.getWidth() / 2.0;
        playerPlayerMinY = 0 - image.getHeight() / 2.0;
        playerPlayerMaxY = Settings.SCENE_HEIGHT -image.getHeight() / 2.0;

    }

    public void processInput() {

        ///// movement ///////

        // vertical direction
        if( input.isMoveUp()) {
            dy = -speed;
        } else if( input.isMoveDown()) {
            dy = speed;
        } else {
            dy = 0d;
        }

        // horizontal direction
        if( input.isMoveLeft()) {
            dx = -speed;
        } else if( input.isMoveRight()) {
            dx = speed;
        } else {
            dx = 0d;
        }

    }
    
    /*
     * Updates player position with key movement.
     */
    @Override
    public void move() {
        super.move();
        // ensure the player can't move outside of the screen
        checkBounds();
    }


    public void checkRemovability() {
      //do nothing
    }

    /*
     * Ensures player stays within the screen.
     */
    private void checkBounds() {

        // vertical
        if( Double.compare( y, playerPlayerMinY) < 0) {
            y = playerPlayerMinY;
        } else if( Double.compare(y, playerPlayerMaxY) > 0) {
            y = playerPlayerMaxY;
        }

        // horizontal
        if( Double.compare( x, playerPlayerMinX) < 0) {
            x = playerPlayerMinX;
        } else if( Double.compare(x, playerPlayerMaxX) > 0) {
            x = playerPlayerMaxX;
        }

    }

}
