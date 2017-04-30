package de.dfki.server.receiver;

import de.dfki.server.notifications.DataNotification;
import de.dfki.server.parsers.Parser;
import de.dfki.server.parsers.xml.MessageParserFactory;
import de.dfki.server.parsers.xml.exceptions.InvalidValue;
import de.dfki.server.parsers.xml.exceptions.NoValueProvided;

/**
 * Created by alvaro on 4/28/17.
 */
public class DataReceiver implements Receiver {
    private Parser parser;
    private final ReceiverObservable notifier;
    private MessageParserFactory parserFactory;

    public DataReceiver(){
        this.notifier = new Notifier();
        parserFactory = new MessageParserFactory();
    }

    @Override
    public void receive(String data) {
        try {
            processReceivedData(data);
        } catch (InvalidValue | NoValueProvided invalidValue) {
            invalidValue.printStackTrace();
        }

    }

    private void processReceivedData(String data) throws InvalidValue, NoValueProvided {
        parser = parserFactory.buildParser(data);
        DataNotification notification = parser.parse();
        notifyAll(notification);
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
