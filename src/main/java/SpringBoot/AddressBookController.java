package SpringBoot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

/**
 * The Address Book Controller
 * @author Muneeb Nasir
 * @version 4806.5
 */

@Controller
public class AddressBookController {

    @Autowired
    private BookRepository repository;

    @Autowired
    private BuddyInfoRepository buddyRepository;

    @GetMapping(path = "/addressBookView")
    public String addressBookView(Model model) {

        Collection<AddressBook> addressBooks = repository.findAll();
        model.addAttribute("allBooks", addressBooks);
        return "addressBookView";
    }

    @GetMapping(path = "/addressBook-all", consumes = "application/json", produces = "application/json")
    @ResponseBody
    Collection<AddressBook> all() {
        return repository.findAll();
    }

    @GetMapping(path = "/addressBook", consumes = "application/json", produces = "application/json")
    @ResponseBody
    AddressBook getById(@RequestParam Long id) {
        return repository.findAddressBookById(id);
    }

    @PostMapping(path = "/addressBook-new", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public AddressBook newBook(@RequestBody AddressBook addressBook) {
        return repository.save(addressBook);
    }

    @PostMapping(path = "/addressBook-addFriend", consumes = "application/json", produces = "application/json")
    @ResponseBody
    String newFriend(@RequestBody BuddyInfo friend, @RequestParam(name = "id") Long id) {
        repository.findAddressBookById(id).addFriend(
                new BuddyInfo(friend.getName(), friend.getphoneNumber()));
        repository.save(repository.findAddressBookById(id));
        return "SUCCESS";
    }

    @DeleteMapping(path = "/addressBook-delete", consumes = "application/json", produces = "application/json")
    @ResponseBody
    void deleteBook(@RequestParam(name = "id") Long id) {
        repository.deleteById(id);
    }

    @DeleteMapping(path = "/addressBook-deleteFriend", consumes = "application/json", produces = "application/json")
    @ResponseBody
    void deleteFriendInBook(@RequestParam(name = "friendId") Long id){
        buddyRepository.deleteById(id);
    }


}
