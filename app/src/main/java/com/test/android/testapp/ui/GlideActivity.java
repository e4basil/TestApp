package com.test.android.testapp.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.test.android.testapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class GlideActivity extends AppCompatActivity {


    @BindView(R.id.imageView)
    ImageView imageView;

    private Unbinder unbinder;
    private String url = "http://i.imgur.com/DvpvklR.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        unbinder = ButterKnife.bind(this);
        findViewById();
        glideIntro();
    }

    private void findViewById() {
    }

    private void glideIntro() {
        Uri uri = resourceIdToUri(this, R.mipmap.ic_launcher);
        Glide.with(this)
                .load(uri)
                .into(imageView);
    }

    public static final String ANDROID_RESOURCE = "android.resource://";

    private Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE
        +context.getPackageName()
        +"/"
        +resourceId);
    }
}
