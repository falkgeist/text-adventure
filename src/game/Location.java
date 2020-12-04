package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Location {
    private String title;
    private String description;
    List<Item> items = new ArrayList<>();

    public Location() {
        title = "";
        description = "";
    }

    public Location(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String toString() {
        return title;
    }

    public void printDescription() {
        System.out.println(description + "\n");
        if (!this.items.isEmpty()){
            for (Item item : this.items) {
                if (item.getLocDescription() != "" && !item.isHidden()) {
                    System.out.println(item.getLocDescription() + "\n");
                }
            }
        }
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void addItems(Item[] items) {
        this.items.addAll(Arrays.asList(items));
    }

    // Return item if its in location
    public Item getItem(String identifier) {
        for(Item item : items) {
            if (item != null && item.getIdentifier().equals(identifier)) {
                return item;
            }
        }
        return null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
