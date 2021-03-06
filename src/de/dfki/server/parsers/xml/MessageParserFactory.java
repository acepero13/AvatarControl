package de.dfki.server.parsers.xml;

import de.dfki.server.parsers.Parser;
import de.dfki.server.parsers.xml.exceptions.NoTagFound;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by alvaro on 4/30/17.
 */
public class MessageParserFactory {
    public static final String INFORMATION_TAG_NAME = "messages";
    public static final String TYPE_ATTRIBUTE = "type";
    XMLReader reader;
    private String messageType;
    private String data;


    public Parser buildParser(String data) {
        Parser parser;
        try {
            this.data = data;
            parser = createParser(data);
        } catch (IOException | ParserConfigurationException | SAXException | NoTagFound e) {
            parser = new DummyParser(data);
        }
        return parser;
    }

    protected Parser createParser(String data) throws IOException, ParserConfigurationException, SAXException, NoTagFound {
        reader = new XMLReader(data);
        Element messages = reader.getFirstTag(INFORMATION_TAG_NAME);
        messageType = messages.getAttribute(TYPE_ATTRIBUTE);
        return createNewParser();
    }

    private Parser createNewParser() throws ParserConfigurationException, SAXException, IOException {
        switch (messageType){
            case "mood":
                return new MoodParser(data);
            case "options":
                return new OptionsParser(data);
            case "selection":
                return new VASelector(data);
            default:
                return new DummyParser(data);
        }
    }
}
