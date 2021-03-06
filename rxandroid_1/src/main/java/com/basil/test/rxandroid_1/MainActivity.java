package com.basil.test.rxandroid_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        justOperator();
//        fromOperator();
//        mapOperator();
        network();
    }

    private void network() {
        Observable<String> stringObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    String data = fetchFromGoogle("test");
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });

        stringObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d(TAG, "call: " + s);
                    }


                });
    }

    private String fetchFromGoogle(String s) {
        return s;
    }

    private void mapOperator() {
        Observable<Integer> observable = Observable.from(new Integer[]{1, 2, 3, 4, 5, 6})
                .map(integer -> {
                    return integer * integer;
                })
                .skip(2)
                .filter(integer -> {
                    return integer % 2 == 0;
                });

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onNext(Integer o) {
                Log.d(TAG, "onNext: " + o);
            }
        };


        subscription = observable.subscribe(observer);
    }

    private void justOperator() {
        Observable<String> observable = Observable.just("hello");


        Observer<String> myObserver = new Observer<String>() {
            @Override
            public void onCompleted() {
                // Called when the observable has no more data to emit
            }

            @Override
            public void onError(Throwable e) {
                // Called when the observable encounters an error
            }

            @Override
            public void onNext(String s) {
                // Called each time the observable emits data
                Log.d("MY OBSERVER", s);
            }
        };

        subscription = observable.subscribe(myObserver);
    }

    private void fromOperator() {

        Observable<Integer> integerObservable = Observable.from(new Integer[]{1, 2, 3, 4});

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onNext(Integer i) {
                Log.d(TAG, "onNext: " + i);
            }
        };

        subscription = integerObservable.subscribe(observer);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscription.unsubscribe();
    }
}
