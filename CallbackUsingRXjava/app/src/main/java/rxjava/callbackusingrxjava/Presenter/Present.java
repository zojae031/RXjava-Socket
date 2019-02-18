package rxjava.callbackusingrxjava.Presenter;

import android.os.Handler;
import android.os.Message;

import com.google.gson.JsonObject;

import java.io.IOException;

import rxjava.callbackusingrxjava.ClientSocket;
import rxjava.callbackusingrxjava.Presentation.IPresentation;
import rxjava.callbackusingrxjava.View.View;

public class Present implements IPresentation {
    private View view;
    private ClientSocket clientSocket;
    JsonObject jsonObject;
    mHandler handler;
    public Present(View view) {
        this.view = view;
        handler = new mHandler();

        clientSocket = new ClientSocket(handler);
        clientSocket.connect();


    }
    @Override
    public void buttonClicked(int data) {
        jsonObject = new JsonObject();
        jsonObject.addProperty("key",data);
        clientSocket.send(jsonObject);
        clientSocket.receive();
    }

    @Override
    public void closeSocket() {
        try {
            clientSocket.closeSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class mHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {

            String data = (String)msg.obj;
            switch (msg.what){
                case 1: view.result1(data);
                    break;
                case 2:view.result2(data);
                    break;
                case 3:view.result3(data);
                    break;
            }
        }
    }
}
