package model;

// Represents librarian/managers of library
public class Librarian {

    private String name;

    // EFFECTS: constructs a librarian with name of librarian
    public Librarian(String name) {
        this.name = name;
    }

    // EFFECTS: returns name of librarian
    public String getName() {
        return name;
    }
}
