package de.dfki.server.notifications;

import de.dfki.gui.renderers.Renderable;

/**
 * Created by alvaro on 4/30/17.
 */
public class MoodNotification implements DataNotification {

    private int moodValue;

    @Override
    public void render() {

    }

    @Override
    public void setRenderer(Renderable renderer) {

    }

    @Override
    public NotificationType getType() {
        return NotificationType.MOOD;
    }



    public int getMoodValue() {
        return moodValue;
    }

    public void setMood(int mood) {
        this.moodValue = mood;
    }
}
