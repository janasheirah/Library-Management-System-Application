package persistence;

import model.Book;
import model.Library;
import model.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            User user = new User("Jane Doe's Cart");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyCart() {
        try {
            User user = new User("Jane Doe's Cart");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCart.json");
            writer.open();
            writer.write(user);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCart.json");
            user = reader.read();
            assertEquals("Jane Doe's Cart", user.getName());
            assertEquals(0, user.getCheckOutCart().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterEmptyNewBooks() {
        try {
            Library lib = new Library("IKB");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyNewBooks.json");
            writer.open();
            writer.writeAddBook(lib);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyNewBooks.json");
            lib = reader.readAddBook();
            assertEquals("IKB", lib.getName());
            assertEquals(0, lib.getListOfNewBooks().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralCart() {
        try {
            User user = new User("Jane Doe's Cart");
            user.addBookToCart(new Book("Harry Potter", "J.K. Rowling", "Fantasy"));
            user.addBookToCart(new Book("It Ends With Us", "Colleen Hoover", "Romance"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCart.json");
            writer.open();
            writer.write(user);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralCart.json");
            user = reader.read();
            assertEquals("Jane Doe's Cart", user.getName());
            List<Book> books = user.getCheckOutCart();
            assertEquals(2, books.size());
            checkBook("Harry Potter", "J.K. Rowling", "Fantasy", books.get(0));
            checkBook("It Ends With Us", "Colleen Hoover", "Romance", books.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralNewBooks() {
        try {
            Library lib = new Library("IKB");
            lib.addBookByLibrarian("Harry Potter", "J.K. Rowling", "Fantasy");
            lib.addBookByLibrarian("It Ends With Us", "Colleen Hoover", "Romance");
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralNewBooks.json");
            writer.open();
            writer.writeAddBook(lib);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralNewBooks.json");
            lib = reader.readAddBook();
            assertEquals("IKB", lib.getName());
            List<Book> books = lib.getListOfNewBooks();
            assertEquals(2, books.size());
            checkBook("Harry Potter", "J.K. Rowling", "Fantasy", books.get(0));
            checkBook("It Ends With Us", "Colleen Hoover", "Romance", books.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}