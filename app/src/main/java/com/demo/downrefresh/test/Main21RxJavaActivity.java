package com.demo.downrefresh.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.demo.downrefresh.R;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 这个界面是来实现Rxjava的
 *
 * @author w.w
 */
public class Main21RxJavaActivity extends Activity {
    private EditText path;
    private TextView result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main21);

        path = (EditText) findViewById(R.id.path);
        result = (TextView) findViewById(R.id.result);


    }

    public void clicks(View view) {
        //创建 被观察者 方法1:  create
//        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
//
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext("hehe");
//                subscriber.onNext("Hello");
//                subscriber.onNext("Hi");
//                subscriber.onNext("Aloha");
//                subscriber.onCompleted();
//            }
//        });
        //创建 被观察者 方法2:  just
//        Observable observable = Observable.just("hehe", "你妹");
        //创建 被观察者 方法3:  form
        String[] arr = {"hehe", "你妹"};
        Observable observable = Observable.from(arr);

        //创建观察者  方法1:  使用observer
//        Observer<String> observer = new Observer<String>() {
//            @Override
//            public void onCompleted() {
//                Log.i("hehe", "oncompleted");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.i("hehe", "onError" + e.getMessage());
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.i("hehe", "onNext" + s);
//            }
//        };
        //创建观察者  方法2:  实际上一般不使用observer,他是一个接口, 一般都是new它的实现类 ,Sub
        Subscriber<String> observer = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i("hehe", "oncompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("hehe", "onError" + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                Log.i("hehe", "onNext" + s);
            }
        };


        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .map(new Func1<String, String>() {
//                    @Override
//                    public String call(String s) {
//                        return s + "s--hehe";
//                    }
//                })
                .subscribe(observer);
//        observable.subscribe();

        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i("hehe", "onNext" + s);
            }

        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.i("hehe", "Throwable" + throwable.getMessage());
            }
        }, new Action0() {
            @Override
            public void call() {
                Log.i("hehe", "onComplete");
            }
        });
    }
}
