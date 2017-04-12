package com.basil.test.rxandroid_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Subscription;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        justoperator();
        fromoperator();


    }

    private void justoperator() {
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

    private void fromoperator() {

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
