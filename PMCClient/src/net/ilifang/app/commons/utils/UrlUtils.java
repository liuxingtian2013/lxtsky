package net.ilifang.app.commons.utils;
import org.json.JSONException;
import org.json.JSONObject;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class UrlUtils {//第一次在家提交
	private Context context;
//	String serverInfo = null;
//	public void PostServer(String action, RequestParams params,final Handler handler){
//		serverInfo ="http://192.168.0.163/public_handler.ashx";
//		HttpUtils http = new HttpUtils();
//		http.configTimeout(10000);
//		http.send(HttpMethod.POST, serverInfo+"?action="+action, params, new RequestCallBack<String>(){
//			Message msg = new Message();
//			@Override
//			public void onFailure(HttpException arg0, String arg1) {
//				String strResult = "{\"status\":-1, \"msg\":\"服务器异常或网络异常\"}";
//				try {
//					msg.obj = new JSONObject(strResult);
//					handler.sendMessage(msg);
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//
//			@Override
//			public void onSuccess(ResponseInfo<String> response) {
//				Log.d("BOBBY", response+"");
//
//				JSONObject jo;
//				try {
//					jo = new JSONObject(response.result);
//					int status = jo.optInt("status");
//					switch (status) {
//					case 500:
//						Toast.makeText(getApplicationContext() ,"", Toast.LENGTH_SHORT).show();
//						break;
//					case -1:
//						Toast.makeText(getApplicationContext() ,"", Toast.LENGTH_SHORT).show();
//						break;
//
//					}
//					msg.obj = jo;
//					handler.sendMessage(msg);
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	public static String geturlresult(){
		Message msg = new Message();
		String strResult = msg.obj.toString();
		return strResult;
	}
}

