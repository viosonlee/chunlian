package com.vioson.chunlian.control;

import android.app.Activity;
import android.content.Context;

import com.vioson.chunlian.jsoup.DataTool;
import com.vioson.chunlian.util.DataUtil;

import lee.vioson.utils.DialogShower;
import lee.vioson.utils.NetWorkUtils;

/**
 * Author:李烽
 * Date:2016-06-22
 * FIXME
 * Todo 数据控制
 */
public class CLController {

    private static CLController instance;

    private CLController() {
    }

    public synchronized static CLController getInstance() {
        if (instance == null)
            synchronized (CLController.class) {
                if (instance == null)
                    instance = new CLController();
            }
        return instance;
    }

    public void initCLData(Context context, DataTool.DataBackListener listener) {
        if (NetWorkUtils.hasMobileNetwork(context)) {
            DataTool.startUpdate((Activity) context, listener);
            DataUtil.inflaterData();
        } else {
            DataUtil.inflaterCLData();
//            DataUtil.inflaterData();
            listener.onAllUpdate(0);
        }
    }


    public void upDateCLDate(Context context, DataTool.DataBackListener listener) {
        if (NetWorkUtils.hasMobileNetwork(context)) {
            DataTool.startUpdate((Activity) context, listener);
        } else {
            DialogShower.showNetWorkDisconnect(context);
        }
    }

    public static void destroy() {
        instance = null;
    }
}
