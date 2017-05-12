package de.dfki.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by alvaro on 5/11/17.
 */
public class Client {

    private final String host;
    private final int port;
    private DataOutputStream os;
    private Socket client;

    public Client(String host, int port){
        this.host = host;
        this.port  = port;
    }


    public void connect() throws IOException {
        client = new Socket(host, port);
        os = new DataOutputStream(client.getOutputStream());
    }

    public void send(String dataToSend) throws IOException {
        os.writeBytes(dataToSend);
    }

    public boolean isConnected(){
        if(client == null)
            return false;
        return client.isConnected();
    }
}
