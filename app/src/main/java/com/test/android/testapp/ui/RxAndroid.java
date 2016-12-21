package com.test.android.testapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.test.android.testapp.R;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by basi on 20/8/16.
 */
public class RxAndroid extends AppCompatActivity {

    private static final String TAG = RxAndroid.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_android);
        Log.i(TAG, "onCreate: ");

    }

    @Override
    protected void onResume() {
        super.onResume();

//        RxIntro();
//        RxJust();
//        RxJust2();
//        RxJust3();
//        RxMap();
//        RxhashCode();
//        RxhashCode2String();
        RxhashCode2String2();
    }

    private void RxhashCode2String2() {
        Observable.just("Hello, world!")
                .map(s -> s + " -Basi")
                .map(s -> s.hashCode())
                .map(i -> Integer.toString(i))
                .subscribe(s -> Log.d(TAG, "RxhashCode2String2: "+s));
    }

    private void RxhashCode2String() {
        Observable.just("Hello Hai")
                .map(String::hashCode)
                .map(integer ->
                        Integer.toString(integer))
                .subscribe(s -> {
                    Log.d(TAG, "RxhashCode2String: "+s);
                });

    }

    private void RxhashCode() {

        Observable.just("Hello Hai")
                .map(String::hashCode)
                .subscribe(integer -> {
                    Log.d(TAG, "RxhashCode: "+integer);
                });
    }

    private void RxMap() {

        Observable.just("Hello Hai")
                .map(s -> {
                    return s + "-Basi";
                })
                .subscribe(r -> {
                    Log.d(TAG, "RxMap: " + r);
                });
    }

    private void RxJust3() {

        Observable.just("Hello Hai")
                .subscribe(s -> {
                    Log.d(TAG, "RxJust3: " + s);
                });
    }

    private void RxJust2() {
        Observable.just("Hai Basil")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d(TAG, "call: " + s);
                    }
                });

    }

    private void RxJust() {

        Observable<String> stringObservable = Observable.just("Hello world");

        Action1<? super String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "call: " + s);
            }
        };

        stringObservable.subscribe(onNextAction);

    }

    private void RxIntro() {
        Observable<Integer> integerObservable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 5; i++) {
                    subscriber.onNext(i);
                }

                subscriber.onCompleted();
            }
        });

//        Subscription subscription = integerObservable.subscribe(new Subscriber<Integer>() {
//            @Override
//            public void onCompleted() {
//                Log.d(TAG, "onCompleted: ");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d(TAG, "onError: " + e.getMessage());
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                Log.d(TAG, "onNext: " + integer);
//            }
//        });

        Observer<Integer> integerObserver = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext: " + integer);
            }
        };

        integerObservable.subscribe(integerObserver);
    }


}

