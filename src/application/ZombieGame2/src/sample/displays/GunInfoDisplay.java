package sample.displays;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.characters.Player2D;
import sample.guns.Gun;

public class GunInfoDisplay extends PermanentDisplay {

    private Player2D player2D;
    private Stage stage;
    private Text currentClip;
    private Text reserve;
    private Text model;

    public GunInfoDisplay(Player2D player2D, Stage stage) {
        this.player2D = player2D;
        this.stage = stage;

        currentClip = new Text(0, 0, "0 / 0");
        currentClip.setFont(new Font(30));

        reserve = new Text(0, 0, "0");
        reserve.setFont(new Font(30));

        model = new Text(0, 0, "");
        model.setFont(new Font(30));

        getChildren().add(currentClip);
        getChildren().add(reserve);
        getChildren().add(model);
    }

    public void update() {
        int clip = player2D.getGun().getClipAmmo();
        int clipCapacity = player2D.getGun().getMaxClipAmmo();

        if(player2D.getGun().doneReloading()) {
            currentClip.setText(clip + " / " + clipCapacity);
        } else {
            currentClip.setText("RELOADING");
        }

        int reserveAmmo = player2D.getGun().getReserveAmmo();
        reserve.setText(reserveAmmo + "");

        model.setText(player2D.getGun().getModel());

        // adjust positions
        double width = stage.getWidth();
        double height = stage.getHeight();
        currentClip.setX(width - 200);
        currentClip.setY(height - 100);
        reserve.setX(width - 200);
        reserve.setY(height - 50);
        model.setX(width - 200);
        model.setY(height - 150);
    }

    public void setGun(Gun gun) {
        update();
    }

}
