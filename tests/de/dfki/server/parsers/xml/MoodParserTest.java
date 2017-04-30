package de.dfki.server.parsers.xml;

import de.dfki.server.notifications.DataNotification;
import de.dfki.server.notifications.MoodNotification;
import de.dfki.server.notifications.NotificationType;
import de.dfki.server.parsers.Parser;
import de.dfki.server.parsers.xml.exceptions.InvalidValue;
import de.dfki.server.parsers.xml.exceptions.NoValueProvided;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.Assert.*;
/**
 * Created by alvaro on 4/28/17.
 */

/*
* TODO:
* 1. Test when no value is provided
* 2. Test string instead of integer
* */
public class MoodParserTest {
    Parser parser ;
    private StringBuilder xmlBuilder;

    @Test
    public void
    parse_MoodXMLAsString_ParsedDataNotificationObject() throws IOException, SAXException, ParserConfigurationException, InvalidValue, NoValueProvided {
        String moodXml = makeSimpleXML();
        parser = makeMoodParser(moodXml);
        DataNotification notification = parser.parse();
        assertEquals(NotificationType.MOOD, notification.getType());
    }

    @Test public void
    getMoodValue_MoodXMLAsString_ValueOne() throws IOException, SAXException, ParserConfigurationException, InvalidValue, NoValueProvided {
        String moodXml = makeSimpleXML();
        parser = makeMoodParser(moodXml);
        DataNotification notification = parser.parse();
        assertEquals(1, ((MoodNotification)notification).getMoodValue());
    }

    @Test(expected = InvalidValue.class) public void
    getMoodValue_NoValidValue_InvalidValueException() throws IOException, SAXException, ParserConfigurationException, InvalidValue, NoValueProvided {
        String moodXml = makeSimpleXML("HelloWorld");
        parser = makeMoodParser(moodXml);
        parser.parse();
    }

    @Test(expected = NoValueProvided.class) public void
    getMoodValue_NoValueProvided_InvalidValueException() throws IOException, SAXException, ParserConfigurationException, InvalidValue, NoValueProvided {
        String moodXml = makeSimpleXMLWithoutValue();
        parser = makeMoodParser(moodXml);
        parser.parse();
    }



    public String makeSimpleXML() {
        return makeSimpleXML("1");
    }

    public String makeSimpleXML(String value) {
        xmlBuilder = new StringBuilder();
        xmlBuilder.append("<?xml version=\"1.0\"?> ");
        xmlBuilder.append("<messages type=\"mood\">");
        xmlBuilder.append("<values>");
        xmlBuilder.append("<value>");
        xmlBuilder.append(value);
        xmlBuilder.append("</value>");
        xmlBuilder.append("</values>");
        xmlBuilder.append("</messages>");
        return xmlBuilder.toString();
    }

    public String makeSimpleXMLWithoutValue() {
        xmlBuilder = new StringBuilder();
        xmlBuilder.append("<?xml version=\"1.0\"?> ");
        xmlBuilder.append("<messages type=\"mood\">");
        xmlBuilder.append("<values>");
        xmlBuilder.append("</values>");
        xmlBuilder.append("</messages>");
        return xmlBuilder.toString();
    }

    private MoodParser makeMoodParser(String xmlString) throws ParserConfigurationException, SAXException, IOException {
        return new MoodParser(xmlString);
    }
}