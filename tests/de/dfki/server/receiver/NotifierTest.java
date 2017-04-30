package de.dfki.server.receiver;

import de.dfki.server.notifications.DataNotification;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by alvaro on 4/28/17.
 */
public class NotifierTest {
    Notifier notifier;
    private ReceiverObserver dummyObserver;

    @Test
    public void
    register_OneObserverItem_TotalListIsOne(){
        makeNotifierWithOneObject();
        assertEquals(1, notifier.getObservers().size());
    }

    private void makeNotifierWithOneObject() {
        notifier = new Notifier();
        dummyObserver = new DummyObserver();
        notifier.register(dummyObserver);
    }

    @Test public void
    unregister_OneItemAddedAndThenRemoved_TotalListIsZero(){
        makeNotifierWithOneObject();
        notifier.unregister(dummyObserver);
        assertEquals(0, notifier.getObservers().size());
    }

    private class DummyObserver implements ReceiverObserver {
        @Override
        public void update(DataNotification notification) {

        }
    }
}