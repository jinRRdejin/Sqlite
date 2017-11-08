package com.example.sqlitetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DbManager {

   public static MySqlite mysqlite;
   
   public static MySqlite getIntance(Context context){
	   
	   if(mysqlite == null){
		   mysqlite = new MySqlite(context); 
	   }
	   
	return mysqlite;
	   
   }
   
   public static void execSQL(SQLiteDatabase db,String sql){
	   
	   if(db != null){
		   if(!" ".equals(sql)  && null != sql ){
			   db.execSQL(sql);
		   }
	   }

   }

}
