package game;

import game.items.*;

public class Game {
    public static void main(String[] args) {
        // Create objects
        Player player1 = new Player("Player 1");
        Location location1 = new Location("bed room", "You are standing in a room without windows.");
        Location location2 = new Location("entrance", "You enter a room without furniture. On the other side of the room is a door.");
        Location end = new Location("outside", "You are outside.");
        Exit exitLoc1toLoc2 = new Exit(Exit.NORTH, location2);
        Exit exitLoc2toLoc1 = new Exit(Exit.SOUTH, location1);
        Exit endGame = new Exit(Exit.NORTH, end);
        Container bed1 = new Container("bed", "bed", "The bed is in bad shape and the sheets are old and torn.", "There is an iron bed with old sheets and in bad shape standing on one side of the room.");
        location1.addItem(bed1);
        Item key1 = new Item("key", "key", "The key has an inscription: \"Bed room\"", "On the floor under the bed is a key.");
        key1.hide("There is a key under the bed");
        bed1.addItem(key1);
        location1.addItem(key1);
        Item lockpicks1 = new Item("set of lockpicks", "lockpick", "A set of lockpicks in surprisingly good shape.", "There is a set of lockpicks behind a loose stone in the wall.");
        lockpicks1.hide("Upon inspection of the wall you find a set of lockpicks hidden behind a loose stone.");
        location1.addItem(lockpicks1);
        Door door1 = new Door("door", "door", "The door is wooden and seems to be sturdy.", "On the other side is a door.", exitLoc1toLoc2);
        door1.setKey(key1);
        door1.lock();
        location1.addItem(door1);
        Door door2 = new Door("door", "door", "The door is wooden and seems to be sturdy.", "", endGame);
        location2.addItem(door2);
        Door door3 = new Door("door", "door-back", "The door is wooden and seems to be sturdy.", "",  exitLoc2toLoc1);
        location2.addItem(door3);

        // Output
        System.out.println(
                """
                ///////////////////////////////////////////////////////

                You wake up. You can't remember who or where you are.
                """
        );
        player1.goToLocation(location1);
        player1.choice();
    }
}
