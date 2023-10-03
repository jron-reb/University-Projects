//https://howtodoinjava.com/java/collections/java-copyonwritearraylist/
import java.io.File;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class PebbleGame {

    private ArrayList<Player> players;//arraylist of players
    private CopyOnWriteArrayList<ArrayList<Integer>> bags;//a bag is treated as an arraylist of integers
    private ArrayList<Thread> threads;//arraylist of players

    public PebbleGame() {
        this.players = new ArrayList<Player>();
        this.bags = new CopyOnWriteArrayList<ArrayList<Integer>>();
        this.threads = new ArrayList<Thread>();
    }

    private ArrayList<Player> getPlayers() {
        return players;
    }

    public List<ArrayList<Integer>> getBags() {
        return bags;
    }

    public ArrayList<Thread> getThreads() {
        return threads;
    }

    //function creates bags from the files that are input by the user
    private ArrayList<Integer> createBag(String filename) {
        
        File file = new File(filename);
        String data = null;
        try {

            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {// read the file until the end
                data = fileReader.nextLine();
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        String[] stringArray = data.split(",\\s*");
        ArrayList<Integer> bag = new ArrayList<>();

        for (String number : stringArray) {
            bag.add(Integer.parseInt(number));
        }

        return bag;
    }

    public String toString() {
        return "[players = " + this.players + "]";
    }

    class Player implements Runnable {
        // players should act as concurrent threads
        private int playerID;
        private ArrayList<Integer> pebbles;
        private int weight;
        private Boolean leave = false;
        // weight is the sum of the pebbles

        public Player(int playerID) throws IOException {
            this.playerID = playerID;
            this.pebbles = new ArrayList<Integer>();
            initialDraw();
        }

        @Override
        public String toString() {
            return "[playerID = " + this.playerID + ", pebbles = " + this.pebbles + ", weight = " + this.weight + "]";
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public ArrayList<Integer> getPebbles() {
            return pebbles;
        }

        //provides the initial setup for the game
        //it should be noted that each player will start with 10 pebbles in their hand since the first action of each player is to draw a pebble
        private void initialDraw() throws IOException {
            for (int i = 0; i < 9; i++) {// calls draw 9 times
                draw();
            }
        }

        //function provides logging functionality and happens when a player, draws a pebble, discards a pebble or wins the game
        //the variable String (type) represents the action that is happening, be it a player drawing or discarding a pebble or having won the game
        private void logger(String type, Integer pebble, Integer bagIndex) throws IOException {
            String bagLetter = null;// otherwise variable might not have been initialized

            String filename = "player_output/player" + playerID + "_output.txt";
            FileWriter file = new FileWriter(filename, true);
            PrintWriter out = new PrintWriter(file);

            if (type.equals("game_won")) {//if player has won the game
                out.println("The weight of the pebbles held by player" + playerID + " is exactly 100 "
                        + ", meaning they have won! The hand of player" + playerID + " is " + pebbles);
                System.out.println("The game is over, player" + playerID + " has won!");
                out.close();// close resource leek
            }

            switch (bagIndex) {
            case 0:
                bagLetter = "A";
                break;
            case 1:
                bagLetter = "B";
                break;
            case 2:
                bagLetter = "C";
                break;
            case 3:
                bagLetter = "X";
                break;
            case 4:
                bagLetter = "Y";
                break;
            default:
                bagLetter = "Z";
                break;
            }


            out.println("player" + playerID + " has " + type + " a " + pebble + " from bag " + bagLetter);
            out.println("player" + playerID + " hand is " + pebbles);
            out.close();// close resource leak

        }

        private synchronized void refill(int bagNum) {

            //bags.get(bagNum-3) represents the corresponding white bag that the pebbles are emptied into the black bag from
            bags.get(bagNum).addAll(bags.get(bagNum-3));
            //addAll is used rather than a for loop or an iterator because it is more thread saef

            // empty the whitebag
            bags.get(bagNum - 3).clear();
        }

        //this method is responsible for drawing the pebbles
        //is synchronized to ensure threadsafety, because this method edits the bags, which may otherwise be accessed by multiple bags at the same time
        private synchronized int draw() throws IOException {
            Boolean pebbleDrawn = false;
            Random randomNumGen = new Random();
            int selectBagIndex = -1;
            int randomPebbleIndex = -1;
            int randomPebble = 0;

            while (!pebbleDrawn) {
                try {
                    //Selects a black bag in a uniformly random method
                    //the 3+ is because the black bags have an index from 3-5 and randonNumGen.nextInt(n) generates an int from 0 to n (exclusive)
                    selectBagIndex = 3 + randomNumGen.nextInt(3);

                    ArrayList<Integer> selectBag = new ArrayList<Integer>();
                    selectBag = bags.get(selectBagIndex);

                    //if the selected bag is empty refill said bag
                    if (selectBag.isEmpty()) {
                        refill(selectBagIndex);
                    }

                    randomPebbleIndex = randomNumGen.nextInt(selectBag.size());
                    randomPebbleIndex--;
                    if (randomPebbleIndex < 0) {
                        randomPebbleIndex = 0;
                    }

                    //select a random pebble using a randomly generated index 
                    randomPebble = selectBag.get(randomPebbleIndex);
                    
                    bags.get(selectBagIndex).remove(randomPebbleIndex);// updates bags
                    // if the whole try is executed successfuly with no array out of bounds then set pebbleDrawn to true and exit the while loop
                    pebbleDrawn = true;
                } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException | NullPointerException e) {
                    // If this error is called refill the bags
                    refill(selectBagIndex);
                    // refill(selectBagIndex);
                } catch (IndexOutOfBoundsException e) {
                    continue;
                    //if this error is called simply try again
                }
            }

            // update arrays
            pebbles.add(randomPebble);// updates player
            weight += randomPebble;// updates player weight

            checkWeight();//checks if player has won

            // log draw to player file
            logger("drawn", randomPebble, selectBagIndex);

            // returns the bag that a pebble was last selected from (for next discard)
            return selectBagIndex;
        }

        //this method checks if a player has won the game
        private boolean checkWeight() throws IOException {

            if (this.weight == 100 && pebbles.size() == 10) {//loop entered when a player has won
                logger("game_won", -1, -1); //-1, -1 are irrelevant the only information needed is the string that says game won so if it does not enter the loop it will crash as designed
                System.out.println("Player" + this.playerID + " won");
                System.exit(0); //exits the system so does not need to return anything and automatically immmediately stops all threads
            }
            return false;
        }

        //this method is responsible for discarding pebbles
        //this method is synchronized to ensure threadsafety
        private synchronized void discard(int selectBagIndex) throws IOException {
            // take first pebble from player's pebbles (remove from arraylist)
            // place in white bag selectBagIndex-3
            Integer pebble;

            // update arrays
            // takes 1st pebble, simple implementation and efficient
            pebble = pebbles.get(0);
            pebbles.remove(0);
            bags.get(selectBagIndex - 3).add(pebble);

            weight -= pebble;// update player weight

            // log discard to player file
            logger("discarded", pebble, selectBagIndex - 3);
        }

        //this method is called when a player is constructed by the thread that the player implements
        @Override
        public synchronized void run() {

            while (!leave) {//in this loop it will draw and discard before repeating the loop until the system shuts down
                try {
                    int bag;
                    bag = draw();
                    discard(bag);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        

    }

    public static void main(String[] args) {

        PebbleGame pebbleGame = new PebbleGame(); // initialised in order to store the bags and players
        InputValidation inputValidation = new InputValidation(); // initialised in order to be able to access the
                                                                 // methods within there

        //// right here is where the method loadstartingmenu begins, idk whether to
        //// implement like this or in a different methiod
        Scanner in = new Scanner(System.in);
        inputValidation.fileEraser();// deletes every playeroutput file from previous games

        // The top 2 commands ensure that the terminal is cleared everytime the
        // interface "switches screens" and loads the main menu
        System.out.print("\033[H\033[2J");// ANSI escape codes, clear screen and home
        System.out.flush();
        System.out.println(
                "Welcome to the PebbleGame!\nYou will be asked to enter the number of players.\nThen for the location of three files in turn containing comma seperated integer values for the pebble weights\nThe integer values must be strictly positive\nPlease enter number of players:");
        String numPlayersString = null;// given an initial null value to stop error, "might not have been initialized"
                                       // occuring
        boolean validNumber = false;
        while (!validNumber) {
            numPlayersString = in.nextLine();
            // the boolean validNumber is passed through to the method, so that it can
            // access and modify it
            validNumber = inputValidation.validateNumberInput(validNumber, numPlayersString);
        }

        int numPlayers = Integer.valueOf(numPlayersString);

        // converting string to int will throw a numberformatexception
        // issues that could happen with the file input
        // it doesnt exist but that gets handled when the file gets processed
        // the input isn't a csv file so that could get checked as well
        System.out.println("Please enter location of bag number 1 to load");

        boolean validFile1 = false;
        boolean validFile2 = false;
        boolean validFile3 = false;
        String bagXFilePath = null;
        String bagYFilePath = null;
        String bagZFilePath = null;
        //the 3 while loops in order to validate the files are implemented in the main here rather than in the InputValidation class in order to easily give the pebbleGame object access to the filenames and to make the implementation of the scanner simpler to understand
        while (!validFile1) {
            bagXFilePath = in.nextLine();

            validFile1 = inputValidation.validateFileInput(validFile1, bagXFilePath, numPlayers);//calls the method input validation from the Input Validation class using the inputValidation object
            //if the file is not valid it will continue to ask until a valid file is entered or E is input
        }

        System.out.println("Please enter location of bag number 2 to load");

        while (!validFile2) {
            bagYFilePath = in.nextLine();

            validFile2 = inputValidation.validateFileInput(validFile2, bagYFilePath, numPlayers);
        }

        System.out.println("Please enter location of bag number 3 to load");

        while (!validFile3) {
            bagZFilePath = in.nextLine();

            validFile3 = inputValidation.validateFileInput(validFile3, bagZFilePath, numPlayers);
        }


        // Create bags and append them to the pebbleGame object

        ArrayList<Integer> bagX = pebbleGame.createBag(bagXFilePath);
        ArrayList<Integer> bagY = pebbleGame.createBag(bagYFilePath);
        ArrayList<Integer> bagZ = pebbleGame.createBag(bagZFilePath);
        ArrayList<Integer> bagA = new ArrayList<>();
        ArrayList<Integer> bagB = new ArrayList<>();
        ArrayList<Integer> bagC = new ArrayList<>();
        pebbleGame.getBags().add(0, bagA);
        pebbleGame.getBags().add(1, bagB);
        pebbleGame.getBags().add(2, bagC);
        pebbleGame.getBags().add(3, bagX);
        pebbleGame.getBags().add(4, bagY);
        pebbleGame.getBags().add(5, bagZ);

        for (int i = 1; i <= numPlayers; i++) {//make the number of players specified by the user, start the thread upon creation
            Player player;
            try {
                player = pebbleGame.new Player(i);
                inputValidation.fileCreator("player_output", "player" + i + "_output.txt");//creates an output file for each player
                pebbleGame.getPlayers().add(player);
                Thread thread = new Thread(player);
                pebbleGame.getThreads().add(thread);
                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        

        while (true) {//test if the user inputs E while the game is running
            inputValidation.shutdownValidator(in.nextLine());
        }
    }
}