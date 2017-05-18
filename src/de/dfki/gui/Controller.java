package de.dfki.gui;

import de.dfki.client.Client;
import de.dfki.gui.renderers.options.MessagesRender;
import de.dfki.gui.renderers.moodbar.MoodBarRender;
import de.dfki.gui.renderers.Renderable;
import de.dfki.gui.renderers.statusbar.StatusBarRenderer;
import de.dfki.server.receiver.DataReceiver;
import de.dfki.server.receiver.ReceiverObserver;
import de.dfki.server.socketserver.ServerController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    public static final String LOCALHOST = "localhost";
    public static final int PORT = 8200;
    private DataReceiver receiver;
    private ServerController serverController;
    private Renderable messageRender;
    private Renderable moodBarRender;

    @FXML
    private Pane paneBar;
    @FXML
    private GridPane messagesControls;
    @FXML
    private ImageView moodImage;
    @FXML
    private Label statusLabel;
    private Client client;

    private void initServer() {
        Thread thread = new Thread(() -> serverController = new ServerController(receiver, statusLabel));
        thread.start();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        receiver = new DataReceiver();
        initServer();
        messagesControls.setAlignment(Pos.CENTER);
        messagesControls.setHgap(10);
        messagesControls.setVgap(10);
        //createClient();
        messageRender = new MessagesRender(messagesControls, client);
        moodBarRender = new MoodBarRender(paneBar, moodImage);
        receiver.register((ReceiverObserver) moodBarRender);
        receiver.register((ReceiverObserver) messageRender);
    }


    private void createClient()  {
        while (client == null || !client.isConnected()){
            client = new Client(LOCALHOST, PORT);
            tryToConnect();
        }

    }

    private void tryToConnect() {
        try {
            client.connect();
        } catch (IOException e) {
            sleep();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
