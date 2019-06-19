# RXjavaExample
RXjava / RXAndroid 예제



## 개요
Problem1. 콜백을 이용한다.  
-> 콜백지옥에 빠져 코드가독성이 현저히 저하

Problem2. Android.os.Handler 를 이용한다.  
-> 코드는 비교적 깔끔해지지만 Message Queue관리와 다른 인스턴스 접근이 힘들다.

###### 위 두가지 문제점을 해결하기 위해 Reactive Extension (ReactiveX)의 RXJava 사용, 공부하는데 목적이 있는 프로젝트   

Solution. RxJava를 이용한다.  
-> 데이터 흐름에 따라 Subscriber로 통지하기 때문에 종속성 감소 및 모듈화가 간편하다.



<hr>  

## 내용  

#### MVP Pattern을 이용한 Java Socket통신 간단한 RxJava 프로젝트
### 1. [master Branch](https://github.com/zojae031/RXjava-Socket/tree/master) : 핸들러를 이용한 간단한 서버통신 코드

<hr>  

### 2. [rx Branch](https://github.com/zojae031/RXjava-Socket/tree/rx) : Rx를 이용한 간단한 서버통신 코드  

- [RxSocket](https://github.com/zojae031/RXjava-Socket/tree/rx/RxSocket) ``coddestX Library``   

- [RxExam](https://github.com/zojae031/RXjava-Socket/tree/rx/RxExam) ``Flowable 사용 직접구현``   

- [BaseServer](https://github.com/zojae031/RXjava-Socket/tree/rx/BaseServer) ``Echo Server``   


<hr>


