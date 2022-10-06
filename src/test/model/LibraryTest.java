package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    private Book book1;
    private Book book2;
    private Book book3;
    private Book book4;
    private Book book5;
    private Library testLibrary;
    private List<Book> listOfBooks;

    @BeforeEach
    public void setup() {
        testLibrary = new Library("Vancouver Public Library");
        listOfBooks = new ArrayList<>();
        book1 = new Book("Harry Potter", "J.K. Rowling", "Fiction");
        book2 = new Book("The Book Thief", "Markus Zusak", "Fiction");
        book3 = new Book("Book Of Proofs", "Richard hammack", "Non fiction");
        book4 = new Book("It Ends with us", "Colleen Hoover", "Romance");
        book5 = new Book("Who moved my cheese?", "Spencer Johnson", "Fiction");
        testLibrary.getListOfBooks().add(book1);
        testLibrary.getListOfBooks().add(book2);
        testLibrary.getListOfBooks().add(book4);
    }

    @Test
    public void testSearchForBookFiction() {
        assertEquals(2, testLibrary.searchForBook("Fiction").size());
        assertEquals(book1, testLibrary.searchForBook("Fiction").get(0));
        assertEquals(book2, testLibrary.searchForBook("Fiction").get(1));
    }

    @Test
    public void testSearchForBookNonFiction() {
        assertEquals(1, testLibrary.searchForBook("Romance").size());
        assertEquals(book4, testLibrary.searchForBook("Romance").get(0));
    }

    @Test
    public void testInStock() {
        assertTrue(testLibrary.inStock(book1));
        assertFalse(testLibrary.inStock(book3));
    }
}


