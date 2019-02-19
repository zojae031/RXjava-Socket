package rxsocket.rxsocket;

import java.nio.charset.Charset;

import moe.codeest.rxsocketclient.RxSocketClient;
import moe.codeest.rxsocketclient.SocketClient;
import moe.codeest.rxsocketclient.meta.SocketConfig;
import moe.codeest.rxsocketclient.meta.ThreadStrategy;

public class RxConnectionHelper {
    private static final String ip = "192.168.0.2";
    private static final int port = 5050;

    private static RxConnectionHelper instance;

    private static SocketClient client;

    private RxConnectionHelper() {

    }

    public static synchronized RxConnectionHelper getInstance() {

        if (instance == null) {
            instance = new RxConnectionHelper();
        }

        return instance;
    }

    public SocketClient getClient() {
        client = RxSocketClient
                .create(new SocketConfig.Builder()
                        .setIp(ip)
                        .setPort(port)
                        .setCharset(Charset.defaultCharset())
                        .setThreadStrategy(ThreadStrategy.ASYNC)
                        .setTimeout(30 * 1000)
                        .build());
        return client;
    }
}
