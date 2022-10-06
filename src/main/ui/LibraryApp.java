package ui;


import model.Book;
import model.Library;

import java.util.List;
import java.util.Scanner;

// Library application
public class LibraryApp {

    List<Book> books;

    Scanner scan = new Scanner(System.in);

    Book hungerGames = new Book("The Hunger Games", "Suzanne Collins", "Fiction");
    Book harryPotter = new Book("Harry Potter", "J.K. Rowling", "Fiction");
    Book prideAndPrejudice = new Book("Pride And Prejudice", "Jane Austen", "Fiction");
    Book twilight = new Book("Twilight", "Stephanie Meyer", "Fiction");
    Book narnia = new Book("The Chronicles Of Narnia", "C.S. Lewis", "Fiction");
    Library ikb = new Library("IKB");

    //        ikb.addBook(hungerGames);
//        ikb.add(harryPotter);
//        ikb.add(prideAndPrejudice);
//        ikb.add(twilight);
//        ikb.add(narnia);


//    // MODIFIES: this
//    // EFFECTS: processes user command
//    private void processCommand(String command) {
//        if (command.equals("s")) {
//            doSearch();
//        } else {
//            System.out.println("Selection not valid, Please try again.");
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: initializes accounts
//    private void init() {
//
//    }
//
//    // EFFECTS: displays menu of options to user
//    private void displayMenu() {
//        System.out.println("Select from:");
//        System.out.println("\ts -> Search");
//        System.out.println("quit");
//    }
//
//    // MODIFIES: this
//    // EFFECTS: searches through books by genre
//    private void doSearch() {
//        Library selected = selectLibrary();
//        System.out.println("Enter one of the following genre: fiction, non fiction, romance, ");
//        String inputGenre = input.nextLine();
//
//        if(inputGenre.equals("Fiction")) {
//            selected.searchForBook(inputGenre);
//        } else {
//            System.out.println("Sorry, this genre is not available");
//        }
//    }
//
}
