import java.util.Arrays;
import java.util.ArrayList;

/**
 * Represents a booking system
 * A booking system contains the an arrayList of bookable rooms, assistants on shift and bookings
 * It also has the functionality to list, add or remove any element from these 3 arraylists
 * These methods are accessed from the bookingApp.java file, they are called using userinput
 *
 * @author Jeroen Mijer
 * @version 1.0
 * @since 1.0
 */
public class BookingSystem {
    private ArrayList bookableRooms;

    private ArrayList assistantsOnShifts;

    private ArrayList bookings;

    /**
     * Creates a booking system
     * <p>
     * Called without anything because it is empty upon creation from BookingApp
     * Creates an ArrayList of assistants on shift
     * ArrayList becomes an attribute of the booking system
     * Creates an ArrayList of bookable rooms
     * ArrayList becomes an attribute of the booking system
     * Creates an ArrayList of bookings
     * ArrayList becomes an attribute of the booking system
     * These ArrayLists are empty and assistants on shift, bookable romos and bookings are added to them
     */
    public BookingSystem() {
        ArrayList<BookableRoom> bookableRooms = new ArrayList<BookableRoom>();
        this.bookableRooms = bookableRooms;
        ArrayList<AssistantOnShift> assistantsOnShifts = new ArrayList<>();
        this.assistantsOnShifts = assistantsOnShifts;
        ArrayList<Booking> bookings = new ArrayList<>();
        this.bookings = bookings;
    }

    /**
     * Gets an ArrayList of bookable rooms
     *
     * @return ArrayList array list of bookable rooms
     */
    @SuppressWarnings("unchecked")
    public ArrayList<BookableRoom> getBookableRooms() {
        return bookableRooms;
    }

    /**
     * Gets an ArrayList of assistants on shift
     *
     * @return ArrayList array list of assistants on shift
     */
    @SuppressWarnings("unchecked")
    public ArrayList<AssistantOnShift> getAssistantsOnShift() {
        return assistantsOnShifts;
    }

    /**
     * Gets an ArrayList of bookings
     *
     * @return ArrayList arraylist of bookings
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    /**
     * Print all bookable rooms in the arraylist bookableRooms along with their sequential ID
     */
    public void listBookableRooms() {
        System.out.println("List of bookable rooms:");
        for (int i = 0; i < getBookableRooms().size(); i++)//iterate through arraylist of bookable rooms
        {
            System.out.println(i + 11 + ". " + getBookableRooms().get(i));//print the bookable room, along with its sequential id (11 is added because 1-10 are used for user functionality)
        }
    }

    /**
     * Print all bookable rooms with the status empty in the arraylist bookableRooms along with their sequential ID
     */
    public void listEmptyBookableRooms() {
        System.out.println("List of bookable rooms status: EMPTY");
        for (int i = 0; i < getBookableRooms().size(); i++)//iterate through arraylist of bookable rooms and display each
        {
            if (getBookableRooms().get(i).getStatus().equals("EMPTY")) {//If the status of the bookable room is EMPTY print it out otherwise continue the loop
                System.out.println(i + 11 + ". " + getBookableRooms().get(i));//print the bookable room, along with its sequential id
            }
        }
    }

    /**
     * Print all assistants on shift in the arraylist assistantsOnShift along with their sequential ID
     */
    public void listAssistantsOnShift() {
        System.out.println("List of assistants on shift:");
        for (int i = 0; i < getAssistantsOnShift().size(); i++) {//iterate through arraylist of assistants on shift and display each
            System.out.println(i + 11 + ". " + getAssistantsOnShift().get(i));//print the assistant on shift, along with its sequential id
        }
    }

    /**
     * Print all assistants on shift with the status empty in the arraylist assistantsOnShift along with their sequential ID
     */
    public void listAssistantsOnShiftFree() {
        System.out.println("List of assistants on shift status: FREE");
        for (int i = 0; i < getAssistantsOnShift().size(); i++) {//iterate through arraylist of assistants on shift and display each
            if (getAssistantsOnShift().get(i).getStatus().equals("FREE")) {//If the status of the assistant on shift is FREE print it out otherwise continue the loop
                System.out.println(i + 11 + ". " + getAssistantsOnShift().get(i));//print the assistant on shift, along with its sequential id
            }
        }
    }

