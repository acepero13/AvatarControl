package de.dfki.server.serverstatus;

import java.util.LinkedList;

/**
 * Created by alvaro on 5/18/17.
 */
public class StatusNotifier implements StatusObservable {
    LinkedList<StatusObserver> observers = new LinkedList<>();
    @Override
    public void register(StatusObserver observer) {
        observers.add(observer);
    }

    @Override
    public void unregister(StatusObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyAll(String status) {
        for (StatusObserver observer: observers) {
            observer.update(status);
        }
    }
}
