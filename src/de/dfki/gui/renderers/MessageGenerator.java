package de.dfki.gui.renderers;

import java.util.LinkedList;

/**
 * Created by alvaro on 5/11/17.
 */
public class MessageGenerator {
    public static final String XML_MESSAGE_HEADER = "<?xml version=\"1.0\"?><messages type=\"uiresponse\">";
    private final LinkedList<String> messages = new LinkedList<>();
    private StringBuilder xml;


    public String generate(String m) {
        messages.clear();
        messages.add(m);
        addHeader();
        for (String message: messages) {
            buildBody(message);
        }
        addFooter();
        return xml.toString();
    }

    private void buildBody(String message) {
        xml.append("<value>");
        xml.append(message);
        xml.append("</value>");
    }

    private void addFooter() {
        xml.append("</values>");
        xml.append("</messages>\n");
    }

    private void addHeader() {
        xml = new StringBuilder(XML_MESSAGE_HEADER);
        xml.append("<values>");
    }
}
