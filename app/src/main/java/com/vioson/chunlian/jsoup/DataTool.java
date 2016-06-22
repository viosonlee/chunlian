package com.vioson.chunlian.jsoup;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;

import com.vioson.chunlian.util.DatabaseHelper;
import com.vioson.chunlian.util.ProgressDialog;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Author:李烽
 * Date:2016-06-21
 * FIXME
 * Todo
 */
public class DataTool {
    public static final String URL = "http://www.duiduilian.com/chunlian/";
    private static final String TAG = DataTool.class.getSimpleName();
    private static final int UPDATE_COMP = 0x001;
    private static final int DOCUMENT_EMPTY = 0x002;
    private static final int IO_ERROR = 0x003;

    private static final int MAX_WORD_NUMBER = 13;
    private static int wordNumber = 4;
    private static int page = 1;
    private static MyHandler handler = new MyHandler();

    public static Activity context;

    /**
     * 开始上传
     *
     * @param context
     */
    public static void startUpdate(Activity context, DataBackListener listener) {
        DataTool.context = context;
        DataTool.listener = listener;
        run();
        ProgressDialog.show(context);
    }

    public static void run() {
        new Thread(new Run()).start();
    }

    private static int totalCount = 0;

    private static void getChunLian() {
        String url = compUrl(wordNumber, page);
        try {
            Document document = Jsoup.connect(url).get();
            if (document == null) {
                handler.sendEmptyMessage(DOCUMENT_EMPTY);
                return;
            }
            Elements contentF = document.getElementsByClass("contentF");
            Elements p = contentF.select("p");
            ContentValues values = new ContentValues();
            SQLiteDatabase database = new DatabaseHelper(context, "appData" + (wordNumber - 3) + ".db")
                    .getWritableDatabase();
            database.execSQL("DELETE FROM DATA");
            database.execSQL("DELETE FROM sqlite_sequence");
            for (Element element : p) {
                String text = element.text();
//                Log.e(TAG, text);
                values.put("mydata", text);
                database.insert("DATA", null, values);
                values.clear();
                totalCount++;
            }
            database.close();
            handler.sendEmptyMessage(UPDATE_COMP);
        } catch (IOException e) {
            e.printStackTrace();
            handler.sendEmptyMessage(IO_ERROR);
        }
    }

    private static String compUrl(int wordNumber, int page) {
        String url;
        if (wordNumber == 5) {
            url = "http://www.duiduilian.com/chunjie/5zi_" + page + ".html";
        } else if (wordNumber == 7) {
            url = URL + "7zichunlian_" + page + ".html";
        } else {
            if (page == 1) {
                url = URL + wordNumber + "zi.html";
            } else {
                url = URL + wordNumber + "zi_" + page + ".html";
            }
        }
        return url;
    }


    static class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IO_ERROR:
//                    listener.onError();
                    if (wordNumber < MAX_WORD_NUMBER) {
                        page = 1;
                        wordNumber++;
                        listener.onNextWordNumber(wordNumber);
                        run();
                    } else {
                        wordNumber = 4;
                        listener.onAllUpdate(totalCount);
                        totalCount = 0;
                        ProgressDialog.dismiss(context);
                    }
                    break;
                case DOCUMENT_EMPTY:
                    if (wordNumber < MAX_WORD_NUMBER) {
                        page = 1;
                        wordNumber++;
                        listener.onNextWordNumber(wordNumber);
                        run();
                    } else {
                        wordNumber = 4;
                        listener.onAllUpdate(totalCount);
                        totalCount = 0;
                        ProgressDialog.dismiss(context);
                    }
                    break;
                case UPDATE_COMP:
                    page++;
                    listener.onNextPage(wordNumber, page);
                    run();
                    break;
            }
        }
    }

    private static DataBackListener listener;

    public interface DataBackListener {
        void onError();

        void onNextPage(int wordNumber, int page);

        void onNextWordNumber(int wordNumber);

        void onAllUpdate(int totalCount);
    }

    static class Run implements Runnable {

        @Override
        public void run() {
            getChunLian();
        }
    }
}
