package rxjava.rxjavaexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    public static final String tag = "Hello";

    TextView textView;
    Button button;
    EditText editText;
    int dan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textview);
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);




        button.setOnClickListener((s)->{
            try {
                textView.setText("");
                dan = Integer.parseInt(editText.getText().toString());
                Observable
                        .range(1,9)
                        .map(row-> dan+"*"+row + "="+row*dan+"\n")
                        .subscribe(result -> textView.append(result),
                                err-> Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show());
            }
            catch (Exception e)
            {
                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
            }
            finally {
                editText.setText("");
            }



        });

    }

}
