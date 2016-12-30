package com.test.android.testapp.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;

/**
 * Created by basi on 29/12/16.
 */

public class SeparatorTextView extends AppCompatTextView {
    public SeparatorTextView(Context context) {
        super(context);
        init(context, null);
    }

    public SeparatorTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SeparatorTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // we use the default paint,
        // our line will be the same color as the text.
        Paint paint = getPaint();

        // start at the vertical centre
        // of the textview

        int PaddingTop = getPaddingTop();
        int PaddingBottom = getPaddingBottom();
        int PaddingRight = getPaddingRight();
        int PaddingLeft = getPaddingLeft();

        int Height = getHeight();
        int Width = getWidth();

        int padding = 16;

        int top = (getHeight() + getPaddingTop() - getPaddingBottom()) / 2;

        int left = getPaddingLeft(); // start at the left margin
        int right = getWidth() - getPaddingRight(); // we draw all the way to the right
        int bottom = top + 2; // we want the line to be 2 pixel thick

        int horizontalCenter = (right + getPaddingLeft()) / 2;  // center of text

        // to measure text Width, we use the paint object.
        // correct way
        int textWidth = (int) paint.measureText(getText().toString());
        int halfTextWidth = textWidth / 2;

        int gravity = getGravity();

        if ((gravity & Gravity.START) == Gravity.START) {
            canvas.drawRect(left + textWidth + padding, //left
                    top,
                    right,
                    bottom,
                    paint);
        } else if ((gravity & Gravity.END) == Gravity.END) {
            canvas.drawRect(left,
                    top,
                    right - textWidth - padding, //right
                    bottom,
                    paint);
        } else {
            canvas.drawRect(left,
                    top,
                    horizontalCenter - halfTextWidth - padding,
                    bottom,
                    paint);
            canvas.drawRect(horizontalCenter + halfTextWidth + padding,
                    top,
                    right,
                    bottom,
                    paint);
        }
    }

    private void init(Context context, AttributeSet attrs) {
    }
}
