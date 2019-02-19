package rxsocket.rxsocket;

public interface Contract {
    interface View{
        void OnConnected();
        void OnDisconnected();
        void onResult(String data);
    }
    interface Presenter{
        void connectServer();
        void disConnectServer();
        void sendData(String data);

    }
}
