package com.vioson.chunlian.activitys;


import android.app.Activity;
import android.content.ContentValues;
import android.view.Window;
import android.view.WindowManager;

import com.vioson.chunlian.activitys.MainActivity;
import com.vioson.chunlian.R;
import com.vioson.chunlian.util.DatabaseHelper;


public class ActivityUtils {
	private static int[]myDataTwo1={R.string.chunjie11,R.string.chunjie12,R.string.chunjie13,R.string.chunjie14,R.string.chunjie15,R.string.chunjie16};
	private static int[]myDataTwo2={R.string.chunjie21,R.string.chunjie22,R.string.chunjie23,R.string.chunjie24,R.string.chunjie25,R.string.chunjie26};
	private static int[]myDataTwo3={R.string.chunjie31,R.string.chunjie32,R.string.chunjie33,R.string.chunjie34,R.string.chunjie35,R.string.chunjie36};
	private static int []myDataTwo4={R.string.chunjie41,R.string.chunjie42,R.string.chunjie43,R.string.chunjie44,R.string.chunjie45,R.string.chunjie46};
	private static int []myDataTwo5={R.string.chunjie51,R.string.chunjie52,R.string.chunjie53,R.string.chunjie54,R.string.chunjie55,R.string.chunjie56};
	private static int []myDataTwo6={R.string.chunjie61,R.string.chunjie62,R.string.chunjie63,R.string.chunjie64,R.string.chunjie65,R.string.chunjie66};
	private static int []myDataTwo7={R.string.chunjie71,R.string.chunjie72,R.string.chunjie73,R.string.chunjie74,R.string.chunjie75,R.string.chunjie76};
	private static int []myDataTwo8={R.string.chunjie81,R.string.chunjie82,R.string.chunjie83,R.string.chunjie84,R.string.chunjie85,R.string.chunjie86};
	private static int []myDataTwo9={R.string.chunjie91,R.string.chunjie92,R.string.chunjie93,R.string.chunjie94,R.string.chunjie95,R.string.chunjie96};
	private static int []myDataTwo10={R.string.chunjie101,R.string.chunjie102,R.string.chunjie103,R.string.chunjie104,R.string.chunjie105,R.string.chunjie106};
	private static int []myDataTwo11={R.string.chunjie111,R.string.chunjie112,R.string.chunjie113,R.string.chunjie114,R.string.chunjie115,R.string.chunjie116};
	private static int []myDataTwo12={R.string.chunjie121,R.string.chunjie122,R.string.chunjie123,R.string.chunjie124,R.string.chunjie125,R.string.chunjie126};
	private static int [][]myDataTwo={myDataTwo1,myDataTwo2,myDataTwo3,myDataTwo4,myDataTwo5,myDataTwo6,myDataTwo7,myDataTwo8,myDataTwo9,myDataTwo10,myDataTwo11,myDataTwo12};

	private static int []myData1={R.string.duilian41,R.string.duilian42,R.string.duilian43,R.string.duilian44,R.string.duilian45,R.string.duilian46};
	private static int []myData2={R.string.duilian51,R.string.duilian52,R.string.duilian53,R.string.duilian54,R.string.duilian55,R.string.duilian56};
	private static int []myData3={R.string.duilian61,R.string.duilian62,R.string.duilian63,R.string.duilian64,R.string.duilian65,R.string.duilian66};
	private static int []myData4={R.string.duilian71,R.string.duilian72,R.string.duilian73,R.string.duilian74,R.string.duilian75,R.string.duilian76};
	private static int []myData5={R.string.duilian81,R.string.duilian82,R.string.duilian83,R.string.duilian84,R.string.duilian85,R.string.duilian86};
	private static int []myData6={R.string.duilian91,R.string.duilian92,R.string.duilian93,R.string.duilian94,R.string.duilian95,R.string.duilian96};
	private static int []myData7={R.string.duilian101,R.string.duilian102,R.string.duilian103,R.string.duilian104,R.string.duilian105,R.string.duilian106};
	private static int []myData8={R.string.duilian111,R.string.duilian112,R.string.duilian113,R.string.duilian114,R.string.duilian115,R.string.duilian116};
	private static int []myData9={R.string.duilian121,R.string.duilian122,R.string.duilian123,R.string.duilian124,R.string.duilian125,R.string.duilian126};
	private static int []myData10={R.string.duilian131,R.string.duilian132,R.string.duilian133,R.string.duilian134,R.string.duilian135,R.string.duilian136};
	private static int [][]myData={myData1,myData2,myData3,myData4,myData5,myData6,myData7,myData8,myData9,myData10};
	public static void requestNotTitleBar(final Activity mActivity){
		mActivity.requestWindowFeature(Window.FEATURE_NO_TITLE);
	}

	public static void requestFullscreen(final Activity mActivity) {
		final Window window = mActivity.getWindow();
		window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		window.clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		window.requestFeature(Window.FEATURE_NO_TITLE);
	}
	public static void test(){
		ContentValues values=new ContentValues();
		for(int j=0;j<myDataTwo.length;j++){
			for(int i=0;i<myDataTwo1.length;i++){
				values.put("mydata",MainActivity.getContext().getResources().getString(myDataTwo[j][i]));
				new DatabaseHelper(MainActivity.getContext(), "appDataTwo"+(j+1)+".db").getWritableDatabase().insert("DATA", null, values);
				values.clear();

			}
		}
		for(int j=0;j<myData.length;j++){
			for(int i=0;i<myData1.length;i++){
				values.put("mydata",MainActivity.getContext().getResources().getString(myData[j][i]));
				new DatabaseHelper(MainActivity.getContext(), "appData"+(j+1)+".db").getWritableDatabase().insert("DATA", null, values);
				values.clear();
			}
		}
	}
}

