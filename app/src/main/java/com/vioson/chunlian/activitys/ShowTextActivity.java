package com.vioson.chunlian.activitys;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.vioson.chunlian.R;
import com.vioson.chunlian.util.DatabaseHelper;
import com.vioson.chunlian.util.ShareUtil;

public class ShowTextActivity extends BackActivity {
    private Button btn_save;
    private TextView tvText;
    static String text;
    private TextView tv_title;
    private int markNum;

    public static String getText() {
        return text;
    }

    public static void setText(String text) {
        ShowTextActivity.text = text;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_text);
        init();
    }

    private void init() {
        tvText = (TextView) findViewById(R.id.tv_text);
        tvText.setText(text);
        tv_title = (TextView) findViewById(R.id.tv_title);
        btn_save = (Button) findViewById(R.id.btn_share);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        markNum = bundle.getInt("mark");
    }

    public void save(View view) {
        btn_save.setText(getString(R.string.saved));
        Toast.makeText(this, getString(R.string.save_ok), Toast.LENGTH_SHORT).show();
    }

    public void next(View view) {
        if (toDo(markNum, "ACTION_NEXT") != null) {
            tvText.setText(toDo(markNum, "ACTION_NEXT"));
            btn_save.setBackgroundResource(R.drawable.save_passed);
        } else {
            Toast.makeText(this, getString(R.string.last_one), Toast.LENGTH_SHORT).show();
        }
    }

    public void previous(View view) {
        if (toDo(markNum, "ACTION_PREVIOUS") != null) {
            tvText.setText(toDo(markNum, "ACTION_PREVIOUS"));
            btn_save.setBackgroundResource(R.drawable.save_passed);
        } else {
            Toast.makeText(this, getString(R.string.already_end), Toast.LENGTH_SHORT).show();
        }
    }


    private String toDo(int posNum, String action) {
        String myData = tvText.getText().toString();
        String data = null;
        String pos = "appData" + (posNum + 1) + ".db";
        int p = 0;
        SQLiteDatabase db = new DatabaseHelper(this, pos).getReadableDatabase();
        Cursor cr = db.query("DATA", null, "mydata like ?", new String[]{myData}, null, null, null);
        Cursor cr2 = null;
        if (cr != null) {
            while (cr.moveToNext()) {
                p = cr.getInt(cr.getColumnIndex("_id"));
            }
        }
        if (action.equals("ACTION_PREVIOUS")) {
            cr2 = db.query("DATA", null, "_id=?", new String[]{(p - 1) + ""}, null, null, null);
        } else if (action.equals("ACTION_NEXT")) {
            cr2 = db.query("DATA", null, "_id=?", new String[]{(p + 1) + ""}, null, null, null);
        }
        if (cr2 != null) {
            while (cr2.moveToNext()) {
                data = cr2.getString(cr2.getColumnIndex("mydata")).toString();
            }
        }
        return data;

    }

    @Override
    protected void send() {
        share();
    }

    private void share() {
        String msg = tvText.getText().toString();
        ShareUtil.share(this, msg);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int i = 0;
        float x1 = i;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                if ((event.getX() - x1) > 100) {
                    Toast.makeText(this, getString(R.string.flop_right), Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return false;
    }

}
