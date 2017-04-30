package de.dfki.server.notifications;

import de.dfki.gui.renderers.Renderable;

public interface DataNotification {
    void render();
    void setRenderer(Renderable renderer);
    NotificationType getType();
}
