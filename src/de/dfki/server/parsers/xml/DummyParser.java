package de.dfki.server.parsers.xml;

import de.dfki.server.notifications.DataNotification;
import de.dfki.server.notifications.DummyNotification;
import de.dfki.server.parsers.Parser;

/**
 * Created by alvaro on 4/30/17.
 */
public class DummyParser implements Parser {
    public DummyParser(String data) {
    }

    @Override
    public DataNotification parse() {
        return new DummyNotification();
    }
}
