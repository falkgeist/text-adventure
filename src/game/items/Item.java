package game.items;

public class Item {
    // Variables
    private String name;
    private String category;
    private String description;
    private String locDescription;
    private boolean hidden = false;
    private String hiddenDescription;

    // Default constructor
    public Item() {
        this.setName("");
        this.setCategory("");
        this.setDescription("");
        this.setLocDescription("");
    }

    // Constructor
    public Item(String name, String category, String description, String locDescription) {
        this.setName(name);
        this.setCategory(category);
        this.setDescription(description);
        this.setLocDescription(locDescription);
    }

    // Print item description
    public void inspect() {
        System.out.println(description + "\n");
    }

    // Hide item and set description for when its found
    public void hide(String hiddenDescription) {
        this.setHidden(true);
        this.hiddenDescription = hiddenDescription;
    }

    // 'Unhide' item and print hiddenDescription, then delete hiddenDescription
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
}
