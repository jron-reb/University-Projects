import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class InputValidation {
    //constructor contains no arguments but exists to allow access to methods
    public InputValidation () {}

    //method validates numberinput by ensuring that user input is an integer greater than 0
    public Boolean validateNumberInput(Boolean validNumber, String numPlayersString) throws NumberFormatException{
        try {
            //tests if the input is E, which means the user wants to shutdown the program
            shutdownValidator(numPlayersString);

            int numPlayers = Integer.valueOf(numPlayersString);
            
            if (numPlayers < 2) {
                System.out.println("Please input an integer greater than 1");
                return validNumber = false;
            }


            
            //if all tests are passed then change the boolean validNumber to true (will exit while loop)
            return validNumber = true;
            
        } catch (NumberFormatException e) {
            System.out.println("Please input an integer greater than 1");
            return validNumber = false;
        }
        
    }

    //method validates that the file input is in the write format and that the file has the correct formatting
    public Boolean validateFileInput(Boolean validFile, String fileName, int numPlayers) {
            
            shutdownValidator(fileName);
            
            File file = new File(fileName);
            
            try {//makes sure that the file matches the regex
                Scanner fileReader = new Scanner(file);
                final String regex ="[0-9]+(,\\s*[0-9]+\\s*)*";
                boolean matches = true;
                String data = null;

                while (fileReader.hasNextLine()){//read the file until the end
                    data = fileReader.nextLine();
                    
                    if (data.matches(regex) == false) { //if the input from the file does not match the regex, the validator will return false
                        matches = false;
                    }
                }

                validFile = matches;
                fileReader.close();

                
                String[] intArray = data.split(",\\s*");
                int count = intArray.length;

                if (validFile == false) {
                    System.out.println("Please input a file of valid format (positive integers seperated by a comma)");
                }

                if (count < 11*numPlayers) {//makes sure that there are enough pebbles in each file
                    validFile = false;
                    System.out.println("Please input a file containing at least 11 times as many pebbles (integers) as players");
                }

                
            } catch (FileNotFoundException e) {
                //TODO: handle exception
                System.out.println("Cannot locate file, please input a valid filepath");
                validFile = false;
                
            }

            return validFile;

    }
    
    
    //method creates player output files
    //WILL NEVER EVER GO INTO THE CATCH
    public void fileCreator(String directoryName, String fileName) {
        try {
            File temp = new File(directoryName, fileName);
            FileWriter writer = new FileWriter(temp);
            writer.close();
        } catch (Exception e) {
        }
        
    }

    //empties the folder player_output before the beginning of each game
    public void fileEraser() {
        File player_output_directory = new File("player_output");
        File fileslist[] = player_output_directory.listFiles();
        for (File file : fileslist) {
            file.delete();
        }
    }

    public void shutdownValidator(String string) {
        if (string.equals("E")) {
            //terminates program
            //0 indicates successful termination
            System.exit(0);
        }
    }
}
