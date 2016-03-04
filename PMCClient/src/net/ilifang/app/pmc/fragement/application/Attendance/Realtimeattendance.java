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
import net.ilifang.app.pmc.R;
import net.ilifang.app.pmc.entity.RowInfo;
import net.ilifang.app.pmc.entity.Rows;
public class Realtimeattendance extends Activity{
	Realtimeattendanceadapter realtimeadapter;
	PullToRefreshListView appinfolist;
	private Context mContext;
	int currentpage = 1;//当前页
	List<RowInfo> dates = new ArrayList<RowInfo>();
	LinkedList<RowInfo> linklist = new LinkedList<RowInfo>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.realtimeattendance);
		mContext = Realtimeattendance.this;
		initview();
	}
	private void initview() {
		appinfolist = (PullToRefreshListView) findViewById(R.id.application_listview);
		appinfolist.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				String label = DateUtils.formatDateTime(mContext, System.currentTimeMillis(),DateUtils.FORMAT_SHOW_TIME
						|DateUtils.FORMAT_SHOW_DATE|DateUtils.FORMAT_ABBREV_ALL);
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
				//Do some work to refullsh the list
				//				new GetDates().execute();
				appinfolist.onRefreshComplete();
			}
		});
		appinfolist.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {
			@Override
			public void onLastItemVisible() {
				Toast.makeText(mContext, "End of the list ", Toast.LENGTH_SHORT).show();
			}
		});
		int a = currentpage*10;
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("page", "1");
//		params.put("rows", a+"");
//		RequestUtils.attendanceHandler(mContext, "get_class_list", params, Rows.class, new ResultHandler<Rows>(){
//			@Override
//			public void onResult(Rows rows) {
//				List<RowInfo> li = rows.getRows();
//				for(RowInfo rowinfo : li){
//					String name = rowinfo.getName();
//					String classname = rowinfo.getClassTypeName();
//					int id = rowinfo.getId();
//					RowInfo item = new RowInfo(name,classname,id);
//					dates.add(item);
//					System.out.println("看看这个dates"+dates);
//				}
//			}});
		List<RowInfo> mdate = getdates();
		realtimeadapter = new Realtimeattendanceadapter(mContext,mdate);
		System.out.println("让我看看这个mdate"+mdate.size());
		appinfolist.setAdapter(realtimeadapter);
	}
		public List<RowInfo> getdates() {
			int a = currentpage*10;
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("page", "1");
			params.put("rows", a+"");
			RequestUtils.attendanceHandler(mContext, "get_class_list", params, Rows.class, new ResultHandler<Rows>(){
				@Override
				public void onResult(Rows rows) {
					List<RowInfo> li = rows.getRows();
					for(RowInfo rowinfo : li){
						String name = rowinfo.getName();
						String classname = rowinfo.getClassTypeName();
						int id = rowinfo.getId();
						RowInfo item = new RowInfo(name,classname,id);
						dates.add(item);
						System.out.println("看看这个dates"+dates);
					}
				}});
			return dates;
		}
	class Realtimeattendanceadapter extends BaseAdapter{
		List<RowInfo> store = new ArrayList<RowInfo>();
		public Realtimeattendanceadapter(Context mContext,List<RowInfo> store){
			this.store = store;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return store == null ? 0: store.size() ;
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
			if(convertView==null){
				convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.realtimeattendanceitem, parent, false);
			}
			TextView tvname = (TextView) convertView.findViewById(R.id.person_tv_list);
			TextView tvnum = (TextView) convertView.findViewById(R.id.person_tv_total);
			tvname.setText(getItem(position).getName());
			tvnum.setText("总人数");
			return convertView;
		}
	}
	//	class GetDates extends AsyncTask<Void, Void, List<RowInfo>>{
	//		@Override
	//		protected List<RowInfo> doInBackground(Void... params) {
	//			try {
	//				Thread.sleep(4000);
	//			} catch (InterruptedException e) {
	//				// TODO Auto-generated catch block
	//				e.printStackTrace();
	//			}
	//			return getdates();
	//		}
	//		@Override
	//		protected void onPostExecute(List<RowInfo> result) {
	//			linklist.addAll(0,result);
	//			System.out.println(result.size());
	//			realtimeadapter.notifyDataSetChanged();
	//			appinfolist.requestLayout();
	//			appinfolist.onRefreshComplete();
	//			super.onPostExecute(result);
	//		}
	//	}
}
