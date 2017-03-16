/**
 *  This class is part of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.
 * 
 *  This class creates all rooms, creates the parser and starts
 *  the game.  It also evaluates and executes the commands that 
 *  the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (Jan 2003)
 */

import java.util.HashMap;
import java.util.Stack;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.Thread;

public class GameEngine
{
    private Stack<Room> historyRoom = new Stack<>();
    private HashMap<String, Room> hmRoom = new HashMap<>();
    private HashMap<String, Item> hmItem = new HashMap<>();
    private Parser parser;
    private Room currentRoom;
    private UserInterface gui;
    private Room room;
    private Player mainPlayer;

    /**
     * Constructor for objects of class GameEngine
     */
    public GameEngine()
    {
        parser = new Parser();
        createItems();
        createRooms();
        mainPlayer = createPlayer();
    }

    public void setGUI(UserInterface userInterface)
    {
        gui = userInterface;
        printWelcome();
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        gui.print("\n");
        gui.println("Bienvenue dans le monde de [INSERER TITRE DE JEU STUPIDE ICI]");
        gui.println("[INSERER TITRE DE JEU STUPIDE ICI] est un magnifique jeu de promenade !");
        //gui.println("Taper 'help' pour obtenir de l'aide.");
        gui.print("\n");
        gui.println(currentRoom.getLongDescription());
        gui.showImage(currentRoom.getImageName());
    }
    
    private Player createPlayer()
    {
        Player player;
        player = new Player("Bernard", "Un preu chevalier resistant contre l'espionage de ça femme", 5.0);
        player.setCurrentRoom(hmRoom.get("outside"));
        return player;
    }  

    private void createItems()
    {
        Item sword, food, chair, keyboard, card;

        // create the item
        sword = new Item("épée", "l'épee vibrante de Maman, Elle en a combatue des monstres avec ça !", 0.6);
        chair = new Item("Chaise", "une chaise, toute bonne grand mère a toujours sa chaise pour faire une pose durant sa promenade", 3.0);
        food = new Item("Nouriture", "du Thon allégée, parfait pour les petites faim.", 0.2);
        keyboard = new Item("clavier", "un clavier qui fait de la lumière !", 1.2);
        card = new Item("Carte", "une carte de sécurité", 0.02);
        
        hmItem.put("sword", sword);
        hmItem.put("food", food);
        hmItem.put("chair", chair);
        hmItem.put("keyboard", keyboard);
        hmItem.put("card", card);

    }    
   
    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theatre, pub, lab, office;

        // create the rooms
        outside = new Room("outside the main entrance of the university", "titre.png");
        theatre = new Room("in a lecture theatre", "castle.gif");
        pub = new Room("in the campus pub", "courtyard.gif");
        lab = new Room("in a computing lab", "stairs.gif");
        office = new Room("the computing admin office", "dungeon.gif");
        
        hmRoom.put("outside", outside);
        hmRoom.put("theatre", theatre);
        hmRoom.put("pub", pub);
        hmRoom.put("lab", lab);
        hmRoom.put("office", office);
        
        // initialise room exits
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theatre.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);
        
        //initialise item
        theatre.setItem("sword", hmItem.get("sword"));
        theatre.setItem("chair", hmItem.get("chair"));
        
        pub.setItem("food", hmItem.get("food"));
        
        lab.setItem("keyboard", hmItem.get("keyboard"));
        
        office.setItem("card", hmItem.get("card"));
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    public void interpretCommand(String commandLine) 
    {
        gui.println(commandLine);
        Command command = parser.getCommand(commandLine);

        if(command.isUnknown()) {
            gui.println("I don't know what you mean...");
            return;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("go"))
            goRoom(command);
        else if (commandWord.equals("back"))
            back(command);
        else if (commandWord.equals("test"))
            test(command);
        else if (commandWord.equals("quit")) {
            if(command.hasSecondWord())
                gui.println("Quit what?");
            else
                endGame();
        }
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    public void printHelp() 
    {
        gui.println("You are lost. You are alone. You wander");
        gui.println("around at Monash Uni, Peninsula Campus." + "\n");
        gui.print("Your command words are: " + parser.showCommands());
        gui.print("\n");
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            gui.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null)
            gui.println("There is no door!");
        else {
            historyRoom.push(currentRoom);
            currentRoom = nextRoom;
            mainPlayer.setCurrentRoom(currentRoom);
            gui.println(currentRoom.getLongDescription());
            gui.println(currentRoom.getItemDescriptionString());
            if(currentRoom.getImageName() != null)
                gui.showImage(currentRoom.getImageName());
        }
    }
    
    private void back(Command command){
        if(command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            gui.println("Back What ?");
            return;
        }
        
        if(historyRoom.empty()){
            gui.println("UGH UGH UGH Fucking retarded player who want back more what he can.");
            return;
        }
        
        currentRoom = historyRoom.peek();
        historyRoom.pop();
        
        gui.println(currentRoom.getLongDescription());
        gui.println(currentRoom.getItemDescriptionString());
        if(currentRoom.getImageName() != null)
            gui.showImage(currentRoom.getImageName());
        else
            gui.showImage("titre.png");
    }
    
    private void test(Command command){
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            gui.println("Tester quoi ?");
            return;
        }
        
        
        String FILENAME = command.getSecondWord();
        BufferedReader br = null;
        FileReader fr = null;
        
        String sCurrentLine;
        try{
        br = new BufferedReader(new FileReader (FILENAME));
        
        while ((sCurrentLine = br.readLine()) != null){
            interpretCommand(sCurrentLine);
        }
        if(br !=null)
            br.close();
        if(fr !=null)
            fr.close();
        }
        catch (IOException e){
            gui.println("Aucun fichier de ce nom existe.");
            return;
        }
        return;
    }

    private void endGame()
    {
        gui.println("Thank you for playing.  Good bye.");
        gui.enable(false);
    }

}
