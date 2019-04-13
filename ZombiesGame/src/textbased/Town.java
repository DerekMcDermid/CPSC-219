import java.util.*;
import java.io.*;

/**
 * Developed with reference to https://codereview.stackexchange.com/questions/9999/text-based-rpg-in-java, under the
 * "Dungeon" class.
 * Creates and updates the entire map through the placing of rooms.
 */
public final class Town {

    private final Map<Integer, Map<Integer, Room>> map = new HashMap<Integer, Map<Integer, Room>>();
    private Room currentRoom;
    private int currentX = 0;
    private int currentY = 0;

    /**
     * Creates a blank, nested map with no rooms and no player position.
     */
    private Town() {
    }

    /**
     * Places rooms in empty spots in the map, associated with a unique 'x' key value.
     * @param x the unique key value for the room, the horizontal position.
     * @param y the vertical position of the room
     * @param room type to be placed.
     */
    private void putRoom(int x, int y, Room room) {
        if (!map.containsKey(x)) {
            map.put(x, new HashMap<Integer, Room>());
        }
        map.get(x).put(y, room);
    }

    /**
     * Retrieves room placed with the map.
     * @param x the unique key value for the room, the horizontal position.
     * @param y the vertical position of the room
     * @return room at the specified location.
     */
    private Room getRoom(int x, int y) {
        return map.get(x).get(y);
    }

    /**
     * @param x the unique key value for the room, the horizontal position.
     * @param y the vertical position of the room
     * @return boolean, true if there is an object in the map in the location specified.
     */
    private boolean roomExists(int x, int y) {
        if (!map.containsKey(x)) {
            return false;
        }
        return map.get(x).containsKey(y);
    }

    /**
     * @return boolean, true if there are no zombies surviving in the room.
     */
    private boolean isComplete() {
        return currentRoom.isBossRoom() && currentRoom.isComplete();
    }

    /**
     * If a room exists in the map, and the player is placed in the room, and their position is updated.
     * @param player
     * @throws IOException
     */
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

    /**
     * Places player in the town, and allows for exploration and movement.
     * @param player
     * @throws IOException
     */
    public void startQuest(Player player) throws IOException {
        while (player.isAlive() && !isComplete()) {
            movePlayer(player);
        }
    }

    /**
     * Builds the town map.
     * @return Town
     */
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
