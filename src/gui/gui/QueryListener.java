package gui;
import gui.QueryEvent;
import java.util.EventListener;

public interface QueryListener extends EventListener {
    public void QueryEventOccured(QueryEvent event);
}
