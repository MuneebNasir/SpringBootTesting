package SpringBoot;
import javax.persistence.*;

/**
 * Buddy Info Entity Class
 * @author Muneeb Nasir
 * @version 4806.5
 */

@Entity
public class BuddyInfo{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The Phone Number of the Buddy (Primary Key)
     */
    private String phoneNumber;

    /**
     * The Name of the Buddy
     */
    private String name;

    /**
     * Default Constructor
     */
    public BuddyInfo() {
    }

    public BuddyInfo(String name, String number) {
        this.name = name;
        this.phoneNumber = number;
    }

    public BuddyInfo(BuddyInfo buddyInfo) {
        this.name = buddyInfo.name;
        this.phoneNumber = buddyInfo.phoneNumber;
    }

    /**
     * Gets the id of this Buddy.
     * @return the id
     */

    public Long getId() {
        return this.id;
    }

    /**
     * The setter method for Buddy ID
     */
    public void setId(Long number) {
        this.id = number;
    }

    /**
     * The getter method for attaining the name of the Buddy object
     */
    public String getName() {
        return name;
    }

    /**
     * The setter method for declaring the name of the Buddy object
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The getter method for attaining the email address of the Buddy object
     */
    public String getphoneNumber() {
        return phoneNumber;
    }

    /**
     * The setter method for declaring the email address of the Buddy object
     */
    public void setphoneNumber(String email) {
        this.phoneNumber = email;
    }

    @Override
    public String toString() {
        return "Buddy Details:  Name = " + name + " & Phone Number = " + phoneNumber;
    }
}
