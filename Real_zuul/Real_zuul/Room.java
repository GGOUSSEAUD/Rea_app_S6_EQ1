import java.util.Set;import java.util.HashMap;import java.util.Iterator;/* * Class Room - a room in an adventure game. * * This class is part of the "World of Zuul" application.  * "World of Zuul" is a very simple, text based adventure game.   * * A "Room" represents one location in the scenery of the game.  It is  * connected to other rooms via exits.  For each existing exit, the room  * stores a reference to the neighboring room. *  * @author  Michael Kolling and David J. Barnes * @version 1.0 (February 2002) DBMOD:04/04/2008 */public class Room {    private String description;    private HashMap<String,Room> exits;       private HashMap<String, Item> itemList;// stores exits of this room.    private String imageName;    /**     * Create a room described "description" with a given image.      * Initially, it has no exits. "description" is something like      * "in a kitchen" or "in an open court yard".     */    public Room(String description, String image)     {        this.description = description;        exits = new HashMap<String,Room>();        itemList = new HashMap<String,Item>();        imageName = image;    }    /**     * Define an exit from this room.     */    public void setExit(String direction, Room neighbor)     {        exits.put(direction, neighbor);    }        /**     * Define an exit from this room.     */    public void setItem(String itemName, Item item)     {        itemList.put(itemName, item);    }    /**     * Room's item getter      */    public Item getItem(String itemName){        return itemList.get(itemName);    }        public Boolean itemExist(String itemName){        return itemList.containsValue(itemName);    }        /**     * Return the description of the room (the one that was defined in the     * constructor).     */    public String getShortDescription()    {        return description;    }    /**     * Return a long description of this room, in the form:     *     You are in the kitchen.     *     Exits: north west     */    public String getLongDescription()    {        return "You are " + description + ".\n" + "Vous trouver :" + getItemString() + "\n" + getExitString();    }    public String getItemString()    {        StringBuilder returnString = new StringBuilder( "" );        if(itemList.isEmpty())            returnString.append(" rien.");        for ( String vS :itemList.keySet() ){            returnString.append(" " + itemList.get(vS).getName());        }        //returnString.deleteCharAt(returnString.length()-1);        return returnString.toString();    }        public String getItemDescriptionString()    {        StringBuilder returnString = new StringBuilder( "" );        if(!itemList.isEmpty()){            for ( String vS :itemList.keySet() ){                returnString.append(" " + vS + " : " + itemList.get(vS).getDescription() + "\n");            }        }        return returnString.toString();    }        /**     * Return a string describing the room's exits, for example     * "Exits: north west".     */    public String getExitString()    {        StringBuilder returnString = new StringBuilder( "Exits:" );        for ( String vS : exits.keySet() )            returnString.append( " " + vS );        return returnString.toString();    }    /**     * Return the room that is reached if we go from this room in direction     * "direction". If there is no room in that direction, return null.     */    public Room getExit(String direction)     {        return exits.get(direction);    }    /**     * Return a string describing the room's image name     */    public String getImageName()    {        return imageName;    }        public void removeItem(String itemName){        itemList.remove(itemName);    }}