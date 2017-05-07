import java.util.Vector;
import java.util.Iterator;

public class Player{
    private String name;
    private String description;
    private double maxWeight;
    private double maxTakable;
    private Vector<Item> playerInventory = new Vector<Item>();
    private Room currentRoom;
    private Item carriedItem;

    public Player(String name, String description, double maxWeight, double maxTakable) 
    {
        this.name = name;
        this.description = description;
        this.maxWeight = maxWeight;
        this.maxTakable = maxTakable;
    }
    
    public String getName()
    {
        return name;
    }
    
    public double getMaxTakable(){
        return maxTakable;
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
            inventory.append("Rien. \nVous avez dans la main : ");
        } else{
            while(b < i){
                inventory.append(playerInventory.elementAt(b).getName() + " ");
                b+= 1;
            }
            inventory.append(".\nVous avez dans la main : ");
        }
        if(carriedItem == null)
            inventory.append("rien.");
        else
            inventory.append(carriedItem.getName() + ".");
        return inventory.toString();
    }
    
    public String showInventory2(){
        StringBuilder inventory = new StringBuilder ("Inventaire : ");
        int i = playerInventory.size();
        int b = 0;
        if(playerInventory.isEmpty()){
            inventory.append("Rien. \nVous avez dans la main : ");
        } else{
            while(b < i){
                inventory.append(playerInventory.elementAt(b).getName() + " : " + playerInventory.elementAt(b).getDescription() + " [" + playerInventory.elementAt(b).getWeight() + "]\n");
                b+= 1;
            }
            inventory.append(".\nVous avez dans la main : ");
        }
        if(carriedItem == null)
            inventory.append("rien.");
        else
            inventory.append(carriedItem.getName() + " : " + carriedItem.getDescription() + " [" + carriedItem.getWeight() + "]");
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
    
    public void removeInvItem(Item thisItem){
        playerInventory.remove(thisItem);
    }
    public void removeHandItem(){
        carriedItem = null;
    }
    public double totalWeight(){
        int i = playerInventory.size();
        double y = 0.0;
        int b = 0;
        while(b < i){
             y += playerInventory.elementAt(b).getWeight();
             i-= 1;
             b+=1;
        }
        return y;
    }
}
