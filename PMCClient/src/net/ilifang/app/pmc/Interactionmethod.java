//package net.ilifang.app.pmc;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import com.lidroid.xutils.http.RequestParams;
//
//import android.app.Activity;
//import android.content.Context;
//import android.os.Handler;
//import android.os.Message;
//import android.widget.Toast;
//
//public class Interactionmethod extends Activity{
//	Context mContext;
//	public static int currentpage=1;//当前页
//	double total;//获取下总记录数
//	public static int totalpage = 0;//总页数
//	public JSONArray getinteractiondate(String action,RequestParams params){
//		BaseActivity ba = new BaseActivity();
//		ba.PostOA(action, params, new Handler(){
//			public void handleMessage(Message msg){
//				super.handleMessage(msg);
//				if(((JSONObject)msg.obj).has("msg")){
//					String message= ((JSONObject)msg.obj).optString("msg");
//					Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
//				}
//				int total = ((JSONObject)msg.obj).optInt("total");
//				totalpage=grttotalpage(total);
//				String strResult = ((JSONObject)msg.obj).optString("rows");
//				System.out.println(strResult); 
//				try {
//					JSONArray jsonlast=new JSONArray(strResult);
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		});
//		return null;
//	}
//	public int grttotalpage(int total){//获取数据页数的方法
//		double signopage = total/10;//获取浮点型的数据
//		int ii = (int)signopage;//获取整形的数据
//		double rr = signopage - ii;//为计算页数进行比较前的准备
//		if(rr>0){totalpage=ii+1;
//		}else{totalpage = ii;}//由此得出所需要的页数将此值赋给变量totalpage
//		return totalpage;}
//}
