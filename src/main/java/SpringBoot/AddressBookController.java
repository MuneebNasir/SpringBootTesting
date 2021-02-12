package SpringBoot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(path = "/addressBook/{id}")
    @ResponseBody
    AddressBook getById(@PathVariable("id") Long id) {
        return repository.findAddressBookById(id);
    }

    @PostMapping(path = "/addressBook-new", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<AddressBook> newBook(@RequestBody AddressBook addressBook) {
        repository.save(addressBook);
        return new ResponseEntity<>(addressBook, HttpStatus.OK);
    }

    @PostMapping(path = "/addressBook-addFriend", consumes = "application/json", produces = "application/json")
    @ResponseBody
    ResponseEntity<AddressBook> newFriend(@RequestBody BuddyInfo friend, @RequestParam(name = "id") Long id) {
        repository.findAddressBookById(id).addFriend(
                new BuddyInfo(friend.getName(), friend.getphoneNumber(), friend.getHomeAddress()));
        repository.save(repository.findAddressBookById(id));
        return new ResponseEntity<>(repository.findAddressBookById(id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/addressBook-delete")
    @ResponseBody
    ResponseEntity<HttpStatus> deleteBook(@RequestParam(name = "id") Long id) {

        repository.deleteById(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/addressBook-deleteFriend")
    @ResponseBody
    ResponseEntity<HttpStatus> deleteFriendInBook(@RequestParam(name = "friendId") Long id){
        buddyRepository.deleteById(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }


}
