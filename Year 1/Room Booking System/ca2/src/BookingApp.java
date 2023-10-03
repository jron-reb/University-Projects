import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Represents a the functionality of the program
 * The booking app only contains the booking system
 * The booking app is used to access all of the functionality of the booking system
 * The booking app loads and deals with inputs from the user and allows them to navigate the interface
 *
 * @author Jeroen Mijer
 * @version 1.0
 * @since 1.0
 */

public class BookingApp {

    private BookingSystem bookingSystem;

    /**
     * Creates a booking app
     * Booking app consists solely of the booking system
     *
     * @param bookingSystem// booking system of the booking app
     */
    public BookingApp(BookingSystem bookingSystem) {
        this.bookingSystem = bookingSystem;
    }

    /**
     * Loads the main menu
     * Triggered at startup and when the user inputs 0
     */
    private void loadMainMenu() {
        //The top 2 commands ensure that the terminal is cleared everytime the interface "switches screens" and loads the main menu
        System.out.print("\033[H\033[2J");//ANSI escape codes, clear screen and home
        System.out.flush();
        System.out.println("University of Knowledge - COVID test\n");
        System.out.println("Manage Bookings\n");
        System.out.println("Please enter the number to select your option:\n");
        System.out.println("To manage Bookable Rooms:\n1. List\n2. Add\n3. Remove");
        System.out.println("To manage Assistants on Shift:\n4. List\n5. Add\n6. Remove");
        System.out.println("To manage Bookings:\n7. List\n8. Add\n9. Remove\n10. Conclude");
        System.out.println("After selecting one the options above, you will be presented other screens.");
        System.out.println("If you press 0, you will be able to return to this main menu.");
        System.out.println("Press -1 (or ctrl+c) to quit this application.");
        System.out.println();
    }

    /**
     * Quits the application
     * Called when the user inputs -1
     */
    private void quitApplication() {
        System.out.print("\033[H\033[2J");//ANSI escape codes, clear screen and home
        System.out.flush();
    }

