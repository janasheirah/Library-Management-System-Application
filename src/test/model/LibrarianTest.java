package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LibrarianTest {

    private Librarian librarian;

    @BeforeEach
    public void setup() {
        librarian = new Librarian("Jane Doe");
    }

    @Test
    public void testConstructor() {
        assertEquals("Jane Doe", librarian.getName());
    }
}
