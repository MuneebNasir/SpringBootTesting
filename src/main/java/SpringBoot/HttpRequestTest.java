package SpringBoot;



import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class HttpRequestTest {

    // This will hold the port number the server was started on
    int port = 8080;

    final TestRestTemplate restTemplate = new TestRestTemplate();

    @Autowired
    private MockMvc addressBookMVC;

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
    public void testGetAddressBookById() throws Exception
    {
        addressBookMVC.perform( MockMvcRequestBuilders
                .get("/addressBook/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testNewBook() throws Exception {
        AddressBook addressBook = new AddressBook();
        addressBookMVC.perform(MockMvcRequestBuilders
                .post("/addressBook-new")
                .content(asJsonString(addressBook))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    public void testAddFriend() throws Exception {
        AddressBook book = new AddressBook();
        addressBookMVC.perform(MockMvcRequestBuilders
                .post("/addressBook-new")
                .content(asJsonString(book))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        BuddyInfo buddyInfo = new BuddyInfo("Muneeb", "613");
        addressBookMVC.perform(MockMvcRequestBuilders
                .post("/addressBook-addFriend").param("id", "1")
                .content(asJsonString(buddyInfo))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.friends").exists());
    }



    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}