package de.dfki.server.parsers.xml;

import de.dfki.server.notifications.DataNotification;
import de.dfki.server.notifications.OptionsNotification;
import de.dfki.server.parsers.xml.exceptions.NoValueProvided;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by alvaro on 4/28/17.
 */
public class OptionsParser  extends MessagesXMLParser{
    LinkedList<String> options = new LinkedList<>();
    public OptionsParser(String data) throws IOException, SAXException, ParserConfigurationException {
        super(data);
        notification = new OptionsNotification();
    }

    @Override
    public DataNotification parse() throws NoValueProvided {
        options = getAllValues();
        getAsOptionNotification().setOptions(options);
        return notification;
    }

    protected OptionsNotification getAsOptionNotification() {
        return (OptionsNotification)notification;
    }

    public LinkedList<String> getOptions() {
        return options;
    }
}
