package de.dfki.server.parsers.xml;

import de.dfki.server.notifications.DataNotification;
import de.dfki.server.notifications.MoodNotification;
import de.dfki.server.notifications.VASelectorNotification;
import de.dfki.server.parsers.xml.exceptions.InvalidValue;
import de.dfki.server.parsers.xml.exceptions.NoValueProvided;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by alvaro on 6/8/17.
 */
public class VASelector  extends MessagesXMLParser {
    public VASelector(String data) throws ParserConfigurationException, SAXException, IOException {
        super(data);
        notification = new VASelectorNotification();
    }

    @Override
    public DataNotification parse() throws InvalidValue, NoValueProvided {
        getAllValues();
        String strValue = values.get(0);
        getAsVASelectorNotification().setSelectedOption(strValue);
        return notification;
    }

    private VASelectorNotification getAsVASelectorNotification() {
        return (VASelectorNotification) notification;
    }
}
