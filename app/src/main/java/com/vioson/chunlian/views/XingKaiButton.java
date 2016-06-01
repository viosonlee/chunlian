package com.vioson.chunlian.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.Button;

/**
 * Author:李烽
 * Date:2016-06-01
 * FIXME
 * Todo
 */
public class XingKaiButton extends Button {
    public XingKaiButton(Context context) {
        this(context, null);
    }

    public XingKaiButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XingKaiButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setGravity(Gravity.CENTER);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Typeface fromAsset = Typeface.createFromAsset(getContext().getAssets(), "fonts/mini.TTF");
        setTypeface(fromAsset);
    }
}
