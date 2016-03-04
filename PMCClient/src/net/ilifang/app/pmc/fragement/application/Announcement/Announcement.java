package net.ilifang.app.pmc.fragement.application.Announcement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.lidroid.xutils.http.RequestParams;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.SyncStateContract.Constants;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import net.ilifang.app.commons.utils.RequestUtils;
import net.ilifang.app.commons.utils.ResultHandler;
import net.ilifang.app.commons.utils.ViewUtils;
import net.ilifang.app.pmc.LoginActivity;
import net.ilifang.app.pmc.MainActivity;
import net.ilifang.app.pmc.R;
import net.ilifang.app.pmc.entity.RowInfo;
import net.ilifang.app.pmc.entity.Rows;

public class Announcement extends Fragment{
	public static int currentpage=1;//当前页
	TextView appInfoType;
	List<RowInfo> list = new ArrayList<RowInfo>();
	PopupWindow typePopupWindow;
	AnnouncementAdapter adapter;
	PullToRefreshListView appInfoListview;
	LinkedList<RowInfo> appInfoList = new LinkedList<RowInfo>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.announcement, container,false );
		appInfoListview = (PullToRefreshListView) view.findViewById(R.id.application_listview);
		appInfoListview.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				String label = DateUtils.formatDateTime(getContext(), System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE
						| DateUtils.FORMAT_ABBREV_ALL);
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
				// Do work to refresh the list here.
				Toast.makeText(getContext(), "正在进行数据加载", Toast.LENGTH_SHORT).show();
				new GetDataTask().execute();//执行加载数据
				//				getDatetest();
			}
		});

		appInfoListview.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {
			@Override
			public void onLastItemVisible() {
				Toast.makeText(getContext(), "End of List!", Toast.LENGTH_SHORT).show();
			}
		});

//		ListView actualListView = appInfoListview.getRefreshableView();
		List<RowInfo> datas = getDatetest();
