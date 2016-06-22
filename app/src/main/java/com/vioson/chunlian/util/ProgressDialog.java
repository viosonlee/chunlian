package com.vioson.chunlian.util;

import android.content.Context;

/**
 * Author:李烽
 * Date:2016-06-22
 * FIXME
 * Todo
 */
public class ProgressDialog {
    private static android.app.ProgressDialog dialog;

    private static android.app.ProgressDialog getDialog(Context context) {
        if (dialog == null) {
            dialog = new android.app.ProgressDialog(context);
        }
        return dialog;
    }

    public static void show(Context context) {
        getDialog(context).show();
    }

    public static void dismiss(Context context) {
        getDialog(context).dismiss();
    }
}
