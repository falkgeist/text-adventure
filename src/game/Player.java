package game;

import game.items.*;

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
    public void choice() {
        System.out.println("What do you do?");
        String action = input.nextLine();
        System.out.println();
        if (action.contains("open")) {
            open(action);
        } else if (action.contains("pick") && action.contains("lock") && !action.contains("lockpick") || action.contains("pick") && action.contains("door")) {
            lockpicking(action);
        } else if (action.contains("pick") && action.contains("up")) {
            pickUp(action);
        } else if (action.contains("take")) {
            take(action);
        } else if (action.contains("find") || action.contains("search") || action.contains("inspect") || action.contains("look") || action.contains("check")) {
            lookAt(action);
        } else if (action.contains("go") || action.contains("leave") || action.contains("move to") || action.contains("walk")) {
            go(action);
        } else {
            System.out.println("Please enter a valid action.\n");
        }
        if (this.currentLocation.getTitle().equals("outside")){
            System.out.println(
                    """
                    
                    You finished the adventure. Congratulations!

                    ///////////////////////////////////////////////////////
                    """
            );
        } else {
            this.choice();
        }
    }

    public void open(String action) {
        if (action.contains("door")) {
            openDoor();
        } else {
            System.out.println("Please specify, what you want to open.\n");
        }
    }

    public void openDoor() {
        if (this.currentLocation.getItem("door") != null) {
            Door door = this.currentLocation.getItem("door");
            Item key = door.getKey();
            if (door.isLocked() && this.inventory.contains(key)) {
                door.unlock();
                System.out.println("You open the door with the key.\n");
            } else if (door.isLocked() && !this.inventory.contains(key)) {
                System.out.println("You don't have a matching key.\n");
            } else if (!door.isLocked()) {
                System.out.println("You open the door.");
            }
        } else {
            System.out.println("This room has no door.\n");
        }
    }

    public void pickLock(String action) {
        boolean isLocked = false;
        for (Item item : this.currentLocation.items) {
            if (item instanceof ItemLockable && ((ItemLockable) item).isLocked()) {
                isLocked = true;
                break;
            }
        }
        if (isLocked) {
            ItemLockable lockedItem = null;
            // Select door or container
            if (this.currentLocation.getItem("door") != null) {
                lockedItem = this.currentLocation.getItem("door");
            } else if (this.currentLocation.getItem("container") != null) {
                lockedItem = this.currentLocation.getItem("container");
            }
            if (this.getItem("lockpick") != null) {
                lockedItem.unlock();
                System.out.println("You open the " + lockedItem.getName() + " with your lockpick.\n");
            } else if (lockedItem.isLocked() && this.getItem("lockpick") == null) {
                System.out.println("You don't have a lockpick.\n");
            } else if (!lockedItem.isLocked()) {
                System.out.println("The " + lockedItem.getName() + " is already unlocked.");
            }
        } else {
            System.out.println("There is nothing locked in this room.\n");
        }
    }

    public void pickUp(String action) {
        if (action.contains("key")) {
            pickUpKey(action);
        } else if (action.contains("lockpick")) {
            pickUpLockpicks(action);
        } else {
            System.out.println("You can't pick that up.\n");
        }
    }

    public void take(String action) {
        if (action.contains("key")) {
            pickUpKey(action);
        } else if (action.contains("lockpick")) {
            pickUpLockpicks(action);
        } else {
            System.out.println("You can't take that.\n");
        }
    }

    public void pickUpKey(String action) {
        if (this.currentLocation.getItem("key") != null) {
            Item key = this.currentLocation.getItem("key");
            this.pickUpItem(key, "the");
        } else {
            System.out.println("You can't see a key that you could pick up.\n");
        }
    }

    public void pickUpLockpicks(String action) {
        if (this.currentLocation.getItem("lockpick") != null) {
            Item lockpick = this.currentLocation.getItem("lockpick");
            this.pickUpItem(lockpick, "the");
        } else {
            System.out.println("You can't see a lockpick that you could pick up.\n");
        }
    }

    public void pickUpItem(Item item, String article) {
        if (!item.isHidden()) {
            this.inventory.add(item);
            System.out.println("You pick up " + article + " " + item.getName() + ".\n");
            this.currentLocation.items.remove(item);
            for (Item container : this.currentLocation.items) {
                if (container instanceof Container) {
                    ((Container) container).removeItem(item);
                }
            }
        } else {
            System.out.println("You don't see anything to pick up.");
        }
    }

    public void lookAt(String action) {
        if (action.contains("door")) {
            Door door = this.currentLocation.getItem("door");
            door.inspect();
        } else if (action.contains("room") || action.contains("around")) {
            this.currentLocation.printDescription();
            searchRoom();
        } else if (action.contains("key") && action.contains("inventory")) {
            Item key = this.getItem("key");
            key.inspect();
        } else if (action.contains("key") && action.contains("room")) {
            Item key = this.currentLocation.getItem("key");
            System.out.println(key.getLocDescription() + "\n");
        } else if (action.contains("bed")) {
            if (this.currentLocation.getItem("bed") != null) {
                Item bed = this.currentLocation.getItem("bed");
                System.out.println(bed.getLocDescription() + "\n");
                if (bed instanceof Container) {
                    ((Container) bed).getContents();
                }
            }
        } else {
            System.out.println("You don't find anything.\n");
        }
    }

    public void searchRoom() {
        if (!this.currentLocation.items.isEmpty()) {
            int foundItems = 0;
            for (Item item : this.currentLocation.items) {
                if (item.isHidden()) {
                    item.find();
                    foundItems++;
                }
            }
            if (foundItems == 0) {
                System.out.println("You can't find anything in this room.");
            }
        } else {
            System.out.println("There's nothing in this room.");
        }
    }

    public void go(String action) {
        if (action.contains("leave") || action.contains("next room") || action.contains("out") || action.contains("through") && action.contains("door")) {
            Door door;
            if (this.currentLocation.getItem("door") != null) {
                door = this.currentLocation.getItem("door");
                if (door.isLocked()) {
                    System.out.println("The door is locked.\n");
                } else if (!door.isLocked()) {
                    goToLocation(door.getExit().getDestination());
                }
            }
        } else {
            System.out.println("You can't go there.\n");
        }
    }

    public void goToLocation(Location destination) {
        this.currentLocation = destination;
        this.currentLocation.printDescription();
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