    /**
     * Print all bookings in the arraylist bookings along with their sequential ID
     * Also sort the bookings in chronological order before printing them
     */
    public void listBookings() {
        //First sort the bookings into chronological order by date
        for (int q = 0; q < getBookings().size(); q++) {
            //seperate the date into the components, day, month, year, hour
            String[] qSplit = getBookings().get(q).getAssistantOnShift().getDate().split(" ");//dd/mm/yy HH:MM
            String[] dateQSplit = qSplit[0].split("/");//dd mm yyyy
            String[] timeQSplit = qSplit[1].split(":");//hh mm
            //Transform each element of the date into integers in order to be able to compare them
            int dayI = Integer.parseInt(dateQSplit[0]);
            int monthI = Integer.parseInt(dateQSplit[1]);
            int yearI = Integer.parseInt(dateQSplit[2]);
            int hourI = Integer.parseInt(timeQSplit[0]);
            int minuteI = Integer.parseInt(timeQSplit[1]);
            //Do the same as above but with each booking that comes after the one it is being compared to
            for (int p = q + 1; p < getBookings().size(); p++) {
                String[] pSplit = getBookings().get(p).getAssistantOnShift().getDate().split(" ");//dd/mm/yy HH:MM
                String[] datePSplit = pSplit[0].split("/");//dd mm yyyy
                String[] timePSplit = pSplit[1].split(":"); //hh mm
                int dayJ = Integer.parseInt(datePSplit[0]);
                int monthJ = Integer.parseInt(datePSplit[1]);
                int yearJ = Integer.parseInt(datePSplit[2]);
                int hourJ = Integer.parseInt(timePSplit[0]);
                int minuteJ = Integer.parseInt(timePSplit[1]);
                //check if the date is before the other one
                //check the year is before otherwise the month must be before, otherwise the day, otherwise the hour, otherwise they are a duplicate which wont happen
                //if a booking has a date before a booking with a lower index they switch places
                if (yearI > yearJ) {
                    Booking temp = getBookings().get(q);
                    getBookings().set(q, getBookings().get(p)); //assistantOnShiftAvailableForBookings.get(q) = assistantOnShiftAvailableForBookings.get(p)
                    getBookings().set(p, temp); //assistantOnShiftAvailableForBookings.get(p) = temp
                } else if (monthI > monthJ) {
                    Booking temp = getBookings().get(q);
                    getBookings().set(q, getBookings().get(p));
                    getBookings().set(p, temp);
                } else if (dayI > dayJ) {
                    Booking temp = getBookings().get(q);
                    getBookings().set(q, getBookings().get(p));
                    getBookings().set(p, temp);
                } else if (hourI > hourJ) {
                    Booking temp = getBookings().get(q);
                    getBookings().set(q, getBookings().get(p));
                    getBookings().set(p, temp);
                }
            }
        }
        System.out.println("List of bookings:");
        for (int i = 0; i < getBookings().size(); i++) {//iterate through arraylist of assistants on shift and display each
            System.out.println(i + 11 + ". " + getBookings().get(i));//print the booking along with its sequential ID
        }
    }

