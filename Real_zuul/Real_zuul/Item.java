import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

/*
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002) DBMOD:04/04/2008
 */

public class Item 
{
    private String name;
    private String description;
    private double weight;

    public Item(String name, String description, double weight) 
    {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }
    
    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }
    
    public double getWeight()
    {
        return weight;
    }
}
