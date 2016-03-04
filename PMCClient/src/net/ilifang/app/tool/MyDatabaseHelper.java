package net.ilifang.app.tool;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper{//建数据库和见表
	private static final String CREATE_USER="create table User ("//此为sql语句
			+ " id integer primary Key autoincrement,"
			+ " accountnumber text,"
			+ " password text,"
			+ " userid text)";
    private Context mContext;
	public MyDatabaseHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_USER);//创建数据库执行sql
//		Toast.makeText(mContext, "创建成功", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists User");//更新数据库时执行此句		
	}

}
