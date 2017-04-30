package de.dfki.server.parsers.xml;

import de.dfki.server.notifications.DataNotification;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by alvaro on 4/28/17.
 */
public class OptionsParser  extends MessagesXMLParser{
    public OptionsParser(String data) throws IOException, SAXException, ParserConfigurationException {
        super(data);
    }

    @Override
    public DataNotification parse() {
        return null;
    }
}
