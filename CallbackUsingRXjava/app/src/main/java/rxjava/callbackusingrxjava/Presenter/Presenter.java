package rxjava.callbackusingrxjava.Presenter;

import android.support.annotation.NonNull;

import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import rxjava.callbackusingrxjava.ClientSocket;
import rxjava.callbackusingrxjava.ConnectServer;
import rxjava.callbackusingrxjava.Contract;

public class Presenter implements Contract.Presenter {
    Contract.View view;
    ClientSocket clientSocket;

    public Presenter(@NonNull Contract.View view) {
        this.view = view;
        clientSocket = new ClientSocket();
        clientSocket.connect();
    }

    @Override
    public void buttonClicked(int data) throws IOException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("key", data);

        Observable observable = ConnectServer.getSocketObservable()
                .subscribeOn(Schedulers.io())
                ;
        observable.subscribe();


        //서버 연결하는 Observable 만들기
        //서버 통신하는 Observable 만들기
        //ClientSocket을 뜯어고치자.

        Observable receive = Observable.just(clientSocket);


    }

    @Override
    public void closeSocket() {
        try {
            clientSocket.closeSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
