package sample.displays;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.ZombieGame;
import sample.characters.Zombie2D;

import java.util.Set;

import static sample.ZombieGameLogic.getNumberOfZombiesForRound;

public class ZombieCountDisplay extends PermanentDisplay {

    private ZombieGame game;
    private Stage stage;
    private Text count;

    public ZombieCountDisplay(ZombieGame game, Stage stage) {
        this.game = game;
        this.stage = stage;
        count = new Text(0, 0, "");
        count.setFont( new Font(20));
        getChildren().add(count);
    }

    @Override
    public void update() {
        count.setX(stage.getWidth() - 200);
        count.setY(stage.getHeight() - 250);
        int zombiesLeft = getNumberOfZombiesForRound(game.getRound()) - game.getDeadZombies().size();
        count.setText("Zombies left " + zombiesLeft);
    }
}
