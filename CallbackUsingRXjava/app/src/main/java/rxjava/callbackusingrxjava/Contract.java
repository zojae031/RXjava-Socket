package rxjava.callbackusingrxjava;

import java.io.IOException;

public interface Contract {
    interface View{
        void result1(String data);

        void result2(String data);

        void result3(String data);
    }
    interface Presenter{
        void buttonClicked(int data) throws IOException;

        void closeSocket();
    }
}
