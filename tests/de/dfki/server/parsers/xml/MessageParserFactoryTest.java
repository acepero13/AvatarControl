package de.dfki.server.parsers.xml;

import de.dfki.server.parsers.Parser;
import de.dfki.server.parsers.xml.exceptions.NoTagFound;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

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
        makeParserFactory();
        String xmlMood = makeSimpleXML();
        Parser parser = factory.buildParser(xmlMood );
        assertTrue(parser instanceof MoodParser);
    }

    @Test public void
    buildParser_OptionType_MoodParser(){
        makeParserFactory();
        String xmlMood = makeSimpleXML("options");
        Parser parser = factory.buildParser(xmlMood );
        assertTrue(parser instanceof OptionsParser);
    }

    @Test public void
    buildParser_NonExistingType_DummyParser(){
        makeParserFactory();
        String xmlMood = makeSimpleXML("NonExistingType");
        Parser parser = factory.buildParser(xmlMood );
        assertTrue(parser instanceof DummyParser);
    }

    @Test public void
    buildParser_FakeParserFactoryThrowIOException_DummyParser(){
        factory = new FakeMessageParserFactory();
        String xmlMood = makeSimpleXML("options");
        Parser parser = factory.buildParser(xmlMood );
        assertTrue(parser instanceof DummyParser);
    }

    protected void makeParserFactory() {
        factory = new MessageParserFactory();
    }

    public String makeSimpleXML() {
        return makeSimpleXML("mood");
    }

    public String makeSimpleXML(String type){
        xmlBuilder = new StringBuilder();
        xmlBuilder.append("<?xml version=\"1.0\"?> ");
        xmlBuilder.append("<messages type=\"" + type + "\">");
        xmlBuilder.append("<values>");
        xmlBuilder.append("<value>");
        xmlBuilder.append("1");
        xmlBuilder.append("</value>");
        xmlBuilder.append("</values>");
        xmlBuilder.append("</messages>");
        return xmlBuilder.toString();
    }

    class FakeMessageParserFactory extends MessageParserFactory{
        public FakeMessageParserFactory(){
            super();
        }
        protected Parser createParser(String data) throws IOException, ParserConfigurationException, SAXException, NoTagFound {
            throw new IOException();
        }
    }
}