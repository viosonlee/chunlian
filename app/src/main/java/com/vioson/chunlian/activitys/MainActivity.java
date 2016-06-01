package com.vioson.chunlian.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

import com.vioson.chunlian.R;
import com.vioson.chunlian.util.DataUtil;

public class MainActivity extends Activity {
	private boolean isFirstIn;
	private static Context context = null;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final View view = View.inflate(this, R.layout.activity_main, null);
		setContentView(view);
		context = this;
//		Bmob.initialize(context, "43eb3ca2f9cce3622d100c826cb4e5db");

		Log.i("dd", Thread.currentThread().getName());
		init();
        initAnim(view);
	}

    private void initAnim(View view) {
        AlphaAnimation ap = new AlphaAnimation(0.1f, 1.0f);
        ap.setDuration(3000);
        view.startAnimation(ap);
        ap.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                jump();
            }
        });
    }


    public static Context getContext() {
		return context;
	}

	private void init() {

		SharedPreferences preferences = getSharedPreferences("first_pref",
				MODE_PRIVATE);
		isFirstIn = preferences.getBoolean("isFirstIn", true);
		if (isFirstIn) {
			writeData();
		}
	}

	private void jump() {
		startActivity(new Intent(MainActivity.this, FragmentTabActivity.class));
		finish();
	}

	public void writeData() {
		HandlerThread handlerThread = new HandlerThread("handler_Thread");
		handlerThread.start();
		MyHandler myHandler = new MyHandler(handlerThread.getLooper());
		myHandler.post(new MyThread());
	}
	class MyHandler extends Handler {

		public MyHandler(Looper looper) {
			super(looper);
		}
	}

	class MyThread implements Runnable {

		@Override
		public void run() {
			DataUtil.inflaterData();
			Log.i("dd", Thread.currentThread().getName());
		}

	}

}
