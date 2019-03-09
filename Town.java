package Project;
import java.util.*;
import java.io.*;

//CITATION: https://codereview.stackexchange.com/questions/9999/text-based-rpg-in-java under the "Dungeon class" code section

public final class Town {

    private final Map<Integer, Map<Integer, Room>> map = new HashMap<Integer, Map<Integer, Room>>();
    private Room currentRoom;
    private int currentX = 0;
    private int currentY = 0;

    private Town() {
    }

    private void putRoom(int x, int y, Room room) {
        if (!map.containsKey(x)) {
            map.put(x, new HashMap<Integer, Room>());
        }
        map.get(x).put(y, room);
    }

    private Room getRoom(int x, int y) {
        return map.get(x).get(y);
    }

    private boolean roomExists(int x, int y) {
        if (!map.containsKey(x)) {
            return false;
        }
        return map.get(x).containsKey(y);
    }

    private boolean isComplete() {
        return currentRoom.isBossRoom() && currentRoom.isComplete();
    }

    public void movePlayer(Player player) throws IOException {
        boolean northPossible = roomExists(currentX, currentY + 1);
        boolean southPossible = roomExists(currentX, currentY - 1);
        boolean eastPossible = roomExists(currentX + 1, currentY);
        boolean westPossible = roomExists(currentX - 1, currentY);
        System.out.print("Where would you like to go :");
        if (northPossible) {
            System.out.print(" North (w)");
        }
        if (eastPossible) {
            System.out.print(" East (d)");
        }
        if (southPossible) {
            System.out.print(" South (s)");
        }
        if (westPossible) {
            System.out.print(" West (a)");
        }
        System.out.print(" ? \n\n");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String direction = in.readLine();
        if (direction.equals("w") && northPossible) {
            currentY++;
        } else if (direction.equals("s") && southPossible) {
            currentY--;
        } else if (direction.equals("d") && eastPossible) {
            currentX++;
        } else if (direction.equals("a") && westPossible) {
            currentX--;
        }
        currentRoom = getRoom(currentX, currentY);
        currentRoom.enter(player);
    }

    public void startQuest(Player player) throws IOException {
        while (player.isAlive() && !isComplete()) {
            movePlayer(player);
        }
    }

    public static Town newInstance() {
        Town town = new Town();
        town.putRoom(0, 0, Room.newRegularInstance());
        town.putRoom(-1, 1, Room.newRegularInstance());
        town.putRoom(0, 1, Room.newRegularInstance());
        town.putRoom(1, 1, Room.newRegularInstance());
        town.putRoom(-1, 2, Room.newRegularInstance());
        town.putRoom(1, 2, Room.newRegularInstance());
        town.putRoom(-1, 3, Room.newRegularInstance());
        town.putRoom(0, 3, Room.newRegularInstance());
        town.putRoom(1, 3, Room.newRegularInstance());
        town.putRoom(0, 4, Room.newBossInstance());
        town.currentRoom = town.getRoom(0, 0);
        return town;
    }

}
