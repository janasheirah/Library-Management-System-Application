package model;

import java.util.ArrayList;
import java.util.List;

// Represents a user account
public class User {

    private List<Book> checkOutCart;
    private String name;
    private Book bk;

    // EFFECTS: constructs a user with a name and an empty checkout cart
    public User(String name) {
        this.name = name;
        checkOutCart = new ArrayList<>();
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

    // EFFECTS: adds book to user's cart if it is available to borrow and sets the loan status to true
    public boolean checkOutBook(Library lib, Book bk) {
        if (bk.availableToBorrow(lib, bk)) {
            bk.setLoanStatus(true);
            checkOutCart.add(bk);
            return true;
        }
        return false;
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
