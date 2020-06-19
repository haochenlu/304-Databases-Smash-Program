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
    private JTextField selectionHeight;
    private JButton selectButton;
    private JTextField textField11;
    private JComboBox joinAType;
    private JComboBox joinADir;
    private JButton joinButton;
    private JButton aggregateButton;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JComboBox comboBox5;
    private JButton nestedAggregationButton;
    private JButton divisionButton;
    private JPanel cardPanel;
    private JButton projectionButton;
    private JCheckBox activeCheckBox;
    private JCheckBox totalCheckBox;
    private JCheckBox percentDoneUnstalledCheckBox;
    private JCheckBox IASACheckBox;
    private JCheckBox typeCheckBox;
    private JCheckBox characterIDCheckBox;
    private JCheckBox directionCheckBox;
    private JTextField nestedActiveFrames;
    private JPanel ShowTable;
    private JComboBox TableBox;
    private JButton showTableButton;
    private JTextPane selectionPane;
    private JTextPane updatePane;
    private JTextPane deletePane;
    private JTextPane insertPane;
    private JTextPane joinPane;
    private JTextPane nestedAggregationPane;
    private JComboBox joinCharBox;
    final static String INSERT = "Insert";
    final static String DELETE = "Delete";
    final static String UPDATE = "Update";
    final static String SELECTION = "Selection";
    final static String PROJECTION = "Projection";
    final static String JOIN = "Join";
    final static String AGGREGATION = "Aggregation";
    final static String NESTED_AGGREGATION = "Nested Aggregation";
    final static String DIVISION = "Division";
    final static String Show_Table = "Show Table";
    private boolean[] delParams;
    protected String[] character_array = {
            "Dr. Mario",
            "Mario",
            "Luigi",
            "Bowser",
            "Peach",
            "Yoshi",
            "Donkey Kong",
            "Captain Falcon",
            "Ganondorf",
            "Falco",
            "Fox",
            "Ness",
            "Ice Climbers",
            "Kirby",
            "Samus",
            "Zelda",
            "Link",
            "Young Link",
            "Pichu",
            "Pikachu",
            "Jigglypuff",
            "Mewtwo",
            "Mr. Game and Watch",
            "Marth",
            "Roy"
    };
    protected String[] attack_type = {
            "Aerial",
            "Smash",
            "Tilt",
            "Special",
            "Jab",
            "Dash Attack"
    };
    protected String[] directions = {
            "U",
            "F",
            "D",
            "N",
            "NA"
    };
    protected String[] tables = {
            "shieldgroup",
            "grabgroup",
            "jumpgroup",
            "characterdata",
            "intothrow",
            "characterattack",
            "airdodgegroup",
            "airdodgedata",
            "stages"
    };

    public OtherQueryUI(Backend b) {
        deletePane.setText("Input an integer to delete the corresponding shield group");
        insertPane.setText("Input an integer into new SG# and any number into newSGVal to insert a new shield group with hard shield value of newSGVal");
        joinPane.setText("Input an integer into AttackStat > to get the characters moves with total frames greater than AttackStat");
        nestedAggregationPane.setText("Insert an integer into Active to get the characters whose average active frames for moves of type 'Attack type' are less than 'Active'");
        selectionPane.setText("Insert any number into shHeight to get the jump group IDs of those characters whose short hop heights are greater than 'shHeight'");
        backend = b;
        String[] comboBoxItems = {INSERT, DELETE, UPDATE, SELECTION, PROJECTION, JOIN, AGGREGATION, NESTED_AGGREGATION, DIVISION, Show_Table};
        System.out.println(comboBoxItems);
        for (String op : comboBoxItems) {
            CardSelection.addItem(op);
        }
        for (String type : attack_type) {
            comboBox4.addItem(type);
            comboBox5.addItem(type);
        }
        for (String dir : directions) {
            comboBox3.addItem(dir);
        }
        for (String table : tables) {
            TableBox.addItem(table);
        }
        ArrayList<JCheckBox> deleteChecks = new ArrayList<>();
        deleteChecks.add(activeCheckBox);
        deleteChecks.add(IASACheckBox);
        deleteChecks.add(totalCheckBox);
        deleteChecks.add(percentDoneUnstalledCheckBox);
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
                String shHeight = selectionHeight.getText();
                // String selectedChar = selectionCharBox.getSelectedItem().toString();
                String query = "select jgID\n from jumpgroup\n where shHeight > " + shHeight + "\n";
                ArrayList<ArrayList<String>> result = backend.query(query);
                ResultPopup resultPopup = new ResultPopup(result, query);
                createPopup(resultPopup);
            }
        });
        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                String cID = "'" + joinCharBox.getSelectedItem().toString() + "'";
