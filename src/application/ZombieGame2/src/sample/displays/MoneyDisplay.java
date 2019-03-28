package sample.displays;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.characters.Player2D;

public class MoneyDisplay extends PermanentDisplay {

    private Stage stage;
    private Player2D player2D;
    private Text money;

    public MoneyDisplay(Stage stage, Player2D player2D) {
        this.stage = stage;
        this.player2D = player2D;
        this.money = new Text(0, 0, "0");
        this.money.setFont(new Font(30));
        getChildren().add(money);
    }

    @Override
    public void update() {
        money.setText(player2D.getMoney() + "");
        money.setX(stage.getWidth() - 100);
        money.setY(stage.getHeight() - 100);
    }
}