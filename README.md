# text-adventure
A text adventure gaming system created for the purpose of learning how to code in Java

This text adventure is supposed to help me learn Java in a way that I feel motivated to do it.

The core concepts:

# Player
The Player is the protagonist, the "you" that walks through the locations, picks up items, fights monsters, talks with people (in future versions).

I thought of the following parameters in the beginning:
- has a name
- has Items
- is in Location
-- gets description of Location
-- gets list of Items in Location
-- picks up Items from Location
-- uses Items in Location
-- goes through Exit

# Location
Locations are the rooms (or later maybe 'map tiles') that the player traverses.

The following concepts came up in the first brainstorm:
- has a title
- has a description
- has Exits

# Exit
Exits are simply the directions a player can move in when leaving a location. This concept was taken more or less from https://www.javacoffeebreak.com/text-adventure/.

These were my first ideas for Exits:
- has a direction (short + long)
- has a start Location
- has a target Location

# Items
Items are any objects that can exist in the game world. For now there's only doors and a key, but I was thinking about anything that is more than just for decoration.

Basic ideas:
- has a title
- has a description
- can be containers
- can trigger events

# Character
This is a future concept, not yet implemented. But in general, this will be any enemy or NPC.

Basic ideas:
- has a name
- speaks
- attacks
- leaves
