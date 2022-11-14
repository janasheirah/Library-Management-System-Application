package ui;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class SplashScreen {

    private JFrame screen;
    private JProgressBar bar;

    public SplashScreen() {
        screen = new JFrame();

        bar = new JProgressBar();
        bar.setValue(0);
        bar.setBounds(0,0,600,300);
        bar.setStringPainted(true); // adds percentage
        bar.setFont(new Font("MV Boli", Font.BOLD, 16));
        bar.setForeground(Color.WHITE);
        bar.setBackground(Color.GREEN);
        screen.getContentPane().add(bar);

        screen.getContentPane().add(
                new JLabel("", new ImageIcon("./data/splashScreen.jpg"), SwingConstants.CENTER));
        screen.setBounds(0, 0, 600, 600);

        screen.setVisible(true);
        fill();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        screen.setVisible(false);
    }

    public void fill() {
        int counter = 0;

        while (counter <= 100) {
            bar.setValue(counter);
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            counter += 1;
        }
        bar.setString("Done!");
    }
}
