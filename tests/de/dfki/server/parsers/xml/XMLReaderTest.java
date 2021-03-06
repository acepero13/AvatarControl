package de.dfki.server.parsers.xml;

import de.dfki.server.parsers.xml.exceptions.NoTagFound;
import de.dfki.server.parsers.xml.exceptions.NoValueProvided;
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
        makeReader(xml);
        Element element = reader.getFirstTag("messages");
        assertEquals("messages", element.getNodeName());
    }

    @Test(expected = NoTagFound.class) public void
    getTag_NonExistingTag_NoTagException() throws ParserConfigurationException, SAXException, IOException, NoTagFound {
        String xml  = makeSimpleXML();
        makeReader(xml);
        reader.getFirstTag("ThisTAgDoesNotExists");
    }

    protected void makeReader(String xml) throws IOException, ParserConfigurationException, SAXException {
        reader = new XMLReader(xml);
    }

    @Test public void
    getAllTagsValues_MessageXMLValueTag_ValueOneReturned() throws ParserConfigurationException, SAXException, IOException, NoValueProvided {
        String xml = makeSimpleXML();
        makeReader(xml);
        LinkedList<String> values = reader.getAllTagsValues("value");
        assertEquals(1, values.size());
    }

    @Test(expected = NoValueProvided.class) public void
    getAllTagValues_MissingValue_NoValueException() throws ParserConfigurationException, SAXException, IOException, NoValueProvided {
        String xml = makeSimpleXML("");
        makeReader(xml);
        LinkedList<String> values = reader.getAllTagsValues("value");
    }

    public String makeSimpleXML() {
        return makeSimpleXML("1");
    }

    public String makeSimpleXML(String value){
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
}