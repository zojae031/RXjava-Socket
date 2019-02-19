package rxsocket.rxsocket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

public class MainActivity extends AppCompatActivity implements Contract.View, View.OnClickListener {

    TextView tv;
    Presenter presenter;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.disConnectServer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.textView);

        presenter = new Presenter(this);
        presenter.connectServer();

    }

    void makeToast(String data) {
        Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnConnected() {
        makeToast("연결 성공");
    }

    @Override
    public void OnDisconnected() {
        makeToast("연결 끊김");
    }

    @Override
    public void onResult(String data) {
        tv.setText(data);
    }

    @Override
    public void onClick(View v) {

        int result = 0;
        switch (v.getId()) {


            case R.id.bt1:
                result = 1;
                break;
            case R.id.bt2:
                result = 2;
                break;
            case R.id.bt3:
                result = 3;
                break;


        }

        JsonObject data = new JsonObject();
        data.addProperty("key", result);

        presenter.sendData(data.toString()+"\r\n");


    }
}
