package sample.guns.submachineguns;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import static sample.guns.submachineguns.Mp5.Mp5Builder.buildBarrel;
import static sample.guns.submachineguns.Mp5.Mp5Builder.buildStock;
import static sample.guns.submachineguns.Mp5.Mp5Builder.gunLength;

public class Mp5 extends SubMachineGun {

    static {
        maxClip = 30;
        maxReserveAmmo = 250;
        bulletSpeed = 12;
        bulletDamage = 15;
        fireRate = 800;
        reloadTime = 1500;
        recoil = 5;
    }

    public Mp5() {
        clipAmmo = maxClip;
        reserveAmmo = maxReserveAmmo;
        getChildren().add(buildStock());
        getChildren().add(buildBarrel());
    }

    @Override
    public double getGunLength() {
        return gunLength();
    }

    @Override
    public String getModel() {
        return "Mp5";
    }

    public static class Mp5Builder {
        private static final int stockLength = 18;
        private static final int stockWidth = 3;
        private static final Color stockColor = Color.GREY;

        private static final int barrelLength = 3;
        private static final int barrelWidth = 2;
        private static final Color barrelColor = Color.GREY;

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
