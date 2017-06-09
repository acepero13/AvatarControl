package de.dfki.server.notifications;

import de.dfki.gui.renderers.Renderable;

/**
 * Created by alvaro on 6/8/17.
 */
public class VASelectorNotification implements DataNotification {
    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }

    private String selectedOption;

    @Override
    public void render() {

    }

    @Override
    public void setRenderer(Renderable renderer) {

    }

    @Override
    public NotificationType getType() {
        return  NotificationType.VASELECTOR;
    }
}
