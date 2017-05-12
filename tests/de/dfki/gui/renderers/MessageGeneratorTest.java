package de.dfki.gui.renderers;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by alvaro on 5/11/17.
 */
public class MessageGeneratorTest {
    private MessageGenerator generator;

    @Test
    public void
    generate_String_XMLMessage(){
        String message = "Hello world";
        generator = new MessageGenerator();
        String res = generator.generate(message);
        String expected = "<?xml version=\"1.0\"?><messages type=\"uiresponse\"><values><value>"
                + message + "</value></values></messages>\n";
        assertEquals(expected, res);
    }
}