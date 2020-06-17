package gui;
import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class MainUI {
    private JPanel panel1;
    private JComboBox character_box_1;
    private JComboBox character_box_2;
    private JComboBox stage_box;
    private JButton generate_button;
    private JButton set_param_button;
    private String char1;
    private String char2;
    private String stage;
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
                String[] params = {char1, char2, stage};
            }
        });
        set_param_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    public void fireQueryEvent(QueryEvent event) {
        Object[] listeners = listenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == QueryListener.class) {
                ((QueryListener)listeners[i + 1]).QueryEventOccured(event);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Smash DB");
                frame.setContentPane(new MainUI().panel1);
                frame.setSize(800, 500);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
