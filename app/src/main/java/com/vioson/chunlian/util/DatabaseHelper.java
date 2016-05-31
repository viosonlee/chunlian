package com.vioson.chunlian.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * 封装好的一个帮助类DatabaseHelper
 * @author viosonlee
 *
 */
public class DatabaseHelper extends SQLiteOpenHelper{
	/**数据库SQL语句 添加一个表**/
	private static final String NAME_TABLE_CREATE = "create table DATA" +
			"(_id INTEGER PRIMARY KEY AUTOINCREMENT,mydata TEXT)";  

	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);

	}

	public DatabaseHelper(Context context,String DATABASE_NAME,int DATABASE_VERSION){

		this(context,DATABASE_NAME,null,DATABASE_VERSION);
	}

	public DatabaseHelper(Context context,String DATABASE_NAME){

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