    /**
     * Print all bookings with the status scheduled in the arraylist bookings along with their sequential ID
     * Also sort the bookings in chronological order before printing them
     */
    public void listBookingsScheduled() {
        //First sort the bookings into chronological order by date
        for (int q = 0; q < getBookings().size(); q++) {
            //seperate the date into the components, day, month, year, hour
            String[] qSplit = getBookings().get(q).getAssistantOnShift().getDate().split(" ");//dd/mm/yy HH:MM
            String[] dateQSplit = qSplit[0].split("/");//dd mm yyyy
            String[] timeQSplit = qSplit[1].split(":");//hh mm
            //Transform each element of the date into integers in order to be able to compare them
            int dayI = Integer.parseInt(dateQSplit[0]);
            int monthI = Integer.parseInt(dateQSplit[1]);
            int yearI = Integer.parseInt(dateQSplit[2]);
            int hourI = Integer.parseInt(timeQSplit[0]);
            int minuteI = Integer.parseInt(timeQSplit[1]);
            //Do the same as above but with each booking that comes after the one it is being compared to
            for (int p = q + 1; p < getBookings().size(); p++) {
                String[] pSplit = getBookings().get(p).getAssistantOnShift().getDate().split(" ");//dd/mm/yy HH:MM
                String[] datePSplit = pSplit[0].split("/");//dd mm yyyy
                String[] timePSplit = pSplit[1].split(":"); //hh mm
                int dayJ = Integer.parseInt(datePSplit[0]);
                int monthJ = Integer.parseInt(datePSplit[1]);
                int yearJ = Integer.parseInt(datePSplit[2]);
                int hourJ = Integer.parseInt(timePSplit[0]);
                int minuteJ = Integer.parseInt(timePSplit[1]);
                //check if the date is before the other one
                //check the year is before otherwise the month must be before, otherwise the day, otherwise the hour, otherwise they are a duplicate which wont happen
                //if a booking has a date before a booking with a lower index they switch places
                if (yearI > yearJ) {
                    Booking temp = getBookings().get(q);
                    getBookings().set(q, getBookings().get(p)); //assistantOnShiftAvailableForBookings.get(q) = assistantOnShiftAvailableForBookings.get(p)
                    getBookings().set(p, temp); //assistantOnShiftAvailableForBookings.get(p) = temp
                } else if (monthI > monthJ) {
                    Booking temp = getBookings().get(q);
                    getBookings().set(q, getBookings().get(p));
                    getBookings().set(p, temp);
                } else if (dayI > dayJ) {
                    Booking temp = getBookings().get(q);
                    getBookings().set(q, getBookings().get(p));
                    getBookings().set(p, temp);
                } else if (hourI > hourJ) {
                    Booking temp = getBookings().get(q);
                    getBookings().set(q, getBookings().get(p));
                    getBookings().set(p, temp);
                }
            }
        }

        System.out.println("List of bookings status: SCHEDULED");
        int sequentialID = 11;
        for (int i = 0; i < getBookings().size(); i++) {//iterate through arraylist of bookings and display each
            if (getBookings().get(i).getStatus().equals("SCHEDULED")) {//If the status of the booking is scheduled, print it otherwise continue with the loop
                System.out.println(sequentialID + ". " + getBookings().get(i));//Print the booking along with its sequential ID
                sequentialID++;
            }
        }
    }

