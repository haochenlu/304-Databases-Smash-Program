package gui.gui;

import main.Backend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class OtherQueryUI implements ItemListener {
    private Backend backend;
    public JPanel container;
    private JComboBox CardSelection;
    private JTextField textField1;
    private JLabel SGNum;
    private JTextField textField2;
    private JLabel SGVal;
    private JTextField textField3;
    private JButton insertButton;
    private JButton deleteButton;
    private JTextField textField4;
    private JTextField textField5;
    private JButton updateButton;
    private JTextField textField6;
    private JTextField textField7;
    private JButton selectButton;
    private JTextField textField10;
    private JTextField textField11;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton joinButton;
    private JButton aggregateButton;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JTextField textField8;
    private JTextField textField9;
    private JComboBox comboBox5;
    private JComboBox comboBox6;
    private JButton nestedAggregationButton;
    private JButton divisionButton;
    private JComboBox comboBox7;
    private JPanel cardPanel;
    private JButton projectionButton;
    private JCheckBox activeCheckBox;
    private JCheckBox totalCheckBox;
    private JCheckBox percentDoneUnstalledCheckBox;
    private JCheckBox IASACheckBox;
    private JCheckBox typeCheckBox;
    private JCheckBox characterIDCheckBox;
    private JCheckBox directionCheckBox;
    final static String INSERT = "Insert";
    final static String DELETE = "Delete";
    final static String UPDATE = "Update";
    final static String SELECTION = "Selection";
    final static String PROJECTION = "Projection";
    final static String JOIN = "Join";
    final static String AGGREGATION = "Aggregation";
    final static String NESTED_AGGREGATION = "Nested Aggregation";
    final static String DIVISION = "Division";
    private boolean[] delParams;

    public OtherQueryUI(Backend b) {
        backend = b;
        String[] comboBoxItems = {INSERT, DELETE, UPDATE, SELECTION, PROJECTION, JOIN, AGGREGATION, NESTED_AGGREGATION, DIVISION};
        System.out.println(comboBoxItems);
        for (String op : comboBoxItems) {
            CardSelection.addItem(op);
        }
        ArrayList<JCheckBox> deleteChecks = new ArrayList<>();
        deleteChecks.add(activeCheckBox);
        deleteChecks.add(totalCheckBox);
        deleteChecks.add(percentDoneUnstalledCheckBox);
        deleteChecks.add(IASACheckBox);
        deleteChecks.add(typeCheckBox);
        deleteChecks.add(characterIDCheckBox);
        CardSelection.addItemListener(this);
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sgnum = textField1.getText();
                String sgval = textField2.getText();
                String sgval2 = Float.toString(2 * Float.parseFloat(sgval));
                backend.load("insert into shieldgroup values('" + sgnum + "', '" + sgnum + "_hs', " + sgval + ", '" + sgnum + "_hs', " + sgval2);
                System.out.println("added");
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sgnum = "'" + textField3.getText() + "'";
                backend.load("delete from shieldgroup where sgID = " + sgnum);
                System.out.println("deleted");
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ggnum = "'" + textField4.getText() + "'";
                String ggval = textField5.getText();
                backend.load("update grabgroup \n set gdFrames = " + ggval + ", gsFrames = " + ggval + "\n where ggID = " + ggnum);
                System.out.println("updated");
            }
        });
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        aggregateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        nestedAggregationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        divisionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        projectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delParams = new boolean[7];
                for (int i = 0; i < deleteChecks.size(); i++) {
                    boolean selected = deleteChecks.get(i).isSelected();
                    delParams[i] = selected;
                }
            }
        });
    }

    private static void createAndShowGUI(Backend b) {
        //Create and set up the window.
        JFrame frame = new JFrame("CardLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        OtherQueryUI demo = new OtherQueryUI(b);
        frame.setContentPane(demo.container);

        //Display the window.
        frame.setSize(800, 500);
        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(new Backend());
            }
        });
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
        System.out.println("event!");
        CardLayout cl = (CardLayout)(cardPanel.getLayout());
        cl.show(cardPanel, (String)e.getItem());
    }

}
