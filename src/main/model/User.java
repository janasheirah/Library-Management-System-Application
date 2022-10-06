package model;

import java.util.ArrayList;
import java.util.List;

// Represents a user account with an empty Checkout Cart
public class User {

    private List<Book> checkOutCart;
    private String name;

    // EFFECTS: constructs a user with an account name and an empty checkout cart
    public User(String name) {
        this.name = name;
        checkOutCart = new ArrayList<>();
    }

    // getter
    // EFFECTS: returns user's account name
    public String getName() {
        return name;
    }


    // EFFECTS: adds book to user's cart if it is available to borrow and sets the loan status to true
    public List<Book> checkOutBook(Library lib, Book bk) {
        if (bk.availableToBorrow(lib, bk)) {
            bk.setLoanStatus(true);
            checkOutCart.add(bk);
        }
        return checkOutCart;
    }

    // EFFECTS: returns the use's checkout cart
    public List<Book> getCheckOutCart() {
        return checkOutCart;
    }

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
