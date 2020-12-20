package game;

import game.items.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Location {
    private String title;
    private String description;
    private boolean end;
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

    public boolean hasVisibleItems() {
        if (!this.items.isEmpty()){
            for (Item item : this.items) {
                if (!item.isHidden()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void printDescription() {
        System.out.println(description);
        if (this.hasVisibleItems()){
            for (Item item : this.items) {
                if (!item.getLocDescription().equals("") && !item.isHidden()) {
                    System.out.println(item.getLocDescription());
                }
            }
        }
        System.out.println();
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void addItems(Item[] items) {
        this.items.addAll(Arrays.asList(items));
    }

    // Return item if its in location
    public <Type> Type getItem(String itemCategory) {
        for(Item item : this.items) {
            if (item != null && item.getCategory().equals(itemCategory) && !item.isHidden()) {
                return (Type) item;
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

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
}
