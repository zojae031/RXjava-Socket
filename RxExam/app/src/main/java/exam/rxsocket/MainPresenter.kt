package exam.rxsocket

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainPresenter(private val view: Contract.View) : Contract.Presenter {
    private var disposable: Disposable?=null
    private var socket : RXSocket?=null
    override fun connectSocket() {
        socket = RXSocket()
        disposable = socket!!.connect()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { Log.e("doOnSubscribe", "구독!") }
            .doOnComplete { Log.e("doOnComplete", "성공!") }
            .doOnError {
                Log.e("doOnError", "에러띠 : $it")
                view.fail()
            }
            .onErrorReturnItem(
                "error"
            )
            .doOnTerminate {
                Log.e("doOnTerminate","제거")
            }
            .subscribe {
                view.success(it)
            }

    }

    override fun sendMessage(data: String) {
        Log.e("전송 데이터", data)
        socket?.sendData(data)
        view.clearText()
    }

    override fun closeSocket() {
        socket?.closeSocket()
        disposable?.dispose()
    }
}