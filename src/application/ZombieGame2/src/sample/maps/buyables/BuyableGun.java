package sample.maps.buyables;


import javafx.scene.text.Font;
import javafx.scene.text.Text;
import sample.guns.Gun;

import static sample.maps.buyables.BuyableGun.BuyableGunBuilder.buildBuyableGun;

public class BuyableGun extends BuyableObject {

    private int cost;
    private Gun gun;

    public BuyableGun(int cost, Gun gun) {
        this.cost = cost;
        this.gun = gun;
        getChildren().add(buildBuyableGun(gun.getModel()));
    }

    public int getCost() {
        return cost;
    }

    public Buyable purchase(int amount) {
        if(amount >= cost) return gun;
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        return false;
    }

    public static class BuyableGunBuilder {

        public static Text buildBuyableGun(String name) {
            Text text = new Text(0, 0, name);
            text.setFont(new Font(30));
            return text;
        }

    }
}
