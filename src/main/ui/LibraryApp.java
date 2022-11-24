package ui;

import model.Book;
import model.Librarian;
import model.Library;
import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

// Library application
public class LibraryApp {

    private static final String JSON_STORE = "./data/checkoutCart.json";
    private static final String JSON_STORE2 = "./data/newBooks.json";
    private User user1;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter2;
    private JsonReader jsonReader2;
    Library vpl = new Library("Vancouver Public Library");

    // EFFECTS: runs the library application
    // citation: TellerApp() from CPSC 210 repo
    public LibraryApp() throws FileNotFoundException {
        System.out.println("Hello, Welcome to the Vancouver Public Library!");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter2 = new JsonWriter(JSON_STORE2);
        jsonReader2 = new JsonReader(JSON_STORE2);
        runLibraryUser();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runLibraryUser() {
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
        } else if (command.equals("a")) {
            doAdd();
        } else if (command.equals("save")) {
            saveCheckoutCart();
        } else if (command.equals("load")) {
            loadCheckoutCart();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void init() {
        user1 = new User("User's Checkout Cart");
//        librarian = new Librarian("Librarian1");
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
        System.out.println("\ta -> add book to list of books");
        System.out.println("\tsave -> save user changes to file");
        System.out.println("\tload -> load user history from file");
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

    // EFFECTS: conducts a search by genre of book
    private void doSearch() {
        System.out.print("Enter one of the following genre: Fantasy, Non Fiction, Romance, Mystery, Biography\n");
        String genre = input.next();

        if (vpl.searchForBook(genre).isEmpty()) {
            System.out.println("Sorry, there are no books available for this genre\n");
        } else {
            System.out.println("These are the available books for " + genre + ": " + vpl.searchForBook(genre));
        }
    }

    // MODIFIES: this
    // EFFECTS: conducts a search by book title and checks out book if it is availble to be checked out
    private void doSearchByName() {
        System.out.print("Enter the title of the book you want to checkout\n");
        String title = input.next();

        if (vpl.getListOfTitles().contains(title)) {
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

    // EFFECTS: conducts return book operation
    private void doReturn() {
        System.out.print("Enter the title of the book you want to return\n");
        String title = input.next();

        if (vpl.getListOfTitles().contains(title)) {
            if (user1.getCheckOutCartByTitle().contains(title)) {
                vpl.searchForBookByTitle(title);
                if (user1.returnBook(vpl.getBookVariable())) {
                    System.out.println("This book is now returned: " + vpl.getBookVariable().getBookName() + " by "
                            + vpl.getBookVariable().getAuthor());
                }
            } else {
                System.out.println("This book was not on loan so not valid to be returned\n");
            }
        } else {
            System.out.println("Sorry, this book is not available in this library.");
        }
    }

    // MODIFIES: this
    // EFFECTS: conducts adding book method
    public void doAdd() {
        System.out.println("Enter the name of the book you want to add \n");
        String name = input.next();
        System.out.println("Enter the name of the author of the book \n");
        String author = input.next();
        System.out.println("Enter the type of genre for this book \n");
        String genre = input.next();

        vpl.addBookByLibrarian(name, author, genre);
        System.out.println("This book has been added to the list of books successfully!");
    }

    // EFFECTS: saves the checkout cart and new books added to file
    private void saveCheckoutCart() {
        try {
            jsonWriter.open();
            jsonWriter2.open();
            jsonWriter.write(user1);
            jsonWriter2.writeAddBook(vpl);
            jsonWriter.close();
            jsonWriter2.close();
            System.out.println("Saved " + user1.getName() + " to " + JSON_STORE);
            System.out.println("Saved list of books in " + vpl.getName() + " to " + JSON_STORE2);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads user's checkout cart and new books added from file
    private void loadCheckoutCart() {
        try {
            user1 = jsonReader.read();
            vpl = jsonReader2.readAddBook();
            System.out.println("Loaded " + user1.getName() + " from " + JSON_STORE);
            System.out.println("Loaded list of books in " + vpl.getName() + " from " + JSON_STORE2);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}



