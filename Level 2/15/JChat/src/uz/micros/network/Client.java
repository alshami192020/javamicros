package uz.micros.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable {

    private final Socket socket;
    private final ClientSink sink;
    private BufferedReader in;
    private PrintWriter out;
    private String name;

    public Client(Socket sock, ClientSink sink) {
        socket = sock;
        this.sink = sink;
    }

    private void notifyEvent(ClientEventType type, String data){
        ClientEvent clientEvent = new ClientEvent();
        clientEvent.setType(type);
        clientEvent.setData(data);
        clientEvent.setName(name);

        sink.notifyEvent(clientEvent);
    }

    @Override
    public void run() {
        notifyEvent(ClientEventType.Connect,
                socket.getInetAddress().getHostAddress());
        init();
        listen();
    }

    private void listen() {
        String msg;
        try {
            msg = in.readLine();
            while (msg != null) {
                notifyEvent(ClientEventType.Message, msg);
                msg = in.readLine();
            }
            notifyEvent(ClientEventType.Disconnect, null);
        } catch (IOException e) {
            notifyEvent(ClientEventType.Disconnect, null);
        }
    }

    private void init() {
        try {
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            out = new PrintWriter(socket.getOutputStream(), true);

            name = in.readLine();
            System.out.println("Name: " + name);

            notifyEvent(ClientEventType.Login, name);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
