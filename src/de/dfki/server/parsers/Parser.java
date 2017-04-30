package de.dfki.server.parsers;

import de.dfki.server.notifications.DataNotification;
import de.dfki.server.parsers.xml.exceptions.InvalidValue;
import de.dfki.server.parsers.xml.exceptions.NoValueProvided;

/**
 * Created by alvaro on 4/28/17.
 */
public interface Parser {
    DataNotification parse() throws InvalidValue, NoValueProvided;
}
