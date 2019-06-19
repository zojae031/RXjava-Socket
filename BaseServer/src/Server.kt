import java.io.*
import java.net.InetAddress
import java.net.ServerSocket
import java.net.Socket
import java.nio.charset.StandardCharsets

object Server {
    val socket = ServerSocket(5050)
    private lateinit var client: Socket
    private val reader by lazy { BufferedReader(InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8)) }
    private val writer by lazy { BufferedWriter(OutputStreamWriter(client.getOutputStream(), StandardCharsets.UTF_8)) }
    private val out by lazy { PrintWriter(writer,true) }
    fun open() {
        try {

            println(InetAddress.getLocalHost().hostAddress)
            client = socket.accept()
            println("클라이언트 접속 : ${client.localAddress}")

            while (true) {
                //읽기
                val data = reader.readLine() ?: break
                println("수신 완료 : $data")
                //쓰기
                out.println(data)
                println("전송완료 : $data")

            }

            client.close()
            socket.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}