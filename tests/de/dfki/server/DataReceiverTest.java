package de.dfki.server;

import de.dfki.gui.renderers.Renderable;
import de.dfki.server.notifications.DataNotification;
import de.dfki.server.notifications.NotificationType;
import de.dfki.server.parsers.Parser;
import de.dfki.server.receiver.DataReceiver;
import de.dfki.server.receiver.Receiver;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Created by alvaro on 4/28/17.
 */
public class DataReceiverTest {
    private Receiver dataReceiver;
    private Receiver spyReceiver;

    @Test public void
    receive_EmptyString_ReturnNothing() {
        makeReceiver();
        spyReceiver();
        spyReceiver.receive("");
        verify(spyReceiver).notifyAll(any());
    }

    private void makeReceiver() {
        dataReceiver = new DataReceiver(new DummyParser());
    }

    private void makeReceiver(Parser parser) {
        dataReceiver = new DataReceiver(parser);
    }

    private void spyReceiver(){
        spyReceiver = spy(dataReceiver);
    }




    class DummyParser implements Parser{
        public DataNotification parsedObject;

        @Override
        public DataNotification parse() {
            parsedObject = new DummyNotification();
            return parsedObject;
        }

        private class DummyNotification implements DataNotification {
            @Override
            public void render() {

            }

            @Override
            public void setRenderer(Renderable renderer) {

            }

            @Override
            public NotificationType getType() {
                return null;
            }
        }
    }
}

