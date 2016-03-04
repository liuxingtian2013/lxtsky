package net.ilifang.app.commons.utils;

import java.util.Map;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import net.ilifang.app.pmc.LoginActivity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class RequestUtils {
	static String serverInfo =null;
	/**
	 * 
	 * @param context
	 *            activity或fragement依附的activity上下文环境
	 * @param method
	 *            HttpMethod.POST或HttpMethod.GET目前也只支持这两个
	 * @param url
	 *            接口地址
	 * @param params
	 *            接口需要的url参数或者post参数
	 * @param clazz
	 *            接收接口返回内容的结果对象
	 * @param resultHandler
	 *            接口返回的数据解析后的回调处理
	 */
	public static<Entity>void attendanceHandler(final Context context, String action,Map<String, Object> params,final Class<Entity> clazz, final ResultHandler<Entity> resultHandler) {
		request(context, HttpMethod.POST,"AttendanceHandler",action,params,clazz,resultHandler);
	}
	public static<Entity>void PostOA(final Context context, String action,Map<String, Object> params,final Class<Entity> clazz, final ResultHandler<Entity> resultHandler) {
		request(context, HttpMethod.POST,"OA_handler",action,params,clazz,resultHandler);
	}
	public static<Entity> void requestlogin(final Context context, String action,Map<String, Object> params,final Class<Entity> clazz, final ResultHandler<Entity> resultHandler){
		request(context, HttpMethod.POST,"public_handler", action,params,clazz,resultHandler);
	}
	public static <Entity> void request(final Context context, HttpMethod method,String psotName,String action, Map<String, Object> params, final Class<Entity> clazz,
			final ResultHandler<Entity> resultHandler) {
		HttpUtils utils = new HttpUtils();
		RequestParams requestParams = new RequestParams("UTF8");
		if (method == HttpMethod.GET) {
			for (String key : params.keySet()) {
				requestParams.addQueryStringParameter(key, String.valueOf(params.get(key)));
			}
		} else if (method == HttpMethod.POST) {
			for (String key : params.keySet()) {
				requestParams.addBodyParameter(key, String.valueOf(params.get(key)));
			}
		} else {
			// TODO 其它处理方式
		}
		//117.34.115.45   //192.168.0.163
		//        serverInfo = getResources().getString(R.string.server_url);
		serverInfo ="http://117.34.115.45/";
		utils.send(method, serverInfo+psotName+".ashx?action="+action, requestParams, new RequestCallBack<Entity>() {
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				Toast.makeText(context, "网络异常或服务异常1", Toast.LENGTH_SHORT).show();
//				new Failedlogin().start();
			}

			@Override
			public void onSuccess(ResponseInfo<Entity> response) {
				Entity result = null;
				if (null != response) {
					if (response.result instanceof String) {
						Gson gson = new Gson();
						//                        JsonObject json = new JsonObject();
						result = gson.fromJson(String.valueOf(response.result), clazz);
					} else {
						result = response.result;
					}
					System.out.println("111111111111111111111"+response.result);
					System.out.println("222222222222222222222"+result);
					resultHandler.onResult(result);
				} else {
					Toast.makeText(context, "网络异常或服务异常2", Toast.LENGTH_SHORT).show();
				}
			}
		});

	}}
//	class Failedlogin extends Thread{
//		public  Handler mHandler;
//		@Override
//		public void run(){
//			super.run();
//			Message message = new Message();
//			message.what=0;
//			mHandler.sendMessage(message);
//		}
//	}
