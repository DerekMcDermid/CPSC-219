package sample.maps.obstacles;

import javafx.scene.shape.Line;

import static sample.maps.obstacles.Wall.WallBuilder.buildWall;

public class Wall extends Obstacle {

    public Wall() {
        getChildren().add(buildWall(100, 10));
    }

    public static class WallBuilder {

        public static Line buildWall(int length, int width) {
            Line wall = new Line(0, 0, 0, length);
            wall.setStrokeWidth(width);
            return wall;
        }

    }

}
