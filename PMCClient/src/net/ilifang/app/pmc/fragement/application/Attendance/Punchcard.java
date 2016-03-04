package net.ilifang.app.pmc.fragement.application.Attendance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.baidu.location.LocationClient;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import net.ilifang.app.commons.utils.RequestUtils;
import net.ilifang.app.commons.utils.ResultHandler;
import net.ilifang.app.commons.utils.ViewUtils;
import net.ilifang.app.pmc.LoginActivity;
import net.ilifang.app.pmc.MainActivity;
import net.ilifang.app.pmc.R;
import net.ilifang.app.pmc.entity.UserLoginInfo;
import net.ilifang.app.tool.GpsService;

public class Punchcard extends Activity implements OnClickListener{
	public LocationClient mLocationClient = null;
	private ImageButton bt;
	ProgressDialog progressDialog;
	//	private GpsReceiver receiver = null;
	private static List<String> adrr = new ArrayList<String>();
	private TextView tv1 ,tv2,tv3,tv5;
	private LinearLayout ly,lly;
	JSONObject json ;
	private Toast toast = null;
	//	private final static String TAG = GpsCore.class.getSimpleName();
	public String addressString=null;
	public String lat=null;
	public String lon=null;
	private static GpsService gpsHelper;
	private boolean  GPSflag = true ;
	private boolean  TIMflag = true ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.punchcard);
		initView();
		gpsHelper = new GpsService();
		gpsHelper.start(this.getApplicationContext());
		new GPSThread().start();
		new TimeThread().start();//启动新的线程
		//		postlasttime();
		mLocationClient = gpsHelper.mLocationClient;
	}
	public void initView() {
		// TODO Auto-generated method stub
		bt =  (ImageButton)findViewById(R.id.workbt);
		bt.setOnClickListener(this);
		tv1 = (TextView)findViewById(R.id.get_time_tv);
		tv2 = (TextView) findViewById(R.id.punchercard_tvvvvvvvv);
		tv3 = (TextView)findViewById(R.id.punch_tv_wodeweizhi);
		//tv4 = (TextView)findViewById(R.id.punch_tv_last_punchtime);
		tv5 = (TextView)findViewById(R.id.punch_tv_signname_num);
		ImageButton ib = (ImageButton) findViewById(R.id.punchcard_shuaxin_ib);
		ib.setOnClickListener(this);
		ly = (LinearLayout) findViewById(R.id.punch_Louyt_dakajilu);
		ly.setOnClickListener(this);
		lly = (LinearLayout) findViewById(R.id.layout_puncher);
		lly.setOnClickListener(this);
	}
	private void getaddress(){
		if(addressString!=null){
			String adr = addressString;
			if(!adr.equals("null")){
				adrr.add(adr);
			} if ( !adrr.isEmpty()) {
				tv3.setText(adrr.get(adrr.size()-1));
			} else{
				tv3.setText("无法获取您的具体位置，请退出重试");
			}
		}
		//		else{tv3.setText("无法获取您的具体位置，请退出重试");
		//		}
	}
	
//	@Override
//	public boolean  onKeyDown( int keyCode, KeyEvent event){//添加返回监听   返回后标记原来类   方便找到原来fragment
//		if(keyCode== KeyEvent.KEYCODE_BACK&&event.getRepeatCount()==0){
//			Intent intent = new Intent();
//			intent.putExtra("sign", "Punchcard");
//			intent.setClass(Punchcard.this, MainActivity.class);
//			startActivity(intent);
//			Punchcard.this.finish();
//			return true;
//		}
//		return super.onKeyDown(keyCode, event);
//	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.workbt:
			if (!GpsService.isGpsEnabled((LocationManager) this
					.getSystemService(Context.LOCATION_SERVICE))) {
				Toast.makeText(Punchcard.this, "GSP当前已禁用，请在您的系统设置",
						Toast.LENGTH_LONG).show();
				Intent callGPSSettingIntent = new Intent(
						android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				startActivity(callGPSSettingIntent);
				return ;
			}
			progressDialog = ProgressDialog.show(Punchcard.this, "请稍等...","正在提交数据...", true);
			sendlocation(); 
			break;
		case R.id.punch_Louyt_dakajilu:
			ViewUtils.forward(Punchcard.this, Punchcardlist.class, false);//跳转的方法
			mLocationClient.stop();
			break;
		case R.id.layout_puncher:
			ViewUtils.forward(Punchcard.this, Mappam.class, false);//跳转的方法
			mLocationClient.stop();
			break;
		case R.id.punchcard_shuaxin_ib:
			//			postlasttime();
			break;
		}
	}
	public void sendlocation(){
		String uid = LoginActivity.getuserid();
		if(lon!=null&&!lon.equals("0.0")){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("lon",lon);
			params.put("lat",lat);
			params.put("empid",uid);
			RequestUtils.attendanceHandler(Punchcard.this, "check_inout", params, UserLoginInfo.class, new ResultHandler<UserLoginInfo>(){
				@Override
				public void onResult(UserLoginInfo result) {
					// TODO Auto-generated method stub
					Toast.makeText(Punchcard.this ,result.getStatus()+result.getMsg(), Toast.LENGTH_SHORT).show();
				}});
			progressDialog.dismiss();
		}else if(lon.equals("0.0")){
			progressDialog.dismiss();
			//		mLocationClient.stop();
			//			CloseGPS();
			Toast.makeText(getApplicationContext() ,"获取坐标中，请稍候...", Toast.LENGTH_SHORT).show();
		}
		else{
			progressDialog.dismiss();
			//		mLocationClient.stop();
			//			CloseGPS();
			Toast.makeText(getApplicationContext() ,"无法获取坐标，请检查您的网络链接", Toast.LENGTH_SHORT).show();
		}
	}
	class TimeThread extends Thread{//时间线程
		@Override
		public void run(){
			do{
				try {
					Thread.sleep(1000);
					Message msg = new Message();
					msg.what = 1;//消息（一个整型值）
					handler.sendMessage(msg);//每隔1秒发送一个msg给handler
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}while(TIMflag);
		}
		private Handler handler = new Handler(){
			@Override
			public void handleMessage(Message msg){
				super.handleMessage(msg);
				switch (msg.what) {
				case 1:
					long sysTime = System.currentTimeMillis();
					CharSequence sysTimeStr = DateFormat.format("   hh:mm:ss"+"\n"+"   yyyy-MM-dd ", sysTime);
					tv1.setText(sysTimeStr);//更新时间
					break;
				}
			}	};
	}
	class GPSThread extends Thread{//获取坐标地址线程
		@Override
		public void run(){
			do{
				try {
					Message msg = new Message();
					msg.obj = gpsHelper.model;
					handler.sendMessage(msg);
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}while(GPSflag);
		}
		private Handler handler = new Handler(){
			@Override
			public void handleMessage(Message msg){
				super.handleMessage(msg);
				GpsService.GPSModel model = (GpsService.GPSModel)msg.obj;
				addressString = model.getAddr();
				lat=model.getLat()+"";
				lon=model.getLon()+"";
				Toast.makeText(	getApplicationContext(), "这两个是什么"+model.getLat()+model.getLon(), Toast.LENGTH_SHORT).show();
				getaddress();
			}	
		};
	}
}
