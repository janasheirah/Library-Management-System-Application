package ui;


import model.Book;
import model.Library;
import model.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

// Library application
public class LibraryApp {

    private User user1;
    private Scanner input;
    Library vpl = new Library("Vancouver Public Library");

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
        } else if (command.equals("c")) {
            doSearchByName();
        } else if (command.equals("v")) {
            doView();
        } else if (command.equals("r")) {
            doReturn();
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
        System.out.println("\tc -> checkout book by searching book title");
        System.out.println("\tv -> view books in cart");
        System.out.println("\tr -> return book");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: returns the list of books available in the library
    public void doPrint() {
        List<Book> totalCatalogue = new LinkedList<>();
        totalCatalogue.addAll(vpl.getListOfBooks());

        System.out.println("Here's the catalogue: \n");
        for (Book b : totalCatalogue) {
            System.out.println("'" + b.getBookName() + "'" + " by " + b.getAuthor());
        }
    }

    // MODIFIES: this
    // EFFECTS: conducts a search by genre
    private void doSearch() {
        System.out.print("Enter one of the following genre: Fantasy, Non Fiction, Romance, Mystery, Biography\n");
        String genre = input.next();

        if (genre.equals("Fantasy") || genre.equals("Non Fiction") || genre.equals("Romance") || genre.equals("Mystery")
                || genre.equals("Biography")) {
            System.out.println("These are the available books for " + genre + ": " + vpl.searchForBook(genre));
        } else {
            System.out.println("This genre is not available in this library\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: conducts a search by book title
    private void doSearchByName() {
        System.out.print("Enter the title of the book you want to checkout\n");
        String title = input.next();

        if (title != null) { // if title in list of titles, if book is available to borrow
            vpl.searchForBookByTitle(title);
            if (user1.checkOutBook(vpl, vpl.getBookVariable())) {
                System.out.println("This book is now checked out: " + vpl.getBookVariable().getBookName() + " by "
                        + vpl.getBookVariable().getAuthor());
            } else {
                System.out.println("This book is currently on loan and cannot be checked out.");
            }
        } else {
            System.out.println("This book is not available in this library\n");
        }
    }

    // EFFECTS: prints the list of book names and author for books in checkout cart
    public void doView() {
        List<Book> totalCheckOutCart = new LinkedList<>();
        totalCheckOutCart.addAll(user1.getCheckOutCart());

        System.out.println("Here's your checkout cart: \n");
        for (Book b : totalCheckOutCart) {
            System.out.println("'" + b.getBookName() + "'" + " by " + b.getAuthor());
        }
    }

    // EFFECTS: conducts return book transaction
    private void doReturn() {
        System.out.print("Enter the title of the book you want to return\n");
        String title = input.next();

        if (title != null) { // if book was on loan by user or title in brdo
            vpl.searchForBookByTitle(title);
            if (user1.returnBook(vpl.getBookVariable())) {
                System.out.println("This book is now returned: " + vpl.getBookVariable().getBookName() + " by "
                        + vpl.getBookVariable().getAuthor());
            } else {
                System.out.println("This book was not on loan and cannot be returned\n");
            }
        }
    }
}


