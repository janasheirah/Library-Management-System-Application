package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LibrarianTest {

    private Librarian testLibrarian;
    private Library testLibrary;

    @BeforeEach
    public void setup() {
        testLibrary = new Library("Vancouver Public Library");
        testLibrarian = new Librarian("Jane Doe");
    }

    @Test
    public void testConstructor() {
        assertEquals("Jane Doe", testLibrarian.getName());
    }

    @Test
    public void testAddBook() {
        testLibrarian.addBook("Book Of Proof", "Richard Hammack", "Non Fiction", testLibrary);
        assertEquals(11, testLibrary.getListOfBooks().size());
        assertEquals("Book Of Proof", testLibrary.getListOfBooks().get(10).getBookName());
        assertEquals("Richard Hammack", testLibrary.getListOfBooks().get(10).getAuthor());
        assertEquals("Non Fiction", testLibrary.getListOfBooks().get(10).getGenre());
    }

    @Test
    public void testAddMultipleBooks() {
        testLibrarian.addBook("Book Of Proof", "Ricahrd Hammack", "Non Fiction", testLibrary);
        testLibrarian.addBook("November 9", "Colleen Hoover", "Romance", testLibrary);
        assertEquals(12, testLibrary.getListOfBooks().size());
        assertEquals("November 9", testLibrary.getListOfBooks().get(11).getBookName());
        assertEquals("Colleen Hoover", testLibrary.getListOfBooks().get(11).getAuthor());
        assertEquals("Romance", testLibrary.getListOfBooks().get(11).getGenre());
    }
}
