package de.dfki.server.serverstatus;

/**
 * Created by alvaro on 5/18/17.
 */
public interface StatusObserver {
    void update(String status);
}
