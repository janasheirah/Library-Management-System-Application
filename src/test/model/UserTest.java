package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class UserTest {

    private Book book1;
    private Book book2;
    private Book book3;
    private Book book4;
    private Book book5;
    private Library lib;
    private User user1;

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
        user1 = new User("Jana");
    }

    @Test
    public void testConstructor() {
        assertEquals("Jana", user1.getName());
        assertEquals(0, user1.getCheckOutCart().size());
    }

    @Test
    public void testCheckOutBook() {
        assertTrue(user1.checkOutBook(lib, book1));
        assertEquals(1, user1.getCheckOutCart().size());
        assertEquals(book1, user1.getCheckOutCart().get(0));
    }

    @Test
    public void testCheckOutMultipleBooks() {
        assertTrue(user1.checkOutBook(lib, book1));
        assertTrue(user1.checkOutBook(lib, book2));
        assertFalse(user1.checkOutBook(lib, book3)); // book cannot be added
        assertTrue(user1.checkOutBook(lib, book4));
        assertEquals(3, user1.getCheckOutCart().size());
        assertEquals(book1, user1.getCheckOutCart().get(0));
    }

    @Test
    public void testReturnBook() {
        book2.setLoanStatus(true);
        assertTrue(user1.returnBook(book2));
        assertFalse(book2.onLoan()); // check that loan status is now false
        assertFalse(user1.returnBook(book1)); // book was never on loam
    }

    @Test
    public void testReturnMultipleBooks() {
        user1.checkOutBook(lib, book1);
        user1.checkOutBook(lib, book2);
        assertTrue(user1.returnBook(book1));
        assertTrue(user1.returnBook(book2));
        assertFalse(user1.returnBook(book3)); // was never checked out
        assertFalse(book1.onLoan()); // check that loan status is now false
        assertFalse(book2.onLoan());
    }

    @Test
    public void testGetCheckOutCartByTitle() {
        user1.checkOutBook(lib, book1);
        user1.checkOutBook(lib, book2);
        assertEquals(2, user1.getCheckOutCartByTitle().size());
        assertEquals("Harry Potter", user1.getCheckOutCartByTitle().get(0));
    }


}

