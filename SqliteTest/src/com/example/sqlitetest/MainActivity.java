package com.example.sqlitetest;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class MainActivity extends Activity  implements OnClickListener{
    
	private Context mContext;
	private Toast myToast = null;
	private MySqlite helper;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = DbManager.getIntance(this);
        mContext = this;
    }       
    	/*
    	 * getReadableDatabase与getWritableDatabase都是数据库不存在创建数据库，如果数据库已经存在，这打开数据库
    	 * 当磁盘已经满的情况下 Read只读Write这只写
    	 */
  
    public void creatDB(View v){
    	
    	SQLiteDatabase db = helper.getWritableDatabase();
		showMyToast("创建数据库");
    }
    
	@Override	
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.insert_dd:
			SQLiteDatabase db = helper.getWritableDatabase();
			String sql = "insert into" +Constant.TABLE_NAME+"values(1,'zhangsan',20)";
			DbManager.execSQL(db, sql);
			String sql2 = "insert into "+Constant.TABLE_NAME+"values(2,'lisi',18)";
			DbManager.execSQL(db, sql2);
			db.close();
			showMyToast("插入数据");
			break;
		case R.id.modefy_dd:
			db = helper.getWritableDatabase();
			String modefy = "update "+Constant.TABLE_NAME+" set " +
					""+Constant.NAME+"=diyi where "+Constant.ID+"=1";
			DbManager.execSQL(db, modefy);
			db.close();
			showMyToast("修改数据");
			break;
		case R.id.delect_dd:
			db = helper.getWritableDatabase();
			String delete = "delete from "+Constant.TABLE_NAME+" where "+Constant.ID+"=2 ";
			DbManager.execSQL(db, delete);
			db.close();
			showMyToast("删除数据");
			break;
		case R.id.api_insert:
			db = helper.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(Constant.ID, 3);//插入的具体的数据库字段，以及具体数值
			values.put(Constant.NAME, "tony");
			values.put(Constant.AGE, 25);
			db.insert(Constant.TABLE_NAME, null, values);
			db.close();
			showMyToast("api插入数据");
			break;
		case R.id.api_update:
			db = helper.getWritableDatabase();
			
			/*
			 * table 修改的数据库的名称
			 * values String类型的Hash Map
			 * whereClause 表示修改的条件
			 * String[]  whereArgs 表示修改条件的占位符
			 * 返回值表示修改的条数
			 */
			ContentValues cv = new ContentValues();
			cv.put(Constant.NAME, "robby");
//			int count = db.update(Constant.TABLE_NAME, cv, Constant.ID + "=3" , null);
			int count = db.update(Constant.TABLE_NAME, cv, Constant.ID + "=?", new String[]{"3"});
			if(count > 0){
				showMyToast("成功修改数据");
			}else{
				showMyToast("修改数据失败");
			}
		    db.close();
		    break;
		    
		case R.id.api_delect:
			db = helper.getWritableDatabase();
			int count2 = db.delete(Constant.DATABASE_NAME, Constant.ID+ "=?", new String[] {"1"});
			if(count2 > 0){
				showMyToast("成功删除数据");
			}else{
				showMyToast("删除失败数据");
			}
			db.close();
		default:
			break;
		}
				
		
		
	}
	
	public void showMyToast(String text) {
        if (myToast == null) {
            myToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
            myToast.setGravity(Gravity.BOTTOM, 0, 100);
        } else {
            myToast.setText(text);
        }
        myToast.show();
    }

}
