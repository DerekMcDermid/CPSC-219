import java.util.*;
import java.io.*;

public final class Room {

    private final String description;
    private final Zombie zombie;
    private final Boolean isBossRoom;
    private final static Random random = new Random();
    private final static Set<Integer> roomsSeen = new HashSet<Integer>();
    private final static int NUM_ROOMS = 7;

    private Room(String description, Zombie zombie, Boolean isBossRoom) {
        this.description = description;
        this.zombie = zombie;
        this.isBossRoom = isBossRoom;
    }

    public static Room newRegularInstance() {
        if (roomsSeen.size() == NUM_ROOMS) {
            roomsSeen.clear();
        }
        int i;
        do {
            i = random.nextInt(NUM_ROOMS);
        } while (roomsSeen.contains(i));
        roomsSeen.add(i);

        String roomDescription = null;
        if (i == 0) {
            roomDescription = "A fetid, dank tunnel teeming with the very recent tracks of the zombie horde. You can even see once zombie that's right here, but thankfully so much of its body has rotted away that it's been rendered immobile. Still, best to keep your distance.";
        } else if (i == 1) {
            roomDescription = "This place was once a nightclub. You had actually considered coming here once. But even if things ever do go back to normal and nightclubs become a thing again, you can't imagine ever being able to enjoy yourself during the night ever again.";
        } else if (i == 2) {
            roomDescription = "A stinking remant of what was once a city sewer. Ugh, hopefully this at least means the zombies won't be able to smell you.";
        } else if (i == 3) {
            roomDescription = "An abandoned corprate office building. You find yourself wondering if there's still any coffee in the break room, only to find that of course there is not. A shame, it's been forever since you've been able to enjoy the taste of your morning pick-me-up.";
        } else if (i == 4) {
            roomDescription =
                    "This was once a fast food joint. The chain it belongs to was actually your favourite. On an impulse, you check in the back to see if you can find anything edible. You find a burger!";
        } else if (i == 5) {
            roomDescription =
                    "An old abandoned warehouse by the docks. At first glance though, there's nothing useful left here. Other looters seem to have picked this clean months ago. But who knows, maybe they missed something.";
        } else if (i == 6) {
            roomDescription = "What was once a cafe... A cafe filled with hipster baristas who refuse to use encapsulation! At least the zombie apocalypse did ONE good thing.";
        } else {
        }
        return new Room(roomDescription, Zombie.newRandomInstance(), false);
    }

    public static Room newBossInstance() {
        return new Room("When you step foot into the building, the entire place screams LABORATORY to you. Hmm, you have the feeling that you'll either find something very useful in here, or the kind of thing that belongs in nightmares.", Zombie.newBossInstance(),
                true);
    }

    public boolean isBossRoom() {
        return isBossRoom;
    }

    public boolean isComplete() {
        return !zombie.isAlive();
    }

    @Override
    public String toString() {
        return description;
    }

    public void enter(Player player) throws IOException {
        System.out.println("You are in " + description);
        if (zombie.isAlive()) {
            new Battle(player, zombie);
        }
    }

}
