package de.dfki.server.parsers.xml;

import de.dfki.server.notifications.DataNotification;
import de.dfki.server.notifications.MoodNotification;
import de.dfki.server.parsers.xml.exceptions.InvalidValue;
import de.dfki.server.parsers.xml.exceptions.NoValueProvided;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


/**
 * Created by alvaro on 4/28/17.
 */
public class MoodParser extends MessagesXMLParser {
    public MoodParser(String data) throws IOException, SAXException, ParserConfigurationException {
        super(data);
        notification = new MoodNotification();
    }

    @Override
    public DataNotification parse() throws InvalidValue, NoValueProvided {
        getAllValues();
        String strValue = values.get(0);
        int value = getValue(strValue);
        getAsMoodNotification().setMood(value);
        return notification;
    }

    private int getValue(String strValue) throws InvalidValue {
        try {
            return Integer.parseInt(strValue);
        }catch (NumberFormatException exception){
            throw new InvalidValue("The value provided is not an integer");
        }
    }

    private MoodNotification getAsMoodNotification() {
        return (MoodNotification)notification;
    }
}
