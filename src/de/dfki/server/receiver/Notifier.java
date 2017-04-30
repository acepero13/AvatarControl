package de.dfki.server.receiver;

import de.dfki.server.notifications.DataNotification;

import java.util.LinkedList;

/**
 * Created by alvaro on 4/28/17.
 */
public class Notifier implements ReceiverObservable {
    private LinkedList<ReceiverObserver> observers = new LinkedList<>();

    @Override
    public void register(ReceiverObserver observer) {
        observers.add(observer);
    }

    @Override
    public void unregister(ReceiverObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyAll(DataNotification notification) {
        for (ReceiverObserver observer: observers  ) {
            observer.update(notification);
        }
    }

    LinkedList<ReceiverObserver> getObservers(){
        return observers;
    }
}
