package ui;

import model.Book;
import model.Library;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class NewWindow {

    JFrame frame = new JFrame();
    JLabel label = new JLabel("Here are the books available: " + "\n");
    Library vpl = new Library("Vancouver Public Library");

    public NewWindow() {

        label.setBounds(0,0,100,50);
        label.setFont(new Font(null, Font.PLAIN, 16));

        // frame.add(label);

        List<String> totalCatalogue = new ArrayList<>();
        totalCatalogue.addAll(vpl.getListOfTitles());
        String list [] = {"jana", "nour", "malak"};

        JList bookList = new JList(list);
        frame.add(bookList);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.pack();
        frame.setVisible(true);

    }
}
