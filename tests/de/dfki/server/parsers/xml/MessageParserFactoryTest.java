package de.dfki.server.parsers.xml;

import de.dfki.server.parsers.Parser;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by alvaro on 4/30/17.
 */
public class MessageParserFactoryTest {
    private StringBuilder xmlBuilder;
    private MessageParserFactory factory;
    @Test
    public void
    buildParser_MessageXMLTag_MoodParser(){
        factory = new MessageParserFactory();
        String xmlMood = makeSimpleXML();
        Parser parser = factory.buildParser(xmlMood );
        assertTrue(parser instanceof MoodParser);
    }

    public String makeSimpleXML() {
        xmlBuilder = new StringBuilder();
        xmlBuilder.append("<?xml version=\"1.0\"?> ");
        xmlBuilder.append("<messages type=\"mood\">");
        xmlBuilder.append("<values>");
        xmlBuilder.append("<value>");
        xmlBuilder.append("1");
        xmlBuilder.append("</value>");
        xmlBuilder.append("</values>");
        xmlBuilder.append("</messages>");
        return xmlBuilder.toString();
    }
}