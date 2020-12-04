package game;

public class Item {
    // Variables
    private String name;
    private String identifier;
    private String description;
    private String locDescription;
    private boolean container;
    private boolean locked = false;
    private boolean hidden = false;
    private String hiddenDescription;
    private Item key;
    private Exit exit;

    // Default constructor
    public Item() {
        this.name = "";
        this.identifier = "";
        this.description = "";
        this.locDescription = "";
    }

    // Constructor
    public Item(String name, String identifier, String description, String locDescription) {
        this.name = name;
        this.identifier = identifier;
        this.description = description;
        this.locDescription = locDescription;
    }

    // Constructor for unlocked doors
    public Item(String name, String identifier, String description, String locDescription, Exit exit) {
        this.name = name;
        this.identifier = identifier;
        this.description = description;
        this.locDescription = locDescription;
        this.exit = exit;
    }

    // Constructor for locked doors
    public Item(String name, String identifier, String description, String locDescription, Exit exit, Item key) {
        this.name = name;
        this.identifier = identifier;
        this.description = description;
        this.locDescription = locDescription;
        this.exit = exit;
        this.key = key;
        this.locked = true;
    }

    public void inspect() {
        System.out.println(description + "\n");
    }

    // Add container state
    public void makeContainer() {
        this.setContainer(true);
    }

    // Add locked state
    public void lock() {
        this.setLocked(true);
    }

    // Remove locked state
    public void unlock() {
        this.setLocked(false);
    }

    // Hide item and set description for when its found
    public void hide(String hiddenDescription) {
        this.setHidden(true);
        this.hiddenDescription = hiddenDescription;
    }

    public void find() {
        this.setHidden(false);
        System.out.println(this.hiddenDescription + "\n");
        this.hiddenDescription = "";
    }

    // Getters &  setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocDescription() {
        return locDescription;
    }

    public void setLocDescription(String locDescription) {
        this.locDescription = locDescription;
    }

    public boolean isContainer() {
        return container;
    }

    public void setContainer(boolean container) {
        this.container = container;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getHiddenDescription() {
        return hiddenDescription;
    }

    public void setHiddenDescription(String hiddenDescription) {
        this.hiddenDescription = hiddenDescription;
    }

    public Exit getExit() {
        return exit;
    }

    public void setExit(Exit exit) {
        this.exit = exit;
    }

    public Item getKey() {
        return key;
    }

    public void setKey(Item key) {
        this.key = key;
    }
}
