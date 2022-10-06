package model;

import java.util.ArrayList;
import java.util.List;

// Represents a book having a name, author, genre and if it is on loan
public class Book {

    private String bookName; // title of the book
    private String author;
    private String genre; // types: romance, fiction, non fiction, mystery, biography
    private boolean isOnLoan;

    // EFFECTS: constructs a book object with title, author, genre, not on loan and an empty checkout cart
    public Book(String bookName, String author, String genre) {
        this.bookName = bookName;
        this.author = author;
        this.genre = genre;
        isOnLoan = false;
    }

    // getters

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    // EFFECTS: returns the value of isOnLoan
    public boolean onLoan() {
        return isOnLoan;
    }

    // setters
    // MODIFIES: this
    // EFFECTS: sets the status of isOnLoan to parameter
    public void setLoanStatus(boolean b) {
        isOnLoan = b;
    }

    // REQUIRES: book != null
    // MODIFIES: this
    // EFFECTS: returns true if the book is in stock and available to borrow
    public boolean availableToBorrow(Library library, Book book) {
        return library.inStock(book) && !book.onLoan();
    }

}