    /**
     * Print all bookings with the status completed in the arraylist bookings along with their sequential ID
     * Also sort the bookings in chronological order before printing them
     */
    public void listBookingsCompleted() {
        for (int q = 0; q < getBookings().size(); q++) {
            //seperate the date into the components, day, month, year, hour
            String[] qSplit = getBookings().get(q).getAssistantOnShift().getDate().split(" ");//dd/mm/yy HH:MM
            String[] dateQSplit = qSplit[0].split("/");//dd mm yyyy
            String[] timeQSplit = qSplit[1].split(":");//hh mm
            int dayI = Integer.parseInt(dateQSplit[0]);
            int monthI = Integer.parseInt(dateQSplit[1]);
            int yearI = Integer.parseInt(dateQSplit[2]);
            int hourI = Integer.parseInt(timeQSplit[0]);
            int minuteI = Integer.parseInt(timeQSplit[1]);
            //Transform each element of the date into integers in order to be able to compare them
            for (int p = q + 1; p < getBookings().size(); p++) {
                //Do the same as above but with each booking that comes after the one it is being compared to
                String[] pSplit = getBookings().get(p).getAssistantOnShift().getDate().split(" ");//dd/mm/yy HH:MM
                String[] datePSplit = pSplit[0].split("/");//dd mm yyyy
                String[] timePSplit = pSplit[1].split(":"); //hh mm
                int dayJ = Integer.parseInt(datePSplit[0]);
                int monthJ = Integer.parseInt(datePSplit[1]);
                int yearJ = Integer.parseInt(datePSplit[2]);
                int hourJ = Integer.parseInt(timePSplit[0]);
                int minuteJ = Integer.parseInt(timePSplit[1]);
                //check if the date is before the other one
                //check the year is before otherwise the month must be before, otherwise the day, otherwise the hour, otherwise they are a duplicate which wont happen
                //if a booking has a date before a booking with a lower index they switch places
                if (yearI > yearJ) {
                    Booking temp = getBookings().get(q);
                    getBookings().set(q, getBookings().get(p)); //assistantOnShiftAvailableForBookings.get(q) = assistantOnShiftAvailableForBookings.get(p)
                    getBookings().set(p, temp); //assistantOnShiftAvailableForBookings.get(p) = temp
                } else if (monthI > monthJ) {
                    Booking temp = getBookings().get(q);
                    getBookings().set(q, getBookings().get(p));
                    getBookings().set(p, temp);
                } else if (dayI > dayJ) {
                    Booking temp = getBookings().get(q);
                    getBookings().set(q, getBookings().get(p));
                    getBookings().set(p, temp);
                } else if (hourI > hourJ) {
                    Booking temp = getBookings().get(q);
                    getBookings().set(q, getBookings().get(p));
                    getBookings().set(p, temp);
                }
            }
        }
        System.out.println("List of bookings status: COMPLETED");
        int sequentialID = 0;
        for (int i = 0; i < getBookings().size(); i++) {//iterate through bookings on shift and display each
            if (getBookings().get(i).getStatus().equals("COMPLETED")) {//If the status of the booking is completed, print it otherwise continue with the loop
                System.out.println((sequentialID + 11) + ". " + getBookings().get(i));//Print the booking along with its sequential ID
                sequentialID++;
            }
        }
    }

    /**
     * List all available bookings
     * First sorts the available bookings
     * Prints out the dates of the assistant on shifts that have been paired up, which equates to the dates of the available bookings
     *
     * @param arrayList //arraylist of assistants on shift that have been paired up with bookable rooms to form bookings
     */
    ///FOUND THE ERROR CAUSING THESE TO BE NO CHRONOLOGICAL
    public void listAvailableBookings(ArrayList<AssistantOnShift> arrayList) {
        for (int q = 0; q < getBookings().size(); q++) {
            //seperate the date into the components, day, month, year, hour
            String[] qSplit = getBookings().get(q).getAssistantOnShift().getDate().split(" ");//dd/mm/yy HH:MM
            String[] dateQSplit = qSplit[0].split("/");//dd mm yyyy
            String[] timeQSplit = qSplit[1].split(":");//hh mm
            //Transform each element of the date into integers in order to be able to compare them
            int dayI = Integer.parseInt(dateQSplit[0]);
            int monthI = Integer.parseInt(dateQSplit[1]);
            int yearI = Integer.parseInt(dateQSplit[2]);
            int hourI = Integer.parseInt(timeQSplit[0]);
            int minuteI = Integer.parseInt(timeQSplit[1]);
            for (int p = q + 1; p < getBookings().size(); p++) {
                //Do the same as above but with each booking that comes after the one it is being compared to
                String[] pSplit = getBookings().get(p).getAssistantOnShift().getDate().split(" ");//dd/mm/yy HH:MM
                String[] datePSplit = pSplit[0].split("/");//dd mm yyyy
                String[] timePSplit = pSplit[1].split(":"); //hh mm
                int dayJ = Integer.parseInt(datePSplit[0]);
                int monthJ = Integer.parseInt(datePSplit[1]);
                int yearJ = Integer.parseInt(datePSplit[2]);
                int hourJ = Integer.parseInt(timePSplit[0]);
                int minuteJ = Integer.parseInt(timePSplit[1]);
                //check if the date is before the other one
                //check the year is before otherwise the month must be before, otherwise the day, otherwise the hour, otherwise they are a duplicate which wont happen
                //if a booking has a date before a booking with a lower index they switch places
                if (yearI > yearJ) {
                    Booking temp = getBookings().get(q);
                    getBookings().set(q, getBookings().get(p)); //assistantOnShiftAvailableForBookings.get(q) = assistantOnShiftAvailableForBookings.get(p)
                    getBookings().set(p, temp); //assistantOnShiftAvailableForBookings.get(p) = temp
                } else if (monthI > monthJ) {
                    Booking temp = getBookings().get(q);
                    getBookings().set(q, getBookings().get(p));
                    getBookings().set(p, temp);
                } else if (dayI > dayJ) {
                    Booking temp = getBookings().get(q);
                    getBookings().set(q, getBookings().get(p));
                    getBookings().set(p, temp);
                } else if (hourI > hourJ) {
                    Booking temp = getBookings().get(q);
                    getBookings().set(q, getBookings().get(p));
                    getBookings().set(p, temp);
                }
            }
        }
        int sequentialID = 11;
        System.out.println("List of available time-slots:");
        for (AssistantOnShift i : arrayList) {//iterate through arraylist of bookings and display each
            System.out.println(sequentialID + ". " + i.getDate());//Print out each booking along with its sequential ID
            sequentialID++;
        }
        System.out.println();
    }

