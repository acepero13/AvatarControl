package de.dfki.server.socketserver;

import de.dfki.server.receiver.Receiver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

/**
 * Created by alvaro on 4/30/17.
 */
public class ServerController {
    public static final int SERVER_PORT = 8100;
    private final Receiver receiver;
    private ServerSocket server = null;
    private final LinkedList<Socket> clients = new LinkedList<>();

    public ServerController(Receiver receiver){
        this.receiver = receiver;
        listen();
        connect();
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
        while (true){
            Socket clientSocket = server.accept();
            System.out.println("Connection established");
            ServerThread serverThread = new ServerThread(clientSocket, receiver);
            clients.add(clientSocket);
            serverThread.start();
        }
    }

}
