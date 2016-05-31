package com.vioson.chunlian;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.vioson.util.DatabaseHelper;

public class ShowTextActivity2 extends Activity implements OnClickListener {
    private Button btn_share, btn_next, btn_previous, btn_save;
    private TextView tvText;
    static String text;
    private TextView tv_title;
    private int markNum;

    public static String getText() {
        return text;
    }

    public static void setText(String text) {
        ShowTextActivity2.text = text;
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
        btn_save = (Button) findViewById(R.id.btn_save);
        tv_title = (TextView) findViewById(R.id.tv_title);
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_share = (Button) findViewById(R.id.btn_share);
        btn_previous = (Button) findViewById(R.id.btn_previous);
        btn_previous.setOnClickListener(this);
        btn_share.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        btn_save.setOnClickListener(this);
        tv_title.setOnClickListener(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        markNum = bundle.getInt("mark2");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_previous:
                if (toDo(markNum, "ACTION_PREVIOUS") != null) {
                    tvText.setText(toDo(markNum, "ACTION_PREVIOUS"));
                    btn_save.setBackgroundResource(R.drawable.save_passed);
                } else {
                    Toast.makeText(this, getString(R.string.already_end), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_share:
                share();
                break;
            case R.id.btn_next:
                if (toDo(markNum, "ACTION_NEXT") != null) {
                    tvText.setText(toDo(markNum, "ACTION_NEXT"));
                    btn_save.setBackgroundResource(R.drawable.save_passed);
                } else {
                    Toast.makeText(this, getString(R.string.last_one), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_save:
                btn_save.setBackgroundResource(R.drawable.save);
                Toast.makeText(this, getString(R.string.save_ok), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_title:
                finish();
        }
    }

    private String toDo(int posNum, String action) {
        String myData = tvText.getText().toString();
        String data = null;
        String pos = "appDataTwo" + (posNum + 1) + ".db";
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

    private void share() {
        String msg = tvText.getText().toString().trim();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, msg);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