//                String type = "'" + joinAType.getSelectedItem().toString() + "'";
//                String dir = "'" + joinADir.getSelectedItem().toString() + "'";
                String attackStat = textField11.getText();
                String queryString = "select a.cID, a.direction, a.type\n from characterattack a, attackstat s\n " +
                        "where s.total > " + attackStat + " AND a.cID = s.cID AND a.type = s.type AND a.direction =\n" +
                        "s.direction\n";

                ArrayList<ArrayList<String>> result = backend.query(queryString);
                ResultPopup resultPopup = new ResultPopup(result, queryString);
                createPopup(resultPopup);
            }
        });
        aggregateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String type = "'" + comboBox4.getSelectedItem().toString() + "'";
               String dir = "'" + comboBox3.getSelectedItem().toString() + "'";
               String queryString = "select a.cID, a.type, SUM(s.active)\n" +
                       "from characterattack a, attackstat s\n" +
                       "where a.cID = s.cID AND a.type = s.type AND a.direction = s.direction and a.type = " + type + "and a.direction = " + dir + "\n" +
                       "GROUP BY a.cID, a.type\n";
               ArrayList<ArrayList<String>> result = backend.query(queryString);
               ResultPopup resultPopup = new ResultPopup(result, queryString);
               createPopup(resultPopup);
            }
        });
        nestedAggregationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String type = "'" + comboBox5.getSelectedItem().toString() + "'";
                String active = "'" + nestedActiveFrames.getText() + "'";
                String queryString = "select a.cID, a.type, SUM(s.active)\n" +
                        "from characterattack a, attackstat s\n" +
                        "where a.cID = s.cID AND a.type = s.type AND a.direction = s.direction AND a.type = " + type + "AND " + active + " < (select AVG(active) from attackstat)\n" +
                        "GROUP BY a.cID, a.type, a.direction\n";

                ArrayList<ArrayList<String>> result = backend.query(queryString);
                ResultPopup resultPopup = new ResultPopup(result, queryString);
                createPopup(resultPopup);
            }
        });
        divisionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String queryString = "select c1.cID\n" +
                        "from characterdata c1\n" +
                        "where not exists\n" +
                        "(select * from characterdata c2\n" +
                        "where not exists\n" +
                        "(select f.c1id\n" +
                        "from fights f\n" +
                        "where c1.cID = f.c1ID AND c2.cID <> f.c2ID))\n";
                ArrayList<ArrayList<String>> result = backend.query(queryString);
                ResultPopup resultPopup = new ResultPopup(result, queryString);

                createPopup(resultPopup);
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
                int yes = 0;
                String queryString = "select ";
                if (delParams[0] == true) {
                    queryString = queryString + "active, ";
                    yes++;
                } if (delParams[1] == true) {
                    queryString = queryString + "IASA, ";
                    yes++;
                } if (delParams[2] == true) {
                    queryString = queryString + "total, ";
                    yes++;
                } if (delParams[3] == true) {
                    queryString = queryString + "percentDoneUnstalled, ";
                    yes++;
                } if (delParams[4] == true) {
                    queryString = queryString + "type, ";
                    yes++;
                } if(delParams[5] == true) {
                    queryString = queryString + "cID, ";
                    yes++;
                }

                queryString = queryString.substring(0, queryString.length() - 2) + "\n from attackstat\n";

                 ArrayList<ArrayList<String>> result = backend.query(queryString);
                ResultPopup resultPopup = new ResultPopup(result, queryString);
                createPopup(resultPopup);
            }
        });

        showTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String table = TableBox.getSelectedItem().toString();
                String queryString = "select * from " + table + "\n";
                ArrayList<ArrayList<String>> result = backend.query(queryString);
                ResultPopup resultPopup = new ResultPopup(result, queryString);
                createPopup(resultPopup);
            }
        });
    }

    private void createPopup(ResultPopup resultPopup) {

        JFrame frame = new JFrame("Query Results");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        resultPopup.panel1.setOpaque(true);
        frame.setContentPane(resultPopup.panel1);

        JFrame.setDefaultLookAndFeelDecorated(true);

        frame.pack();
        frame.setSize(800, 600);
        frame.setVisible(true);
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
