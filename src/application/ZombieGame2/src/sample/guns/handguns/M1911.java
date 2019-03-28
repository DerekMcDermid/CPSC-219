package sample.guns.handguns;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import static sample.guns.handguns.M1911.M1911Builder.buildBarrel;
import static sample.guns.handguns.M1911.M1911Builder.buildStock;
import static sample.guns.handguns.M1911.M1911Builder.gunLength;

public class M1911 extends Handgun {

    static {
        maxClip = 12;
        maxReserveAmmo = 100;
        bulletSpeed = 10;
        bulletDamage = 10;
        fireRate = 100;
        reloadTime = 1500;
        recoil = 5;
    }

    public M1911() {
        clipAmmo = maxClip;
        reserveAmmo = maxReserveAmmo;
        this.getChildren().add(buildStock());
        this.getChildren().add(buildBarrel());
    }

    @Override
    public String getModel() {
        return "M1911";
    }

    public double getGunLength() {
        return gunLength();
    }

    static class M1911Builder {

        protected static final int stockLength = 15;
        protected static final int stockWidth = 3;
        protected static final Color stockColor = Color.GREY;

        protected static final int barrelLength = 3;
        protected static final int barrelWidth = 2;
        protected static final Color barrelColor = Color.GREY;

        public static Line buildStock() {
            Line stock = new Line(0, 0, 0, stockLength);
            stock.setStrokeWidth(stockWidth);
            stock.setStroke(stockColor);
            return stock;
        }

        public static Line buildBarrel() {
            Line barrel = new Line(0, 0, 0, barrelLength);
            barrel.setStrokeWidth(barrelWidth);
            barrel.setStroke(barrelColor);
            return barrel;
        }

        public static double gunLength() {
            return stockLength + barrelLength;
        }

    }
}