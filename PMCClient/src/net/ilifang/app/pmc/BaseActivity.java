//package net.ilifang.app.pmc;
//
//import java.util.List;
//
//import org.apache.http.NameValuePair;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import com.lidroid.xutils.HttpUtils;
//import com.lidroid.xutils.exception.HttpException;
//import com.lidroid.xutils.http.RequestParams;
//import com.lidroid.xutils.http.ResponseInfo;
//import com.lidroid.xutils.http.callback.RequestCallBack;
//import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Toast;
//
//public class BaseActivity extends Activity{//这是我的基类
//	String serverInfo = null;
//	public void PostOA(String action, RequestParams params,final Handler handler) {
//		PostServer("OA_handler",action,params,handler);
//	}
//	public void postLogin(String action, RequestParams params,final Handler handler){
//		PostServer("public_handler",action,params,handler);
//	}
//	public void PostServer(String psotName,String action, RequestParams params,final Handler handler){
//		//117.34.115.45   //192.168.0.163
////      serverInfo = getResources().getString(R.string.server_url);
//		serverInfo ="http://192.168.0.163/";
//		HttpUtils http = new HttpUtils();
//		http.configTimeout(10000);
//		http.send(HttpMethod.POST, serverInfo+psotName+".ashx?action="+action, params, new RequestCallBack<String>(){
//			Message msg = new Message();
//			@Override
//			public void onFailure(HttpException arg0, String arg1) {
//				String strResult = "{\"status\":-1, \"msg\":\"服务器异常或网络异常\"}";
//					try {
//						msg.obj = new JSONObject(strResult);
//						handler.sendMessage(msg);
//					} catch (JSONException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//			}
//			@Override
//			public void onSuccess(ResponseInfo<String> response) {
//				Log.d("BOBBY", response+"");
//				JSONObject jo;
//				try {
//					jo = new JSONObject(response.result);
//					int status =0;
//					if(jo.has("status")){
//						status = jo.optInt("status");}
//					if(status==500||status==-1){
//						Toast.makeText(getApplicationContext() ,jo.optString("msg"), Toast.LENGTH_SHORT).show();
//					}else if(status==200){
//						msg.obj = jo;
//						handler.sendMessage(msg);
//					}else{
//						msg.obj = jo;
//						handler.sendMessage(msg);
//					}
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//}
