package com.vioson.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * ��װ�õ�һ��������DatabaseHelper
 * @author Administrator
 *
 */
public class DBaseHelper extends SQLiteOpenHelper{
	/**���ݿ�SQL��� ���һ����**/  
	private static final String NAME_TABLE_CREATE = "create table DATA" +
			"(_id INTEGER PRIMARY KEY AUTOINCREMENT,mydata INTEGER)";  
	//���캯��һ
	public DBaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);

	}
	//���캯����
	public DBaseHelper(Context context,String DATABASE_NAME,int DATABASE_VERSION){
		//���ù��캯��һ
		this(context,DATABASE_NAME,null,DATABASE_VERSION);
	}
	//���캯����
	public DBaseHelper(Context context,String DATABASE_NAME){
		//���ù��캯����
		this(context,DATABASE_NAME,null,1);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(NAME_TABLE_CREATE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


	}
	public boolean deleteDatabase(Context context,String DATABASE_NAME) {  
		return context.deleteDatabase(DATABASE_NAME);  
	}  

}
