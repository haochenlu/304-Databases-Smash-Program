package gui.gui;

import javax.swing.*;
import java.util.ArrayList;

public class ResultPopup {
    private JPanel panel1;
    private JTextArea textArea1;
    private ArrayList<ArrayList<String>> output;

    public ResultPopup(ArrayList<ArrayList<String>> output) {
        for (ArrayList<String> row : output) {
            for (String qRes : row) {
                textArea1.append(String.format("%-20s", qRes));
            }
            textArea1.append("\n");
        }
    }
    public void createAndShowGUI() {

        JFrame frame = new JFrame("Query Results");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ResultPopup resultPopup = new ResultPopup(output);
        resultPopup.panel1.setOpaque(true);
        frame.setContentPane(resultPopup.panel1);

        JFrame.setDefaultLookAndFeelDecorated(true);

        frame.pack();
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}
