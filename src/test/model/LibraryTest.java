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
    public void testAddBook() {
        testLibrary.addBook("To Kill A Mocking Bird", "Harper Lee", "Fantasy");
        assertEquals(12, testLibrary.getListOfBooks().size());
        assertEquals("To Kill A Mocking Bird", testLibrary.getListOfBooks().get(11).getBookName());
    }

    @Test
    public void testAddMultipleBooks() {
        testLibrary.addBook("To Kill A Mocking Bird", "Harper Lee", "Fiction");
        testLibrary.addBook("Who moved my cheese?", "Spencer Johnson", "Non-Fiction");
        assertEquals(13, testLibrary.getListOfBooks().size());
        assertEquals("Who moved my cheese?", testLibrary.getListOfBooks().get(12).getBookName());
    }

    @Test
    public void testSearchByTitle() {
        assertEquals(book1, testLibrary.searchForBookByTitle("The Book Thief"));
        // how to test return null?
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
}




