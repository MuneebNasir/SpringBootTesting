package SpringBoot;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.testng.annotations.Test;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    // This will hold the port number the server was started on
    int port = 8080;

    final TestRestTemplate restTemplate = new TestRestTemplate();

    @Autowired
    private AddressBookController controller;



    @Test
    public void testLocalhostResponse() throws Exception {
        String message = restTemplate.getForObject("http://localhost:" + port + "/", String.class);
        org.junit.jupiter.api.Assertions.assertNotEquals("This is a test message", message);
    }

    @Test
    public void testBookEmpty() throws Exception {
        String message = restTemplate.getForObject("http://localhost:" + port + "/addressBookView", String.class);
        org.junit.jupiter.api.Assertions.assertNotNull(message);
    }

    @Test
    public void testAddFriend() throws Exception {
        String exampleCourseJson = "{\"name\":\"Spring\",\"phoneNumber\":\"613s\"}";
        String message = restTemplate.getForObject("http://localhost:" + port + "/addressBookView", String.class);
        org.junit.jupiter.api.Assertions.assertNotNull(message);
    }
}