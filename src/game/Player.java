package game;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private Location currentLocation;
    List<Item> inventory = new ArrayList<>();
    // Input
    Scanner input = new Scanner(System.in);

    // Default constructor
    public Player() {
        this.name = "";
    }

    // Full constructor
    public Player(String name) {
        this.name = name;
    }

    // Commands
    public void open(String action) {
        if (action.contains("door")) {
            openDoor();
            this.choice();
        } else {
            System.out.println("Please specify, what you want to open.\n");
            this.choice();
        }
    }

    public void openDoor() {
        Item door = null;
        if (this.currentLocation.getItem("door") != null) {
            door = this.currentLocation.getItem("door");
            Item key = door.getKey();
            if (door.isLocked() && this.inventory.contains(key)) {
                door.unlock();
                System.out.println("DYou open the door with the key.\n");
                this.choice();
            } else if (door.isLocked() && !this.inventory.contains(key)) {
                System.out.println("You don't have a matching key.\n");
                this.choice();
            } else if (!door.isLocked()) {
                goThrough(door);
            }
        } else {
            System.out.println("This room has no door.\n");
            this.choice();
        }
    }

    public void pickUpKey(String action) {
        if (action.contains("key")) {
            Item key = this.currentLocation.getItem("key");
            this.pickUpItem(key, "the");
            this.choice();
        }
    }

    public void lookAt(String action) {
        if (action.contains("door")) {
            lookAtDoor();
        } else if (action.contains("room") || action.contains("around")) {
            lookAtRoom();
        } else if (action.contains("key") && action.contains("inventory")) {
            lookAtKeyInInventory();
        } else if (action.contains("key") && action.contains("room")) {
            lookAtKeyInRoom();
        } else {
            System.out.println("Please specify, what you're looking at.\n");
            this.choice();
        }
    }

    public void lookAtDoor() {
        Item door = this.currentLocation.getItem("door");
        door.inspect();
        this.choice();
    }

    public void lookAtRoom() {
        this.currentLocation.printDescription();
        this.choice();
    }

    public void lookAtKeyInInventory() {
        Item key = this.getItem("key");
        key.inspect();
        this.choice();
    }

    public void lookAtKeyInRoom() {
        Item key = this.currentLocation.getItem("key");
        System.out.println(key.getLocDescription() + "\n");
        this.choice();
    }

    public void search(String action) {
        if (action.contains("room")) {
            searchRoom();
        } else {
            System.out.println("Please specify, what you're searching.\n");
            this.choice();
        }
    }

    public void searchRoom() {
        if (!this.currentLocation.items.isEmpty()) {
            for (Item item : this.currentLocation.items) {
                if (item.isHidden()) {
                    item.find();
                }
            }
        }
    }

    public void go(String action) {
        if (action.contains("next room")) {
            Item door = null;
            if (this.currentLocation.getItem("door") != null) {
                door = this.currentLocation.getItem("door");
                if (door.isLocked()) {
                    System.out.println("The door is locked.\n");
                    this.choice();
                } else if (!door.isLocked()) {
                    goToLocation(door.getExit().getDestination());
                }
            }
        } else {
            System.out.println("Please specify, where you want to go.\n");
            this.choice();
        }
    }

    public void goThrough(Item door) {
        Location destination = door.getExit().getDestination();
        this.goToLocation(destination);
        this.choice();
    }

    public void choice() {
        System.out.println("What do you do?");
        String action = input.nextLine();
        System.out.println();
        if (action.contains("open")) {
            open(action);
        } else if (action.contains("pick") && action.contains("up") || action.contains("take")) {
            pickUpKey(action);
        } else if (action.contains("inspect") || action.contains("look") || action.contains("check")) {
            lookAt(action);
        } else if (action.contains("search")) {
            search(action);
        } else if (action.contains("go")) {
            go(action);
        } else {
            System.out.println("This action is not available.\n" +
                    "Please choose another action.\n");
            this.choice();
        }
    }

    public void goToLocation(Location destination) {
        this.currentLocation = destination;
        this.currentLocation.printDescription();
        if (this.currentLocation.getTitle().equals("Outside")){
            System.out.println(
                    "\n\n" +
                            "You finished the adventure. Congratulations!\n" +
                            "\n" +
                            "///////////////////////////////////////////////////////\n"
            );
        } else {
            this.choice();
        }
    }

    public void pickUpItem(Item item, String article) {
        this.inventory.add(item);
        System.out.println("You pick up " + article + " " + item.getName() + ".\n");
        this.currentLocation.items.remove(item);
    }

    // Get item if its in inventory
    public Item getItem(String identifier) {
        for(Item item : inventory) {
            if (item != null && item.getIdentifier().equals(identifier)) {
                return item;
            }
        }
        return null;
    }

    // Getters & setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }
}
