package de.dfki.server.receiver;

import de.dfki.server.notifications.DataNotification;
import de.dfki.server.parsers.Parser;
import de.dfki.server.parsers.xml.exceptions.InvalidValue;
import de.dfki.server.parsers.xml.exceptions.NoValueProvided;

/**
 * Created by alvaro on 4/28/17.
 */
public class DataReceiver implements Receiver {
    private final Parser parser;
    private final ReceiverObservable notifier;


    public DataReceiver(Parser parser){
        this.parser = parser;
        this.notifier = new Notifier();
    }

    @Override
    public void receive(String data) {
        DataNotification notification = null;
        try {
            notification = parser.parse();
            notifyAll(notification);
        } catch (InvalidValue | NoValueProvided invalidValue) {
            invalidValue.printStackTrace();
        }

    }

    @Override
    public void notifyAll(DataNotification notification) {
        notifier.notifyAll(notification);
    }

    @Override
    public void register(ReceiverObserver observer) {
        notifier.register(observer);
    }

    @Override
    public void unregister(ReceiverObserver observer) {
        notifier.unregister(observer);
    }
}
