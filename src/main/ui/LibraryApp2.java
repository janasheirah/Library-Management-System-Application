//package ui;
//
//
//import model.Book;
//import model.Librarian;
//import model.Library;
//import model.User;
//
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Scanner;
//
//// Library application
//public class LibraryApp2 {
//
//    private User user1;
//    private Librarian librarian;
//    private Scanner input;
//    Library vpl = new Library("Vancouver Public Library");
//
//    // EFFECTS: runs the library application
//    // citation: TellerApp() from CPSC 210 repo
//    public LibraryApp2() {
//        System.out.println("Hello, Welcome to the Vancouver Public Library!");
//        runMenu();
//    }
//
//    private void displayFirstMenu() {
//        System.out.println("\n Please select the option that describes you:");
//        System.out.println("\n1: User");
//        System.out.println("\n2: Librarian");
//    }
//
//    private void runMenu() {
//        String input = null;
//        displayFirstMenu();
//
//        init();
//
//        input = this.input.next();
//        input = input.toLowerCase();
//
//        if (input.equals("1")) {
//            runLibraryUser();
//        } else if (input.equals("2")) {
//            runLibrarianMenu();
//        } else {
//            System.out.println("This selection is not valid");
//        }
//    }
//
//    private void displayLibrarianMenu() {
//        System.out.println("\nSelect from:");
//        System.out.println("\ta -> Add book");
//        System.out.println("\tm -> return to main menu");
//    }
//
//    private void runLibrarianMenu() {
//        boolean keepGoing = true;
//        String command = null;
//
//        init();
//
//        while (keepGoing) {
//            displayLibrarianMenu();
//            command = input.next();
//            command = command.toLowerCase();
//
//            if (command.equals("q")) {
//                keepGoing = false;
//            } else {
//                processLibrarianCommand(command);
//            }
//        }
//
//        System.out.println("\nGoodbye! Thank you for visiting our library.");
//    }
////        input = this.input.next();
////        input = input.toLowerCase();
////
////        if (input.equals("a")) {
////            doAdd();
////        } else if (input.equals("m")) {
////            mainMenu();
////        }
//
//    // MODIFIES: this
//    // EFFECTS: processes user command
//    private void processLibrarianCommand(String command) {
//        if (command.equals("a")) {
//            doAdd();
//        } else if (command.equals("m")) {
//            mainMenu();
//        } else {
//            System.out.println("Selection not valid...");
//        }
//    }
//
//    private void mainMenu() {
//        displayFirstMenu();
//    }
//
//
//    // MODIFIES: this
//    // EFFECTS: processes user input
//    private void runLibraryUser() {
//        boolean keepGoing = true;
//        String command = null;
//
//        init();
//
//        while (keepGoing) {
//            displayMenu();
//            command = input.next();
//            command = command.toLowerCase();
//
//            if (command.equals("q")) {
//                keepGoing = false;
//            } else {
//                processCommand(command);
//            }
//        }
//
//        System.out.println("\nGoodbye! Thank you for visiting our library.");
//    }
//
//    // MODIFIES: this
//    // EFFECTS: processes user command
//    private void processCommand(String command) {
//        if (command.equals("l")) {
//            doPrint();
//        } else if (command.equals("s")) {
//            doSearch();
//        } else if (command.equals("c")) {
//            doSearchByName();
//        } else if (command.equals("v")) {
//            doView();
//        } else if (command.equals("r")) {
//            doReturn();
//        } else if (command.equals("a")) {
//            doAdd();
//        } else {
//            System.out.println("Selection not valid...");
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: initializes accounts
//    private void init() {
//        user1 = new User("Joe");
//        librarian = new Librarian("Jan Doe");
//        input = new Scanner(System.in);
//        input.useDelimiter("\n");
//    }
//
//    // EFFECTS: displays menu of options to user
//    private void displayMenu() {
//        System.out.println("\nSelect from:");
//        System.out.println("\tl -> view list of books in library");
//        System.out.println("\ts -> search for book by genre");
//        System.out.println("\tc -> checkout book by searching book title");
//        System.out.println("\tv -> view books in cart");
//        System.out.println("\tr -> return book");
//        System.out.println("\ta -> add book to list of books");
//        System.out.println("\tq -> quit");
//    }
//
//    // EFFECTS: returns the list of books available in the library
//    public void doPrint() {
//        List<Book> totalCatalogue = new LinkedList<>();
//        totalCatalogue.addAll(vpl.getListOfBooks());
//
//        System.out.println("Here's the catalogue: \n");
//        for (Book b : totalCatalogue) {
//            System.out.println("'" + b.getBookName() + "'" + " by " + b.getAuthor());
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: conducts a search by genre of book
//    private void doSearch() {
//        System.out.print("Enter one of the following genre: Fantasy, Non Fiction, Romance, Mystery, Biography\n");
//        String genre = input.next();
//
//        if (genre.equals("Fantasy") || genre.equals("Non Fiction") || genre.equals("Romance") || genre.equals("Mystery")
//                || genre.equals("Biography")) {
//            System.out.println("These are the available books for " + genre + ": " + vpl.searchForBook(genre));
//        } else {
//            System.out.println("This genre is not available in this library\n");
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: conducts a search by book title
//    private void doSearchByName() {
//        System.out.print("Enter the title of the book you want to checkout\n");
//        String title = input.next();
//
//        if (vpl.getListOfTitles().contains(title)) {
//            vpl.searchForBookByTitle(title);
//            if (user1.checkOutBook(vpl, vpl.getBookVariable())) {
//                System.out.println("This book is now checked out: " + vpl.getBookVariable().getBookName() + " by "
//                        + vpl.getBookVariable().getAuthor());
//            } else {
//                System.out.println("This book is currently on loan and cannot be checked out.");
//            }
//        } else {
//            System.out.println("This book is not available in this library\n");
//        }
//    }
//
//    // EFFECTS: prints the list of book names and author for books in checkout cart
//    public void doView() {
//        List<Book> totalCheckOutCart = new LinkedList<>();
//
//        totalCheckOutCart.addAll(user1.getCheckOutCart());
//        System.out.println("Here's your checkout cart: \n");
//        for (Book b : totalCheckOutCart) {
//            System.out.println("'" + b.getBookName() + "'" + " by " + b.getAuthor());
//        }
//    }
//
//    // EFFECTS: conducts return book operation
//    private void doReturn() {
//        System.out.print("Enter the title of the book you want to return\n");
//        String title = input.next();
//
//        if (vpl.getListOfTitles().contains(title)) {
//            if (user1.getCheckOutCartByTitle().contains(title)) {
//                vpl.searchForBookByTitle(title);
//                if (user1.returnBook(vpl.getBookVariable())) {
//                    System.out.println("This book is now returned: " + vpl.getBookVariable().getBookName() + " by "
//                            + vpl.getBookVariable().getAuthor());
//                }
//            } else {
//                System.out.println("This book was not on loan so not valid to be returned\n");
//            }
//        } else {
//            System.out.println("Sorry, this book is not available in this library.");
//        }
//    }
//
//    // EFFECTS: conducts adding book method
//    public void doAdd() {
//        System.out.println("Enter the name of the book you want to add \n");
//        String name = input.next();
//        System.out.println("Enter the name of the author of the book \n");
//        String author = input.next();
//        System.out.println("Enter the type of genre for this book \n");
//        String genre = input.next();
//
//        librarian.addBook(name, author, genre, vpl);
//        System.out.println("This book has been added to the list of books successfully!");
//    }
//
//}
//
//
//
