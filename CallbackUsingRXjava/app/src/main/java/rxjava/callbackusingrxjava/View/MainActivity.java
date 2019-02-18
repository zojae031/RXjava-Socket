package rxjava.callbackusingrxjava.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import rxjava.callbackusingrxjava.Presentation.Present;
import rxjava.callbackusingrxjava.R;

public class MainActivity extends AppCompatActivity implements View{
    Present present;
    Button b1,b2,b3;
    TextView textView;
    @Override
    protected void onDestroy() {
        super.onDestroy();
        present.closeSocket();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        textView = findViewById(R.id.textview);
        present = new Present(this);


        b1.setOnClickListener(v->{
            present.buttonClicked(1);
        });
        b2.setOnClickListener(v->{
            present.buttonClicked(2);
        });
        b3.setOnClickListener(v->{
            present.buttonClicked(3);
        });
        //기본 서버 연결 예시 완료
        /*
        TODO 서버로 어떠한 데이터를 case별로 보내고 (Button 활용) 그에 따른 콜백을 RXjava로 코딩해보기
        */
    }

    @Override
    public void result1(String data) {
        textView.setText(data);
    }

    @Override
    public void result2(String data) {
        textView.setText(data);
    }

    @Override
    public void result3(String data) {
        textView.setText(data);
    }
}
