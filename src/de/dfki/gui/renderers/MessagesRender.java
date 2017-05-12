package de.dfki.gui.renderers;

import de.dfki.client.Client;
import de.dfki.server.notifications.DataNotification;
import de.dfki.server.notifications.NotificationType;
import de.dfki.server.notifications.OptionsNotification;
import de.dfki.server.receiver.ReceiverObserver;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.io.IOException;
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
        for (String option:options) {
            createOptions(option);
            updateIndexes();
        }
    }

    protected void createOptions(String option) {
        final Button button = new Button(option);
        final int column = colIndex;
        final int row = rowIndex;
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        Platform.runLater(()->optionsContainer.add(button, column, row));
        button.setOnAction(new EventHandler<ActionEvent>() {
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
        });
    }

    private void updateIndexes() {
        colIndex++;
        if(colIndex >= MAX_COLUMNS_ALLOWED){
            colIndex = 0;
            rowIndex++;
        }
    }

    protected void clearPane() {
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
}
