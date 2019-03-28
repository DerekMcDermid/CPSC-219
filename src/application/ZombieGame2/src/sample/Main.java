package sample;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.stage.Stage;
import sample.maps.ZombieMap;

import java.util.ArrayList;
import java.util.Arrays;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Zombie Hunter");

        ZombieGameRunner runner = new ZombieGameRunner(
                new ZombieGame(), primaryStage
        );
        runner.initGame(new ZombieMap(900,
                500,
                new Point2D(100, 100),
                new ArrayList<>(Arrays.asList(
                        new Point2D(50, 50),
                        new Point2D(300, 300)
                ))
                )
        );
        runner.play();

        primaryStage.setScene(runner.getScene());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
