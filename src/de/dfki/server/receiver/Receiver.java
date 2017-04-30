package de.dfki.server.receiver;

/**
 * Created by alvaro on 4/28/17.
 */
public interface Receiver extends ReceiverObservable{
    void receive(String data);
}
