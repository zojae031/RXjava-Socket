package rxjava.callbackusingrxjava;

import android.os.Handler;
import android.os.Message;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClientSocket {
    private static final String ip = "192.168.25.61";
    private static final int port = 5050;
    private BufferedReader reader;
    private BufferedWriter writer;
    private Socket socket;
    Handler handler;
    Message message;
    public ClientSocket(Handler handler) {
        this.handler = handler;
    }

    public void send(final JsonObject data) {

        new Thread(() -> {
            PrintWriter out = new PrintWriter(writer, true);
            out.println(data);
        }).start();

    }

    public void receive() {
        message = handler.obtainMessage();
        new Thread(()->{
            try {
                JsonParser parser = new JsonParser();
                JsonObject result = (JsonObject) parser.parse(reader.readLine());
                message.what = result.get("key").getAsInt();
                message.obj = result.get("value").toString();
                handler.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }

    public void connect() {
        Thread connect = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket(ip, port);
                    socket.setSoTimeout(5000);
                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                } catch (SocketException e) {
                    e.printStackTrace();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        connect.start();
        try {
            connect.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public void closeSocket() throws IOException {
        reader.close();
        writer.close();
        socket.close();
    }
}
