package ui;

import model.Book;
import model.Library;
import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class LibraryUI extends JFrame {

    private static final String JSON_STORE = "./data/checkoutCart.json";
    private static final String JSON_STORE2 = "./data/newBooks.json";
    private JFrame frame;
    private ImageIcon backgroundImage;
    private JTextArea listArea;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter2;
    private JsonReader jsonReader2;
    private Library vpl;
    private User user1;
    private JDesktopPane desktop;

    public LibraryUI() {

        vpl = new Library("library");
        user1 = new User("user");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter2 = new JsonWriter(JSON_STORE2);
        jsonReader2 = new JsonReader(JSON_STORE2);

        backgroundImage = new ImageIcon("./data/backgroundImage.jpg");
        JLabel myLabel = new JLabel(backgroundImage);
        myLabel.setBounds(0,0,600,600);

        frame = new JFrame("Library Management System");
        frame.add(myLabel);

        addLabel();
        addButtonPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setBounds(200, 200, 600, 600);

        frame.getContentPane().setBackground(new Color(204, 204, 255));
        frame.setVisible(true);

    }

    private void addLabel() {
        JLabel label = new JLabel("Hello, Welcome to Vancouver Public Library", JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setForeground(Color.black);
        label.setFont(new Font("MV Boli", Font.PLAIN, 16));
        frame.getContentPane().add(label);
    }

    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(8,1));
//        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        button1 = new JButton(new ViewBooksAction());
        button2 = new JButton(new CheckoutBookAction());
        button3 = new JButton(new ReturnBookAction());
        button4 = new JButton(new SearchByGenreAction());
        button5 = new JButton(new ViewUserCartAction());
        button6 = new JButton(new SaveHistoryAction());
        button7 = new JButton(new LoadDataAction());
        button8 = new JButton(new AddBookAction());
        buttonPanel.add(button1);
        buttonPanel.add(button4);
        buttonPanel.add(button2);
        buttonPanel.add(button5);
        buttonPanel.add(button3);
        buttonPanel.add(button8);
        buttonPanel.add(button6);
        buttonPanel.add(button7);
        buttonPanel.setBounds(100,100,40,40);
        buttonPanel.setMaximumSize(new Dimension(40,40));
        frame.getContentPane().add(buttonPanel, BorderLayout.WEST);
    }

    private class ViewBooksAction extends AbstractAction {

        ViewBooksAction() {
            super("View Books");
        }

        // create an internal frame and add label to print out books
        // customize this frame by perhaps adding background picture or pictures for the books

        @Override
        public void actionPerformed(ActionEvent e) {
            List<String> totalCatalogue = new LinkedList<>();
            totalCatalogue.addAll(vpl.getListOfTitles());

            JOptionPane.showMessageDialog(null,
                    "Here is the Catalogue: " + "\n" + vpl.getListOfTitles(),
                    "Book Catalogue",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private class CheckoutBookAction extends AbstractAction {

        CheckoutBookAction() {
            super("Checkout Book");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String bookName = JOptionPane.showInputDialog(null,
                    "Enter the name of the book you want to checkout",
                    "Book Name?",
                    JOptionPane.QUESTION_MESSAGE);
            if (vpl.getListOfTitles().contains(bookName)) {
                vpl.searchForBookByTitle(bookName);
                if (user1.checkOutBook(vpl, vpl.getBookVariable())) {
                    JOptionPane.showMessageDialog(null, "This book is now checked out: "
                                    + vpl.getBookVariable().getBookName() + " by " + vpl.getBookVariable().getAuthor(),
                            "Checkout",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null,
                            bookName + " book is currently on loan and cannot be checked out. ");
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        bookName + " book is not available in this library.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public class ReturnBookAction extends AbstractAction {

        ReturnBookAction() {
            super("Return book");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String bookName = JOptionPane.showInputDialog(null,
                    "Enter the name of the book you want to return",
                    "Book Name?",
                    JOptionPane.QUESTION_MESSAGE);

            if (vpl.getListOfTitles().contains(bookName)) {
                if (user1.getCheckOutCartByTitle().contains(bookName)) {
                    vpl.searchForBookByTitle(bookName);
                    if (user1.returnBook(vpl.getBookVariable())) {
                        JOptionPane.showMessageDialog(null, "This book is now returned: "
                                        + vpl.getBookVariable().getBookName() + " by " + vpl.getBookVariable().getAuthor(),
                                "Return",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                            bookName + " book was not on loan so not valid to be returned. \n. ");
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        bookName + " book is not available in this library.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class SearchByGenreAction extends AbstractAction {

        SearchByGenreAction() {
            super("Search By Genre");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String genre = JOptionPane.showInputDialog(null,
                    "Enter one of the following genre: Fantasy, Non Fiction, Romance, Mystery, Biography\n",
                    "Genre?",
                    JOptionPane.QUESTION_MESSAGE);

            if (vpl.searchForBook(genre).isEmpty()) {
                JOptionPane.showMessageDialog(null,
                         " Sorry, there are no books available for this genre\n");
            } else {
                JOptionPane.showMessageDialog(null,
                        "These are the available books for " + genre + "\n " + vpl.searchForBook(genre));
            }
        }
    }

    private class ViewUserCartAction extends AbstractAction {

        ViewUserCartAction() {
            super("View Checkout Cart");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null,
                    "Here is your checkout cart: \n " + user1.getCheckOutCartByTitle(),
                    "Checkout Cart",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private class SaveHistoryAction extends AbstractAction {

        SaveHistoryAction() {
            super("Save");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                jsonWriter.open();
                jsonWriter2.open();
                jsonWriter.write(user1);
                jsonWriter2.writeAddBook(vpl);
                jsonWriter.close();
                jsonWriter2.close();
                JOptionPane.showMessageDialog(null,
                        "Saved " + user1.getName() + "'s data to \n" + JSON_STORE + "\n" + JSON_STORE2,
                        "Saved Data",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (FileNotFoundException exc) {
                JOptionPane.showMessageDialog(null,
                        "Unable to write to file: " + JSON_STORE,
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class LoadDataAction extends AbstractAction {

        LoadDataAction() {
            super("Load");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                user1 = jsonReader.read();
                vpl = jsonReader2.readAddBook();
                JOptionPane.showMessageDialog(null,
                        "Loaded " + user1.getName() + "'s history from \n" + JSON_STORE + "\n" + JSON_STORE2,
                        "Load History",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException exc) {
                JOptionPane.showMessageDialog(null,
                        "Unable to read from file:  " + JSON_STORE,
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class AddBookAction extends AbstractAction {

        AddBookAction() {
            super("Add Book");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = JOptionPane.showInputDialog(null,
                    "Enter the name of the book you want to add \n",
                    "Book Name?",
                    JOptionPane.QUESTION_MESSAGE);
            String author = JOptionPane.showInputDialog(null,
                    "Enter the name of the author of the book \n",
                    "Author Name?",
                    JOptionPane.QUESTION_MESSAGE);
            String genre = JOptionPane.showInputDialog(null,
                    "Enter the type of genre for this book \n",
                    "Genre?",
                    JOptionPane.QUESTION_MESSAGE);

            vpl.addBookByLibrarian(name, author, genre);
            JOptionPane.showMessageDialog(null,
                    "This book has been added to the list of books successfully!",
                    "Successful transaction",
                    JOptionPane.OK_OPTION);
        }
    }
}

//
//            JTextField fname = new JTextField();
//            JTextField fgenre = new JTextField();
//            JTextField fauthor = new JTextField();
//
//            JButton create = new JButton("Submit");
//            create.addActionListener(e1 -> {
//                String bName = fname.getText();
//                String genre = fgenre.getText();
//                String author = fauthor.getText();
//                        Library vpl;
//                        vpl.addBook(fname.getText(), fauthor.getText(),fgenre.getText());
//                jOptionPane.showMessageDialog(null, "Book added!");
//            });
//            g.add(l3);
//            g.add(create);
//            g.add(l1);
//            g.add(l2);
//            g.add(fname);
//            g.add(fgenre);
//            g.add(fauthor);
//            g.setVisible(true);
//            frame.add(g);
//        });
//    }


