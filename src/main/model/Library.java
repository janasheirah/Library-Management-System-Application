package model;

import java.util.ArrayList;
import java.util.List;

// Represents books available in the library
public class Library {

    public List<Book> listOfBooks;
    private String name;

    // EFFECTS: constructs a library with a name and an empty list of books
    public Library(String name) {
        this.name = name;
        listOfBooks = new ArrayList<>();
    }

    // EFFECTS: returns the list of books
    public List<Book> getListOfBooks() {
        return listOfBooks;
    }

    // EFFECTS: returns books available according to genre searched for by user
    public List<Book> searchForBook(String genre) {
        ArrayList<Book> booksByGenre = new ArrayList<>();
        for (Book book : listOfBooks) {
            if (book.getGenre().contains(genre)) {
                booksByGenre.add(book);
            }
        }
        return booksByGenre;

    }

    // REQUIRES: book != null
    // EFFECTS: returns true if given book is in list of books in library
    public boolean inStock(Book book) {
        return listOfBooks.contains(book);
    }

}
