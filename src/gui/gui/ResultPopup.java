package gui.gui;

import javax.swing.*;
import java.util.ArrayList;

public class ResultPopup {
    private JPanel panel1;
    private JTextArea textArea1;

    public ResultPopup(ArrayList<ArrayList<String>> output) {
        for (ArrayList<String> row : output) {
            for (String qRes : row) {
                textArea1.append(String.format("%-20s", qRes));
            }
            textArea1.append("\n");
        }
    }
}
