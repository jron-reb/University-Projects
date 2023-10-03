/**
 * Represents a room within the university resources
 * A room represents a space where students can get tested and consists of a code and a capacity
 *
 * @author Jeroen Mijer
 * @version 1.0
 * @since 1.0
 */
public class Room {
    private String code;

    private int capacity;

    /**
     * Creates a room
     * The ccapacity must be greater than 0
     * The code is checked for uniqueness when hardcoded
     *
     * @param code     //code of the room
     * @param capacity //capacity of the room
     */
    public Room(String code, int capacity) {
        //Code must be unique for each room
        //Capacity must be an integer value above 0
        this.code = code;
        if (capacity < 1) {
            System.err.println("Room capacity must be greater than 0 ");
            System.exit(1);
        } else {
            this.capacity = capacity;
        }
    }

    /**
     * Gets room code
     *
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets room code
     *
     * @param code code of a room
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets room capacity
     *
     * @return capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets room capacity
     *
     * @param capacity capacity of a room
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * When method or System.out.print() is called, returns the room in the desired format
     *
     * @return toString of Room
     */
    public String toString() {
        return "| " + code + " | capacity: " + capacity + " |";
    }
}
