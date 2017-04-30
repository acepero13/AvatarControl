package integration;

import de.dfki.server.notifications.DataNotification;
import de.dfki.server.notifications.MoodNotification;
import de.dfki.server.receiver.DataReceiver;
import de.dfki.server.receiver.ReceiverObserver;
import de.dfki.server.socketserver.ServerController;
import org.junit.Test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import static org.junit.Assert.*;

/**
 * Created by alvaro on 4/30/17.
 */
public class ServerTest implements ReceiverObserver {
    private Socket client;
    private  PrintStream output;
    DataOutputStream os = null;
    DataInputStream is = null;
    private StringBuilder xmlBuilder;
    private ServerController controller;
    private DataReceiver receiver;
    private boolean updated = false;
    @Test
    public void
    connect_ConnectDataLocalhost_Connected() throws IOException {
        receiver = new DataReceiver();
        receiver.register(this);
        runServerThread();
        String host = "localhost";
        client = new Socket(host, ServerController.SERVER_PORT);
        os = new DataOutputStream(client.getOutputStream());
        is = new DataInputStream(client.getInputStream());

        sleep();
        if (client != null && os != null) {
            writeSimpleXML();
        }
        sleep();
        assertTrue(updated);
    }

    protected void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void runServerThread() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                controller = new ServerController(receiver);
            }
        });
        thread.start();
    }

    private void writeSimpleXML() throws IOException {
        os.writeBytes("<?xml version=\"1.0\"?>");
        os.writeBytes("<messages type=\"mood\">");
        os.writeBytes("<values>");
        os.writeBytes("<value>");
        os.writeBytes("10");
        os.writeBytes("</value>");
        os.writeBytes("</values>");
        os.writeBytes("</messages> \n");
    }

    @Override
    public void update(DataNotification notification) {
        updated  = true;
        System.out.println(notification);
        assertTrue(notification instanceof MoodNotification);
    }
}