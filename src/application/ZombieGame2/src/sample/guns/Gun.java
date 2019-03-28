package sample.guns;

import sample.GameObject;
import sample.Moveable;
import sample.maps.buyables.Buyable;
import sample.maps.buyables.BuyableObject;

public abstract class Gun extends Buyable {

    protected static int maxClip;
    protected static int maxReserveAmmo;
    protected static int bulletSpeed;
    protected static int bulletDamage;
    protected static int fireRate;      // per minute
    protected static long reloadTime;   // milliseconds
    protected static int recoil;      // degrees of variance

    protected int clipAmmo;
    protected int reserveAmmo;
    protected long lastShotTime;
    protected long reloadStartTime;

    public Bullet2D shoot() {
        // clip empty
        if(clipAmmo == 0) return null;

        // not finished reloading
        if(!doneReloading()) return null;

        // not enough time between shots
        long currentTime = System.currentTimeMillis();
        if((currentTime - lastShotTime) < millisBetweenShots()) return null;

        // successful shooting
        clipAmmo--;
        lastShotTime = currentTime;
        return new Bullet2D(bulletSpeed, bulletDamage);
    }

    public void reload() {
        // if clip still full
        if(clipAmmo == maxClip) return;

        int neededAmmo = maxClip - clipAmmo;
        if(neededAmmo > reserveAmmo) neededAmmo = reserveAmmo;
        clipAmmo += neededAmmo;
        reserveAmmo -= neededAmmo;
        reloadStartTime = System.currentTimeMillis();
    }

    public boolean doneReloading() {
        if((System.currentTimeMillis() - reloadStartTime) < reloadTime) return false;
        return true;
    }

    public int getClipAmmo() {
        return clipAmmo;
    }

    public int getReserveAmmo() {
        return reserveAmmo;
    }

    public void replenishReserveAmmo() {
        reserveAmmo = maxReserveAmmo;
    }

    public long millisBetweenShots() {
        return (long)((1.0/ fireRate) * 60 * 1000);
    }

    public int getMaxClipAmmo() { return maxClip; }

    public int getMaxReserveAmmo() { return maxReserveAmmo; }

    public int getRecoil() { return recoil; }

    public abstract double getGunRunSpeed();

    public abstract double getGunLength();

    public abstract String getModel();
}