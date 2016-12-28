package com.test.android.testapp.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.test.android.testapp.R;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observable;
import rx.subscriptions.CompositeSubscription;

public class RxRegForm extends AppCompatActivity {

    private Unbinder unbinder;
    private CompositeSubscription compositeSubscription;
    @BindView(R.id.UsernameEdtx)
    EditText userNameEdit;
    @BindView(R.id.EmailEdtx)
    EditText emailEdit;
    @BindView(R.id.registerBtn)
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_reg_form);
        unbinder = ButterKnife.bind(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    protected void onResume() {
        super.onResume();

        test();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void test() {
        Pattern emailPattern = Pattern.compile(
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Observable<Boolean> usernameValid = RxTextView.textChanges(userNameEdit)
                .map(charSequence -> charSequence.length() > 4);

        compositeSubscription.add(usernameValid
                .doOnNext(aBoolean -> Log.d("[Rx]", "Uname " + (aBoolean ? "Valid" : "Invalid")))
                .map(aBoolean -> aBoolean ? Color.BLACK : Color.RED)
                .subscribe(integer -> userNameEdit.setTextColor(integer))
        );

        Observable<Boolean> emailObservable = RxTextView.textChanges(emailEdit)
                .map(charSequence -> emailPattern.matcher(charSequence).matches());

        compositeSubscription.add(emailObservable.distinctUntilChanged()
                .doOnNext(aBoolean -> Log.d("[Rx]", "Email " + (aBoolean ? "Valid" : "Invalid")))
                .map(aBoolean -> aBoolean ? Color.BLACK : Color.RED)
                .subscribe(integer -> emailEdit.setTextColor(integer)));

        Observable<Boolean> registerObservable = Observable.combineLatest(usernameValid, emailObservable, (aBoolean, aBoolean2) -> aBoolean && aBoolean2);
        compositeSubscription.add(registerObservable
                .distinctUntilChanged()
                .doOnNext(aBoolean -> Log.d("[Rx]", "Button " + (aBoolean ? "Enabled" : "Disabled")))
                .subscribe(aBoolean -> registerButton.setEnabled(aBoolean)));
    }
}
