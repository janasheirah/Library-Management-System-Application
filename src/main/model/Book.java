package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a book having a name, author, genre and if it is on loan
public class Book implements Writable {

    private String bookName; // title of the book
    private String author;
    private String genre;
    // types: fantasy, non fiction, romance, mystery, biography
    private boolean isOnLoan;

    // MODIFIES: this
    // EFFECTS: constructs a book object with title, author, genre, and sets it to not be on loan
    public Book(String bookName, String author, String genre) {
        this.bookName = bookName;
        this.author = author;
        this.genre = genre;
        isOnLoan = false;
    }

    // getters

    // EFFECTS: returns book name
    public String getBookName() {
        return bookName;
    }

    // EFFECTS: returns book author
    public String getAuthor() {
        return author;
    }

    // EFFECTS: returns book genre
    public String getGenre() {
        return genre;
    }

    // EFFECTS: returns whether the book is on loan
    public boolean onLoan() {
        return isOnLoan;
    }

    // setters
    // MODIFIES: this
    // EFFECTS: sets the status of isOnLoan to parameter defined
    public void setLoanStatus(boolean b) {
        isOnLoan = b;
    }

    // REQUIRES: book, library != null
    // EFFECTS: returns true if the book is in stock and available to borrow
    public boolean availableToBorrow(Library library, Book book) {
        return library.inStock(book) && !book.onLoan();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", bookName);
        json.put("author", author);
        json.put("genre", genre);
        return json;
    }
}
