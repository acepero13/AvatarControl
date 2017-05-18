package de.dfki.server.serverstatus;

/**
 * Created by alvaro on 5/18/17.
 */
public interface StatusObservable {
    void register(StatusObserver observer);
    void unregister (StatusObserver observer);
    void notifyAll(String status);
}
