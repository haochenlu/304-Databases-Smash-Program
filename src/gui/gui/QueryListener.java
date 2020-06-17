package gui;

import java.util.EventListener;

public interface QueryListener extends EventListener {
    public void queryEventOccured(QueryEvent event);
}
