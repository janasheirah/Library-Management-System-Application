package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Book;
import model.Library;
import model.User;
import org.json.*;

// Represents a reader that reads user information from JSON data stored in file
// citation: Methods were taken from JsonSerializationDemo from CPSC210
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads User's checkout cart from file and returns it;
    // throws IOException if an error occurs reading data from file
    public User read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUser(jsonObject);
    }


    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses list of books in cart from JSON object and returns it
    private User parseUser(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        User user = new User(name);
        addBooks(user, jsonObject);
        return user;
    }

    // MODIFIES: user
    // EFFECTS: parses list of books from JSON object and adds them to cart
    private void addBooks(User user, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("books");
        for (Object json : jsonArray) {
            JSONObject nextBook = (JSONObject) json;
            addBook(user, nextBook);
        }
    }

    // MODIFIES: user
    // EFFECTS: parses book from JSON object and adds it to user's cart
    private void addBook(User user, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String author = jsonObject.getString("author");
        String genre = jsonObject.getString("genre");
        Book book = new Book(name, author, genre);
        user.addBookToCart(book);
    }

    // persistence for new books added by librarian/user

    // EFFECTS: reads library's list of new books from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Library readAddBook() throws IOException {
        String jsonData = readFileAddBook(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLibrary(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFileAddBook(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses list of books added by user from JSON object and returns it
    private Library parseLibrary(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Library lib = new Library(name);
        addNewBooks(lib, jsonObject);
        return lib;
    }

    // MODIFIES: lib
    // EFFECTS: parses list of books from JSON object and adds them to list of new books
    private void addNewBooks(Library lib, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("new books");
        for (Object json : jsonArray) {
            JSONObject nextBook = (JSONObject) json;
            addNewBook(lib, nextBook);
        }
    }

    // MODIFIES: lib
    // EFFECTS: parses book from JSON object and adds it to list of books
    private void addNewBook(Library lib, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String author = jsonObject.getString("author");
        String genre = jsonObject.getString("genre");
        Book book = new Book(name, author, genre);
        lib.addBook(book);
    }
}
