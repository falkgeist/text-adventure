package game;

import game.items.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private String action;
    private boolean gameOver;
    private Location currentLocation;
    List<Item> inventory = new ArrayList<>();
    // Input
    Scanner input = new Scanner(System.in);

    // Default constructor
    public Player() {
    }

    // Commands
    public void choice() {
        System.out.println("What do you do?");
        this.action = input.nextLine();
        System.out.println();
        if (this.action.contains("open") || this.action.contains("unlock")) {
            open();
        } else if (this.action.contains("pick") && this.action.contains("lock") && !this.action.contains("lockpick") || this.action.contains("pick") && this.action.contains("door") && !this.action.contains("lockpick")) {
            pickLock();
        } else if (this.action.contains("pick") && this.action.contains("up") || this.action.contains("take")) {
            pickUp();
        } else if (this.action.contains("find") || this.action.contains("search") || this.action.contains("inspect") || this.action.contains("look") || this.action.contains("check")) {
            lookAt();
        } else if (this.action.contains("go") || this.action.contains("leave") || this.action.contains("move to") || this.action.contains("walk")) {
            go();
        } else if (this.action.contains("die") || this.action.contains("suicide")) {
            System.out.println("You killed yourself.\n");
            this.gameOver = true;
        } else if (this.action.contains("smash")) {
            System.out.println("You are not strong enough. You broke your hand.\n");
            this.gameOver = true;
        } else {
            System.out.println("That is not possible.\n");
        }
        if (this.currentLocation.isEnd()){
            System.out.println(
                    """
                    
                    You finished the adventure. Congratulations!

                    ///////////////////////////////////////////////////////
                    """
            );
        } else if (this.gameOver) {
            System.out.println(
                    """
                    
                    Game over. Try again...

                    ///////////////////////////////////////////////////////
                    """
            );
        } else {
            this.choice();
        }
    }

    public void open() {
        if (this.action.contains("door")) {
            openDoor();
        } else if (this.action.contains("door") && this.action.contains("unlock")) {
            unlockDoor();
        } else {
            System.out.println("That is not something you can open right now.\n");
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
                System.out.println("You open the door.\n");
            }
        } else {
            System.out.println("This room has no door.\n");
        }
    }

    public void unlockDoor() {
        if (this.currentLocation.getItem("door") != null) {
            Door door = this.currentLocation.getItem("door");
            Item key = door.getKey();
            if (door.isLocked() && this.inventory.contains(key)) {
                door.unlock();
                System.out.println("You unlock the door with the key.\n");
            } else if (door.isLocked() && !this.inventory.contains(key)) {
                System.out.println("You don't have a matching key.\n");
            } else if (!door.isLocked()) {
                System.out.println("The door is already unlocked.\n");
            }
        } else {
            System.out.println("This room has no door.\n");
        }
    }

    public void pickLock() {
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

    public void pickUp() {
        if (this.currentLocation.hasVisibleItems()) {
            if (this.action.contains("key")) {
                pickUpKey();
            } else if (this.action.contains("lockpick")) {
                pickUpLockpicks();
            } else {
                if (this.action.contains("take")) {
                    System.out.println("You can't take that.\n");
                } else {
                    System.out.println("You can't pick that up.\n");
                }
            }
        } else {
            if (this.action.contains("take")) {
                System.out.println("There's nothing to take.\n");
            } else {
                System.out.println("There is nothing to pick up.\n");
            }
        }
    }

    public void pickUpKey() {
        if (this.currentLocation.getItem("key") != null) {
            Item key = this.currentLocation.getItem("key");
            this.pickUpItem(key, "the");
        } else {
            System.out.println("You can't see a key that you could pick up.\n");
        }
    }

    public void pickUpLockpicks() {
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

    public void lookAt() {
        if (this.action.contains("room") || this.action.contains("around")) {
            this.currentLocation.printDescription();
            searchLocation();
        } else if (this.action.contains("door")) {
            Door door = this.currentLocation.getItem("door");
            door.inspect();
        } else if (this.action.contains("key")) {
            if (this.getItem("key") != null && !this.action.contains("room")) {
                Item key = this.getItem("key");
                key.inspect();
            } else if (this.currentLocation.getItem("key") != null && !this.action.contains("inventory")) {
                Item key = this.currentLocation.getItem("key");
                System.out.println(key.getLocDescription() + "\n");
            } else {
                if (this.action.contains("check") || this.action.contains("inspect") || this.action.contains("look")) {
                    System.out.println("You don't have a key and cannot see any key here.");
                } else if (this.action.contains("find") || this.action.contains("search")) {
                    System.out.println("You cannot find a key. Maybe it is hidden somewhere?");
                }
            }
        } else if (this.action.contains("bed")) {
            if (this.currentLocation.getItem("bed") != null) {
                Item bed = this.currentLocation.getItem("bed");
                System.out.println(bed.getLocDescription() + "\n");
                if (bed instanceof Container) {
                    ((Container) bed).getContents();
                }
            }
        } else {
            if (this.action.contains("inspect") || this.action.contains("look") || this.action.contains("check")) {
                System.out.println("You do not see anything in particular.\n");
            } else if (this.action.contains("find") || this.action.contains("search")){
                System.out.println("You cannot find anything.\n");
            }
        }
    }

    public void searchLocation() {
        if (!this.currentLocation.items.isEmpty()) {
            boolean itemFound = false;
            for (Item item : this.currentLocation.items) {
                if (item.isHidden()) {
                    item.find();
                    itemFound = true;
                }
            }
            if (!itemFound) {
                System.out.println("You can't find anything in this room.");
            }
        } else {
            System.out.println("There's nothing in this room.");
        }
    }

    public void go() {
        if (this.action.contains("leave") || this.action.contains("next room") || this.action.contains("out") || this.action.contains("through") && this.action.contains("door")) {
            if (this.currentLocation.getItem("door") != null) {
                Door door = this.currentLocation.getItem("door");
                if (door.isLocked()) {
                    System.out.println("The door is locked.\n");
                } else if (!door.isLocked()) {
                    this.goToLocation(door.getExit().getDestination());
                }
            }
        } else if (this.action.contains("to")) {
            System.out.println("You do not need to go there, just specify, what you want to do with that.\n");
        } else {
            System.out.println("That is not possible.\n");
        }
    }

    public void goToLocation(Location destination) {
        this.currentLocation = destination;
        this.currentLocation.printDescription();
    }

    // Get item if its in inventory
    public Item getItem(String identifier) {
        for(Item item : inventory) {
            if (item != null && item.getCategory().equals(identifier)) {
                return item;
            }
        }
        return null;
    }

    // Getters & setters
    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }
}
