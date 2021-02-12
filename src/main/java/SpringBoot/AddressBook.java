package SpringBoot;
import javax.persistence.*;
import java.util.*;

/**
 * The Address Book Class Updated With Java Persistence Class Handling
 * @author Muneeb Nasir
 * @version 4806.4
 */
@Entity
public class AddressBook{

    @Id
    @GeneratedValue(generator = "address", strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn
    private Collection<BuddyInfo> friends;


    /** Creates a new instance of AddressBook */
    public AddressBook() {
        friends = new ArrayList();
    }

    /** Creates a new instance of AddressBook */
    public AddressBook(Long id) {
        this.id = id;
    }

    public AddressBook(Collection<BuddyInfo> friends) {
        this.friends = friends;
    }

    /**
     * Gets the id of this Team.
     * @return the id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Sets the id of this Address Book to the specified value.
     * @param id the new id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for the Friends List in the Address Book
     * @return  friends
     */
    public Collection<BuddyInfo> getFriends() {
        return friends;
    }

    /**
     * Setter for the Friends List in the Address Book
     * @param friends the Friend List
     */
    public void setFriends(Collection<BuddyInfo> friends) {
        this.friends = friends;
    }


    public void addFriend(BuddyInfo friend){
        friends.add(friend);
    }

    public void removeFriend(BuddyInfo friend){
        friends.remove(friend);
    }

    /**
     * The Method is used to print the contents Address Book
     */
    public void printBook(){
        System.out.println("\n---------------- Address Book Details ----------------\n");
        for(BuddyInfo temp: friends )
        {
            System.out.println(temp.toString().trim());
        }
        System.out.println("\n------------------------------------------------\n");
    }

    @Override
    public String toString() {
        return "AddressBook{" +
                "id=" + id +
                ", friends=" + friends +
                '}';
    }
}