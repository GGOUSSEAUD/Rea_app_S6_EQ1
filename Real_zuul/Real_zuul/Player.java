import java.util.Vector;
import java.util.Iterator;

public class Player{
    private String name;
    private String description;
    private double maxWeight;
    private Vector<Item> playerInventory = new Vector<Item>();
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
    
    public String showInventory(){
        StringBuilder inventory = new StringBuilder ("Inventaire : ");
        int i = playerInventory.size();
        if(playerInventory.isEmpty()){
            inventory.append("Rien.");
        } else{
            while(i != 0){
                inventory.append(playerInventory.elementAt(i).getName());
                i-= 1;
            }
            inventory.append(".");
        }
        return inventory.toString();
    }
    
    public String getCarriedItem(){
        if(carriedItem != null)
            return carriedItem.getName();
        return "Rien";
    }
}