    /**
     * Creates assistant on shift and bookable room pairs in order to make bookings
     * Does so by ensuring that both of them have the status that lets them be booked, while checking if their date matches
     * If these conditions are satisfied then the assistant on shift is appended to an arraylist and the bookable room is appended to another
     * They can then be retrieved by using the same index to retrieve both objects, since each pair will have the same index
     *
     * @return AssistantOnShiftBookingAppPair //An object containing 2 arraylists containing assistants on shift and bookable rooms that have been paired up according to date
     */
    public AssistantOnShiftBookingAppPair getMatchingAssistantsOnShiftBookableRooms() {
        ArrayList[] matchAssistantsOnShiftBookableRooms = new ArrayList[2];//Create an Array that can contain 2 array lists
        ArrayList<AssistantOnShift> assistantOnShiftAvailableForBookings = new ArrayList<>();//create an arraylist to store assistants on shift
        ArrayList<BookableRoom> bookableRoomAvailableForBookings = new ArrayList<>();//create an arraylist to store bookable rooms
        matchAssistantsOnShiftBookableRooms[0] = assistantOnShiftAvailableForBookings;//assign the arraylist to the array
        matchAssistantsOnShiftBookableRooms[1] = bookableRoomAvailableForBookings;//assign the arraylist to the array
        for (AssistantOnShift i : getAssistantsOnShift()) {//Compare each assistant on shift with each bookable room
            for (BookableRoom j : getBookableRooms()) {
                //If the status of both the assistant on shift and the bookable room is valid for a booking and they have an equal date add the objects to their respective array lists
                if (i.getStatus() == "FREE" && (j.getStatus() == "EMPTY" || j.getStatus() == "AVAILABLE") && i.getDate().equals(j.getDate())) {
                    assistantOnShiftAvailableForBookings.add(i);
                    bookableRoomAvailableForBookings.add(j);
                    break;//Break out since there is no need to have the same date available twice
                }

            }
        }
        //Sort the assistants on shift and bookable rooms by date
        for (int q = 0; q < matchAssistantsOnShiftBookableRooms[0].size(); q++) {
            String[] qSplit = assistantOnShiftAvailableForBookings.get(q).getDate().split(" ");//dd/mm/yy HH:MM
            String[] dateQSplit = qSplit[0].split("/");//dd mm yyyy
            String[] timeQSplit = qSplit[1].split(":"); //hh mm
            //Transform each element of the date into integers in order to be able to compare them
            int dayI = Integer.parseInt(dateQSplit[0]);
            int monthI = Integer.parseInt(dateQSplit[1]);
            int yearI = Integer.parseInt(dateQSplit[2]);
            int hourI = Integer.parseInt(timeQSplit[0]);
            int minuteI = Integer.parseInt(timeQSplit[1]);
            for (int p = q + 1; p < matchAssistantsOnShiftBookableRooms[0].size(); p++) {
                //Do the same as above but with each booking that comes after the one it is being compared to
                String[] pSplit = assistantOnShiftAvailableForBookings.get(p).getDate().split(" ");//dd/mm/yy HH:MM
                String[] datePSplit = pSplit[0].split("/");//dd mm yyyy
                String[] timePSplit = pSplit[1].split(":"); //hh mm
                int dayJ = Integer.parseInt(datePSplit[0]);
                int monthJ = Integer.parseInt(datePSplit[1]);
                int yearJ = Integer.parseInt(datePSplit[2]);
                int hourJ = Integer.parseInt(timePSplit[0]);
                int minuteJ = Integer.parseInt(timePSplit[1]);
                //check if the date is before the other one
                //check the year is before otherwise the month must be before, otherwise the day, otherwise the hour, otherwise they are a duplicate which wont happen
                //if a booking has a date before a booking with a lower index they switch places
                if (yearI > yearJ) {
                    AssistantOnShift temp = assistantOnShiftAvailableForBookings.get(q);
                    assistantOnShiftAvailableForBookings.set(q, assistantOnShiftAvailableForBookings.get(p)); //assistantOnShiftAvailableForBookings.get(q) = assistantOnShiftAvailableForBookings.get(p)
                    assistantOnShiftAvailableForBookings.set(p, temp); //assistantOnShiftAvailableForBookings.get(p) = temp
                } else if (monthI > monthJ) {
                    AssistantOnShift temp = assistantOnShiftAvailableForBookings.get(q);
                    assistantOnShiftAvailableForBookings.set(q, assistantOnShiftAvailableForBookings.get(p));
                    assistantOnShiftAvailableForBookings.set(p, temp);
                } else if (dayI > dayJ) {
                    AssistantOnShift temp = assistantOnShiftAvailableForBookings.get(q);
                    assistantOnShiftAvailableForBookings.set(q, assistantOnShiftAvailableForBookings.get(p));
                    assistantOnShiftAvailableForBookings.set(p, temp);
                } else if (hourI > hourJ) {
                    AssistantOnShift temp = assistantOnShiftAvailableForBookings.get(q);
                    assistantOnShiftAvailableForBookings.set(q, assistantOnShiftAvailableForBookings.get(p));
                    assistantOnShiftAvailableForBookings.set(p, temp);
                }
            }
        }

        //Sort the bookable rooms in the same method
        for (int q = 0; q < matchAssistantsOnShiftBookableRooms[1].size(); q++) {
            String[] qSplit = bookableRoomAvailableForBookings.get(q).getDate().split(" ");//dd/mm/yy HH:MM
            String[] dateQSplit = qSplit[0].split("/");//dd mm yyyy
            String[] timeQSplit = qSplit[1].split(":"); //hh mm
            //Transform each element of the date into integers in order to be able to compare them
            int dayI = Integer.parseInt(dateQSplit[0]);
            int monthI = Integer.parseInt(dateQSplit[1]);
            int yearI = Integer.parseInt(dateQSplit[2]);
            int hourI = Integer.parseInt(timeQSplit[0]);
            int minuteI = Integer.parseInt(timeQSplit[1]);
            for (int p = q + 1; p < matchAssistantsOnShiftBookableRooms[1].size(); p++) {
                //Do the same as above but with each booking that comes after the one it is being compared to
                String[] pSplit = bookableRoomAvailableForBookings.get(p).getDate().split(" ");//dd/mm/yy HH:MM
                String[] datePSplit = pSplit[0].split("/");//dd mm yyyy
                String[] timePSplit = pSplit[1].split(":"); //hh mm
                int dayJ = Integer.parseInt(datePSplit[0]);
                int monthJ = Integer.parseInt(datePSplit[1]);
                int yearJ = Integer.parseInt(datePSplit[2]);
                int hourJ = Integer.parseInt(timePSplit[0]);
                int minuteJ = Integer.parseInt(timePSplit[1]);
                //check if the date is before the other one
                //check the year is before otherwise the month must be before, otherwise the day, otherwise the hour, otherwise they are a duplicate which wont happen
                //if a booking has a date before a booking with a lower index they switch places
                if (yearI > yearJ) {
                    BookableRoom temp = bookableRoomAvailableForBookings.get(q);
                    bookableRoomAvailableForBookings.set(q, bookableRoomAvailableForBookings.get(p)); //assistantOnShiftAvailableForBookings.get(q) = assistantOnShiftAvailableForBookings.get(p)
                    bookableRoomAvailableForBookings.set(p, temp); //assistantOnShiftAvailableForBookings.get(p) = temp
                } else if (monthI > monthJ) {
                    BookableRoom temp = bookableRoomAvailableForBookings.get(q);
                    bookableRoomAvailableForBookings.set(q, bookableRoomAvailableForBookings.get(p));
                    bookableRoomAvailableForBookings.set(p, temp);
                } else if (dayI > dayJ) {
                    BookableRoom temp = bookableRoomAvailableForBookings.get(q);
                    bookableRoomAvailableForBookings.set(q, bookableRoomAvailableForBookings.get(p));
                    bookableRoomAvailableForBookings.set(p, temp);
                } else if (hourI > hourJ) {
                    BookableRoom temp = bookableRoomAvailableForBookings.get(q);
                    bookableRoomAvailableForBookings.set(q, bookableRoomAvailableForBookings.get(p));
                    bookableRoomAvailableForBookings.set(p, temp);
                }
            }
        }
        //Create the object that stores the 2 arraylists and return it
        AssistantOnShiftBookingAppPair finalPairs = new AssistantOnShiftBookingAppPair(assistantOnShiftAvailableForBookings, bookableRoomAvailableForBookings);
        return finalPairs;
    }

