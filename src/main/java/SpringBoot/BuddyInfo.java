package SpringBoot;
import javax.persistence.*;

/**
 * Buddy Info Entity Class
 * @author Muneeb Nasir
 * @version 4806.6
 */

@Entity
public class BuddyInfo{

    @Id
    @GeneratedValue(generator = "Id", strategy = GenerationType.IDENTITY)
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
     * The Address
     */
    private String homeAddress;

    /**
     * Associated Book ID
     */
    private Long bookId;

    /**
     * Default Constructor
     */
    public BuddyInfo() {
    }

    public BuddyInfo(String name, String number, String homeAddress) {
        this.name = name;
        this.phoneNumber = number;
        this.homeAddress = homeAddress;
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

    /**
     * The setter method for address
     */
    public void setHomeAddress(String address) {
        this.homeAddress = address;
    }

    /**
     * The getter method for address
     */
    public String getHomeAddress() { return this.homeAddress; }

    /**
     * The getter method for address book ID (The Getters/Setters Used By Default by Thymeleaf)
     */
    public Long getBookId() { return this.bookId; }

    public void setBookId(int id) {
        this.bookId = new Long(id);
    }

    @Override
    public String toString() {
        return "Buddy Details:  Name = " + name + " & Phone Number = " + phoneNumber;
    }
}
