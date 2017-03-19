import java.util.Vector;
import java.util.Iterator;

public class Player{
    private String name;
    private String description;
    private double maxWeight;
    private Vector<Item> playerInventory;
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
    
    public void setMaxWeight(double newMaxWeight){
        maxWeight = newMaxWeight;
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
        int b = 0;
        if(playerInventory.isEmpty()){
            inventory.append("Rien.");
        } else{
            while(b < i){
                inventory.append(playerInventory.elementAt(b).getName());
                b+= 1;
            }
            inventory.append(".");
        }
        return inventory.toString();
    }
    
    public String getCarriedItemName(){
            return carriedItem.getName();
    }
    
    public Item getCarriedItem(){
            return carriedItem;
    }
    
    public boolean inInventory(Item thisItem){
        return playerInventory.contains(thisItem);
    }
    
    public void uncarryItem(Item thisItem){
        playerInventory.remove(thisItem);
    }
    
    
    public double totalWeight(){
        int i = playerInventory.size();
        double y = 0.0;
        while(i != 0){
             y += playerInventory.elementAt(i).getWeight();
             i-= 1;
        }
        return y;
    }
}
