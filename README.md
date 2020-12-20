# Text Adventure
A text adventure (gaming system) created for the purpose of learning how to code in Java.

This text adventure is supposed to help me learn Java in a way that I feel motivated with.

## The core concepts:

### Player
The Player is the protagonist, the "you" that walks through the locations, picks up items, fights monsters, talks with people (in future versions).

Attributes:
(- Name)
- Inventory (items)
- Current location
- Current action

Available methods:
- get description of Location
- get list of Items in Location
- pick up Items from Location
- use Items in Location
- go through Exit
- etc.

### Location
Locations are the rooms (or maybe 'map tiles') that the player traverses.

Attributes:
- Title
- Description
- End (y/n)
- Items

Available methods:
none

### Exit
Exits are simply the directions a player can move in when leaving a location. This concept was taken more or less from https://www.javacoffeebreak.com/text-adventure/.

Attributes:
- Direction name
- Long direction name
- Destination

Available methods:
none

### Item
Items are any objects that can exist in the game world. For now there's only doors and a key, but I was thinking about anything that is more than just for decoration.

Attributes:
- Title
- Description

Available methods:
- Inspect
- Hide
- Find

#### Lockable
NEW: This is the parent class for doors and containers, essentially just making the item lockable.

Attributes:
- Locked (y/n)
- Key

Available methods:
- Lock
- Unlock

#### Door
NEW: Doors are also Items, but with a designated Exit.

Attributes:
- Exit

Available methods:
none

#### Container
NEW: Containers are Items, that can contain Items.

Attributes:
- Items

Available methods:
- add Item(s)
- remove Item(s)
- show contents
- get certain Item

### Character
This is a future concept, not yet implemented. But in general, this could be any enemy or NPC.

Attributes:
(- Name)

Available methods:
(- speaks)
(- attacks)
(- leaves)
