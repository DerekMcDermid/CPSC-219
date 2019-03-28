package sample;

import javafx.geometry.Point2D;
import sample.characters.Player2D;
import sample.guns.Bullet2D;
import sample.guns.Gun;
import sample.maps.buyables.BuyableObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ZombiesPlayerController {

    public static final Map<String, Point2D> walkingControls;
    public static final Map<String, Integer> turningControls;
    public static final Set<String> shootingControl;
    public static final Set<String> reloadControl;
    public static final Set<String> purchaseControl;
    static {
        walkingControls = new HashMap<>();
        turningControls = new HashMap<>();
        shootingControl = new HashSet<>();
        reloadControl = new HashSet<>();
        purchaseControl = new HashSet<>();

        // scale the values by multiplying in player class
        int speed = 4;

        walkingControls.put("A", new Point2D(-speed, 0));
        walkingControls.put("D", new Point2D(speed, 0));
        walkingControls.put("S", new Point2D(0, speed));
        walkingControls.put("W", new Point2D(0, -speed));

        turningControls.put("J", -5);
        turningControls.put("L", 5);

        shootingControl.add("K");
        reloadControl.add("R");
        purchaseControl.add("B");
    }

    public static void handleKeyPresses(Set<String> keyPresses, ZombieGame game) {
        Player2D player2D = game.getPlayer2D();
        for(String key : keyPresses) {
            if(walkingControls.containsKey(key)) {
                // player move
                player2D.move(walkingControls.get(key), game.getMap().getObstacles());
            }
            if(turningControls.containsKey(key)) {
                // player turn
                player2D.turn(turningControls.get(key));
            }
            if(shootingControl.contains(key)) {
                // player shoot
                Bullet2D bullet2D = player2D.shoot();
                if(bullet2D != null) game.addBullet(bullet2D);
            }
            if(reloadControl.contains(key)) {
                // player reload
                player2D.reload();
            }
            if(purchaseControl.contains(key)) {
                // purchase gun
                for(BuyableObject o : game.getMap().getBuyables()) {
                    if(player2D.isInsideBoundsOf(o)) {
                        int money = player2D.spendMoney(o.getCost());
                        Gun purchased = (Gun)o.purchase(money);
                        player2D.setGun(purchased);
                    }
                }
            }
        }
    }

}
