package ui;


import model.Book;
import model.Library;
import model.User;

import java.util.List;
import java.util.Scanner;

// Library application
public class LibraryApp {

    private User user1;
    private Scanner input;
    Library vpl = new Library("Vancouver Public Library");
    private List<Book> listOfBooks;
    private Book bk;
    private List<String> listOfTitles;

//    public void addTheBooks() {
//        Book hungerGames = new Book("The Hunger Games", "Suzanne Collins", "Fiction");
//        Book harryPotter = new Book("Harry Potter", "J.K. Rowling", "Fiction");
//        listOfBooks.add(hungerGames);
//        listOfBooks.add(harryPotter);
//    }

    // EFFECTS: runs the library application
    public LibraryApp() {
        runTeller();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runTeller() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye! Thank you for visiting our library.");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("l")) {
            doPrint();
        } else if (command.equals("s")) {
            doSearch();
        } else if (command.equals("sn")) {
            doSearchByName();
        } else if (command.equals("v")) {
            doView();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void init() {
        user1 = new User("Joe");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tl -> view list of books in library");
        System.out.println("\ts -> search for book by genre");
        System.out.println("\tsn -> checkout book by searching book title");
        System.out.println("\tv -> view books in cart");
        System.out.println("\tr -> return book");
        System.out.println("\tq -> quit");
    }

    private void doPrint() {
        vpl.getListOfBooksByName();
    }

    // MODIFIES: this
    // EFFECTS: conducts a search by genre
    private void doSearch() {
        System.out.print("Enter one of the following genre: Fiction, Non Fiction, Romance, Mystery, Biography\n");
        String genre = input.next();

        if (genre.equals("Fiction") || genre.equals("Non Fiction") || genre.equals("Romance") || genre.equals("Mystery")
                || genre.equals("Biography")) {
            System.out.println("These are the available books for " + genre + ": " + vpl.searchForBook(genre));
        } else {
            System.out.println("This genre is not available in this library\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: conducts a search by book title
    private void doSearchByName() {
        System.out.print("Enter the title of the book you want to checkout");
        String title = input.next();

        if (title != null) { // if title in list of titles, if book is available to borrow
            vpl.searchForBookByTitle(title);
            System.out.println("This book is now checked out " + vpl.getBookVariable().getBookName() + " by "
                    + vpl.getBookVariable().getAuthor());
            user1.checkOutBook(vpl, vpl.getBookVariable());
        } else {
            System.out.println("This book is not available in this library\n");
        }
    }

    private void doView() {
        System.out.println(user1.getCheckOutCartByName());
    }
}

