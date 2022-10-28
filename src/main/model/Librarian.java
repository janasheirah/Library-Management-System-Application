package model;

import org.json.JSONArray;
import org.json.JSONObject;

// Represents librarian/managers of library
public class Librarian {

    private String name;
    private Library lib;

    // EFFECTS: constructs a librarian with name of librarian
    public Librarian(String name) {
        this.name = name;
    }

    // EFFECTS: returns name of librarian
    public String getName() {
        return name;
    }

//    // MODIFIES: Library
//    // EFFECTS: adds a new book to library's list of books
//    public void addBook(String bookName, String author, String genre, Library lib) {
//        Book book = new Book(bookName, author, genre);
//        lib.getListOfBooks().add(book);
//    }
}
