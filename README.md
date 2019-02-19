# RXjavaExample
RXjava / RXAndroid 예제



## 개요
Problem1. 콜백을 이용한다.  
-> 콜백지옥에 빠져 코드가독성이 현저히 저하

Problem2. Android.os.Handler 를 이용한다.  
-> 코드는 비교적 깔끔해지지만 Message Queue관리와 다른 인스턴스 접근이 힘들다.

###### 위 두가지 문제점을 해결하기 위해 Reactive Extension (ReactiveX)의 RXJava 사용, 공부하는데 목적이 있는 프로젝트  

<hr>  

## 내용  

1. MVP Pattern을 이용한 Java Socket통신 간단한 RxJava 프로젝트
2. SingleTon을 이용하여 Socket을 하나만 유지 (by RxConnectionHelper)
3. byte[] 배열을 String 으로 변환  
``String str = new String(bytes,StandardCharsets.UTF_8);``
4. https://github.com/codeestX/RxSocketClient
- master Branch : 핸들러를 이용한 간단한 서버통신 코드
- rx Branch : Rx를 이용한 간단한 서버통신 코드  
**두 코드의 기능은 완벽히 일치한다.**

<hr>

## 추가해야 하는 부분  
##### bulidType 아래  
    allprojects {
        repositories {
            maven { url "https://jitpack.io" }
        }
    }

##### dependency  
    implementation 'com.github.codeestX:RxSocketClient:v1.0.1'
    implementation 'io.reactivex.rxjava2:rxjava:2.2.6'
    implementation 'io.reactivex.rxjava2:rxandroid:2.1.1'
    implementation 'com.google.code.gson:gson:2.8.5'