    /**
     * Adds a bookable room to the arrayList of bookable rooms in the booking system
     * Calls the constructor in BookableRoom {@link BookableRoom}
     * Then adds the bookable room to the arraylist
     *
     * @param bookableRoom room //bookable room
     */
    @SuppressWarnings("unchecked")
    public void addBookableRoom(BookableRoom bookableRoom) {
        bookableRooms.add(bookableRoom);
    }

    /**
     * Adds a assistant on shift to the arrayList of assistant on shift in the booking system
     * Calls the constructor in AssistantOnShift {@link AssistantOnShift}
     * Then adds the assistant on shift to the arraylist
     *
     * @param assistantOnShift //assistant on shift
     */
    @SuppressWarnings("unchecked")
    public void addAssistantsOnShift(AssistantOnShift assistantOnShift) {
        assistantsOnShifts.add(assistantOnShift);
    }

    /**
     * Adds a booking to the arrayList of booking in the booking system
     * Calls the constructor in booking
     * Then adds the booking to the arraylist
     *
     * @param booking //booking
     */
    @SuppressWarnings("unchecked")
    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    /**
     * Removes a bookable room from the arraylist of bookable rooms in the booking system
     *
     * @param index //index of the bookable room in the arrayList
     */
    public void removeBookableRoom(int index) {
        bookableRooms.remove(index);
    }

    /**
     * Removes an assistant on shift from the arraylist of assistants on shift in the booking system
     *
     * @param index //index of the assistant on shift in the arrayList
     */
    public void removeAssistantOnShift(int index) {
        assistantsOnShifts.remove(index);
    }

    /**
     * Removes an booking from the arraylist of bookings in the booking system
     *
     * @param index //index of the booking in the arrayList
     */
    public void removeBooking(int index) {
        bookings.remove(index);
    }
}