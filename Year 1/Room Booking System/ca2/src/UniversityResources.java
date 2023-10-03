import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the university resources
 * The university resources represent the rooms and assistants that are available to use for covid testing
 *
 * @author Jeroen Mijer
 * @version 1.0
 * @since 1.0
 */
public class UniversityResources {
    private ArrayList assistantArrayList;

    private ArrayList roomArrayList;

    /**
     * Creates a universityresource
     * Called without anything because it is empty upon creation from BookingApp
     * Creates an ArrayList of Assistants
     * ArrayList becomes an attribute of university resoruces
     * Creates an ArrayList of Rooms
     * ArrayList becomes an attribute of university resources
     * Both of these ArrayLists are empty and assistants and rooms are added to them
     */
    public UniversityResources() {
        ArrayList<Assistant> assistantArrayList = new ArrayList<Assistant>();
        this.assistantArrayList = assistantArrayList;
        ArrayList<Room> roomArrayList = new ArrayList<>();
        this.roomArrayList = roomArrayList;

    }

    /**
     * Gets an ArrayList of assistants
     *
     * @return ArrayList arraylist of assistants
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Assistant> getAssistantArrayList() {
        return assistantArrayList;
    }

    /**
     * Gets an ArrayList of rooms
     *
     * @return ArrayList arraylists of rooms
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Room> getRoomArrayList() {
        return roomArrayList;
    }

    /**
     * Adds an assistant to the arrayList of assistants in university resources
     * Calls the constructor in Assistant {@link Assistant}
     * Then adds the assistant to the arraylist
     *
     * @param name  //name of an assistant
     * @param email //email of an assistant
     */
    @SuppressWarnings("unchecked")
    public void addAssistant(String name, String email) {
        Assistant newAssistant = new Assistant(name, email);
        assistantArrayList.add(newAssistant);
    }

    /**
     * Adds an assistant to the arrayList of rooms in university resources
     * Calls the constructor in Room {@link Room}
     * Then adds the room to the arraylist
     *
     * @param code     //code of an room
     * @param capacity //capacity of an room
     */
    @SuppressWarnings("unchecked")
    public void addRoom(String code, int capacity) {
        Room newRoom = new Room(code, capacity);
        roomArrayList.add(newRoom);
    }
}