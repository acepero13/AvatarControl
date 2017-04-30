package de.dfki.server.parsers.xml;

import de.dfki.server.notifications.DataNotification;
import de.dfki.server.parsers.Parser;
import de.dfki.server.parsers.xml.exceptions.InvalidValue;
import de.dfki.server.parsers.xml.exceptions.NoValueProvided;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by alvaro on 4/28/17.
 */
public abstract class MessagesXMLParser implements Parser{

    public static final String VALUE_TAG = "value";
    protected XMLReader reader;
    protected DataNotification notification;
    protected LinkedList<String> values = new LinkedList<>();

    public MessagesXMLParser(String data) throws ParserConfigurationException, SAXException, IOException {
        reader = new XMLReader(data);
    }

    @Override
    public abstract DataNotification parse() throws InvalidValue, NoValueProvided;

    protected LinkedList<String> getAllValues() throws NoValueProvided {
        values =  reader.getAllTagsValues(VALUE_TAG);
        shouldReturnValues();
        return values;
    }

    private void shouldReturnValues() throws NoValueProvided {
        if(values.size() <= 0 ){
            throw new NoValueProvided("No value tag provided");
        }
    }

}

