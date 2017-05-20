package de.dfki.server.socketserver;

import de.dfki.server.receiver.Receiver;

import java.io.*;
import java.net.Socket;

/**
 * Created by alvaro on 4/30/17.
 */
public class ServerThread extends Thread {
    public static final String QUIT_COMMAND = "QUIT";
    private final Socket socket;
    private String line = "";
    private BufferedReader is = null;
    private DataOutputStream os = null;
    private Receiver receiver;
    private String clientId;

    public ServerThread(Socket socket, Receiver receiver, String clientId) throws IOException {
        this.clientId = clientId;
        this.socket = socket;
        this.receiver = receiver;
        init(socket);
        os.writeBytes(clientId + "\n");

    }

    private void init(Socket socket) throws IOException {
        is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        os = new DataOutputStream(socket.getOutputStream());
    }

    public void run() {
        try {
            line = is.readLine();
            keepReadingData();
        } catch (IOException e) {
            reportError();
        } finally {
            cleanup();
        }
    }

    private void cleanup() {
        try {
            os.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void reportError() {
        line = this.getName(); //reused String line for getting thread name
        System.out.println("IO Error/ Client " + line + " terminated abruptly");
    }

    private void keepReadingData() throws IOException {
        while (!isQuitCommand()) {
            System.out.println(line);
            receiver.receive(line.trim());
            System.out.println("Response to Client  :  " + line);
            line = is.readLine();
        }
    }
    public void interrupt(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isQuitCommand() {
        return line.contains(QUIT_COMMAND) && line.substring(QUIT_COMMAND.length() + 1).equals(clientId);
    }
}
