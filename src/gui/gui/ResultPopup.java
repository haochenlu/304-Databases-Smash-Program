package gui.gui;

import javax.swing.*;
import java.util.ArrayList;

public class ResultPopup {
    public JPanel panel1;
    private JTextArea textArea1;
    private ArrayList<ArrayList<String>> output;

    public ResultPopup(ArrayList<ArrayList<String>> output, String query) {
        textArea1.append(query);
        for (ArrayList<String> row : output) {
            for (String qRes : row) {
                textArea1.append(String.format("%-20s", qRes));
            }
            textArea1.append("\n");
        }
    }
}
