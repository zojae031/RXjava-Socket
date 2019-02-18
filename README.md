# RXjavaExample
RXjava / RXAndroid 예제

## 개요
Problem1. 콜백을 이용한다.  
-> 콜백지옥에 빠져 코드가독성이 현저히 저하

Problem2. Android.os.Handler 를 이용한다.  
-> 코드는 비교적 깔끔해지지만 Message Queue관리와 다른 인스턴스 접근이 힘들다.

##### 위 두가지 문제점을 해결하기 위해 Reactive Extension (ReactiveX)의 RXJava 사용, 공부하는데 목적이 있는 프로젝트

## 추가해야 하는 부분

### app.gradle::

~~~
android {
compileOptions {
sourceCompatibility JavaVersion.VERSION_1_8
targetCompatibility JavaVersion.VERSION_1_8
      }
    }
    
dependencies {
    //rxjava
implementation 'io.reactivex.rxjava2:rxandroid:2.0.1'
implementation 'io.reactivex.rxjava2:rxjava:2.1.3'
    //lifecycle
implementation 'com.trello.rxlifecycle2:rxlifecycle:2.1.0'
implementation 'com.trello.rxlifecycle2:rxlifecycle-android:2.1.0'
implementation 'com.trello.rxlifecycle2:rxlifecycle-components:2.1.0'

}
~~~

