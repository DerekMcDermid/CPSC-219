package sample.displays;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.ZombieGame;

public class RoundDisplay extends PermanentDisplay {

    private ZombieGame game;
    private Stage stage;
    private Text counter;

    public RoundDisplay(ZombieGame game, Stage stage) {
        this.game = game;
        this.stage = stage;
        counter = new Text(0, 0, "");
        counter.setFont(new Font(20));
        getChildren().add(counter);
    }

    @Override
    public void update() {
        counter.setX(stage.getWidth() - 200);
        counter.setY(stage.getHeight() - 300);
        counter.setText("Round " + game.getRound());
    }
}

