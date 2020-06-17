package gui;

import java.util.EventObject;

public class QueryEvent extends EventObject {
    private int params;
    private String[] characters;
    public QueryEvent(Object source, int params, String[] characters) {
        super(source);
        this.params = params;
        this.characters = characters;
    }
}
