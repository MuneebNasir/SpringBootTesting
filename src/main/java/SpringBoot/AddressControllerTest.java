package SpringBoot;


import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest
public class AddressControllerTest {

    @Autowired
    private AddressBookController controller;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertThat(controller).isNotNull();
    }

    @Test
    public void testAddressBookLoad() throws Exception {
        AddressBook book = new AddressBook();
        BuddyInfo buddyInfo = new BuddyInfo("Muneeb", "123");
        book.addFriend(buddyInfo);
        ResponseEntity<String> result = this.restTemplate.postForEntity("http://localhost:8080/addressbook",
                book,
                String.class);
        org.junit.jupiter.api.Assertions.assertEquals(200, result.getStatusCodeValue());
    }
}
