package de.dfki.server.parsers.xml;

import de.dfki.server.parsers.xml.exceptions.NoValueProvided;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Created by alvaro on 5/1/17.
 */
public class OptionsParserTest {
    OptionsParser parser ;
    private StringBuilder xmlBuilder;
    @Test
    public void
    parse_MessageWithTwoOptions_ArraySizeTwo() throws ParserConfigurationException, SAXException, IOException, NoValueProvided {
        LinkedList<String> options = new LinkedList<>();
        options.add("Hola mundo");
        options.add("Hallo welt");
        String xml = makeSimpleXML(options);
        OptionsParser parser = new OptionsParser(xml);
        parser.parse();
        assertEquals(2, parser.getOptions().size());
    }

    public String makeSimpleXML(LinkedList<String> options) {
        xmlBuilder = new StringBuilder();
        xmlBuilder.append("<?xml version=\"1.0\"?> ");
        xmlBuilder.append("<messages type=\"mood\">");
        xmlBuilder.append("<values>");
        for (String option: options  ) {
            xmlBuilder.append("<value>");
            xmlBuilder.append(option);
            xmlBuilder.append("</value>");
        }
        xmlBuilder.append("</values>");
        xmlBuilder.append("</messages>");
        return xmlBuilder.toString();
    }
}