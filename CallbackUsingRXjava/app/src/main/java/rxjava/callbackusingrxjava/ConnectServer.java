package rxjava.callbackusingrxjava;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class ConnectServer {
    private static final String ip = "192.168.0.5";
    private static final int port = 5050;

    private static ConnectServer instance;
    private ConnectServer(){}

    public static  Socket socket = null;
    public static  BufferedReader reader=null;
    public static  BufferedWriter writer = null;
    public static PrintWriter out=null;
    public static synchronized ConnectServer getInstance(){
        if(instance == null){
            instance = new ConnectServer();
        }
        return instance;
    }
    public static Observable getSocketObservable(){
        return  Observable.create((emitter)->{
            socket = new Socket(ip,port);
            socket.setSoTimeout(5000);
             reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             out = new PrintWriter(writer,true);
            emitter.setCancellable(()->socket.close());


        });
    }
    public static void asd(String data){

    }

}
