package de.dfki.server.receiver;

import de.dfki.server.notifications.DataNotification;

/**
 * Created by alvaro on 4/28/17.
 */
public interface ReceiverObserver {

    void update(DataNotification notification);
}
