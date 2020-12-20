package game.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Container extends ItemLockable {
    List<Item> contents = new ArrayList<>();

    public Container(){
        this.setName("");
        this.setCategory("");
        this.setDescription("");
        this.setLocDescription("");
    }

    // Constructor for container
    public Container(String name, String category, String description, String locDescription) {
        this.setName(name);
        this.setCategory(category);
        this.setDescription(description);
        this.setLocDescription(locDescription);
    }

    public void addItem(Item item) {
        contents.add(item);
    }

    public void addItems(Item[] items) {
        contents.addAll(Arrays.asList(items));
    }

    public void removeItem(Item item) {
        contents.remove(item);
    }

    public void removeItems(Item[] items) {
        contents.removeAll(Arrays.asList(items));
    }

    public void getContents() {
        for(Item item : contents) {
            if (!item.isHidden()) {
                System.out.println(item.getLocDescription() + "\n");
            } else {
                item.find();
            }
        }
    }

    // Return item if its in container
    public Item getItem(String identifier) {
        for(Item item : contents) {
            if (item != null && item.getCategory().equals(identifier)) {
                return item;
            }
        }
        return null;
    }
}
