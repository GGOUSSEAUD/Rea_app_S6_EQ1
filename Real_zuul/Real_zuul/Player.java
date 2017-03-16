import java.util.Vector;

public class Player{
    private String name;
    private String description;
    private double maxWeight;
    private Vector playerInventory;
    private Room currentRoom;
    private Item carriedItem;

    public Player(String name, String description, double maxWeight) 
    {
        this.name = name;
        this.description = description;
        this.maxWeight = maxWeight;
    }
    
    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }
    
    public double getMaxWeight(){
        return maxWeight;
    }
   
    public Room getCurrentRoom()
    {
        return currentRoom;
    }
    
    public void setCurrentRoom(Room thisRoom){
        currentRoom = thisRoom;
    }
    
    public int carryItem(){
        if(carriedItem !=null){
            playerInventory.add(carriedItem);
            carriedItem = null;
        }
        else
            return 0;
        return 1;
    }
    
    public int takeItem(Item thisItem){
        if(carriedItem == null)
            carriedItem = thisItem;
        else
            return 0;
        return 1;
    }
    
    public int dropItem(){
        if(carriedItem != null){
            carriedItem = null;
            return 1;
        }
        else
            return 0;
    }
}
