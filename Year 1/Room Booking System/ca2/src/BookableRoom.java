/**
 * Represents a bookable room within the booking system
 * A bookable room is a Room anchored in time that can be used in a booking, in order to schedule and take a covid test in
 *
 * @author Jeroen Mijer
 * @version 1.0
 * @since 1.0
 */
public class BookableRoom {
    private String code;

    private int capacity;

    private String date;

    private String status;

    private int occupancy;

    /**
     * Creates a bookable room
     * Sets the bookable rooms status as empty, since it is just created and cannot be full
     * The occupancy is 0 for the same reason
     *
     * @param code     // code of the bookable room
     * @param capacity // capacity of the bookable room
     * @param date     // date of the bookable room
     */
    public BookableRoom(String code, int capacity, String date) {
        this.code = code;
        this.capacity = capacity;
        this.date = date;
        this.status = "EMPTY";
        this.occupancy = 0;
    }

    /**
     * Creates a bookable room
     * Takes in an existing room containing a code and capacity
     * This takes in an occupancy so that the initial data can be changed to have an available and a full room, otherwise a new bookable room would be set to an empty status with an occupancy of 0
     * To ensure that happens each time use the previous constructor with parameters code, capacity, date
     *
     * @param room      // room of the bookable room, used to extract the code and capacity
     * @param date      //date of the bookable room
     * @param occupancy //occupancy of the bookable room
     */
    public BookableRoom(Room room, String date, int occupancy) {
        this.code = room.getCode();
        this.capacity = room.getCapacity();
        this.date = date;
        this.occupancy = occupancy;
        if (occupancy == 0) {
            this.status = "EMPTY";
        } else if (0 < occupancy && occupancy < capacity) {
            this.status = "AVAILABLE";
        } else if (occupancy == capacity) {
            this.status = "FULL";
        }
    }

    /**
     * Creates a bookable room
     * Creates a clone of a bookable room, copying all of its attributes, but being stored in a different memory location
     *
     * @param bookableRoom //bookable room of the bookable room
     */
    public BookableRoom(BookableRoom bookableRoom) {
        this.code = bookableRoom.getCode();
        this.capacity = bookableRoom.getCapacity();
        this.date = bookableRoom.getDate();
        this.status = bookableRoom.getStatus();
        this.occupancy = bookableRoom.getOccupancy();
    }

    /**
     * Gets bookable room code
     *
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets bookable room code
     *
     * @param code bookable room code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets bookable room capacity
     *
     * @return capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets bookable room capacity
     *
     * @param capacity bookable room capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Gets bookable room date
     *
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets bookable room date
     *
     * @param date bookable room date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets bookable room status
     *
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets bookable room status
     *
     * @param status bookable room status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets bookable room occupancy
     *
     * @return occupancy
     */
    public int getOccupancy() {
        return occupancy;
    }

    /**
     * Sets bookable room occupancy
     *
     * @param occupancy bookable room occupancy
     */
    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }

    /**
     * Increases the occupancy of a bookable room by 1
     */
    public void increaseOccupancy() {
        this.occupancy++;
    }

    /**
     * When method or System.out.print() is called, returns the bookable room in the desired format
     *
     * @return toString of bookable room
     */
    public String toString() {
        //In the specification it says room code isntead of code so there may be something slightly different between the 2 codes
        return "| " + date + " | " + status + " | " + code + " | occupancy: " + occupancy;
    }
}