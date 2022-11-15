package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    private Book book1;
    private Book book2;
    private Book book3;
    private Book book4;
    private Book book5;
    private Library lib;

    @BeforeEach
    public void setup() {
        lib = new Library("IKB");
        book1 = new Book("Harry Potter", "J.K. Rowling", "Fiction");
        book2 = new Book("The Book Thief", "Markus Zusak", "Fiction");
        book3 = new Book("Book Of Proofs", "Richard Hammack", "Non fiction");
        book4 = new Book("It Ends with us", "Colleen Hoover", "Romance");
        book5 = new Book("Who moved my cheese?", "Spencer Johnson", "Fiction");
        lib.getListOfBooks().add(book1);
        lib.getListOfBooks().add(book2);
        lib.getListOfBooks().add(book4);
    }

    @Test
    public void testConstructor() {
        assertEquals("Harry Potter", book1.getBookName());
        assertEquals("J.K. Rowling", book1.getAuthor());
        assertEquals("Fiction", book1.getGenre());
        assertFalse(book1.onLoan());
    }

    @Test
    public void testSetLoanStatus() {
        book1.setLoanStatus(false);
        assertFalse(book1.onLoan());
        book2.setLoanStatus(true);
        assertTrue(book2.onLoan());
    }

    @Test
    public void testAvailableToBorrow() {
        assertTrue(book1.availableToBorrow(lib, book1));
        assertFalse(book3.availableToBorrow(lib, book3)); // not in stock but not on loan
        book4.setLoanStatus(true);
        assertFalse(book4.availableToBorrow(lib, book4)); // in stock but is on loan
        book5.setLoanStatus(true);
        assertFalse(book5.availableToBorrow(lib, book5)); // not in stock and on loan
    }

    @Test
    public void testToString() {
        assertEquals("Harry Potter by J.K. Rowling", book1.toString());
    }

}