package sample;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class GameObject extends Parent {

    public Bounds getBounds() {
        return localToScene(getBoundsInLocal());
    }

    public boolean isInsideBoundsOf(GameObject object) {
        if(object.getBounds().intersects(this.getBounds())) return true;
        return false;
    }

    public void place(Point2D point2D, int rotationAngle) {
        getTransforms().add(new Translate(point2D.getX(), point2D.getY()));
        getTransforms().add(new Rotate(rotationAngle));
    }

}