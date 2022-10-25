package persistence;

import model.Book;
import model.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            User u = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyCart() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCart.json");
        try {
            User user = reader.read();
            assertEquals("Jane Doe's Cart", user.getName());
            assertEquals(0, user.getCheckOutCart().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralCart() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCart.json");
        try {
            User user = reader.read();
            assertEquals("Jane Doe's Cart", user.getName());
            List<Book> books = user.getCheckOutCart();
            assertEquals(2, books.size());
            checkBook("Harry Potter", "J.K. Rowling", "Fantasy", books.get(0));
            checkBook("It Ends With Us", "Colleen Hoover", "Romance", books.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}