import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Zombie extends SpriteBase {

    HealthBar healthBar;

    double healthMax;

    public Zombie(Pane layer, Image image, double x, double y, double dx, double dy, double health, double damage) {

        super(layer, image, x, y, dx, dy, health, damage);

        healthMax = Settings.ENEMY_HEALTH;

        setHealth(healthMax);

    }

    /**
     * Check if zombie has gone outside bounds to remove from image list.
     */
    @Override
    public void checkRemovability() {

        if( Double.compare( getY(), Settings.SCENE_HEIGHT) > 0) {
            setRemovable(true);
        }
    }

    public void addToLayer() {

        super.addToLayer();

        // create health bar; has to be created here because addToLayer is called in super constructor
        // and it wouldn't exist yet if we'd create it as class member
        healthBar = new HealthBar();

        this.layer.getChildren().add(this.healthBar);
    }
    

    public void removeFromLayer() {
        super.removeFromLayer();
        this.layer.getChildren().remove(this.healthBar);

    }

    /**
     * Health as a value from 0 to 1.
     * @return
     */
    public double getRelativeHealth() {
        return getHealth() / healthMax;
    }

    /*
     * Updates health bar above zombie as it moves.
     */
    public void updateUI() {

        super.updateUI();

        // update health bar
        healthBar.setValue( getRelativeHealth());

        // locate healthbar above enemy, centered horizontally
        healthBar.relocate(x + (imageView.getBoundsInLocal().getWidth() - healthBar.getBoundsInLocal().getWidth()) / 2, y - healthBar.getBoundsInLocal().getHeight() - 4);      
    }
}