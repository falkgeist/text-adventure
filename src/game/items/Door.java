package game.items;

import game.Exit;

public class Door extends ItemLockable {
    Exit exit;

    public Door(){
        this.setName("");
        this.setIdentifier("");
        this.setDescription("");
        this.setLocDescription("");
    }

    // Constructor for unlocked doors
    public Door(String name, String identifier, String description, String locDescription, Exit exit) {
        this.setName(name);
        this.setIdentifier(identifier);
        this.setDescription(description);
        this.setLocDescription(locDescription);
        this.setExit(exit);
    }

    public Exit getExit() {
        return exit;
    }

    public void setExit(Exit exit) {
        this.exit = exit;
    }
}