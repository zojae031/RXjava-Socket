package exam.rxsocket

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() ,Contract.View{

    private val presenter = MainPresenter(this)
    private lateinit var connBtn : Button
    private lateinit var sendBtn : Button
    private lateinit var inputText : EditText
    private var flag = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        inputText = findViewById<EditText>(R.id.input)
        connBtn = findViewById<Button>(R.id.connect)
        connBtn.setOnClickListener {
            if(flag) {
                presenter.connectSocket()
                (it as Button).text = "Close"
                flag = false
            }else{
                presenter.closeSocket()
                (it as Button).text = "Connect"
                flag = true
            }

        }

        sendBtn = findViewById<Button>(R.id.send)
        sendBtn.setOnClickListener {
            presenter.sendMessage(inputText.text.toString())
        }


    }
    override fun success(data: String) {
        Toast.makeText(this,data,Toast.LENGTH_SHORT).show()
    }

    override fun fail() {
        Toast.makeText(this,"앙 에러띠",Toast.LENGTH_SHORT).show()
    }

    override fun clearText() {
        inputText.setText("")
    }

    override fun onDestroy() {
        presenter.closeSocket()
        super.onDestroy()
    }

}
