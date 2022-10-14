package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// Represents library with list of books available
public class Library {

    private List<Book> listOfBooks;
    private String name;
    private Book bookVariable;

    // EFFECTS: constructs a library with a name and a list of books
    public Library(String name) {
        this.name = name;
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
    public List<String> searchForBook(String genre) {
        ArrayList<String> booksByGenre = new ArrayList<>();
        for (Book book : listOfBooks) {
            if (book.getGenre().contains(genre)) {
                booksByGenre.add(book.getBookName());
            }
        }
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

}
