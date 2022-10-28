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

}
