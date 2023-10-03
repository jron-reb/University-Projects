/**
 * Represents a booking within the booking system
 * A booking is a pair of an assistant on shift and a bookable room anchored in time along with the students email who is taking the test
 * In order to have an assistant on shift and a bookable room assigned to a booking, they have to be available at the same time
 * This means the assistant on shift thaving the status "FREE", and the bookable room have the status "EMPTY" or "AVAILABLE"
 *
 * @author Jeroen Mijer
 * @version 1.0
 * @since 1.0
 */
public class Booking {
    private BookableRoom bookableRoom;

    private AssistantOnShift assistantOnShift;

    private String status;

    private String studentEmail;

    /**
     * Creates a booking
     * When creating a booking the date of the bookable room and the assistant on shift is already checked to be matching
     * The email is also already checked for validity
     * This constructor sets the status of the booking to be "SCHEDULED"
     *
     * @param bookableRoom     //bookable room of the booking
     * @param assistantOnShift //assistant on shift of the booking
     * @param studentEmail     //student email of the booking
     */
    public Booking(BookableRoom bookableRoom, AssistantOnShift assistantOnShift, String studentEmail) {
        this.bookableRoom = bookableRoom;
        this.assistantOnShift = assistantOnShift;
        this.status = "SCHEDULED";
        this.studentEmail = studentEmail;
    }

    /**
     * Creates a booking
     * When creating a booking the date of the bookable room and the assistant on shift is already checked to be matching
     * The email is also already checked for validity
     * This constructor also takes in the input of the status of the booking so that the booking can have the status "COMPLETED" upon initialization
     *
     * @param bookableRoom     //bookable room of the booking
     * @param assistantOnShift //assistant on shift of the booking
     * @param studentEmail     //student email of the booking
     * @param status           //status of the booking
     */
    public Booking(BookableRoom bookableRoom, AssistantOnShift assistantOnShift, String studentEmail, String status) {
        this.bookableRoom = bookableRoom;
        this.assistantOnShift = assistantOnShift;
        this.status = status;
        this.studentEmail = studentEmail;
    }

    /**
     * Creates a booking
     * Creates a clone of a booking, copying all of its attributes, but being stored in a different memory location
     *
     * @param booking //booking of the booking
     */
    public Booking(Booking booking) {
        this.bookableRoom = booking.getBookableRoom();
        this.assistantOnShift = booking.getAssistantOnShift();
        this.status = booking.getStatus();
        this.studentEmail = booking.getStudentEmail();

    }

    /**
     * Gets booking bookable room
     *
     * @return bookable room
     */
    public BookableRoom getBookableRoom() {
        return bookableRoom;
    }

    /**
     * Sets booking bookable room
     *
     * @param bookableRoom bookableRoom used to change all attributes of a bookable room
     */
    public void setBookableRoom(BookableRoom bookableRoom) {
        this.bookableRoom = bookableRoom;
    }

    /**
     * Gets booking assistant on shift
     *
     * @return assistant on shift
     */
    public AssistantOnShift getAssistantOnShift() {
        return assistantOnShift;
    }

    /**
     * Sets booking assistant on shift
     *
     * @param assistantOnShift AssistantOnShift used to change all attributes of an assistant on shift
     */
    public void setAssistantOnShift(AssistantOnShift assistantOnShift) {
        this.assistantOnShift = assistantOnShift;
    }

    /**
     * Gets booking status
     *
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets booking status
     *
     * @param status status of a booking
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets booking student email
     *
     * @return student email
     */
    public String getStudentEmail() {
        return studentEmail;
    }

    /**
     * Sets booking student email
     *
     * @param studentEmail student email of a booking
     */
    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    /**
     * When method or System.out.print() is called, returns the booking in the desired format
     *
     * @return toString of a booking
     */
    public String toString() {
        return "| " + bookableRoom.getDate() + " | " + status + " | " + assistantOnShift.getEmail() + " | " + bookableRoom.getCode() + " | " + studentEmail + " |";
    }
}