/**
 * Represents an assistant on shift within the booking system
 * An assistant on shift is an Assistant that is anchored in time and can be assigned to a room in a booking in order to administer a covid test
 *
 * @author Jeroen Mijer
 * @version 1.0
 * @since 1.0
 */
public class AssistantOnShift {
    private String name;

    private String email;

    private String date;

    private String status;

    /**
     * Create an assistant on shift
     * The email must be end in @uok.ac.uk
     * The name not being null, the date being the right format and the email being unique is checked when hard coding the data
     * The status is set as free upon creating the assistant on shift
     *
     * @param name  //name of the assistant on shift
     * @param email //email of the assistant on shift
     * @param date  //date of the assistant on shift
     */
    public AssistantOnShift(String name, String email, String date) {
        this.name = name;
        Boolean emailCheck = email.endsWith("@uok.ac.uk");
        if (emailCheck == false) {
            System.err.println("Error!\nThe student email must end in @uok.ac.uk");
            System.exit(1);
        } else {
            this.email = email;
        }
        this.date = date;
        this.status = "FREE";
    }

    /**
     * Creates an assistant on shift
     * The name and email have already been checked when creating the assistant and therefore does not need to be checked again
     * This constructor allows the status to be input and is done so that there can be a Busy assistant upon initialization
     * This constructor takes an already existing assistant and extracts the name and email from there
     *
     * @param assistant //assistant of the assistant on shift
     * @param date      //date of the assistant on shift
     * @param status    //status of the assistant on shift
     */
    public AssistantOnShift(Assistant assistant, String date, String status) {
        this.name = assistant.getName();
        this.email = assistant.getEmail();
        this.date = date;
        this.status = status;
    }

    /**
     * Creates a assistant on shift
     * Creates a clone of a assistant on shift, copying all of its attributes, but being stored in a different memory location
     *
     * @param assistantOnShift //assistant on shift of the assistant on shift
     */
    public AssistantOnShift(AssistantOnShift assistantOnShift) {
        this.name = assistantOnShift.getName();
        this.email = assistantOnShift.getEmail();
        this.date = assistantOnShift.getDate();
        this.status = "FREE";
    }

    /**
     * Gets assistant on shift name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets assistant on shift name
     *
     * @param name name of the assistant on shift
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets assistant on shift email
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets assistant on shift email
     *
     * @param email email of the assistant on shift
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets assistant on shift date
     *
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets assistant on shift date
     *
     * @param date date of the assistant on shift
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets assistant on shift status
     *
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets assistant on shift status
     *
     * @param status status of the asssistant on shift
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * When method or System.out.print() is called, returns the assistant on shift in the desired format
     *
     * @return toString of an assistant on shift
     */
    public String toString() {
        return "| " + date + " | " + status + " | " + email + " |";
    }
}
