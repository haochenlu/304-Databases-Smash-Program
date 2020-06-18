package gui;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;
import gui.QueryEvent;
import gui.QueryListener;
import gui.gui.BackgroundFrame;
import gui.gui.OtherQueryUI;

public class MainUI implements ActionListener {
    public static JFrame frame;
    public static MainUI gui;
    private static final String ELSE = "else";
    private static final String PROJECTION = "projection";
    private JPanel panel1;
    private JComboBox character_box_1;
    private JComboBox character_box_2;
    private JComboBox stage_box;
    private JButton generate_button;
    private String char1;
    private String char2;
    private String stage;
    private boolean[] params;
    private Image img = null;
    private EventListenerList listenerList = new EventListenerList();
    private JCheckBoxMenuItem weight;
    private JCheckBoxMenuItem gravity;
    private JCheckBoxMenuItem attacks;
    private JCheckBoxMenuItem shields;
    private JCheckBoxMenuItem jumps;
    private JCheckBoxMenuItem stage_size;
    private JCheckBoxMenuItem platforms;
    private JCheckBoxMenuItem hazards;
    private JRadioButtonMenuItem projection;
    private JRadioButtonMenuItem others;
    ArrayList<JCheckBoxMenuItem> items = new ArrayList<>();

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.getContentPane().removeAll();
        if (e.getActionCommand() == PROJECTION) {
            frame.getContentPane().add(gui.panel1);
        } else {
            OtherQueryUI queryUI = new OtherQueryUI();
            queryUI.container.setOpaque(false);
            frame.getContentPane().add(queryUI.container);
        }
        frame.getContentPane().revalidate();
        frame.repaint();
    }

    protected enum characterList {
        DR_MARIO,
        MARIO,
        LUIGI,
        BOWSER,
        PEACH,
        YOSHI,
        DONKEY_KONG,
        CAPTAIN_FALCON,
        GANONDORF,
        FALCO,
        FOX,
        NESS,
        ICE_CLIMBERS,
        KIRBY,
        SAMUS,
        ZELDA,
        LINK,
        YOUNG_LINK,
        PICHU,
        PIKACHU,
        JIGGLYPUFF,
        MEWTWO,
        GAME_AND_WATCH,
        MARTH,
        ROY
    }

    protected enum stageList {
        YOSHIS_STORY,
        FINAL_DESTINATION,
        FOUNTAIN_OF_DREAMS,
        DREAMLAND,
        BATTLEFIELD,
        POKEMON_STADIUM
    }


    public static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }


    public MainUI() {
        String[] character_array = getNames(characterList.class);
        String[] stage_array = getNames(stageList.class);
        panel1.setOpaque(true);
        for (String character : character_array) {
            character_box_1.addItem(character);
            character_box_2.addItem(character);
        }
        for (String stage : stage_array) {
            stage_box.addItem(stage);
        }
        generate_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object first = character_box_1.getSelectedItem();
                char1 = first.toString();
                Object second = character_box_2.getSelectedItem();
                char2 = second.toString();
                Object st = stage_box.getSelectedItem();
                stage = st.toString();
                String[] characters = {char1, char2, stage};
                params = new boolean[8];
                Arrays.fill(params, Boolean.FALSE);
                for (int i = 0; i < items.size(); i++) {
                    boolean selected = items.get(i).isSelected();
                    params[i] = selected;
                }
                fireQueryEvent(new QueryEvent(this, params, characters));
            }
        });

    }

    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu;
        menuBar = new JMenuBar();
        menu = new JMenu("Set Parameters");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "Specify the query you want");
        menuBar.add(menu);
        menu.addSeparator();
        ButtonGroup group = new ButtonGroup();

        projection = new JRadioButtonMenuItem("Projection menu");
        projection.setSelected(true);
        projection.setMnemonic(KeyEvent.VK_P);
        group.add(projection);
        menu.add(projection);
        projection.setActionCommand(PROJECTION);
        projection.addActionListener(this);


        others = new JRadioButtonMenuItem("Everything Else");
        others.setMnemonic(KeyEvent.VK_E);
        group.add(others);
        menu.add(others);
        others.setActionCommand(ELSE);
        others.addActionListener(this);

        menu.addSeparator();
        weight = new JCheckBoxMenuItem("Weight");
        weight.setMnemonic(KeyEvent.VK_W);
        menu.add(weight);
        items.add(weight);

        gravity = new JCheckBoxMenuItem("Gravity");
        gravity.setMnemonic(KeyEvent.VK_G);
        menu.add(gravity);
        items.add(gravity);

        attacks = new JCheckBoxMenuItem("Attacks");
        attacks.setMnemonic(KeyEvent.VK_A);
        menu.add(attacks);
        items.add(attacks);

        shields = new JCheckBoxMenuItem("Shields");
        shields.setMnemonic(KeyEvent.VK_S);
        menu.add(shields);
        items.add(shields);

        jumps = new JCheckBoxMenuItem("Jumps");
        jumps.setMnemonic(KeyEvent.VK_J);
        menu.add(jumps);
        items.add(jumps);

        stage_size = new JCheckBoxMenuItem("Stage Size");
        stage_size.setMnemonic(KeyEvent.VK_I);
        menu.add(stage_size);
        items.add(stage_size);

        hazards = new JCheckBoxMenuItem("Hazards");
        hazards.setMnemonic(KeyEvent.VK_Z);
        menu.add(hazards);
        items.add(hazards);

        platforms = new JCheckBoxMenuItem("Platforms");
        platforms.setMnemonic(KeyEvent.VK_P);
        menu.add(platforms);
        items.add(platforms);

        return menuBar;
    }


    public void fireQueryEvent(QueryEvent event) {
        Object[] listeners = listenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == QueryListener.class) {
                ((QueryListener) listeners[i + 1]).QueryEventOccured(event);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gui = new MainUI();
                frame = new JFrame("Smash DB");
                frame.setLayout(new BorderLayout());
                frame.setContentPane(new JLabel(new ImageIcon("C:\\Users\\haoch\\UBC CS\\jav\\304-Databases-Smash-Program\\src\\gui\\gui\\ssbm.jpg")));
                frame.setLayout(new FlowLayout());
                frame.add(gui.panel1);
                frame.setSize(399,399);
                frame.setJMenuBar(gui.createMenuBar());
                gui.panel1.setOpaque(false);
                frame.setSize(800, 500);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
