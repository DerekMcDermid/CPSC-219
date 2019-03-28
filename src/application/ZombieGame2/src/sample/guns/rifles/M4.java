package sample.guns.rifles;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import static sample.guns.rifles.M4.M4Builder.buildBarrel;
import static sample.guns.rifles.M4.M4Builder.buildStock;
import static sample.guns.rifles.M4.M4Builder.gunLength;

public class M4 extends Rifle {

    static {
        maxClip = 30;
        maxReserveAmmo = 210;
        bulletSpeed = 12;
        bulletDamage = 20;
        fireRate = 600;
        reloadTime = 2000;
        recoil = 3;
    }

    public M4() {
        clipAmmo = maxClip;
        reserveAmmo = maxReserveAmmo;
        getChildren().add(buildStock());
        getChildren().add(buildBarrel());
    }

    @Override
    public String getModel() {
        return "M4";
    }

    @Override
    public double getGunLength() {
        return gunLength();
    }

    public static class M4Builder {
        private static final int stockLength = 25;
        private static final int stockWidth = 3;
        private static final Color stockColor = Color.GREY;

        private static final int barrelLength = 5;
        private static final int barrelWidth = 2;
        private static final Color barrelColor = Color.GREY;

        public static Line buildStock() {
            Line stock = new Line(0, 0, 0, stockLength);
            stock.setStrokeWidth(stockWidth);
            stock.setStroke(stockColor);
            return stock;
        }

        public static Line buildBarrel() {
            Line barrel = new Line(0, stockLength, 0, stockLength + barrelLength);
            barrel.setStrokeWidth(barrelWidth);
            barrel.setStroke(barrelColor);
            return barrel;
        }

        public static double gunLength() {
            return stockLength + barrelLength;
        }
    }
}