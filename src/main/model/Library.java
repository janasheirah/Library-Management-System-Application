package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// Represents library with list of books available
public class Library implements Writable {

    private List<Book> listOfBooks;
    private String name;
    private Book bookVariable;
    private List<Book> booksAddedByLibrarian;

    // EFFECTS: constructs a library with a name and a list of books
    public Library(String name) {
        this.name = name;
        booksAddedByLibrarian = new ArrayList<>();
        listOfBooks = new ArrayList<>();
        Book hungerGames = new Book("The Hunger Games", "Suzanne Collins", "Fantasy");
        Book harryPotter = new Book("Harry Potter", "J.K. Rowling", "Fantasy");
        listOfBooks.add(hungerGames);
        listOfBooks.add(harryPotter);
        Book theFaultInOurStars = new Book("The Fault In Our Stars", "John Green", "Romance");
        Book theNoteBook = new Book("The Notebook", "Nicholas Sparks", "Romance");
        listOfBooks.add(theNoteBook);
        listOfBooks.add(theFaultInOurStars);
        Book theSilentPatient = new Book("The Silent Patient", "Alex Michaelides", "Mystery");
        Book goneGirl = new Book("Gone Girl", "Gillian Flynn", "Mystery");
        listOfBooks.add(theSilentPatient);
        listOfBooks.add(goneGirl);
        Book inColdBlood = new Book("In Cold Blood", "Truman Capote", "Non Fiction");
        Book educated = new Book("Educated", "Tara Westover", "Non Fiction");
        listOfBooks.add(inColdBlood);
        listOfBooks.add(educated);
        Book steveJobs = new Book("Steve Jobs", "Walter Isaacson", "Biography");
        Book beautifulMind = new Book("A Beautiful Mind", "Sylvia Nasar", "Biography");
        listOfBooks.add(steveJobs);
        listOfBooks.add(beautifulMind);
    }

    // EFFECTS: returns name of library
    public String getName() {
        return name;
    }

    // EFFECTS: returns the list of new books added by librarian
    public List<Book> getListOfNewBooks() {
        return booksAddedByLibrarian;
    }

    // EFFECTS: returns the list of books
    public List<Book> getListOfBooks() {
        return listOfBooks;
    }

    // EFFECTS: returns the list of book titles available in the library
    public List<String> getListOfTitles() {
        List<String> listOfTitles = new LinkedList<>();

        for (Book b : listOfBooks) {
            listOfTitles.add(b.getBookName());
        }
        return listOfTitles;
    }

    // EFFECTS: returns the book object from searchForBook method
    public Book getBookVariable() {
        return bookVariable;
    }

    // REQUIRES: genre is one of: Fantasy, Non Fiction, Romance, Mystery, Biography
    // EFFECTS: returns books available according to genre searched for by user
    // account for books added by use with other genres
    public List<String> searchForBook(String genre) {
        ArrayList<String> booksByGenre = new ArrayList<>();
        for (Book book : listOfBooks) {
            if (book.getGenre().contains(genre)) {
                booksByGenre.add(book.getBookName());
            }
        }
        EventLog.getInstance().logEvent(new Event("Searched for genre " + genre));
        return booksByGenre;
    }

    // REQUIRES: title is in listOfTitles
    // EFFECTS: returns books available according to title searched for by user
    public Book searchForBookByTitle(String name) {
        for (Book book : listOfBooks) {
            if (book.getBookName().equals(name)) {
                bookVariable = book;
                return book;
            }
        }
        return null;
    }

    // REQUIRES: book != null
    // EFFECTS: returns true if given book is in list of books in library
    public boolean inStock(Book book) {
        return listOfBooks.contains(book);
    }

    // MODIFIES: this
    // EFFECTS: adds a new book to library's list of books
    public void addBookByLibrarian(String bookName, String author, String genre) {
        Book book = new Book(bookName, author, genre);
        addBook(book);
    }

    // MODIFIES: this
    // EFFECTS: adds book to library's list of books and to list of new books added
    public void addBook(Book book) {
        booksAddedByLibrarian.add(book);
        listOfBooks.add(book);
        EventLog.getInstance().logEvent(new Event("Added book: " + book.getBookName()));
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("new books", booksAddedToJson());
        return json;
    }

    // EFFECTS: returns books in user's checkout cart as a JSON array
    private JSONArray booksAddedToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Book b : booksAddedByLibrarian) {
            jsonArray.put(b.toJson());
        }

        return jsonArray;
    }

}
