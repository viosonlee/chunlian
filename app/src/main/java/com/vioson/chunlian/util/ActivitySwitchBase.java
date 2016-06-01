package com.vioson.chunlian.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.vioson.chunlian.activitys.WebActivity;

/**
 * Author:李烽
 * Date:2016-06-01
 * FIXME
 * Todo
 */
public class ActivitySwitchBase {


    public static final String DATA = "data";

    /**
     * 跳转到H5页面
     *
     * @param context
     * @param title
     * @param URL
     */
    public static void toH5Activity(Context context, String title, String URL) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Bundle bundle = new Bundle();
        bundle.putString(WebActivity.TITLE, title);
        bundle.putString(WebActivity.URL, URL);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
