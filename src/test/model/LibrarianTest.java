package model;

import org.junit.jupiter.api.BeforeEach;

public class LibrarianTest {

    private Librarian librarian;
    private Library library;

    @BeforeEach
    public void setup() {
        librarian = new Librarian("Jane Doe");
    }
}
