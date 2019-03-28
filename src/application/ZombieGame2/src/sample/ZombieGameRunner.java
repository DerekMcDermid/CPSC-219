package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.displays.GunInfoDisplay;
import sample.displays.MoneyDisplay;
import sample.displays.RoundDisplay;
import sample.displays.ZombieCountDisplay;
import sample.guns.rifles.M4;
import sample.maps.ZombieMap;
import sample.maps.buyables.BuyableGun;
import sample.maps.obstacles.Wall;

import java.util.HashSet;
import java.util.Set;

import static sample.ZombieGameLogic.*;
import static sample.ZombiesPlayerController.handleKeyPresses;

public class ZombieGameRunner {

    private ZombieGame game;
    private Scene scene;
    private Set<String> keyPresses;
    private Stage stage;
    private long lastSpawnTime;

    public ZombieGameRunner(ZombieGame game, Stage stage) {
        this.game = game;
        this.stage = stage;
        this.scene = new Scene(game.getRoot(), 900, 500);
        this.keyPresses = new HashSet<>();
    }

    public Scene getScene() {
        return scene;
    }

    public void initGame(ZombieMap map) {
        game.addMap(map);
        game.addPlayer(spawnPlayer(game.getMap()));
        game.addPermanentDisplay(
                new GunInfoDisplay(game.getPlayer2D(), stage)
        );
        game.addPermanentDisplay(
                new ZombieCountDisplay(game, stage)
        );
        game.addPermanentDisplay(
                new RoundDisplay(game, stage)
        );
        game.addPermanentDisplay(
                new MoneyDisplay(stage, game.getPlayer2D())
        );

        game.getMap().addObstacle(new Wall(), new Point2D(200, 200));

        game.getMap().addBuyable(new BuyableGun(500, new M4()), new Point2D(400, 400));

        game.nextRound();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                keyPresses.add(keyEvent.getCode().toString());
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                keyPresses.remove(keyEvent.getCode().toString());
            }
        });
    }

    public void play() {
        Timeline gameloop = new Timeline();
        gameloop.setCycleCount(Timeline.INDEFINITE);
        KeyFrame keyFrame = new KeyFrame(
                Duration.seconds(0.017),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        handleKeyPresses(keyPresses, game);
                        if(game.getDeadZombies().size() == getNumberOfZombiesForRound(game.getRound())) {
                            game.nextRound();
                        } else {
                            if(game.getSpawnedZombies() < getNumberOfZombiesForRound(game.getRound())) {
                                if(System.currentTimeMillis() - lastSpawnTime > 5000) {
                                    game.addZombie(spawnZombie(game.getMap()));
                                    lastSpawnTime = System.currentTimeMillis();
                                }
                            }
                            if(chase(game.getZombies(), game.getPlayer2D(), game.getMap())) {
                                stage.close();
                            }
                            game.addDeadZombies(damageZombies(game));
                        }
                        game.removeBullets(propagateBullets(game.getBullets(), game.getMap()));
                        updateDisplay(game.getPermanentDisplays());
                    }
                }
        );
        gameloop.getKeyFrames().add(keyFrame);
        gameloop.play();
    }

}
