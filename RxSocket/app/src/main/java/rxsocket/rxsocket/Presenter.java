package rxsocket.rxsocket;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import moe.codeest.rxsocketclient.SocketClient;
import moe.codeest.rxsocketclient.SocketSubscriber;

public class Presenter implements Contract.Presenter {

    private Contract.View view;
    private SocketClient client;
    private Disposable ref;

    public Presenter(Contract.View view) {
        this.view = view;
        client = RxConnectionHelper.getInstance().getClient();


    }


    @Override
    public void connectServer() {
        ref = client.connect()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SocketSubscriber() {
                    @Override
                    public void onConnected() {
                        view.OnConnected();
                    }

                    @Override
                    public void onDisconnected() {
                        view.OnDisconnected();
                    }

                    @Override
                    public void onResponse(@NotNull byte[] bytes) {
                        JsonParser parser = new JsonParser();

                        String str = new String(bytes,StandardCharsets.UTF_8);
                        JsonObject result = (JsonObject) parser.parse(str);

                        view.onResult(result.get("value").toString());
                    }
                });
    }

    @Override
    public void disConnectServer() {
        client.disconnect();
        ref.dispose();
    }

    @Override
    public void sendData(String data) {
        client.sendData(data);
    }

}
