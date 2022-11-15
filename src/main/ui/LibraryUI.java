package ui;

import model.Book;
import model.Library;
import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Represents application's main window frame.
 */
public class LibraryUI extends JFrame {

    private static final String JSON_STORE = "./data/checkoutCart.json";
    private static final String JSON_STORE2 = "./data/newBooks.json";
    private JPanel panel;
    private JInternalFrame internalFrame;
    private JFrame frame;
    private JLabel myLabel;
    private ImageIcon backgroundImage;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter2;
    private JsonReader jsonReader2;
    private Library vpl;
    private User user1;

    /**
     * Constructor sets up frame, adds button panel and adds background image.
     */
    public LibraryUI() {

        vpl = new Library("library");
        user1 = new User("user");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter2 = new JsonWriter(JSON_STORE2);
        jsonReader2 = new JsonReader(JSON_STORE2);

        new SplashScreen();

        backgroundImage = new ImageIcon("./data/backgroundImage.jpg");
        myLabel = new JLabel(backgroundImage);
        myLabel.setBounds(0,0,600,600);

        frame = new JFrame("Library Management System");
        frame.add(myLabel);

        addLabel();
        addButtonPanel();
        saveOnClose();

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);
        frame.setBounds(0, 0, 600, 600);

        // frame.getContentPane().setBackground(new Color(204, 204, 255));
        frame.setVisible(true);
    }

    private void addLabel() {
        JLabel label = new JLabel("Hello, Welcome to Vancouver Public Library", JLabel.CENTER);
        label.setText("hello");
        label.setVerticalAlignment(JLabel.TOP);
        label.setForeground(Color.black);
        label.setFont(new Font("MV Boli", Font.PLAIN, 16));
        myLabel.add(label);
        frame.add(label);
    }

    /**
     * Helper to add buttons menu.
     */
    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(7,1));
        button1 = new JButton(new ViewBooksAction());
        button2 = new JButton(new CheckoutBookAction());
        button3 = new JButton(new ReturnBookAction());
        button4 = new JButton(new SearchByGenreAction());
        button5 = new JButton(new ViewUserCartAction());
        button6 = new JButton(new LoadDataAction());
        button7 = new JButton(new AddBookAction());
        buttonPanel.add(button1);
        buttonPanel.add(button4);
        buttonPanel.add(button2);
        buttonPanel.add(button5);
        buttonPanel.add(button3);
        buttonPanel.add(button7);
        buttonPanel.add(button6);
        buttonPanel.setBounds(100,100,40,40);
        frame.getContentPane().add(buttonPanel, BorderLayout.WEST);
    }

    /**
     * Helper to save files.
     * @throws FileNotFoundException for json operations
     */
    private void saveOperations() throws FileNotFoundException {
        jsonWriter.open();
        jsonWriter2.open();
        jsonWriter.write(user1);
        jsonWriter2.writeAddBook(vpl);
        jsonWriter.close();
        jsonWriter2.close();
    }

    /**
     * Gives the user the option of saving the data before closing the application
     */
    private void saveOnClose() {
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(frame, "Do you want to save your current data?",
                        "Save data", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    try {
                        saveOperations();
                        JOptionPane.showMessageDialog(frame,
                                "Saved " + user1.getName() + "'s data to \n" + JSON_STORE + "\n" + JSON_STORE2,
                                "Saved Data", JOptionPane.INFORMATION_MESSAGE);
                    } catch (FileNotFoundException exc) {
                        JOptionPane.showMessageDialog(frame,
                                "Unable to write to file: " + JSON_STORE, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    System.exit(0);
                } else {
                    JOptionPane.showMessageDialog(frame,"Your data will not be saved", "Warning",
                            JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }
            }
        });
    }

    /**
     * Represents action to be taken when user wants to view list of books in library
     */
    private class ViewBooksAction extends AbstractAction {

        ViewBooksAction() {
            super("View Books");
        }

        // create an internal frame and add label to print out books
        // customize this frame by perhaps adding background picture or pictures for the books

        @Override
        public void actionPerformed(ActionEvent e) {

            panel = new JPanel();
            internalFrame = new JInternalFrame();
            panel.add(internalFrame);
            internalFrame.setBounds(100, 100, 400, 400);
            backgroundImage = new ImageIcon("./data/listOfBooks.jpg");
            JLabel label = new JLabel(backgroundImage);
            internalFrame.add(label);

            JList<Book> list = new JList<>();
            DefaultListModel<Book> model = new DefaultListModel<>();

            list.setModel(model);
            list.setBounds(100, 100, 300, 300);
            list.setFont(new Font("Times New Roman", Font.PLAIN, 17));
            internalFrame.add(new JScrollPane(list));
//            model.addAll(vpl.getListOfBooks());

            for (Book b : vpl.getListOfBooks()) {
                model.addElement(b);
            }

            internalFrame.add(list);
            internalFrame.add(label);
            internalFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            panel.add(internalFrame);
            frame.add(panel);
            internalFrame.setVisible(true);
            panel.setVisible(true);
        }
    }

    /**
     * Represents the action to be taken when user wants to check out a book
     */
    private class CheckoutBookAction extends AbstractAction {

        CheckoutBookAction() {
            super("Checkout Book");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            internalFrame.dispose();
            panel.setVisible(false);
            String bookName = JOptionPane.showInputDialog(frame,
                    "Enter the name of the book you want to checkout",
                    "Book Name?",
                    JOptionPane.QUESTION_MESSAGE);
            if (vpl.getListOfTitles().contains(bookName)) {
                vpl.searchForBookByTitle(bookName);
                if (user1.checkOutBook(vpl, vpl.getBookVariable())) {
                    JOptionPane.showMessageDialog(frame, "This book is now checked out: "
                                    + vpl.getBookVariable().getBookName() + " by " + vpl.getBookVariable().getAuthor(),
                            "Checkout",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame,
                            bookName + " book is currently on loan and cannot be checked out. ");
                }
            } else {
                JOptionPane.showMessageDialog(frame,
                        bookName + " book is not available in this library.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Represents action to be taken when user wants to return a book.
     */
    public class ReturnBookAction extends AbstractAction {

        ReturnBookAction() {
            super("Return book");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            internalFrame.dispose();
            panel.setVisible(false);
            String bookName = JOptionPane.showInputDialog(frame,
                    "Enter the name of the book you want to return",
                    "Book Name?", JOptionPane.QUESTION_MESSAGE);

            if (vpl.getListOfTitles().contains(bookName)) {
                if (user1.getCheckOutCartByTitle().contains(bookName)) {
                    vpl.searchForBookByTitle(bookName);
                    if (user1.returnBook(vpl.getBookVariable())) {
                        JOptionPane.showMessageDialog(frame, "This book is now returned: "
                                        + vpl.getBookVariable().getBookName() + " by "
                                        + vpl.getBookVariable().getAuthor(),
                                "Return", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame,
                            bookName + " book was not on loan so not valid to be returned. \n");
                }
            } else {
                JOptionPane.showMessageDialog(frame,
                        bookName + " book is not available in this library.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Represents action to be taken when user wants to filter books by genre
     */
    private class SearchByGenreAction extends AbstractAction {

        SearchByGenreAction() {
            super("Search By Genre");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            internalFrame.dispose();
            panel.setVisible(false);
            String genre = JOptionPane.showInputDialog(frame,
                    "Enter one of the following genre: Fantasy, Non Fiction, Romance, Mystery, Biography\n",
                    "Genre?",
                    JOptionPane.QUESTION_MESSAGE);

            if (vpl.searchForBook(genre).isEmpty()) {
                JOptionPane.showMessageDialog(frame,
                         " Sorry, there are no books available for this genre\n");
            } else {
                ImageIcon image = new ImageIcon("./data/icon.jpg");
                JOptionPane.showMessageDialog(frame,
                        "These are the available books for " + genre + "\n " + vpl.searchForBook(genre),
                        "Filtered Books", JOptionPane.INFORMATION_MESSAGE,
                        image);
            }
        }
    }

    /**
     * Represents action to be taken when user wants to view books in his cart.
     */
    private class ViewUserCartAction extends AbstractAction {

        ViewUserCartAction() {
            super("View Checkout Cart");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            internalFrame.dispose();
            panel.setVisible(false);
            JOptionPane.showMessageDialog(frame,
                    "Here is your checkout cart: \n " + user1.getCheckOutCartByTitle(),
                    "Checkout Cart",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Represents the action to be taken when the user wants to load the data from file.
     */
    private class LoadDataAction extends AbstractAction {

        LoadDataAction() {
            super("Load");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
//                if (e.getSource() == button1) {
//                    internalFrame.dispose();
//                    panel.setVisible(false);
//                }
                user1 = jsonReader.read();
                vpl = jsonReader2.readAddBook();
                JOptionPane.showMessageDialog(frame,
                        "Loaded " + user1.getName() + "'s history from \n" + JSON_STORE + "\n" + JSON_STORE2,
                        "Load History",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException exc) {
                JOptionPane.showMessageDialog(frame,
                        "Unable to read from file:  " + JSON_STORE,
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Represents the action to be taken when the user wants to add a book to the library.
     */
    private class AddBookAction extends AbstractAction {

        AddBookAction() {
            super("Add Book");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            internalFrame.dispose();
            panel.setVisible(false);
            String name = JOptionPane.showInputDialog(frame,
                    "Enter the name of the book you want to add \n",
                    "Book Name?",
                    JOptionPane.QUESTION_MESSAGE);
            String author = JOptionPane.showInputDialog(frame,
                    "Enter the name of the author of the book \n",
                    "Author Name?",
                    JOptionPane.QUESTION_MESSAGE);
            String genre = JOptionPane.showInputDialog(frame,
                    "Enter the type of genre for this book \n",
                    "Genre?",
                    JOptionPane.QUESTION_MESSAGE);

            vpl.addBookByLibrarian(name, author, genre);
            JOptionPane.showMessageDialog(frame,
                    "This book has been added to the list of books successfully!",
                    "Successful transaction",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}


