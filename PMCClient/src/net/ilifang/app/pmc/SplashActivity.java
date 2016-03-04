package net.ilifang.app.pmc;
import net.ilifang.app.commons.utils.RequestUtils;
import net.ilifang.app.commons.utils.ResultHandler;
import net.ilifang.app.commons.utils.ViewUtils;
import net.ilifang.app.pmc.entity.RowInfo;
import net.ilifang.app.pmc.entity.Rows;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import cn.jpush.android.api.JPushInterface;
/**
 * @Description 启动画面
 * @author 曹保利
 * @date
 */
public class SplashActivity extends Activity {

	/**
	 * 启动画面停留的时间
	 */
	private final int SPLASH_DISPLAY_LENGHT = 3000;//让我看看  到底变量没//rangwo zaikankna 
	private Context context;//这是在家实验提交

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_splash);
		context = SplashActivity.this;

		new Handler().postDelayed(new Runnable() {
			public void run() {
				// TODO 初始化数据
				ViewUtils.forward(context, LoginActivity.class, Boolean.TRUE);
			}
		}, SPLASH_DISPLAY_LENGHT);

		// 以下内容为测试内容
		//        1.分页list结果请求方法
		//        Map<String, Object> params = new HashMap<String, Object>();
		//        params.put("page", 1);
		//        params.put("rows", 10);
		//
		//        RequestUtils.test(context, "get_class_list_emp", params, Rows.class,
		//                new ResultHandler<Rows>() {
		//                    @Override
		//                    public void onResult(Rows rows) {
		//                        System.out.println("这是什么鬼"+rows.getRows());
		////                        System.out.println("total:  "+rows.getTotal());//以下5行为获取对象中的各元素的方法
		////                        List<RowInfo> list = rows.getRows();
		////                        for (RowInfo rowInfo : list) {
		////                        	System.out.println("CName:  "+rowInfo.getCName());}
		//                    }
		//                });
		//         2.不用分页list结果请求方法
		//      Map<String, Object> params = new HashMap<String, Object>();
		//      params.put("txtLoginName", "admin");
		//      params.put("txtPassword", "123456");
		//      RequestUtils.request(
		//              context, 
		//              HttpMethod.POST, 
		//              "http://117.34.115.45/public_handler.ashx?action=login", params, UserLoginInfo.class,
		//              new ResultHandler<UserLoginInfo>() {
		//                  @Override
		//                  public void onResult(UserLoginInfo result) {
		//                      Log.d("bobby", "{status:"+result.getStatus()+",msg:"+result.getMsg()+"}");
		//                      if(200 == result.getStatus()){
		//                          Toast.makeText(context, result.getMsg(), 0).show();
		//                      }else{
		//                          Toast.makeText(context, result.getMsg(), 0).show();
		//                      }
		//                      
		//                  }
		//              });
		//        

	}
	// 初始化 JPush。如果已经初始化，但没有登录成功，则执行重新登录。
	private void init(){
		JPushInterface.init(getApplicationContext());
	}
	//此处添加的为统计推送总条数的方法
	@Override
	protected void onResume() {
		super.onResume();
		JPushInterface.onResume(this);
	}
	@Override
	protected void onPause() {
		super.onPause();
		JPushInterface.onPause(this);
	}

}