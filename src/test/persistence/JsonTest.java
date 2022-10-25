package persistence;

import model.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkBook(String name, String author, String genre, Book book) {
        assertEquals(name, book.getBookName());
        assertEquals(author, book.getAuthor());
        assertEquals(genre, book.getGenre());
    }
}

