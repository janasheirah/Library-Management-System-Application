package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// Represents a user account
public class User implements Writable {

    private List<Book> checkOutCart;
    private String name;

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

    // MODIFIES: this
    // EFFECTS: adds book to this user's checkout chart
    public void addBookToCart(Book bk) {
        checkOutCart.add(bk);
    }

    // EFFECTS: returns the titles of the books in the checkout cart
    public List<String> getCheckOutCartByTitle() {
        List<String> listOfTitles = new LinkedList<>();

        for (Book b : checkOutCart) {
            listOfTitles.add(b.getBookName());
        }
        return listOfTitles;
    }

    // REQUIRES: bk != null, bk is in listOfBooks
    // MODIFIES: this, Book
    // EFFECTS: returns true if the book is available to borrow, adds book to user's cart and sets the loan status to
    // true, returns false otherwise
    public boolean checkOutBook(Library lib, Book bk) {
        if (bk.availableToBorrow(lib, bk)) {
            bk.setLoanStatus(true);
            addBookToCart(bk);
            EventLog.getInstance().logEvent(new Event("Checked out book: " + bk.getBookName()));
            return true;
        }
        return false;
    }

    // REQUIRES: bk != null
    // MODIFIES: this, Book
    // EFFECTS: returns true if the book was on loan and sets its loan status to false
    // otherwise, returns false
    public boolean returnBook(Book bk) {
        if (bk.onLoan()) {
            bk.setLoanStatus(false);
            checkOutCart.remove(bk);
            EventLog.getInstance().logEvent(new Event("Returned book: " + bk.getBookName()));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("books", booksToJson());
        return json;
    }

    // EFFECTS: returns books in user's checkout cart as a JSON array
    private JSONArray booksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Book b : checkOutCart) {
            jsonArray.put(b.toJson());
        }

        return jsonArray;
    }

}
