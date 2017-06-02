package de.dfki.gui.renderers.options;

import de.dfki.client.Client;
import de.dfki.gui.renderers.Renderable;
import de.dfki.server.notifications.DataNotification;
import de.dfki.server.notifications.NotificationType;
import de.dfki.server.notifications.OptionsNotification;
import de.dfki.server.receiver.ReceiverObserver;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Collections;
import java.util.EventListener;
import java.util.LinkedList;

/**
 * Created by alvaro on 4/30/17.
 */
public class MessagesRender implements Renderable, ReceiverObserver {
    public static final int MAX_COLUMNS_ALLOWED = 2;
    private final GridPane optionsContainer;
    private final Client client;
    private LinkedList<String> options = new LinkedList<>();
    private int rowIndex = 0;
    private int colIndex = 0;
    private MessageGenerator messageGenerator;

    public MessagesRender(GridPane optionsContainer, Client client){
        this.optionsContainer = optionsContainer;
        messageGenerator = new MessageGenerator();
        this.client = client;
    }

    @Override
    public void render() {
        clearPane();
        Collections.shuffle(options);
        for (String option:options) {
            createOptions(option);
            updateIndexes();
        }
    }

    private void createOptions(String option) {
        final Button button = new Button(option);
        final int column = colIndex;
        final int row = rowIndex;
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        button.getStyleClass().add("dark-blue");
        Platform.runLater(()->optionsContainer.add(button, column, row));
        button.setOnAction(new SendEventHandler(button));
    }


    private void updateIndexes() {
        colIndex++;
        if(colIndex >= MAX_COLUMNS_ALLOWED){
            colIndex = 0;
            rowIndex++;
        }
    }

    private void clearPane() {
        if(optionsContainer.getChildren().size() > 0)
            Platform.runLater(()->optionsContainer.getChildren().clear());
    }

    @Override
    public void update(DataNotification notification) {
        if(notification.getType() == NotificationType.SPEAKOPTIONS){
            resetIndex();
            options = ((OptionsNotification)notification).getOptions();
            render();
        }
    }

    private void resetIndex() {
        colIndex = 0;
        rowIndex = 0;
    }

    private class SendEventHandler implements EventHandler<ActionEvent> {
        private final Button button;

        SendEventHandler(Button button) {
            this.button = button;
        }

        @Override
        public void handle(ActionEvent event) {
            String text = button.getText();
            String textToSend = messageGenerator.generate(text);
            send(textToSend);
        }

        void send(String textToSend) {
            try {
                client.send(textToSend);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
