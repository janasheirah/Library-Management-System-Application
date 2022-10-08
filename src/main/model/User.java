package model;

import java.util.ArrayList;
import java.util.List;

// Represents a user account
public class User {

    private List<Book> checkOutCart;
    private List<String> checkOutCartByName;
    private String name;
    private Book bk;

    // EFFECTS: constructs a user with a name and an empty checkout cart
    public User(String name) {
        this.name = name;
        checkOutCart = new ArrayList<>();
        checkOutCartByName = new ArrayList<>();
    }

    // getters
    // EFFECTS: returns user's account name
    public String getName() {
        return name;
    }

    // EFFECTS: returns the use's checkout cart
    public List<Book> getCheckOutCart() {
        return checkOutCart;
    }

    // MODIFIES: this
    // EFFECTS: returns the user checkout cart with book names
    public List<String> getCheckOutCartByName() {
        for (Book book : checkOutCart) {
            checkOutCartByName.add(book.getBookName());
        }
        return getCheckOutCartByName();
    }

    // EFFECTS: adds book to user's cart if it is available to borrow and sets the loan status to true
    public List<Book> checkOutBook(Library lib, Book bk) {
        if (bk.availableToBorrow(lib, bk)) {
            bk.setLoanStatus(true);
            checkOutCart.add(bk);
        }
        return checkOutCart;
    }

    // MODIFIES: this
    // EFFECTS: returns true if the book was on loan and sets its loan status to false
    // otherwise, return false
    public boolean returnBook(Book bk) {
        if (bk.onLoan()) {
            bk.setLoanStatus(false);
            return true;
        } else {
            return false;
        }
    }

}
