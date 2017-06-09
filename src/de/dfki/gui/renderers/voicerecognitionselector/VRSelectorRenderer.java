package de.dfki.gui.renderers.voicerecognitionselector;

import de.dfki.client.Client;
import de.dfki.gui.renderers.Renderable;
import de.dfki.gui.renderers.options.MessageGenerator;
import de.dfki.matcher.SentenceMatcher;
import de.dfki.server.notifications.*;
import de.dfki.server.receiver.ReceiverObserver;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.LinkedList;

/**
 * Created by alvaro on 6/8/17.
 */
public class VRSelectorRenderer implements Renderable, ReceiverObserver {

    private final GridPane optionsContainer;
    private String value;
    private String selectedValue = "";
    private SentenceMatcher matcher;

    public VRSelectorRenderer(GridPane optionsContainer){
        this.optionsContainer = optionsContainer;
    }

    @Override
    public void render() {
        for (Node button: optionsContainer.getChildren()) {
            if(button instanceof Button && getAsButton( button).getText().equals(selectedValue)){
                getAsButton(button).fire();
            }
        }
    }

    protected Button getAsButton(Node button) {
        return (Button)button;
    }

    @Override
    public void update(DataNotification notification) {
        if(notification.getType() == NotificationType.VASELECTOR){
            value = ((VASelectorNotification)notification).getSelectedOption();
            System.out.println("Spoken sentence");
            searchForMatch();
        }
        if(notification.getType() == NotificationType.SPEAKOPTIONS){
            LinkedList<String> options = ((OptionsNotification)notification).getOptions();
            matcher = new SentenceMatcher(options);
            System.out.println("Displayed options");
            System.out.println(options);
        }
    }

    private void searchForMatch() {
        matcher.match(value);
        if(matcher.hasMatch()){
            selectedValue = matcher.getMatch();
            System.out.println("Match founnd: " + selectedValue);
            render();
        }
    }
}
