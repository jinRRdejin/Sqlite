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
    	 * getReadableDatabase��getWritableDatabase�������ݿⲻ���ڴ������ݿ⣬������ݿ��Ѿ����ڣ�������ݿ�
    	 * �������Ѿ���������� Readֻ��Write��ֻд
    	 */
  
    public void creatDB(View v){
    	
    	SQLiteDatabase db = helper.getWritableDatabase();
		showMyToast("�������ݿ�");
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
			showMyToast("��������");
			break;
		case R.id.modefy_dd:
			db = helper.getWritableDatabase();
			String modefy = "update "+Constant.TABLE_NAME+" set " +
					""+Constant.NAME+"=diyi where "+Constant.ID+"=1";
			DbManager.execSQL(db, modefy);
			db.close();
			showMyToast("�޸�����");
			break;
		case R.id.delect_dd:
			db = helper.getWritableDatabase();
			String delete = "delete from "+Constant.TABLE_NAME+" where "+Constant.ID+"=2 ";
			DbManager.execSQL(db, delete);
			db.close();
			showMyToast("ɾ������");
			break;
		case R.id.api_insert:
			db = helper.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(Constant.ID, 3);//����ľ�������ݿ��ֶΣ��Լ�������ֵ
			values.put(Constant.NAME, "tony");
			values.put(Constant.AGE, 25);
			db.insert(Constant.TABLE_NAME, null, values);
			db.close();
			showMyToast("api��������");
			break;
		case R.id.api_update:
			db = helper.getWritableDatabase();
			
			/*
			 * table �޸ĵ����ݿ������
			 * values String���͵�Hash Map
			 * whereClause ��ʾ�޸ĵ�����
			 * String[]  whereArgs ��ʾ�޸�������ռλ��
			 * ����ֵ��ʾ�޸ĵ�����
			 */
			ContentValues cv = new ContentValues();
			cv.put(Constant.NAME, "robby");
//			int count = db.update(Constant.TABLE_NAME, cv, Constant.ID + "=3" , null);
			int count = db.update(Constant.TABLE_NAME, cv, Constant.ID + "=?", new String[]{"3"});
			if(count > 0){
				showMyToast("�ɹ��޸�����");
			}else{
				showMyToast("�޸�����ʧ��");
			}
		    db.close();
		    break;
		    
		case R.id.api_delect:
			db = helper.getWritableDatabase();
			int count2 = db.delete(Constant.DATABASE_NAME, Constant.ID+ "=?", new String[] {"1"});
			if(count2 > 0){
				showMyToast("�ɹ�ɾ������");
			}else{
				showMyToast("ɾ��ʧ������");
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
