/**
 * Represents an assistant in the university resources
 * An assistant represents an assistant administering covid tests and consists of a name and an email
 *
 * @author Jeroen Mijer
 * @version 1.0
 * @since 1.0
 */

public class Assistant {
    private String name;

    private String email;

    /**
     * Creates an assistant
     * Name cannot be null
     * Email must end with @uok.ac.uk
     * Email checked for uniqueness when hardcoding
     *
     * @param name  name of the assistant
     * @param email email of the assistant
     */
    public Assistant(String name, String email) {
        if (name == null) {
            if (name == null) {
                System.err.println("Name input cannot be null ");
                System.exit(1);
            }
            this.name = name;
        } else {
            this.name = name;
            Boolean emailCheck = email.endsWith("@uok.ac.uk");
            if (emailCheck == false) {
                System.err.println("Error!\nThe student email must end in @uok.ac.uk");
                System.exit(1);
            } else {
                this.email = email;
            }
        }
    }

    /**
     * Gets assistant name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets assistant name
     *
     * @param name name of assistant
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets assistant email
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets assistant email
     *
     * @param email email of assistant
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * When method or System.out.print() is called, returns the assistant in the desired format
     *
     * @return toString of assistant
     */
    public String toString() {
        return "| " + name + " | " + email + " |";
    }
}
