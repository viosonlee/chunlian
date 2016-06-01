package com.vioson.chunlian.views;

import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Author:李烽
 * Date:2016-06-01
 * FIXME
 * Todo
 */
public class XingKaiTextView extends TextView {

    public XingKaiTextView(Context context) {
        this(context, null);
    }

    public XingKaiTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XingKaiTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Typeface fromAsset = Typeface.createFromAsset(getContext().getAssets(), "fonts/mini.TTF");
        setTypeface(fromAsset);
    }
}
