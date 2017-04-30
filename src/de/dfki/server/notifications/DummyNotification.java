package de.dfki.server.notifications;

import de.dfki.gui.renderers.Renderable;

/**
 * Created by alvaro on 4/30/17.
 */
public class DummyNotification implements DataNotification {
    @Override
    public void render() {

    }

    @Override
    public void setRenderer(Renderable renderer) {

    }

    @Override
    public NotificationType getType() {
        return NotificationType.DUMMY;
    }
}
