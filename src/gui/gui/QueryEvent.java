package gui;

import java.util.EventObject;

public class QueryEvent extends EventObject {
    private String[] params;
    private String[] characters;
    public QueryEvent(Object source, String[] params, String[] characters) {
        super(source);
        this.params = params;
        this.characters = characters;
    }
}
