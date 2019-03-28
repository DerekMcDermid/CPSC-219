package sample.guns.submachineguns;


import sample.guns.Gun;

public abstract class SubMachineGun extends Gun {

    @Override
    public double getGunRunSpeed() {
        return 1;
    }
}