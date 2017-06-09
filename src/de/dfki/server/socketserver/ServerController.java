package de.dfki.server.socketserver;

import de.dfki.gui.renderers.statusbar.StatusBarRenderer;
import de.dfki.server.receiver.DataReceiver;
import de.dfki.server.receiver.Receiver;
import de.dfki.server.serverstatus.StatusNotifier;
import de.dfki.server.serverstatus.StatusObservable;
import de.dfki.server.serverstatus.StatusObserver;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by alvaro on 4/30/17.
 */
public class ServerController implements StatusObservable {
    public static final int SERVER_PORT = 8100;
    public static final int MAX_CONNECTED_CLIENTS = 4;
    public static final String CLIENT_PREFIX = "Client:";
    public static final boolean ALLOW_MULTIPLE_CONNECTIONS_DEFAULT = true;
    private final boolean allowMultipleClients;
    private Receiver receiver;
    private ServerSocket server = null;
    private final HashMap<String, Socket> clients = new HashMap<>();
    private StatusNotifier statusNotifier = new StatusNotifier();
    private int clientsCounter = 0;

    public ServerController(Receiver receiver){
        this.allowMultipleClients = ALLOW_MULTIPLE_CONNECTIONS_DEFAULT;
        startServer(receiver);
    }

    public ServerController(Receiver receiver, boolean allowMultipleClients){
        this.allowMultipleClients = allowMultipleClients;
        startServer(receiver);
    }

    public ServerController(DataReceiver receiver, Label statusLabel) {
        this.allowMultipleClients = ALLOW_MULTIPLE_CONNECTIONS_DEFAULT;
        StatusBarRenderer statusBarRenderer = new StatusBarRenderer(statusLabel);
        register(statusBarRenderer);
        startServer(receiver);
    }

    private void startServer(Receiver receiver) {
        this.receiver = receiver;
        listen();
        connect();
    }

    public void close() throws IOException {
        notifyAll("Disconnected");
        server.close();
    }

    private void listen(){
        try {
            server = new ServerSocket(SERVER_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void connect(){
        try {
            acceptConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void acceptConnection() throws IOException {
        while (canAcceptMoreConnections()){
            Socket clientSocket = server.accept();
            String clientId = getClientId();
            notifyAll("Connected");
            ServerThread serverThread = new ServerThread(clientSocket, receiver, clientId);
            clients.put(clientId, clientSocket);
            serverThread.start();
        }
    }

    private String getClientId() {
        clientsCounter++;
        return CLIENT_PREFIX + clientsCounter;
    }

    private boolean canAcceptMoreConnections() {
        return (allowMultipleClients && MAX_CONNECTED_CLIENTS > clients.size()) || (noClientsConnected());
    }

    private boolean noClientsConnected() {
        return clients.size() == 0;
    }

    @Override
    public void register(StatusObserver observer) {
        statusNotifier.register(observer);
    }

    @Override
    public void unregister(StatusObserver observer) {
        statusNotifier.unregister(observer);
    }

    @Override
    public void notifyAll(String status) {
        statusNotifier.notifyAll(status);
    }
}
