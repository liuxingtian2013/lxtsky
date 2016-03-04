package net.ilifang.app.pmc.fragement.application.Attendance;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import net.ilifang.app.commons.utils.RequestUtils;
import net.ilifang.app.commons.utils.ResultHandler;
import net.ilifang.app.pmc.LoginActivity;
import net.ilifang.app.pmc.R;
import net.ilifang.app.pmc.entity.RowInfo;
import net.ilifang.app.pmc.entity.Rows;
public class Punchcardlist extends Activity{
	List<RowInfo> dates = new ArrayList<RowInfo>();
	PullToRefreshListView appInfoListview;
	private Punchcardlistadapter pladapter;
	private Context mContext;
	public static int currentpage=1;//当前页
	LinkedList<RowInfo> linkedlist = new LinkedList<RowInfo>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.punchcardlist);
		mContext = Punchcardlist.this;
		initview();
	}
	private void initview() {
		appInfoListview = (PullToRefreshListView) findViewById(R.id.application_listview);
		appInfoListview.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				String label = DateUtils.formatDateTime(mContext, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME|
						DateUtils.FORMAT_SHOW_DATE|DateUtils.FORMAT_ABBREV_ALL);
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
				// Do work to refresh the list here.
				new GetDates().execute();
			}
		});
		appInfoListview.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {
			@Override
			public void onLastItemVisible() {
				Toast.makeText(mContext, "End of the list", Toast.LENGTH_SHORT).show();
			}
		});
		List<RowInfo> mdate = getmdate();
		pladapter = new Punchcardlistadapter(mContext,mdate);
		appInfoListview.setAdapter(pladapter);
	}
	public List<RowInfo> getmdate() {
		String uid = LoginActivity.getuserid();
		int a =  currentpage*10;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("emp", uid);
		params.put("page", "1");
		params.put("rows", "10");
		RequestUtils.attendanceHandler(mContext, "get_check_record", params, Rows.class, new ResultHandler<Rows>() {
			@Override
			public void onResult(Rows rows) {
				// TODO Auto-generated method stub
				List<RowInfo> li = new ArrayList<RowInfo>();
				li = rows.getRows();
				if(!li.isEmpty()){
					for (RowInfo rowinfo : li) {
						String CheckTime = rowinfo.getCheckTime();
						int id = rowinfo.getId();//pc端此处还有错误   在官网上应该是get static   但由于是Android端的关键字 需要修改
						RowInfo item= new RowInfo(CheckTime,id);
						dates.add(item);
					}
				}else{Toast.makeText(mContext, "无打卡记录", Toast.LENGTH_SHORT).show();}//官网上json有问题导致出现解析的时报引用空对象的错
			}
		});
		if(!dates.isEmpty()){
			return null;
		}else{return dates;}
	}
	class Punchcardlistadapter extends BaseAdapter{
		List<RowInfo> store = new ArrayList<RowInfo>();

		public Punchcardlistadapter(Context mContext , List<RowInfo> store){
			this.store = store;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return store==null? 0:store.size();
		}

		@Override
		public RowInfo getItem(int position) {
			// TODO Auto-generated method stub
			return store.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return getItem(position).getId();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView == null){
				convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.punchcardlistitem, parent ,false);
			}
			TextView tvcontent  = (TextView) convertView.findViewById(R.id.tv_dakashijian);
			TextView tvTime  = (TextView) convertView.findViewById(R.id.tv_zhuangtai);
			tvcontent.setText(getItem(position).getCheckTime());
			tvTime.setText((getItem(position).getId())+"");
			return convertView;
		}
	}
	class GetDates extends AsyncTask<Void , Void, List<RowInfo>>{//异步加载数据
		@Override
		protected List<RowInfo> doInBackground(Void... params) {
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return getmdate();
		}
		@Override
		protected void onPostExecute(List<RowInfo> result) {
			linkedlist.addAll(0,result);
			pladapter.notifyDataSetChanged();
			appInfoListview.requestLayout();  
			appInfoListview.onRefreshComplete();
			super.onPostExecute(result);
		}
	}
}