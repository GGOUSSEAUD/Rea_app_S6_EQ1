import java.util.*;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Room 
{
    private String description;
    private String exit_description;
    private HashMap<String, Room> exits;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    /**
    * Define an exit from this room.
    * @param direction The direction of the exit.
    * @param neighbor The room in the given direction.
    */
    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
    * Return a long description of this room, of the form:
    * You are in the kitchen.
    * Exits: north west
    * @return A description of the room, including exits.
    */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }
    
    
    /**
     * Return a description of the room's exits,
     * for example, "Exits: north west".
     * @return A description of the available exits
     * Fonction qui bug
     */
   public String getExitString()
    {
        if(exit_description != null)
            return exit_description; 
        exit_description = "";        
        for (Map.Entry<String, Room> entry : exits.entrySet()) {
            String key = entry.getKey();
            Room value = entry.getValue();
            //System.out.println("Key is :"+ key + " value is: " + value);
            if(value != null){
                exit_description += key + " ";
                //System.out.println("curr exit desc =" + exit_description);
            }else
                exit_description += "";
            
        }
     return exit_description;
    }
    
    /**
    * Return the room that is reached if we go from this 
    * room in direction "direction". If there is no room in 
    * that direction, return null.
    */
    
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }
    
    
}