    /**
     * Takes the scanner input and adds a bokoable room accordingly
     * This splits the scanner input into its respective components (sequential ID and date)
     * Adds the bookable room accordingly
     *
     * @param scannerInput//scanner input of user
     * @throws java.lang.IndexOutOfBoundsException
     * @throws java.lang.NumberFormatException
     */
    private void addBookableRoom(String scannerInput) {
        try {
            String[] scannerInputSplit = scannerInput.split(" ");
            int i = Integer.parseInt(scannerInputSplit[0]) - 11;//Convert the input sequential ID into an integer, subtract it by 11 to find the index of the bookable room it refers to
            String tempCode = bookingSystem.getBookableRooms().get(i).getCode();//Collect the code of the bookable room
            int tempCapacity = bookingSystem.getBookableRooms().get(i).getCapacity();
            String tempDate = scannerInputSplit[1] + " " + scannerInputSplit[2];
            bookingSystem.addBookableRoom(new BookableRoom(tempCode, tempCapacity, tempDate));
            //After the room is added succesfully, let the user know and give them the option to add another bookable room
            System.out.println("Bookable Room added successfully:");
            int indexBookableRoomAdded = bookingSystem.getBookableRooms().size() - 1; //the most recently added room will be the last one in the ArrayList of Bookable Rooms
            System.out.println(bookingSystem.getBookableRooms().get(indexBookableRoomAdded));//Print out the just added bookable room
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Error!\nThe input must consist of a sequential ID between 11 and " + (bookingSystem.getBookableRooms().size() + 10) +
                    " and a date in the format dd/mm/yyyy. You input " + scannerInput + ".");
        } catch (NumberFormatException ex) {
            System.out.println("Error!\nThe first input must be an integer");
        } finally {//Want this code to print regardless of whether adding the bookable room was successful or not
            //formatting text for the user
            System.out.println("Please, enter one of the following:\n");
            System.out.println("The sequential ID listed to a room, a date (dd/mm/yyyy), and a time (HH:MM), seperated by a white space.");
            System.out.println("0. Back to main menu");
            System.out.println("-1. Quit application\n");
        }
    }

    /**
     * Checks whether the input to add a bookable room is in the right format
     * Returns a true or false value, where true means that it was valid and false means it was invalid
     *
     * @param scannerInput
     * @return Boolean
     * @throws java.lang.NumberFormatException
     * @throws java.lang.ArrayIndexOutOfBoundsException
     */
    private Boolean inputAddBookableRoomCheck(String scannerInput) {
        try {
            /*
            The checks done in this method are:
            Day is between 1 and 31
            Month is between 1 and 12
            Year is between 2021 and 2100
            hour is between 7 and 9
            Minute is 00
            The day,month,hour,minute are 2 digit integers and that the year is a 4 digit integer
            That there are / seperating day, month and year
            That the hour and minute input is split by :
            That there is 3 inputs seperated by a white space
             */
            String[] scannerInputSplit = scannerInput.split(" ");//int //dd/mm/yy //HH:MM
            String[] dateSplit = scannerInputSplit[1].split("/");//dd //mm //yyyy
            String[] timeSplit = scannerInputSplit[2].split(":"); //hh //mm
            int day = Integer.parseInt(dateSplit[0]);
            int month = Integer.parseInt(dateSplit[1]);
            int year = Integer.parseInt(dateSplit[2]);
            int hour = Integer.parseInt(timeSplit[0]);
            int minute = Integer.parseInt(timeSplit[1]);
            int scannerInputLength = scannerInputSplit.length;
            if (scannerInputLength > 3) {
                System.err.println("Error!\nThe input had more than 3 terms");
                return false;
            }
            if (1 > day || day > 31) {
                System.err.println("Error!\nThe day must be between 1 and 31");
                return false;
            }
            if (1 > month || month > 12) {
                System.err.println("Error!\nThe month must be between 1 and 12");
                return false;
            }
            if (2021 > year || year > 2100) {
                System.err.println("Error!\nThe year must be between 2021 and 2100");
                return false;
            }
            if (00 != minute) {
                System.err.println("Error!\nThe minute must be 00");
                return false;
            }
            if (7 > hour || hour > 9) {
                System.err.println("Error!\nThe hour must be between 7 and 9");
                return false;
            }
            if (dateSplit[0].length() != 2) {
                System.err.println("Error!\nThe day must be a 2 digit integer");
                return false;
            }
            if (dateSplit[1].length() != 2) {
                System.err.println("Error!\nThe month must be a 2 digit integer");
                return false;
            }
            if (dateSplit[2].length() != 4) {
                System.err.println("Error!\nThe year must be a 4 digit integer");
                return false;
            }
            if (timeSplit[0].length() != 2) {
                System.err.println("Error!\nThe hour must be a 2 digit integer");
                return false;
            }
            if (timeSplit[1].length() != 2) {
                System.err.println("Error!\nThe minute must be a 2 digit integer");
                return false;
            }
        } catch (NumberFormatException ex) {
            System.out.println("Error!\nThe input date and time format is invalid");
            return false;
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Error!\nThe input is in the invalid format");
            return false;
        }
        return true;
    }

    /**
     * Takes the scanner input and adds an assistant on shift accordingly
     * This splits the scanner input into its respective components (sequential ID and date)
     *
     * @param scannerInput//scanner input of user
     * @throws java.lang.IndexOutOfBoundsException
     * @throws java.lang.NumberFormatException
     */
    private void addAssistantOnShift(String scannerInput) {
        try {
            String[] scannerInputSplit = scannerInput.split(" ");
            int i = Integer.parseInt(scannerInputSplit[0]) - 11;//Convert the input sequential ID into an integer, subtract it by 11 to find the index of the assistant on shift it refers to
            String tempName = bookingSystem.getAssistantsOnShift().get(i).getName();//Store the name of the selected assistant on shift
            String tempEmail = bookingSystem.getAssistantsOnShift().get(i).getEmail();//Store the email of the selected assistant on shift
            String tempDate = scannerInputSplit[1];//Store the date that was input by the user
            String tempDate1 = tempDate + " 07:00";
            String tempDate2 = tempDate + " 08:00";
            String tempDate3 = tempDate + " 09:00";
            //When adding an assistant on shift, 1 assistant is used to fill in the 3 shifts for the selected day
            bookingSystem.addAssistantsOnShift(new AssistantOnShift(tempName, tempEmail, tempDate1));
            bookingSystem.addAssistantsOnShift(new AssistantOnShift(tempName, tempEmail, tempDate2));
            bookingSystem.addAssistantsOnShift(new AssistantOnShift(tempName, tempEmail, tempDate3));
            //After the assistant on shift is added succesfully, let the user know and give them the option to add another assistant on shift
            System.out.println("Assistant on Shift added successfully:");
            //To retrieve the 3 most recently added assistants on shift take the index of the last 3 Assistants on Shift from the Arraylist of assistants on shift
            int indexAssistantOnShiftAdded1 = bookingSystem.getAssistantsOnShift().size() - 3;
            int indexAssistantOnShiftAdded2 = bookingSystem.getAssistantsOnShift().size() - 2;
            int indexAssistantOnShiftAdded3 = bookingSystem.getAssistantsOnShift().size() - 1;
            System.out.println(bookingSystem.getAssistantsOnShift().get(indexAssistantOnShiftAdded1));
            System.out.println(bookingSystem.getAssistantsOnShift().get(indexAssistantOnShiftAdded2));
            System.out.println(bookingSystem.getAssistantsOnShift().get(indexAssistantOnShiftAdded3));
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Error!\nThe input must consist of a sequential ID currently between 11 and " + (bookingSystem.getAssistantsOnShift().size() + 10) +
                    " and a date in the format dd/mm/yyyy. You input " + scannerInput + ".");
        } catch (NumberFormatException ex) {
            System.out.println("Error!\nThe first input must be an integer");
        } finally {//Want this code to print regardless of whether adding the bookable room was successful or not
            //formatting text for the user
            System.out.println("Please, enter one of the following:\n");
            System.out.println("The sequential ID of an assistant and date (dd/mm/yyyy), seperated by a white space.");
            System.out.println("0. Back to main menu");
            System.out.println("-1. Quit application\n");
        }
    }

    /**
     * Checks whether the date input when adding an assistant on shift is valid
     * Returns a True or False value where True shows that the input was valid and False indicates it is invalid
     *
     * @param scannerInput//scanner input of the user
     * @return Boolean
     * @throws java.lang.NumberFormatException
     * @throws java.lang.ArrayIndexOutOfBoundsException
     */
    private Boolean inputAddAssistantOnShiftDateCheck(String scannerInput) {
        try {
            /*
            The checks done in this method are:
            Day is between 1 and 31
            Month is between 1 and 12
            Year is between 2021 and 2100
            The day and month are 2 digit integers and that the year is a 4 digit integer
            That there are / seperating day, month and year
            That there are 2 inputs seperated by a white space
             */
            String[] scannerInputSplit = scannerInput.split(" ");
            String[] dateSplit = scannerInputSplit[1].split("/");//dd //mm //yyyy
            int day = Integer.parseInt(dateSplit[0]);
            int month = Integer.parseInt(dateSplit[1]);
            int year = Integer.parseInt(dateSplit[2]);

            int scannerInputLength = scannerInputSplit.length;
            if (scannerInputLength > 2) {
                System.err.println("Error!\nThe input had more than 2 terms");
                return false;
            }
            if (1 > day || day > 31) {
                System.err.println("Error!\nThe day must be between 1 and 31");
                return false;
            }
            if (1 > month || month > 12) {
                System.err.println("Error!\nThe month must be between 1 and 12");
                return false;
            }
            if (2021 > year || year > 2100) {
                System.err.println("Error!\nThe year must be between 2021 and 2100");
                return false;
            }
            if (dateSplit[0].length() != 2) {
                System.err.println("Error!\nThe day must be a 2 digit integer");
                return false;
            }
            if (dateSplit[1].length() != 2) {
                System.err.println("Error!\nThe month must be a 2 digit integer");
                return false;
            }
            if (dateSplit[2].length() != 4) {
                System.err.println("Error!\nThe year must be a 4 digit integer");
                return false;
            }
        } catch (NumberFormatException ex) {
            System.out.println("Error!\nThe input date and time format is invalid");
            return false;
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Error!\nThe input is in the invalid format");
            return false;
        }
        return true;
    }

    /**
     * Checks whether the input when adding an assistant on shift is valid, everything but the date (this is checked in {@link inputAddAssistantOnShiftDateCheck})
     * Returns a True or False value where True shows that the input was valid and False indicates it is invalid
     *
     * @param scannerInput//scanner input of the user
     * @return Boolean
     * @throws java.lang.IndexOutOfBoundsException
     * @throws java.lang.ArrayIndexOutOfBoundsException
     */
    private Boolean inputAddBookingCheck(String scannerInput) {
        try {
            /*
            That there are 2 inputs seperated by a white space
            That the email input ends in @uok.ac.uk
            That the email is longer than just @uok.ac.uk
            That the email is unique
            That the sequential ID is a valid integer
             */
            String[] scannerInputSplit = scannerInput.split(" ");
            int scannerInputLength = scannerInputSplit.length;
            if (scannerInputLength > 2) {
                System.err.println("Error!\nThe input had more than 2 terms");
                return false;
            }
            Boolean emailEndCheck = scannerInputSplit[1].endsWith("@uok.ac.uk");
            if (emailEndCheck == false) {
                System.err.println("Error!\nThe student email must end in @uok.ac.uk");
                return false;
            }
            if (scannerInputSplit[1].length() < 11) {//the length check is to check that the email is not just @uok.ac.uk (10 char long)
                System.err.println("Error!\nThe student email must be longer than 10 characters");
                return false;
            }
            for (int i = 0; i < bookingSystem.getBookings().size(); i++) {
                for (int j = i + 1; j < bookingSystem.getBookings().size(); j++) {
                    if (bookingSystem.getBookings().get(i).getStudentEmail().equals(bookingSystem.getBookings().get(j).getStudentEmail())) {
                        System.err.println("Error!\nThe student email must be unique");
                        return false;
                    }
                }
            }
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Error!\nThe input must consist of a sequential ID of an available time-slot, currently between 11 and " + (bookingSystem.getMatchingAssistantsOnShiftBookableRooms().getAssistantsOnShift().size() + 10) +
                    " and the student email, seperated by a white space.  You input " + scannerInput + ".");

            return false;
        } catch (NumberFormatException ex) {
            System.out.println("Error!\nThe first input must be an integer");
            return false;
        }
        return true;
    }

    /**
     * Takes the scanner input and adds a booking accordingly
     * This splits the scanner input into its respective components (sequential ID and date)
     *
     * @param scannerInput//scanner input of user
     * @throws java.lang.IndexOutOfBoundsException
     * @throws java.lang.NumberFormatException
     */
    private void addBooking(String scannerInput) {
        try {
            String[] scannerInputSplit = scannerInput.split(" ");
            int i = Integer.parseInt(scannerInputSplit[0]) - 11;//Convert the input sequential ID into an integer, subtract it by 11 to find the index of the assistant on shift it refers to
            AssistantOnShift tempAssistantOnShift = bookingSystem.getMatchingAssistantsOnShiftBookableRooms().getAssistantsOnShift().get(i);//Store the assistant on shift available at the selected time
            BookableRoom tempBookableRoom = bookingSystem.getMatchingAssistantsOnShiftBookableRooms().getBookableRooms().get(i);//Store the bookable room available at the selected time
            bookingSystem.addBooking(new Booking(tempBookableRoom, tempAssistantOnShift, scannerInputSplit[1]));
            System.out.println("Booking added successfully:");
            //To retrieve the most recently added booking take the index of the last booking from the Arraylist of bookings
            int indexBookingAdded = bookingSystem.getMatchingAssistantsOnShiftBookableRooms().getAssistantsOnShift().size() - 1;
            System.out.println(bookingSystem.getBookings().get(indexBookingAdded) + "\n");
            //Update the status and occupancy of the assistant on shift and the bookable room, involved in the booking
            AssistantOnShiftBookingAppPair tempPair = bookingSystem.getMatchingAssistantsOnShiftBookableRooms(); //this is because if this is not stored and the list is accessed directly by changing the status it eliminate the pair from the list and only one status can be changed
            tempPair.getAssistantsOnShift().get(i).setStatus("BUSY");
            tempPair.getBookableRooms().get(i).increaseOccupancy();
            //Print the avaialble bookings
            bookingSystem.listAvailableBookings(bookingSystem.getMatchingAssistantsOnShiftBookableRooms().getAssistantsOnShift());
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Error!\nThe input must consist of a sequential ID of an available time-slot, currently between 11 and " + (bookingSystem.getMatchingAssistantsOnShiftBookableRooms().getAssistantsOnShift().size() + 10) +
                    " and the student email, seperated by a white space.  You input " + scannerInput + ".");
        } catch (NumberFormatException ex) {
            System.out.println("Error!\nThe first input must be an integer");
        } finally {//Want this code to print regardless of whether adding the bookable room was successful or not
            //formatting text for the user
            System.out.println("Please, enter one of the following:\n");
            System.out.println("The sequential ID of an assistant and date (dd/mm/yyyy) and student email, seperated by a white space.");
            System.out.println("0. Back to main menu");
            System.out.println("-1. Quit application\n");
        }

    }

    /**
     * Main method
     * Responsible for calling every method in booking App and booking System
     * Loads all initial data
     * Then runs a while (true) loop that continues to take in user input and act accordingly until escaped using ctrl+c or entering -1
     *
     * @param args main method args
     */
    public static void main(String[] args) {
        //Loading initial data until bookingApp.loadMainmMenu is called
        UniversityResources universityResources = new UniversityResources();

        Assistant assistant1 = new Assistant("Diogo", "diogo1@uok.ac.uk");
        Assistant assistant2 = new Assistant("Linda", "linda1@uok.ac.uk");
        Assistant assistant3 = new Assistant("Sarah", "sarah1@uok.ac.uk");
        universityResources.addAssistant(assistant1.getName(), assistant1.getEmail());
        universityResources.addAssistant(assistant2.getName(), assistant2.getEmail());
        universityResources.addAssistant(assistant3.getName(), assistant3.getEmail());

        Room room1 = new Room("ICI101", 3);
        Room room2 = new Room("ICI102", 3);
        Room room3 = new Room("ICI103", 3);
        universityResources.addRoom(room1.getCode(), room1.getCapacity());
        universityResources.addRoom(room2.getCode(), room2.getCapacity());
        universityResources.addRoom(room3.getCode(), room3.getCapacity());

        BookingSystem bookingSystem = new BookingSystem();


        BookableRoom bookableRoom1 = new BookableRoom(room1, "26/02/2021 07:00", 3);
        BookableRoom bookableRoom2 = new BookableRoom(room1, "26/02/2021 08:00", 1);
        BookableRoom bookableRoom3 = new BookableRoom(room1, "26/02/2021 09:00", 0);

        BookableRoom bookableRoom4 = new BookableRoom(room2, "26/02/2021 07:00", 0);
        BookableRoom bookableRoom5 = new BookableRoom(room2, "26/02/2021 08:00", 0);
        BookableRoom bookableRoom6 = new BookableRoom(room2, "26/02/2021 09:00", 0);

        BookableRoom bookableRoom7 = new BookableRoom(room3, "26/02/2021 07:00", 0);
        BookableRoom bookableRoom8 = new BookableRoom(room3, "26/02/2021 08:00", 0);
        BookableRoom bookableRoom9 = new BookableRoom(room3, "26/02/2021 09:00", 0);

        bookingSystem.addBookableRoom(bookableRoom1);
        bookingSystem.addBookableRoom(bookableRoom2);
        bookingSystem.addBookableRoom(bookableRoom3);

        bookingSystem.addBookableRoom(bookableRoom4);
        bookingSystem.addBookableRoom(bookableRoom5);
        bookingSystem.addBookableRoom(bookableRoom6);

        bookingSystem.addBookableRoom(bookableRoom7);
        bookingSystem.addBookableRoom(bookableRoom8);
        bookingSystem.addBookableRoom(bookableRoom9);

        AssistantOnShift assistantOnShift1 = new AssistantOnShift(assistant1, "26/02/2021 07:00", "BUSY");
        AssistantOnShift assistantOnShift2 = new AssistantOnShift(assistant1, "26/02/2021 08:00", "BUSY");
        AssistantOnShift assistantOnShift3 = new AssistantOnShift(assistant1, "26/02/2021 09:00", "FREE");

        AssistantOnShift assistantOnShift4 = new AssistantOnShift(assistant2, "26/02/2021 07:00", "BUSY");
        AssistantOnShift assistantOnShift5 = new AssistantOnShift(assistant2, "26/02/2021 08:00", "FREE");
        AssistantOnShift assistantOnShift6 = new AssistantOnShift(assistant2, "26/02/2021 09:00", "FREE");

        AssistantOnShift assistantOnShift7 = new AssistantOnShift(assistant3, "26/02/2021 07:00", "BUSY");
        AssistantOnShift assistantOnShift8 = new AssistantOnShift(assistant3, "26/02/2021 08:00", "FREE");
        AssistantOnShift assistantOnShift9 = new AssistantOnShift(assistant3, "26/02/2021 09:00", "FREE");

        bookingSystem.addAssistantsOnShift(assistantOnShift1);
        bookingSystem.addAssistantsOnShift(assistantOnShift2);
        bookingSystem.addAssistantsOnShift(assistantOnShift3);

        bookingSystem.addAssistantsOnShift(assistantOnShift4);
        bookingSystem.addAssistantsOnShift(assistantOnShift5);
        bookingSystem.addAssistantsOnShift(assistantOnShift6);

        bookingSystem.addAssistantsOnShift(assistantOnShift7);
        bookingSystem.addAssistantsOnShift(assistantOnShift8);
        bookingSystem.addAssistantsOnShift(assistantOnShift9);

        Booking booking1 = new Booking(bookableRoom1, assistantOnShift1, "matpic@uok.ac.uk", "COMPLETED");
        Booking booking2 = new Booking(bookableRoom2, assistantOnShift2, "jack@uok.ac.uk");
        Booking booking3 = new Booking(bookableRoom3, assistantOnShift3, "sam@uok.ac.uk");
        Booking booking4 = new Booking(bookableRoom4, assistantOnShift4, "jasper@uok.ac.uk");
        Booking booking5 = new Booking(bookableRoom5, assistantOnShift5, "samantha@uok.ac.uk");
        Booking booking6 = new Booking(bookableRoom6, assistantOnShift6, "mathew@uok.ac.uk");
        Booking booking7 = new Booking(bookableRoom5, assistantOnShift7, "matteo@uok.ac.uk");
        Booking booking8 = new Booking(bookableRoom8, assistantOnShift8, "susie@uok.ac.uk");
        Booking booking9 = new Booking(bookableRoom9, assistantOnShift9, "eloise@uok.ac.uk");

        bookingSystem.addBooking(booking1);
        bookingSystem.addBooking(booking2);
        bookingSystem.addBooking(booking3);

        bookingSystem.addBooking(booking4);
        bookingSystem.addBooking(booking5);
        bookingSystem.addBooking(booking6);

        bookingSystem.addBooking(booking7);
        bookingSystem.addBooking(booking8);
        bookingSystem.addBooking(booking9);

        BookingApp bookingApp = new BookingApp(bookingSystem);

        bookingApp.loadMainMenu(); //Main menu loaded on botting the system

        Scanner in = new Scanner(System.in);//define scanner
        int a;//initialize variable a used as a proxy for the user input
        while (true) {
            try {
                a = in.nextInt();//Allows for more than one scanner input
                if (a == 0) {
                    bookingApp.loadMainMenu();
                    continue;
                }
                if (a == -1) {//Breaks out of the system
                    bookingApp.quitApplication();
                    break;
                }
                if (a == 1) {//List all bookable rooms
                    //The top 2 commands ensure that the terminal is cleared everytime the interface "switches screens" and loads the main menu
                    System.out.print("\033[H\033[2J");//ANSI escape codes, clear screen and home
                    System.out.flush();
                    System.out.println("University of Knowledge - COVID Test\n");//formatting text
                    bookingSystem.listBookableRooms();
                    System.out.println("0. Back to main menu");
                    System.out.println("-1. Quit application\n");
                    continue;
                }
                if (a == 2) {//Add a bookable rooms
                    //The top 2 commands ensure that the terminal is cleared everytime the interface "switches screens" and loads the main menu
                    System.out.print("\033[H\033[2J");//ANSI escape codes, clear screen and home
                    System.out.flush();
                    System.out.println("University of Knowledge - COVID Test\n");//formatting text
                    System.out.println("Adding bookable room\n");//formatting text
                    bookingSystem.listBookableRooms();
                    System.out.println("Please, enter one of the following:\n");
                    System.out.println("The sequential ID listed to a room, a date, (dd/mm/yy) and a time (HH:MM), seperated by a white space.");
                    System.out.println("0. Back to main menu");
                    System.out.println("-1. Quit application\n");
                    in.nextLine();//When finished reading the integer the cursor is after the integer and not on the new line, this code finishes reading the input after the integer and moves the cursor to the next line, allowing the user to input the necessary information
                    String scannerInput; // Initialize the scanner input outside the while loop, so that it can be accessed to check if it was a 0 or -1 when the user wants to load the main menu or quit the application
                    while (true) {
                        scannerInput = in.nextLine();// sequential ID, date and time
                        //check if the input is 0 or -1 in order to either load the main menu or quit the application
                        if (scannerInput.equals("0") || scannerInput.equals("-1")) {//If the input is 0 or -1 exit the while true loop
                            break;
                        }
                        Boolean inputFormatStatus = bookingApp.inputAddBookableRoomCheck(scannerInput);
                        if (inputFormatStatus == false) {//If the input is invalid
                            System.out.println("Please, enter one of the following:\n");
                            System.out.println("The sequential ID listed to a room, a date (dd/mm/yyyy), and a time (HH:MM), seperated by a white space.");
                            System.out.println("0. Back to main menu");
                            System.out.println("-1. Quit application\n");
                            continue;
                        } else {
                            bookingApp.addBookableRoom(scannerInput);//Otherwise let the user add a bookable room
                            continue;
                        }
                    }
                    if (scannerInput.equals("0")) {// If the input is 0 load the main menu
                        bookingApp.loadMainMenu();
                        continue;
                    }
                    if (scannerInput.equals("-1")) {//If the input is -1 quit the application
                        bookingApp.quitApplication();
                        break;
                    }
                }
                if (a == 3) {//Remove a bookable room
                    //The top 2 commands ensure that the terminal is cleared everytime the interface "switches screens" and loads the main menu
                    System.out.print("\033[H\033[2J");//ANSI escape codes, clear screen and home
                    System.out.flush();
                    System.out.println("University of Knowledge - COVID Test\n");//formatting text
                    bookingSystem.listEmptyBookableRooms();
                    System.out.println("Removing bookable room\n");//formatting text
                    System.out.println("Please, enter one of the following:\n");
                    System.out.println("The sequential ID to select the bookable room removed");
                    System.out.println("0. Back to main menu");
                    System.out.println("-1. Quit application\n");
                    in.nextLine();//When finished reading the integer the cursor is after the integer and not on the new line, this code finishes reading the input after the integer and moves the cursor to the next line, allowing the user to input the necessary information
                    int scannerInput;
                    int numberOfRemovals = 0;
                    while (true) {
                        try {
                            scannerInput = in.nextInt();
                            if (scannerInput == 0 || scannerInput == -1) {//If the input is 0 or -1 exit the while true loop
                                break;
                            }
                            BookableRoom bookableRoomRemoved = new BookableRoom(bookingSystem.getBookableRooms().get(scannerInput - (11 + numberOfRemovals)));//Create a copy of the bookable room about to be removed so that it can be printed
                            bookingSystem.removeBookableRoom(scannerInput - (11 + numberOfRemovals));//Remove the bookable room that has the index that matches its sequential ID (index = sequential ID - 11)
                            numberOfRemovals++;
                            //formatting text for the user
                            System.out.println("Bookable Room removed successfully:");
                            System.out.println(bookableRoomRemoved);
                        } catch (IndexOutOfBoundsException ex1) {//If the input is not within the sequential ID range
                            System.out.println("Error!");
                            System.out.println("Sequential ID input has to have a matching bookable room. This ranges from 11 to " + (bookingSystem.getBookableRooms().size() + 10) + ".");
                        } catch (InputMismatchException ex2) {//If the input is not an integer
                            System.out.println("Error!\nThe sequential ID input must be an integer.");
                            in.nextLine();//Moves the cursor to the next line, if this is removed the scanner will keep reading the same line and this error will cause an endless loop where this error is raised each time
                        } finally {//Want this code to print regardless of whether adding the bookable room was successful or not
                            //formatting text for the user
                            System.out.println("Please, enter one of the following:\n");
                            System.out.println("The sequential ID of the bookable room to be removed.");
                            System.out.println("0. Back to main menu");
                            System.out.println("-1. Quit application\n");
                        }
                    }
                    if (scannerInput == 0) {// If the input is 0 load the main menu
                        bookingApp.loadMainMenu();
                        continue;
                    }
                    if (scannerInput == -1) {//If the input is -1 quit the application
                        bookingApp.quitApplication();
                        break;
                    }
                    continue;
                }

                if (a == 4) {//List assistants on shift
                    //The top 2 commands ensure that the terminal is cleared everytime the interface "switches screens" and loads the main menu
                    System.out.print("\033[H\033[2J");//ANSI escape codes, clear screen and home
                    System.out.flush();
                    System.out.println("University of Knowledge - COVID Test\n");//formatting text
                    bookingSystem.listAssistantsOnShift();
                    System.out.println("0. Back to main menu");
                    System.out.println("-1. Quit application\n");
                    continue;
                }
                if (a == 5) {//Add assistants on shift
                    //The top 2 commands ensure that the terminal is cleared everytime the interface "switches screens" and loads the main menu
                    System.out.print("\033[H\033[2J");//ANSI escape codes, clear screen and home
                    System.out.flush();
                    System.out.println("University of Knowledge - COVID Test\n");//formatting text
                    System.out.println("Adding assistant on shift\n");//formatting text
                    bookingSystem.listAssistantsOnShift();
                    System.out.println("Please, enter one of the following:\n");
                    System.out.println("The sequential ID listed of an assistant and date (dd/mm/yyyy), seperated by a white space.");
                    System.out.println("0. Back to main menu");
                    System.out.println("-1. Quit application\n");
                    in.nextLine();//When finished reading the integer the cursor is after the integer and not on the new line, this code finishes reading the input after the integer and moves the cursor to the next line, allowing the user to input the necessary information
                    String scannerInput; // Initialize the scanner input outside the while loop, so that it can be accessed to check if it was a 0 or -1 when the user wants to load the main menu or quit the application
                    while (true) {
                        scannerInput = in.nextLine();// sequential ID and date
                        //check if the input is 0 or -1 in order to either load the main menu or quit the application
                        if (scannerInput.equals("0") || scannerInput.equals("-1")) {//If the input is 0 or -1 exit the while true loop
                            break;
                        }
                        Boolean inputFormatStatus = bookingApp.inputAddAssistantOnShiftDateCheck(scannerInput);
                        if (inputFormatStatus == false) {//If the input is invalid
                            System.out.println("Please, enter one of the following:\n");
                            System.out.println("The sequential ID listed of an assistant and date (dd/mm/yyyy), seperated by a white space.");
                            System.out.println("0. Back to main menu");
                            System.out.println("-1. Quit application\n");
                            continue;
                        } else {
                            bookingApp.addAssistantOnShift(scannerInput);//Otherwise let the user add a bookable room
                            continue;
                        }
                    }
                    if (scannerInput.equals("0")) {// If the input is 0 load the main menu
                        bookingApp.loadMainMenu();
                        continue;
                    }
                    if (scannerInput.equals("-1")) {//If the input is -1 quit the application
                        bookingApp.quitApplication();
                        break;
                    }
                }
                if (a == 6) {//Remove Assistant On Shift
                    //The top 2 commands ensure that the terminal is cleared everytime the interface "switches screens" and loads the main menu
                    System.out.print("\033[H\033[2J");//ANSI escape codes, clear screen and home
                    System.out.flush();
                    System.out.println("University of Knowledge - COVID Test\n");//formatting text
                    bookingSystem.listAssistantsOnShiftFree();
                    System.out.println("Removing assistant on shift\n");//formatting text
                    System.out.println("Please, enter one of the following:\n");
                    System.out.println("The sequential ID to select the assistant on shift removed");
                    System.out.println("0. Back to main menu");
                    System.out.println("-1. Quit application\n");
                    in.nextLine();//When finished reading the integer the cursor is after the integer and not on the new line, this code finishes reading the input after the integer and moves the cursor to the next line, allowing the user to input the necessary information
                    int scannerInput;
                    int numberOfRemovals = 0;
                    while (true) {
                        try {
                            scannerInput = in.nextInt();
                            if (scannerInput == 0 || scannerInput == -1) {//If the input is 0 or -1 exit the while true loop
                                break;
                            }
                            AssistantOnShift assistantOnShiftRemoved = new AssistantOnShift(bookingSystem.getAssistantsOnShift().get(scannerInput - (11 + numberOfRemovals)));//Create a copy of the assistant on shift about to be removed so that it can be printed
                            bookingSystem.removeAssistantOnShift(scannerInput - (11 + numberOfRemovals));//Remove the assistant on shift that has the index that matches its sequential ID (index = sequential ID - 11)
                            // formatting text for the user
                            System.out.println("Assistant on Shift removed successfully:");
                            System.out.println(assistantOnShiftRemoved);
                        } catch (IndexOutOfBoundsException ex1) {//If the input is not within the sequential ID range
                            System.out.println("Error!");
                            System.out.println("Sequential ID input has to have a matching assistant on shift. This ranges from 11 to " + (bookingSystem.getAssistantsOnShift().size() + 10) + ".");
                        } catch (InputMismatchException ex2) {//If the input is not an integer
                            System.out.println("Error!\nThe sequential ID input must be an integer.");
                            in.nextLine();//Moves the cursor to the next line, if this is removed the scanner will keep reading the same line and this error will cause an endless loop where this error is raised each time
                        } finally {//Want this code to print regardless of whether adding the bookable room was successful or not
                            // formatting text for the user
                            System.out.println("Please, enter one of the following:\n");
                            System.out.println("The sequential ID of the assistant on shift to be removed.");
                            System.out.println("0. Back to main menu");
                            System.out.println("-1. Quit application\n");
                        }
                    }
                    if (scannerInput == 0) {// If the input is 0 load the main menu
                        bookingApp.loadMainMenu();
                        continue;
                    }
                    if (scannerInput == -1) {//If the input is -1 quit the application
                        bookingApp.quitApplication();
                        break;
                    }
                    continue;
                }
                if (a == 7) {
                    //The top 2 commands ensure that the terminal is cleared everytime the interface "switches screens" and loads the main menu
                    System.out.print("\033[H\033[2J");//ANSI escape codes, clear screen and home
                    System.out.flush();
                    System.out.println("University of Knowledge - COVID Test\n");//formatting text
                    System.out.println("Select which booking to list:");
                    System.out.println("1. All\n2. Only bookings status: SCHEDULED\n3. Only bookings status:COMPLETED");
                    System.out.println("0. Back to main menu");
                    System.out.println("-1. Quit application\n");
                    in.nextLine();//When finished reading the integer the cursor is after the integer and not on the new line, this code finishes reading the input after the integer and moves the cursor to the next line, allowing the user to input the necessary information
                    try {
                        int scannerInput = in.nextInt();
                        if (scannerInput == 0) {// If the input is 0 load the main menu
                            bookingApp.loadMainMenu();
                            continue;
                        } else if (scannerInput == -1) {//If the input is -1 quit the application
                            bookingApp.quitApplication();
                            break;
                        }
                        //Depending on what the input is show a list of bookings with a specific status
                        else if (scannerInput == 1) {
                            bookingSystem.listBookings();
                        } else if (scannerInput == 2) {
                            bookingSystem.listBookingsScheduled();
                        } else if (scannerInput == 3) {
                            bookingSystem.listBookingsCompleted();
                        } else { //If the input is invalid default to showing the ArrayList of all bookings
                            System.out.println("Input was invalid, showing all bookings");
                            bookingSystem.listBookings();
                        }
                    } catch (InputMismatchException ex) {//If the input is not an integer
                        System.out.println("Input was invalid, showing all bookings");
                        bookingSystem.listBookings();
                        in.nextLine();//Moves the cursor to the next line, if this is removed the scanner will keep reading the same line and this error will cause an endless loop where this error is raised each time
                    } finally {
                        System.out.println("0. Back to main menu");
                        System.out.println("-1. Quit application\n");
                        continue;
                    }
                }
                if (a == 8) {//Add Booking
                    //The top 2 commands ensure that the terminal is cleared everytime the interface "switches screens" and loads the main menu
                    System.out.print("\033[H\033[2J");//ANSI escape codes, clear screen and home
                    System.out.flush();
                    System.out.println("University of Knowledge - COVID Test\n");//formatting text
                    System.out.println("Adding booking (appointment for a COVID test) to the system\n");//formatting text
                    bookingSystem.listAvailableBookings(bookingSystem.getMatchingAssistantsOnShiftBookableRooms().getAssistantsOnShift());
                    System.out.println("Please, enter one of the following:\n");
                    System.out.println("The sequential ID of an available time-slot and the student email, separated by a white space.");
                    System.out.println("0. Back to main menu");
                    System.out.println("-1. Quit application\n");
                    in.nextLine();//When finished reading the integer the cursor is after the integer and not on the new line, this code finishes reading the input after the integer and moves the cursor to the next line, allowing the user to input the necessary information
                    String scannerInput; // Initialize the scanner input outside the while loop, so that it can be accessed to check if it was a 0 or -1 when the user wants to load the main menu or quit the application
                    while (true) {
                        scannerInput = in.nextLine();// sequential ID, date and time
                        //check if the input is 0 or -1 in order to either load the main menu or quit the application
                        if (scannerInput.equals("0") || scannerInput.equals("-1")) {//If the input is 0 or -1 exit the while true loop
                            break;
                        }
                        Boolean inputFormatStatus = bookingApp.inputAddBookingCheck(scannerInput);
                        if (inputFormatStatus == false) {
                            System.out.println("Please, enter one of the following:\n");
                            System.out.println("The sequential ID of an available time-slot and the student email, separated by a white space.");
                            System.out.println("0. Back to main menu");
                            System.out.println("-1. Quit application\n");
                            continue;
                        } else {
                            bookingApp.addBooking(scannerInput);
                            continue;
                        }

                    }
                    if (scannerInput.equals("0")) {// If the input is 0 load the main menu
                        bookingApp.loadMainMenu();
                        continue;
                    }
                    if (scannerInput.equals("-1")) {//If the input is -1 quit the application
                        bookingApp.quitApplication();
                        break;
                    }
                }
                if (a == 9) {//Remove a booking
                    //The top 2 commands ensure that the terminal is cleared everytime the interface "switches screens" and loads the main menu
                    System.out.print("\033[H\033[2J");//ANSI escape codes, clear screen and home
                    System.out.flush();
                    System.out.println("University of Knowledge - COVID Test\n");//formatting text
                    bookingSystem.listBookingsScheduled();
                    System.out.println("Removing booking from system\n");//formatting text
                    System.out.println("Please, enter one of the following:\n");
                    System.out.println("The sequential ID to select the booking to be removed from the listed bookings above.");
                    System.out.println("0. Back to main menu");
                    System.out.println("-1. Quit application\n");
                    in.nextLine();//When finished reading the integer the cursor is after the integer and not on the new line, this code finishes reading the input after the integer and moves the cursor to the next line, allowing the user to input the necessary information
                    int scannerInput;
                    int numberOfRemovals = 0;
                    while (true) {
                        try {
                            scannerInput = in.nextInt();
                            if (scannerInput == 0 || scannerInput == -1) {//If the input is 0 or -1 exit the while true loop
                                break;
                            }
                            ArrayList<Booking> scheduledBookings = new ArrayList<>();
                            ArrayList<Integer> indexScheduledBookings = new ArrayList<>();
                            int index = 0;
                            for (Booking i : bookingSystem.getBookings()) {
                                if (i.getStatus().equals("SCHEDULED")) {
                                    scheduledBookings.add(i);
                                    indexScheduledBookings.add(index);
                                }
                                index++;
                            }
                            //Find the index of the scheduled booking selected in the arraylist scheduled bookings
                            index = 0;//reset the index counter
                            int indexSelected = -1;//If the sequential id does not correspond to a scheduled booking this will raise an error
                            for (Booking i : scheduledBookings) {
                                if (i == scheduledBookings.get(scannerInput - (11 + numberOfRemovals))) {
                                    indexSelected = index;
                                }
                                index++;
                            }
                            //use the index of the scheduled to find what integer in the arraylist indexScheduledBookings corresponds to
                            //This integer corresponds to the index of the scheduled booking selected in the arrayList of bookings
                            int indexOfBooking = indexScheduledBookings.get(indexSelected);
                            Booking bookingRemoved = new Booking(bookingSystem.getBookings().get(indexOfBooking));
                            bookingSystem.removeBooking(indexOfBooking);//Remove the bookable room that has the index that matches its sequential ID (index = sequential ID - 11)
                            //formatting text for the user
                            System.out.println("Booking removed successfully:");
                            System.out.println(bookingRemoved);
                            numberOfRemovals++;
                        } catch (IndexOutOfBoundsException ex1) {//If the input is not within the sequential ID range
                            System.out.println("Error!");
                            System.out.println("Sequential ID input has to have a matching booking.");
                        } catch (InputMismatchException ex2) {//If the input is not an integer
                            System.out.println("Error!\nThe sequential ID input must be an integer.");
                            in.nextLine();//Moves the cursor to the next line, if this is removed the scanner will keep reading the same line and this error will cause an endless loop where this error is raised each time
                        } finally {
                            //formatting text for the user
                            System.out.println("Please, enter one of the following:\n");
                            System.out.println("The sequential ID to select the booking to be removed from the listed bookings above.");
                            System.out.println("0. Back to main menu");
                            System.out.println("-1. Quit application\n");
                        }
                    }
                    if (scannerInput == 0) {// If the input is 0 load the main menu
                        bookingApp.loadMainMenu();
                        continue;
                    }
                    if (scannerInput == -1) {//If the input is -1 quit the application
                        bookingApp.quitApplication();
                        break;
                    }
                    continue;

                }
                if (a == 10) {
                    //The top 2 commands ensure that the terminal is cleared everytime the interface "switches screens" and loads the main menu
                    System.out.print("\033[H\033[2J");//ANSI escape codes, clear screen and home
                    System.out.flush();
                    System.out.println("University of Knowledge - COVID Test\n");//formatting text
                    bookingSystem.listBookingsScheduled();
                    System.out.println("Conclude booking\n");//formatting text
                    System.out.println("Please, enter one of the following:\n");
                    System.out.println("The sequential ID to select the booking to be completed.");
                    System.out.println("0. Back to main menu");
                    System.out.println("-1. Quit application\n");
                    in.nextLine();//When finished reading the integer the cursor is after the integer and not on the new line, this code finishes reading the input after the integer and moves the cursor to the next line, allowing the user to input the necessary information
                    int scannerInput;
                    int numberOfConclusions = 0;

                    while (true) {
                        try {
                            scannerInput = in.nextInt();
                            if (scannerInput == 0 || scannerInput == -1) {//If the input is 0 or -1 exit the while true loop
                                break;
                            }
                            //Create a temporary array to store the index of the scheduled bookings, since the sequential ID does not == the index of a booking
                            ArrayList<Booking> scheduledBookings = new ArrayList<>();
                            ArrayList<Integer> indexScheduledBookings = new ArrayList<>();
                            int index = 0;
                            for (Booking i : bookingSystem.getBookings()) {
                                if (i.getStatus().equals("SCHEDULED")) {
                                    scheduledBookings.add(i);
                                    indexScheduledBookings.add(index);
                                }
                                index++;
                            }
                            //Find the index of the scheduled booking selected in the arraylist scheduled bookings
                            index = 0;//reset the index counter
                            int indexSelected = -1;//If the sequential id does not correspond to a scheduled booking this will raise an error
                            for (Booking i : scheduledBookings) {
                                if (i == scheduledBookings.get(scannerInput - (11 + numberOfConclusions))) {
                                    indexSelected = index;
                                }
                                index++;
                            }
                            //use the index of the scheduled to find what integer in the arraylist indexScheduledBookings corresponds to
                            //This integer corresponds to the index of the scheduled booking selected in the arrayList of bookings
                            int indexOfBooking = indexScheduledBookings.get(indexSelected);
                            Booking bookingConcluded = new Booking(bookingSystem.getBookings().get(indexOfBooking));
                            bookingSystem.getBookings().get(indexOfBooking).setStatus("COMPLETED");
                            numberOfConclusions++;
                            System.out.println("Booking completed successfully");
                            //
                            System.out.println(bookingSystem.getBookings().get(indexOfBooking));
                        } catch (IndexOutOfBoundsException ex1) {//If the input is not within the sequential ID range
                            System.out.println("Error!");
                            System.out.println("Please input only a sequential ID connected to a booking, listed above.");
                        } catch (InputMismatchException ex2) {//If the input is not an integer
                            System.out.println("Error!\nThe sequential ID input must be an integer.");
                            in.nextLine();//Moves the cursor to the next line, if this is removed the scanner will keep reading the same line and this error will cause an endless loop where this error is raised each time
                        } finally {//Want this code to print regardless of whether adding the bookable room was successful or not
                            //formatting text for the user
                            System.out.println("Please, enter one of the following:\n");
                            System.out.println("The sequential ID to select the booking to be completed.");
                            System.out.println("0. Back to main menu");
                            System.out.println("-1. Quit application\n");
                        }
                    }
                    if (scannerInput == 0) {// If the input is 0 load the main menu
                        bookingApp.loadMainMenu();
                        continue;
                    }
                    if (scannerInput == -1) {//If the input is -1 quit the application
                        bookingApp.quitApplication();
                        break;
                    }
                    continue;
                } else {//if the input is not an integer between -1 and 10 warn the user and allow them to try again
                    System.out.println("Error!\nPlease input must be an integer between -1 and 10");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Error!\nThe input must be an integer.");
                in.nextLine();
            }
        }
    }
}