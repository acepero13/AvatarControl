package de.dfki.server.receiver;

import de.dfki.server.notifications.DataNotification;

/**
 * Created by alvaro on 4/28/17.
 */
public interface ReceiverObservable {
    void register(ReceiverObserver observer);
    void unregister(ReceiverObserver observer);
    void notifyAll(DataNotification notification);

}
