package com.vioson.chunlian.fragments;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.vioson.chunlian.R;
import com.vioson.chunlian.activitys.ShowTextActivity2;
import com.vioson.chunlian.util.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LunarFragment extends Fragment {
    private GridView list2;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> mData;
    //    private String[] mText = {getString(R.string.shu), getString(R.string.niu),
//            getString(R.string.hu), getString(R.string.tu), getString(R.string.long_), getString(R.string.she)
//            , getString(R.string.ma), getString(R.string.yang), getString(R.string.hou),
//            getString(R.string.ji), getString(R.string.gou), getString(R.string.zhu)};
    private ArrayList<String> mList;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mList = new ArrayList<>();
        mList.add(getString(R.string.shu));
        mList.add(getString(R.string.niu));
        mList.add(getString(R.string.hu));
        mList.add(getString(R.string.tu));
        mList.add(getString(R.string.long_));
        mList.add(getString(R.string.she));
        mList.add(getString(R.string.ma));
        mList.add(getString(R.string.yang));
        mList.add(getString(R.string.hou));
        mList.add(getString(R.string.ji));
        mList.add(getString(R.string.gou));
        mList.add(getString(R.string.zhu));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.text_animation2, container, false);
        mData = new ArrayList<Map<String, Object>>();
        list2 = (GridView) view.findViewById(R.id.list2);
        adapter = new SimpleAdapter(getActivity(), getData(), R.layout.mylist2, new String[]{"icon", "text"}, new int[]{R.id.img_list2, R.id.tv_list2});
        list2.setAdapter(adapter);
        list2.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ShowTextActivity2.setText(getTextData(position));
                jumpToText(position);
            }


        });
        return view;
    }


    private List<? extends Map<String, ?>> getData() {
        for (int i = 0; i < mList.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("text", mList.get(i));
            map.put("icon", R.drawable.ic_launcher);
            mData.add(map);
        }
        return mData;

    }

    private void jumpToText(int markNum) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), ShowTextActivity2.class);
        intent.putExtra("mark2", markNum);
        startActivity(intent);

    }

    private String getTextData(int i) {
        String data = null;
        String pos = "appDataTwo" + (i + 1) + ".db";
        SQLiteDatabase db = new DatabaseHelper(getActivity(), pos).getReadableDatabase();
        Cursor cr = db.query("DATA", null, "_id=?", new String[]{"1"}, null, null, null);
        if (cr != null) {
            while (cr.moveToNext()) {
                data = cr.getString(cr.getColumnIndex("mydata")).toString().trim();

            }
        }
        return data;
    }
}
