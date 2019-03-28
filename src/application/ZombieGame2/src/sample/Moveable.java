package sample;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import sample.GameObject;
import sample.maps.obstacles.Obstacle;

import java.util.Set;

public class Moveable extends GameObject {

    public boolean move(Point2D point2D, Set<Obstacle> obstacles) {
        getTransforms().add(new Translate(point2D.getX(), point2D.getY()));
        for(Obstacle o : obstacles) {
            if(this.isInsideBoundsOf(o)) {
                getTransforms().add(new Translate(-point2D.getX(), -point2D.getY()));
                return false;
            }
        }
        return true;
    }

    public void turn(int degrees) {
        getTransforms().add(new Rotate(degrees));
    }

    public double getAngle() {
        double xx = getLocalToSceneTransform().getMxx();
        double xy = getLocalToSceneTransform().getMxy();
        double angle = Math.atan2(-xy, xx);
        return Math.toDegrees(angle);
    }

    public Point2D getLocation() {
        Bounds bounds = localToScene(getBoundsInLocal());
        double x = (bounds.getMinX() + bounds.getMaxX()) / 2.0;
        double y = (bounds.getMinY() + bounds.getMaxY()) / 2.0;
        return new Point2D(x, y);
    }

}