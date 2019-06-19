package exam.rxsocket

interface Contract {
    interface View{
        fun success(data:String)
        fun fail()
        fun clearText()
    }
    interface Presenter{
        fun connectSocket()
        fun sendMessage(data:String)
        fun closeSocket()
    }
}