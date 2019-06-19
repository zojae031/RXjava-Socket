package exam.rxsocket

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import io.reactivex.FlowableOnSubscribe
import java.io.*
import java.lang.Exception
import java.net.InetSocketAddress
import java.net.Socket
import java.nio.charset.StandardCharsets

class RXSocket {
    companion object {
        private const val IP = "192.168.123.3"
        private const val port = 5050
    }


    private var socket: Socket ?=null
    private val writer: BufferedWriter by lazy {
        BufferedWriter(
            OutputStreamWriter(
                socket!!.getOutputStream(),
                StandardCharsets.UTF_8
            )
        )
    }
    private val reader: BufferedReader by lazy {
        BufferedReader(
            InputStreamReader(
                socket!!.getInputStream(),
                StandardCharsets.UTF_8
            )
        )
    }
    private val out: PrintWriter by lazy { PrintWriter(writer, true) }

    fun connect(): Flowable<String> = Flowable.create({ emitter ->
        socket = Socket()
        socket!!.connect(InetSocketAddress(IP, port), 3000)

        while (!socket!!.isClosed) {
            try {
                emitter.onNext(reader.readLine() ?: "error")
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }

        emitter.onComplete()
    }, BackpressureStrategy.BUFFER)

    fun sendData(data: String) {
        Thread { out.println(data) }.start()
    }

    fun closeSocket() {
        socket?.close()
    }
}