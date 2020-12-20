package game;

public class Exit {
    // Numerical codes
    public static final int UNDEFINED = 0;
    public static final int NORTH = 1;
    public static final int SOUTH = 2;
    public static final int EAST  = 3;
    public static final int WEST  = 4;
    public static final int UP    = 5;
    public static final int DOWN  = 6;
    public static final int NORTHEAST = 7;
    public static final int NORTHWEST = 8;
    public static final int SOUTHEAST = 9;
    public static final int SOUTHWEST = 10;
    public static final int IN = 11;
    public static final int OUT = 12;

    // String codes
    public static final String[] dirName =
            {
                    "undefined",
                    "north",
                    "south",
                    "east",
                    "west",
                    "up",
                    "down",
                    "northeast",
                    "northwest",
                    "southeast",
                    "southwest",
                    "out",
                    "in"
            };

    public static final String[] shortDirName =
            {
                    "null",
                    "N",
                    "S",
                    "E",
                    "W",
                    "↑",
                    "↓",
                    "NE",
                    "NW",
                    "SE",
                    "SW",
                    "IN",
                    "OUT"
            };

    // Variables
    private Location destination;
    private int direction;
    private String directionName;
    private String directionShortName;

    // Default constructor
    public Exit()
    {
        this.direction = Exit.UNDEFINED;
        this.destination = null;
        this.directionName = dirName[UNDEFINED];
        this.directionShortName = shortDirName[UNDEFINED];
    }

    // Full constructor
    public Exit( int direction, Location destination)
    {
        this.direction = direction;

        if (direction <= dirName.length )
            this.directionName = dirName[this.direction];
        if (direction <= shortDirName.length )
            this.directionShortName = shortDirName[this.direction];

        this.destination = destination;
    }

    // Getters & setters
    public void setDirectionName(String directionName)
    {
        this.directionName = directionName;
    }

    public String getDirectionName()
    {
        return directionName;
    }

    public void setDirectionShortName(String directionShortName)
    {
        this.directionShortName = directionShortName;
    }

    public String getDirectionShortName()
    {
        return directionShortName;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public Location getDestination() {
        return destination;
    }
}
