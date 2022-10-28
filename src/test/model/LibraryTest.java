package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    private Book book1;
    private Book book2;
    private Library testLibrary;

    @BeforeEach
    public void setup() {
        testLibrary = new Library("Vancouver Public Library");
        book1 = new Book("The Book Thief", "Markus Zusak", "Fiction");
        book2 = new Book("Book Of Proofs", "Richard hammack", "Non fiction");
        testLibrary.getListOfBooks().add(book1);
    }

    @Test
    public void testSearchForBookFiction() {
        assertEquals(2, testLibrary.searchForBook("Fantasy").size());
        assertEquals("The Hunger Games", testLibrary.searchForBook("Fantasy").get(0));
        assertEquals("Harry Potter", testLibrary.searchForBook("Fantasy").get(1));
    }

    @Test
    public void testSearchForBookRomance() {
        assertEquals(2, testLibrary.searchForBook("Romance").size());
        assertEquals("The Notebook", testLibrary.searchForBook("Romance").get(0));
        assertEquals("The Fault In Our Stars", testLibrary.searchForBook("Romance").get(1));
    }

    @Test
    public void testInStock() {
        assertTrue(testLibrary.inStock(book1));
        assertFalse(testLibrary.inStock(book2));
    }

    @Test
    public void testSearchByTitle() {
        assertEquals(book1, testLibrary.searchForBookByTitle("The Book Thief"));
        assertNull(testLibrary.searchForBookByTitle("November 9"));
    }

    @Test
    public void testGetBookVariable() {
        testLibrary.searchForBookByTitle("The Book Thief");
        assertEquals(book1, testLibrary.getBookVariable());
    }

    @Test
    public void testGetBookTitles() {
        assertEquals(11, testLibrary.getListOfTitles().size());
        assertEquals("The Book Thief", testLibrary.getListOfTitles().get(10));
    }


    @Test
    public void testAddBook() {
        testLibrary.addBookByLibrarian("Book Of Proof", "Richard Hammack", "Non Fiction");
        assertEquals(12, testLibrary.getListOfBooks().size());
        assertEquals("Book Of Proof", testLibrary.getListOfBooks().get(11).getBookName());
        assertEquals("Richard Hammack", testLibrary.getListOfBooks().get(11).getAuthor());
        assertEquals("Non Fiction", testLibrary.getListOfBooks().get(11).getGenre());
    }

    @Test
    public void testAddMultipleBooks() {
        testLibrary.addBookByLibrarian("Book Of Proof", "Ricahrd Hammack", "Non Fiction");
        testLibrary.addBookByLibrarian("November 9", "Colleen Hoover", "Romance");
        assertEquals(13, testLibrary.getListOfBooks().size());
        assertEquals("November 9", testLibrary.getListOfBooks().get(12).getBookName());
        assertEquals("Colleen Hoover", testLibrary.getListOfBooks().get(12).getAuthor());
        assertEquals("Romance", testLibrary.getListOfBooks().get(12).getGenre());
    }
}




