package sample.maps.buyables;

import sample.GameObject;

public abstract class BuyableObject extends GameObject {

    public abstract int getCost();
    public abstract Buyable purchase(int amount);

}