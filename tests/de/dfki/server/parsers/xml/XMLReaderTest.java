package de.dfki.server.parsers.xml;

import de.dfki.server.parsers.xml.exceptions.NoTagFound;
import org.junit.Test;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Created by alvaro on 4/30/17.
 */
public class XMLReaderTest {
    private XMLReader reader;
    private StringBuilder xmlBuilder;

    @Test
    public void
    getTag_MessageXML_MessageTagElement() throws IOException, ParserConfigurationException, SAXException, NoTagFound {
        String xml = makeSimpleXML();
        reader = new XMLReader(xml);
        Element element = reader.getFirstTag("messages");
        assertEquals("messages", element.getNodeName());
    }

    @Test public void
    getAllTagsValues_MessageXMLValueTag_ValueOneReturned() throws ParserConfigurationException, SAXException, IOException {
        String xml = makeSimpleXML();
        reader = new XMLReader(xml);
        LinkedList<String> values = reader.getAllTagsValues("value");
        assertEquals(1, values.size());
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