package game.items;

public class ItemLockable extends Item {
    // Variables
    boolean locked;
    Item key;

    public ItemLockable() {
    }

    // Add locked state
    public void lock() {
        this.setLocked(true);
    }

    // Remove locked state
    public void unlock() {
        this.setLocked(false);
    }

    // Getters &  setters
    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Item getKey() {
        return key;
    }

    public void setKey(Item key) {
        this.key = key;
    }
}