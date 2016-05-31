package com.vioson.chunlian;



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
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.vioson.util.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AppsFragment extends Fragment {
	private ListView list1;
	private SimpleAdapter adapter;
	private List<Map<String,Object>>mList;
	private String[]mTv={getString(R.string.duilian_4_word),getString(R.string.duilian_5_word),
			getString(R.string.duilian_6_word),getString(R.string.duilian_7_word),
			getString(R.string.duilian_8_word),getString(R.string.duilian_9_word),
			getString(R.string.duilian_10_word),getString(R.string.duilian_11_word),
			getString(R.string.duilian_12_word),
			getString(R.string.duilian_13_word)};
	@Override



	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//		System.out.println("AppsFragment :: onCreateView...");

		View view = inflater.inflate(R.layout.text_animation, container, false);

		mList=new ArrayList<Map<String,Object>>();
		list1=(ListView) view.findViewById(R.id.list1);
		adapter=new SimpleAdapter(getActivity(), getData(), R.layout.mylist, new String[]{"icon","text"}, new int[]{R.id.img_list,R.id.tv_list});
		list1.setAdapter(adapter);
		list1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ShowTextActivity.setText(getTextData(position));
				jumpToText(position);
			}
		});
		return view;
	}
	private void jumpToText(int markNum) {
		Intent intent=new Intent();
		intent.setClass(getActivity(), ShowTextActivity.class);
		intent.putExtra("mark", markNum);
		startActivity(intent);

	}

	private List<Map<String,Object>> getData() {
		for(int i=0;i<mTv.length;i++){
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("text", mTv[i]);
			map.put("icon", R.drawable.ic_launcher);
			mList.add(map);
		}
		return mList;
	}
	private String getTextData(int i) {
		String data=null;
		String pos="appData"+(i+1)+".db";
		SQLiteDatabase db=new DatabaseHelper(getActivity(),pos).getReadableDatabase();
		Cursor cr=db.query("DATA",null,"_id=?",new String[]{"1"},null,null,null);
		if(cr!=null){
			while(cr.moveToNext()){
				data=cr.getString(cr.getColumnIndex("mydata")).toString().trim();
				
			}
		}
		return data;
	}

}
