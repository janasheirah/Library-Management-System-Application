package ui;

import model.Book;
import model.Library;

import javax.swing.*;
import java.awt.*;

public class BookPrinter extends JInternalFrame {

    private JTextArea listArea;
    JLabel label  = new JLabel("Here are the list of books available");

    public BookPrinter() {
        super("List of books", false, true, false, false);
        listArea = new JTextArea();
        listArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(listArea);
        add(scrollPane);
        setVisible(true);
        label.setBounds(0,0,200,100);
        label.setFont(new Font(null, Font.PLAIN, 20));
    }

//    @Override
//    public void printLog(Library vpl) {
//        for (Book b : vpl.getListOfBooks()) {
//            listArea.setText(listArea.getText() + "\n");
//        }
//        repaint();
//    }
}
