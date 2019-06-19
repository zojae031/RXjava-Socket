package exam.rxsocket

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainPresenter(private val view: Contract.View) : Contract.Presenter {
    private var disposable: Disposable? = null
    private var socket: RXSocket? = null
    override fun connectSocket() {
        socket = RXSocket()
        disposable = socket!!.connect()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { Log.e("doOnSubscribe", "구독!") }
            .doOnTerminate {
                Log.e("doOnTerminate", "제거")
                closeSocket()
                disposable?.dispose()
            }
            .subscribe(
                { view.success(it) }
                , { view.fail() }
            )


    }

    override fun sendMessage(data: String) {
        Log.e("전송 데이터", data)
        socket?.sendData(data)
        view.clearText()
    }

    override fun closeSocket() {
        socket?.closeSocket()
    }
}