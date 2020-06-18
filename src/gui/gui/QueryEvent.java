package gui;

import java.util.EventObject;

public class QueryEvent extends EventObject {
    private boolean[] params;
    private String[] characters;
    public QueryEvent(Object source, boolean[] params, String[] characters) {
        super(source);
        this.params = params;
        this.characters = characters;
    }
}
