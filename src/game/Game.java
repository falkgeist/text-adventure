package game;

public class Game {
    public static void main(String[] args) {
        // Create objects
        Player player1 = new Player("Player 1");
        Location location1 = new Location("Bed room", "You're standing in a room without windows.\n" +
                "There's an iron bed with old sheets and in bad shape standing on one side of the room. On the other side is a door.");
        Location location2 = new Location("Eingangsbereich", "You enter a room without furniture. On the other side of the room is a door.");
        Location end = new Location("Outside", "You are outside.");
        Exit exitLoc1toLoc2 = new Exit(Exit.NORTH, location2);
        Exit exitLoc2toLoc1 = new Exit(Exit.SOUTH, location1);
        Exit endGame = new Exit(Exit.NORTH, end);
        Item key1 = new Item("Key", "key", "The key has an inscription: \"Bed room\"", "On the floor under the bed is a key.");
        key1.hide("Under the bed is a key.");
        location1.addItem(key1);
        Item door1 = new Item("Door", "door", "The door is wooden and seems to be sturdy.", "", exitLoc1toLoc2, key1);
        location1.addItem(door1);
        Item door2 = new Item("Door", "door", "The door is wooden and seems to be sturdy.", "", endGame);
        location2.addItem(door2);
        Item door3 = new Item("Door", "door-back", "The door is wooden and seems to be sturdy.", "",  exitLoc2toLoc1, key1);
        location2.addItem(door3);

        // Output
        System.out.println(
                "///////////////////////////////////////////////////////\n" +
                "\n" +
                "You wake up. You can't remember who you are or where.\n"
        );
        player1.goToLocation(location1);
        player1.choice();
    }
}
