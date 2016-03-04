//package net.ilifang.app.commons;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//import net.ilifang.app.pmc.R;
//public class AnnouncementAdapter extends BaseAdapter{
//	public static final String TAG = "PMCClient";
//
//	public static final String[] APPLICATION_TYPE = new String[] { "公告", "审批", "邮箱", "任务", "文件" };
//	//	    public static final String APPLICATION_TITLE = "电子商务建设专家";
//	//	    public static final String APPLICATION_CONTENT = "SHOP++网上商城系统是基于JavaEE技术的企业级电子商务平台系统，以其安全稳定、强大易用、高效专业等优势赢得了用户的广泛好评。SHOP++为大、中、小企业提供一个安全、高效、强大的电子商务解决方案，协助企业快速构建、部署和管理其电子商务平台，拓展企业销售渠道，突显电子商务商业价值，致力于推动JavaEE技术和电子商务行业的发展而不断努力。";
//	public static ArrayList<String> title = new ArrayList<String>();
//	public static ArrayList<String> content = new ArrayList<String>();
//	public static ArrayList<String> time = new ArrayList<String>();
//	public static ArrayList<String> colorarr = new ArrayList<String>();
//	public static ArrayList<String> id = new ArrayList<String>();
//	private Context mContext;
//	public AnnouncementAdapter(Context mContext) {
//		super();
//		this.mContext= mContext;
//		// TODO Auto-generated constructor stub
//	}
//
//	@Override
//	public int getCount() {
//		// TODO Auto-generated method stub
//		return title.size();
//	}
//
//	@Override
//	public Object getItem(int position) {
//		// TODO Auto-generated method stub
//		return position;
//	}
//
//	@Override
//	public long getItemId(int position) {
//		// TODO Auto-generated method stub
//		return position;
//	}
//
//	@Override
//	public View getView(int position, View convertView, ViewGroup parent) {
//		// TODO Auto-generated method stub
//		if (convertView == null){
//			convertView = LayoutInflater.from(mContext).inflate(R.layout.appinfo_list_item, null,false);
//		}
//		TextView typeView = (TextView) convertView.findViewById(R.id.lbl_appinfo_type);
//		TextView titleView = (TextView) convertView.findViewById(R.id.lbl_appinfo_title);
//		TextView contentView = (TextView) convertView.findViewById(R.id.lbl_appinfo_content);
//		TextView timeView = (TextView) convertView.findViewById(R.id.lbl_appinfo_time);
//		//    			titleView.setText(APPLICATION_TITLE);
//		//    	        typeView.setText("[" + APPLICATION_TYPE[position] + "]  ");
//		//    	        contentView.setText(APPLICATION_CONTENT);
//		//    	        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
//		//    	        timeView.setText(dateformat.format(((AppInfo) getItem(position)).getCreateDate()));
//		titleView.setText(title.get(position));
//		//    	        typeView.setText("[" + APPLICATION_TYPE[position] + "]  ");
//		contentView.setText(content.get(position));
//		timeView.setText(time.get(position));
//		//    	        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
//		//    	        timeView.setText(dateformat.format((getItem(position))));
//		return convertView;
//	}
//}
