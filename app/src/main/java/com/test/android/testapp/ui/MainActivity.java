package com.test.android.testapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;

import com.test.android.testapp.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int Counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById();
    }

    private void findViewById() {


    }


    public void RxAndroid(View view) {
        startActivity(new Intent(this, RxAndroid.class));
    }

    public void Glide(View view) {
        startActivity(new Intent(this, GlideActivity.class));
    }

    public void CustomViews(View view) {
        startActivity(new Intent(this, CustomViews.class));
    }

    public void FragmentBasics(View view) {
        startActivity(new Intent(this, FragmentBasicsActivity.class));
    }

    public void Stripe(View view){
        startActivity(new Intent(this,StripeActivity.class));
    }
    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {


        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
