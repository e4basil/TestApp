package com.test.android.testapp.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.test.android.testapp.R;

/**
 * Created by basi on 30/1/17.
 */

public class FadingTextView extends AppCompatTextView {

    private Animation fadeInAnimation, fadeOutAnimation;
    private Handler handler;
    private CharSequence[] texts;
    private boolean isShown;
    private int position;
    private int timeout = 15000;

    public FadingTextView(Context context) {
        super(context);
        init(context);
    }

    public FadingTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        handleAttrs(context, attrs);
    }

    public FadingTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        handleAttrs(context, attrs);
    }

    public void resume() {
        isShown = true;
        startAnimation();
    }

    public void pause() {
        isShown = false;
        stopAnimation();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        pause();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        resume();
    }

    private void init(Context context) {

        fadeInAnimation = AnimationUtils.loadAnimation(context, R.anim.fadein);
        fadeOutAnimation = AnimationUtils.loadAnimation(context, R.anim.fadeout);
        handler = new Handler();
        isShown = true;
    }

    private void handleAttrs(Context context, AttributeSet attrs) {

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.fading_text_view);
        this.texts = array.getTextArray(R.styleable.fading_text_view_texts);
        this.timeout = array.getInteger(R.styleable.fading_text_view_timeout, timeout);

        array.recycle();
        startAnimation();
    }


    public CharSequence[] getTexts() {
        return texts;
    }

    /**
     * for Resource String array
     *
     * @param texts
     */
    public void setTexts(@ArrayRes int texts) {
        if (getResources().getStringArray(texts).length < 1)
            throw new IllegalArgumentException("There must be at least one text");
        else {
            this.texts = getResources().getStringArray(texts);
            stopAnimation();
            position = 0;
            startAnimation();
        }
    }

    /**
     * for string array
     *
     * @param texts
     */
    public void setTexts(@NonNull String[] texts) {
        if (texts.length < 1)
            throw new IllegalArgumentException("There must be at least one text");
        else {
            this.texts = texts;
            stopAnimation();
            position = 0;
            startAnimation();

        }
    }

    public void setTimeout(int timeout) {
        if (timeout < 1)
            throw new IllegalArgumentException("Timeout must be longer than 0");
        else
            this.timeout = timeout;
    }

    private void stopAnimation() {
        handler.removeCallbacksAndMessages(null);
        if (getAnimation() != null) getAnimation().cancel();
    }

    protected void startAnimation() {
        setText(texts[position]);
        startAnimation(fadeInAnimation);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startAnimation(fadeOutAnimation);
                getAnimation().setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        if (isShown) {
                            position = position == texts.length - 1 ? 0 : position + 1;
                            startAnimation();
                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        }, timeout);
    }
}
