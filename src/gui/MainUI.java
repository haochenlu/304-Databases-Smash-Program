package gui;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import gui.QueryEvent;
import gui.QueryListener;
import gui.gui.BackgroundFrame;

public class MainUI {
    private JPanel panel1;
    private JComboBox character_box_1;
    private JComboBox character_box_2;
    private JComboBox stage_box;
    private JButton generate_button;
    private String char1;
    private String char2;
    private String stage;
    private int params;
    private Image img = null;
    private EventListenerList listenerList = new EventListenerList();


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
                fireQueryEvent(new QueryEvent(this, params, characters));
            }
        });

    }

    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JRadioButtonMenuItem rbMenuItem;
        JMenu menu;
        menuBar = new JMenuBar();
        menu = new JMenu("Set Parameters");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "Specify the query you want");
        menuBar.add(menu);
        menu.addSeparator();
        ButtonGroup group = new ButtonGroup();

        rbMenuItem = new JRadioButtonMenuItem("Compare X");
        rbMenuItem.setSelected(true);
        rbMenuItem.setMnemonic(KeyEvent.VK_R);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);

        rbMenuItem = new JRadioButtonMenuItem("Compare Y");
        rbMenuItem.setMnemonic(KeyEvent.VK_O);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);

        rbMenuItem = new JRadioButtonMenuItem("Compare Z");
        rbMenuItem.setMnemonic(KeyEvent.VK_P);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);
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
                MainUI gui = new MainUI();
                JFrame frame = new JFrame("Smash DB");
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
