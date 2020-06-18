package gui.gui;

import javax.swing.*;
import java.awt.*;

public class BackgroundFrame extends JFrame {

    public BackgroundFrame(JPanel panel)
    {
        setTitle("Background Color for JFrame");
        setSize(800,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        setLayout(new BorderLayout());
        setContentPane(new JLabel(new ImageIcon(".\\ssbm.jpg")));
        setLayout(new FlowLayout());
        add(panel);
        setSize(399,399);
        setSize(800,500);
    }
}

// Code for background from https://stackoverflow.com/questions/18777893/jframe-background-image