//		appInfoList.addAll(datas);
		adapter = new AnnouncementAdapter(getContext(),datas);
		appInfoListview.requestLayout();  
		adapter.notifyDataSetChanged();//此句通知UI刷新  但然并卵
		appInfoListview.setAdapter(adapter);
		
		appInfoType = (TextView) view.findViewById(R.id.lbl_appinfo_type);
		appInfoType.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view){
				showTypePopupWindow(view);
			}
		});
		return view;
	}
	private void showTypePopupWindow(View view) {//这是切换公告类型的弹窗

		if (typePopupWindow == null) {
			View contentView = LayoutInflater.from(getContext()).inflate(R.layout.middle_point_popup_window, null, false);
			ListView popupMenuItemListView = (ListView) contentView.findViewById(R.id.popupMenuItemListview);
			String[] types = getResources().getStringArray(R.array.app_item_type);
			List<Map<String, String>> menuStore = new ArrayList<Map<String, String>>();
			for (int i = 0; i < types.length; i++) {
				Map<String, String> menuItem = new HashMap<String, String>();
				menuItem.put("name", types[i]);
				menuStore.add(menuItem);
			}
			SimpleAdapter menuItemAdapter = new SimpleAdapter(getContext(), menuStore, R.layout.simple_list_item, new String[] { "name" },
					new int[] { R.id.simple_list_item_text });
			popupMenuItemListView.setAdapter(menuItemAdapter);
			// 创建一个PopuWidow对象
			typePopupWindow = new PopupWindow(contentView, ViewUtils.dip2px(getContext(), 132), WindowManager.LayoutParams.WRAP_CONTENT);
		}
		typePopupWindow.setFocusable(true);
		typePopupWindow.setOutsideTouchable(true);
		typePopupWindow.setBackgroundDrawable(new BitmapDrawable());
		typePopupWindow.showAsDropDown(view);
	}
	//	public void clearall(){//清除适配器中的数据
	//		AnnouncementAdapter.title.clear();
	//		AnnouncementAdapter.time.clear();
	//		AnnouncementAdapter.content.clear();
	//		AnnouncementAdapter.colorarr.clear();
	//		AnnouncementAdapter.id.clear();
	//	}
	//	public void getjsondate(List<RowInfo> list) {
	//		for (RowInfo rowInfo : list) {
	//       	 System.out.println("Title:  "+rowInfo.getTitle());
	//			AnnouncementAdapter.title.add(rowInfo.getTitle());
	//			AnnouncementAdapter.time.add(rowInfo.getPublishTime());
	//			AnnouncementAdapter.content.add(rowInfo.getSummary());
	//			AnnouncementAdapter.colorarr.add(rowInfo.getColor());
	//			AnnouncementAdapter.id.add(rowInfo.getId());
	//		}
	//加载数据结束时提示框消失
	//		if(AnnouncementAdapter.title.size()>0){
	//			appInfoListview.setAdapter(adapter);
	//			appInfoListview.onRefreshComplete();
	//		}else{
	//			appInfoListview.setAdapter(adapter);
	//			Toast.makeText(getContext(), "无记录", Toast.LENGTH_SHORT).show();
	//			appInfoListview.onRefreshComplete();
	//		}
	//	}


	class GetDataTask extends AsyncTask<Void, Void, List<RowInfo>> {
		@Override
		protected List<RowInfo> doInBackground(Void... params) {
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
			}
			return getDatetest();
		}
		@Override
		protected void onPostExecute(List<RowInfo> result) {
			appInfoList.addAll(0, result);
			adapter.notifyDataSetChanged();
			appInfoListview.requestLayout();  
			appInfoListview.onRefreshComplete();
			super.onPostExecute(result);
		}
	}
	//	
	//				    List<AppInfo> getStaticData() {
	//				        List<AppInfo> datas = new ArrayList<AppInfo>();
	//				        AppInfo app1 = new AppInfo(1, 1, Constants.APPLICATION_TITLE, Constants.APPLICATION_CONTENT, new Date());
	//				        AppInfo app2 = new AppInfo(2, 2, Constants.APPLICATION_TITLE, Constants.APPLICATION_CONTENT, new Date());
	//				        AppInfo app3 = new AppInfo(3, 3, Constants.APPLICATION_TITLE, Constants.APPLICATION_CONTENT, new Date());
	//				        AppInfo app4 = new AppInfo(4, 4, Constants.APPLICATION_TITLE, Constants.APPLICATION_CONTENT, new Date());
	//				        datas.clear();
	//				        datas.add(app1);
	//				        datas.add(app2);
	//				        datas.add(app3);
	//				        datas.add(app4);
	//				        return datas;
	//				    }
	List<RowInfo> getDatetest(){//获取网络数据
		String uid = LoginActivity.getuserid();
		int a =  currentpage*10;
		//			    		RequestParams params = new RequestParams();
		//			    		params.addBodyParameter("emp","0000001");
		//			    		params.addBodyParameter("page", "1");          
		//			    		params.addBodyParameter("rows",a+"");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("emp",uid);
		params.put("page", "1");
		params.put("rows",a+"");
		//			            clearall();
		RequestUtils.PostOA(getContext(), "get_notice_list", params, Rows.class, new ResultHandler<Rows>() {
			@Override
			public void onResult(Rows rows) {
				List<RowInfo> li = new ArrayList<RowInfo>();
				li = rows.getRows();
				for (RowInfo rowInfo : li) {
					if(rowInfo!=null){
					String Title=rowInfo.getTitle();
					String PublishTime= rowInfo.getPublishTime();
					String Summary = rowInfo.getSummary();
					String Color = rowInfo.getColor();
					int  id = rowInfo.getId();
					RowInfo item = new RowInfo(Title, PublishTime, Summary, Color, id);
					list.add(item);
					}else{Toast.makeText(getContext(), "rowInfo为空", Toast.LENGTH_SHORT).show();}
				}
				System.out.println("看看这个列表中有啥"+list);
			}
		});
		return list;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	public class AnnouncementAdapter extends BaseAdapter {

		//				        LayoutInflater mInflater;
		List<RowInfo> store = new ArrayList<RowInfo>();

		public AnnouncementAdapter(Context context,List<RowInfo> store) {
			//				            mInflater = LayoutInflater.from(context);
			this.store = store;
		}

		@Override
		public int getCount() {
			return store == null ? 0 : store.size();
		}

		@Override
		public RowInfo getItem(int position) {
			return store.get(position);
		}

		@Override
		public long getItemId(int position) {
			return getItem(position).getId();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView == null){
				//				            convertView = mInflater.inflate(R.layout.appinfo_list_item, null);
				convertView = LayoutInflater.from(getContext()).
						inflate(R.layout.appinfo_list_item, parent, false);
			}
			TextView typeView = (TextView) convertView.findViewById(R.id.lbl_appinfo_type);
			TextView titleView = (TextView) convertView.findViewById(R.id.lbl_appinfo_title);
			TextView contentView = (TextView) convertView.findViewById(R.id.lbl_appinfo_content);
			TextView timeView = (TextView) convertView.findViewById(R.id.lbl_appinfo_time);

			titleView.setText(getItem(position).getTitle());
			System.out.println("怎么没有值呢  怎么回事呢"+getItem(position).getTitle());
			//				            typeView.setText("[" + getItem(position). + "]  ");
			contentView.setText(getItem(position).getSummary());
			timeView.setText(getItem(position).getPublishTime());
			//				            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			//				            timeView.setText(dateformat.format(getItem(position).getCreateDate()));
			return convertView;
		}

	}

}







