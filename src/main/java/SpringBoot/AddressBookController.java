package SpringBoot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import SpringBoot.RequestResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The Address Book Controller
 * @author Muneeb Nasir
 * @version 4806.6
 */

@Controller
public class AddressBookController {

    @Autowired
    private BookRepository repository;

    @Autowired
    private BuddyInfoRepository buddyRepository;

    @GetMapping(path = "/addressBooksView")
    public String addressBookView(Model model) {

        Collection<AddressBook> addressBooks = repository.findAll();
        model.addAttribute("allBooks", addressBooks);

        // Empty Buddy Object - namespace
        model.addAttribute("newBuddy", new BuddyInfo());
        return "addressBooksView";
    }

    @GetMapping(path = "/addressBooksViewAll", produces = "application/json")
    @ResponseBody
    public RequestResponse addressBooksViewAll() {

        Collection<AddressBook> addressBooks = repository.findAll();
        return new RequestResponse("OK", addressBooks);
    }

    @GetMapping(path = "/addressBook-all", produces = "application/json")
    @ResponseBody
    public RequestResponse all() {
        ArrayList bookCollection = new ArrayList<>();
        for (AddressBook book: repository.findAll()){
            bookCollection.add(book.getId());
        }
        return new RequestResponse("OK", bookCollection);
    }

    @PostMapping(path = "/addressBook-addNewFriend", consumes = "application/json")
    @ResponseBody
    public RequestResponse addNewBuddy(@RequestBody BuddyInfo buddy) {
        AddressBook book  = repository.findById(buddy.getBookId()).orElse(new AddressBook());

        book.addFriend(
                new BuddyInfo(buddy.getName(), buddy.getphoneNumber(), buddy.getHomeAddress()));
        repository.save(book);

        return new RequestResponse("OK", book);
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

    @PostMapping("/addressBookView")
    public String addBuddy(@ModelAttribute BuddyInfo buddy, Model model) {

        AddressBook book  = repository.findById(buddy.getBookId()).orElse(new AddressBook());

        book.addFriend(
                new BuddyInfo(buddy.getName(), buddy.getphoneNumber(), buddy.getHomeAddress()));
        repository.save(book);

        model.addAttribute("addressBook", book);
        return "addressBookView";
    }


}
