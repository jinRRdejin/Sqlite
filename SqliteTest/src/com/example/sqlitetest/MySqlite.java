package com.example.sqlitetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MySqlite extends SQLiteOpenHelper{
	
	private  static String TAG = "MySqlite";
    /*
     * SQLiteOpenHelper �� ���� ��ͼ
     * 
     * context�����Ķ���
     * name ���������ݿ�����
     * factory �α깤��
     * version ���ݿ�汾>=1
     */
	public MySqlite(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		
	}
	
	public MySqlite(Context context){
		
		super(context,Constant.DATABASE_NAME,null,Constant.DATABASE_VERSION);
	}

	@Override //db���ݿ����
	public void onCreate(SQLiteDatabase db) {
		Log.i(TAG, "---------onCreate---------");
		String sql = "creat table "+Constant.DATABASE_NAME+"("+Constant.ID+" Integer primary key,"
		+Constant.NAME+" varchar(10),"+Constant.AGE+" Integer)";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i(TAG, "---------onUpgrade---------");
		
	}
	
	@Override
	public void onOpen(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		super.onOpen(db);
		Log.i(TAG, "---------onOpen---------");
	}

	

}
