package de.dfki.server.notifications;

import de.dfki.gui.renderers.Renderable;

import java.util.LinkedList;

/**
 * Created by alvaro on 5/1/17.
 */
public class OptionsNotification implements DataNotification {
    private LinkedList<String> options;

    @Override
    public void render() {

    }

    @Override
    public void setRenderer(Renderable renderer) {

    }

    @Override
    public NotificationType getType() {
        return NotificationType.SPEAKOPTIONS;
    }

    public LinkedList<String> getOptions(){
        return options;
    }

    public void setOptions(LinkedList<String> options){
        this.options = options;
    }
}
