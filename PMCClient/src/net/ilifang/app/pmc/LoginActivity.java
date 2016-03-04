package net.ilifang.app.pmc;

import net.ilifang.app.commons.utils.RequestUtils;
import net.ilifang.app.commons.utils.ResultHandler;
import net.ilifang.app.commons.utils.ViewUtils;
import net.ilifang.app.pmc.entity.UserLoginInfo;
import net.ilifang.app.tool.MyDatabaseHelper;

import java.util.HashMap;
import java.util.Map;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @Description 登录
 * @author 刘兴田  
 * @date
 */
public class LoginActivity extends Activity{

    private Context context;
    public String uid = null;
    EditText userName ,userPwd;
    public ProgressDialog progressDialog;
    private static MyDatabaseHelper mydatabase;
    public  SQLiteDatabase db;//创建数据库对象
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        mydatabase = new MyDatabaseHelper(getApplicationContext(), "Userinfo.db", null, 2);
        mydatabase.getWritableDatabase();
        db=mydatabase.getWritableDatabase();
        context = LoginActivity.this;
        userName = (EditText)findViewById(R.id.edt_user_name);
        userPwd = (EditText)findViewById(R.id.edt_user_pwd);
        getnumposs();
        Button loginBtn = (Button) findViewById(R.id.btn_login);
        loginBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	progressDialog = ProgressDialog.show(LoginActivity.this, "请稍等...","玩儿命登录中...", true);
//            	RequestParams params = new RequestParams();
//                params.addBodyParameter("txtLoginName",userName.getText().toString());
//                params.addBodyParameter("txtPassword",userPwd.getText().toString());
//                postLogin("login", params, new Handler(){//继承自BaseActivity，所以直接调用  不用实例化
//                	public void handleMessage(Message msg){
//                		super.handleMessage(msg);
//                		String strResult= ((JSONObject)msg.obj).optString("msg");
//                		int status = ((JSONObject)msg.obj).optInt("status");
//                		Toast.makeText(getApplicationContext(), strResult, Toast.LENGTH_SHORT).show();
//                		if(status==200){
//                			ViewUtils.forward(context, MainActivity.class, true);//跳转的方法
//                		}
//                	}
            	 Map<String, Object> params = new HashMap<String, Object>();
                 params.put("txtLoginName", userName.getText().toString());
                 params.put("txtPassword", userPwd.getText().toString());
                 RequestUtils.requestlogin(context, "login", params, UserLoginInfo.class, new ResultHandler<UserLoginInfo>() {
                             @Override
                             public void onResult(UserLoginInfo result) {
//                                 Log.d("bobby", "{status:"+result.getStatus()+",msg:"+result.getMsg()+"}");
                                 Toast.makeText(getApplicationContext(), "是这个toast吗"+result.getMsg()+result.getUid(), Toast.LENGTH_SHORT).show();
                         		uid = result.getUid();
                                 if(200 == result.getStatus()){
                                	 remenber();
                         			ViewUtils.forward(context, MainActivity.class, true);//跳转的方法
                         		}
                                 progressDialog.dismiss();
                             } 
                         });
//                });
//                HttpUtils http = new HttpUtils();
//            	http.configTimeout(10000);
//                http.send(HttpMethod.POST, serverInfo+"?action=login", params, new RequestCallBack<String>(){
//					@Override
//					public void onFailure(HttpException arg0, String arg1) {
//						Toast.makeText(context, "网络不可用或链接超时", Toast.LENGTH_LONG).show();
//					}
//
//					@Override
//					public void onSuccess(ResponseInfo<String> response) {
//						Log.d("BOBBY", response+"");
//						
//						JSONObject jo;
//						try {
//							jo = new JSONObject(response.result);
//							int status = jo.optInt("status");
//							String msg = jo.optString("msg");
//							Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
//							if(status ==200){
//								ViewUtils.forward(context, MainActivity.class, true);
//							}
//						} catch (JSONException e) {
//							e.printStackTrace();
//						}
//					}
//                });
            }
        });

    }
//    private  Handler hander = new Handler(){//这是在没有返回值的情况下，接收发送消息关闭progressDialog
//    	public void handerMessage(Message msg){
//    		Toast.makeText(context, "看看这个消息能不能发送过来", Toast.LENGTH_SHORT).show();
//    		if(msg.what==0){
//    			progressDialog.dismiss();
//    		}
//    		super.handleMessage(msg);   
//    	}
//    };
 // remenber方法用于判断是否记住密码，采用数据库的方式将数据存储在数据库中
 	public void remenber() {
// 		if (cb.isChecked()) {
 			db.delete("User", "id >= ?", new String[]{"0"});//插数据之前先清空数据库，以保证数据库中的数据只有一条
 			ContentValues values = new ContentValues();
 			//开始组装第一条数据
 			values.put("accountnumber",userName.getText().toString());
 			values.put("password",userPwd.getText().toString());
 			values.put("userid",uid);
 			db.insert("User", null, values);
// 		} else if (!cb.isChecked()) {
// 			db.delete("User", "id >= ?", new String[]{"0"});
// 		}
 	}
 	public void getnumposs(){
 		//查询表中所有的数据；
 		Cursor cursor = db.query("User", null, null, null, null, null,null);
 		if(cursor.moveToFirst()){
 			do{
 				//				遍历Cursor对象，取出数据
 				String accountnumber = cursor.getString(cursor.getColumnIndex("accountnumber"));
 				String password = cursor.getString(cursor.getColumnIndex("password"));
 				userName.setText(accountnumber);
 				userPwd.setText(password);
 			}while(cursor.moveToNext());}
 		cursor.close();
 	}
 	public  static String  getuserid(){
 		String userid = "";
 		SQLiteDatabase db = mydatabase.getWritableDatabase();
 		//查询表中所有的数据
 		Cursor cursor = db.query("User", null, null, null, null, null,null);
 		if(cursor.moveToFirst()){
 			do{
 				//遍历Cursor对象，取出数据
 				userid = cursor.getString(cursor.getColumnIndex("userid"));
 			}while(cursor.moveToNext());
 		}cursor.close();
 		return userid;
 	}
}