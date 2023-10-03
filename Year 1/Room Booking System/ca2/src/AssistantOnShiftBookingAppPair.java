import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents an assistant on shift booking app pair
 * This is represented by 2 array lists where the index in one arraylist has a matching pair in the other arraylist
 * This is to store the pairs of bookable rooms and assistants on shift. If they have the same index they are a pair and have teh same date
 * In order to have an assistant on shift and a bookable room to be a pair, they have to be available at the same time
 * This means the assistant on shift thaving the status "FREE", and the bookable room have the status "EMPTY" or "AVAILABLE"
 *
 * @author Jeroen Mijer
 * @version 1.0
 * @since 1.0
 */
public class AssistantOnShiftBookingAppPair {
    private ArrayList<AssistantOnShift> assistantsOnShift;

    private ArrayList<BookableRoom> bookableRooms;

    /**
     * Creates an assistant on shift bookable room pair
     *
     * @param assistantsOnShift arrayList of assistants on shift of an assistant on shift bookable room pair
     * @param bookableRooms     arraylist of bookable rooms of an assistant on shift bookable room pair
     */
    public AssistantOnShiftBookingAppPair(ArrayList<AssistantOnShift> assistantsOnShift, ArrayList<BookableRoom> bookableRooms) {
        this.assistantsOnShift = assistantsOnShift;
        this.bookableRooms = bookableRooms;
    }

    /**
     * Gets assistant on shift bookable room pair arrayList of assistants on shift
     *
     * @return assistantsOnShift arraylist of assistants on shift
     */
    public ArrayList<AssistantOnShift> getAssistantsOnShift() {
        return assistantsOnShift;
    }

    /**
     * Sets assistant on shift bookable room pair arrayList of assistants on shift
     *
     * @param assistantsOnShift arraylist of assistants on shift
     */
    public void setAssistantsOnShift(ArrayList<AssistantOnShift> assistantsOnShift) {
        this.assistantsOnShift = assistantsOnShift;
    }

    /**
     * Gets assistant on shift bookable room pair arrayList of bookable rooms
     *
     * @return bookableRooms arraylist of bookable rooms
     */
    public ArrayList<BookableRoom> getBookableRooms() {
        return bookableRooms;
    }

    /**
     * Sets assistant on shift bookable room pair arrayList of bookable rooms
     *
     * @param bookableRooms arraylist of bookable rooms
     */
    public void setBookableRooms(ArrayList<BookableRoom> bookableRooms) {
        this.bookableRooms = bookableRooms;
    }
}