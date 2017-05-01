package de.dfki.gui;

import de.dfki.gui.renderers.MessagesRender;
import de.dfki.gui.renderers.MoodBarRender;
import de.dfki.gui.renderers.Renderable;
import de.dfki.server.receiver.DataReceiver;
import de.dfki.server.receiver.ReceiverObserver;
import de.dfki.server.socketserver.ServerController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    private DataReceiver receiver;
    private ServerController serverController;
    private Renderable messageRender;
    private Renderable moodBarRender;
    @FXML
    private HBox moodBar;
    @FXML
    private GridPane messagesControls;


    private void initServer() {
        Thread thread = new Thread(() -> serverController = new ServerController(receiver));
        thread.start();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        receiver = new DataReceiver();
        messageRender = new MessagesRender(messagesControls);
        moodBarRender = new MoodBarRender(moodBar);
        receiver.register((ReceiverObserver) moodBarRender);
        receiver.register((ReceiverObserver) messageRender);
        messagesControls.setAlignment(Pos.CENTER);
        messagesControls.setHgap(10);
        messagesControls.setVgap(10);
        initServer();
    }
}
