//package net.ilifang.app.pmc.fragement.application;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.LinkedList;
//import java.util.List;
//
//import net.ilifang.app.commons.Constants;
//import net.ilifang.app.pmc.R;
//import net.ilifang.app.pmc.entity.AppInfo;
//import android.content.Context;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.text.format.DateUtils;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.handmark.pulltorefresh.library.PullToRefreshBase;
//import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
//import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
//import com.handmark.pulltorefresh.library.PullToRefreshListView;
//
//public class NewsFragment extends Fragment {
//
//    View rootView;
//    Context context;
//    ApplicationAdapter adapter;
//    PullToRefreshListView appInfoListview;
//    LinkedList<AppInfo> appInfoList = new LinkedList<AppInfo>();
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        context = (Context) getActivity().getApplicationContext();
//
//        rootView = inflater.inflate(R.layout.fragment_application_item, container, false);
//        appInfoListview = (PullToRefreshListView) rootView.findViewById(R.id.application_listview);
//
//        appInfoListview.setOnRefreshListener(new OnRefreshListener<ListView>() {
//            @Override
//            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//                String label = DateUtils.formatDateTime(context, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE
//                        | DateUtils.FORMAT_ABBREV_ALL);
//                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
//
//                // Do work to refresh the list here.
//                new GetDataTask().execute();
//            }
//        });
//
//        appInfoListview.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {
//            @Override
//            public void onLastItemVisible() {
//                Toast.makeText(context, "End of List!", Toast.LENGTH_SHORT).show();
//            }
//        });
//
////         actualListView = appInfoListview.getRefreshableView();
//        List<AppInfo> datas = getStaticData();
//        appInfoList.addAll(datas);
//        adapter = new ApplicationAdapter(context, appInfoList);
//        appInfoListview.setAdapter(adapter);
//
//        return rootView;
//    }
//
//    private class GetDataTask extends AsyncTask<Void, Void, List<AppInfo>> {
//
//        @Override
//        protected List<AppInfo> doInBackground(Void... params) {
//            try {
//                Thread.sleep(4000);
//            } catch (InterruptedException e) {
//            }
//            return getStaticData();
//        }
//
//        @Override
//        protected void onPostExecute(List<AppInfo> result) {
//            appInfoList.addAll(0, result);
//            adapter.notifyDataSetChanged();
//            appInfoListview.onRefreshComplete();
//            super.onPostExecute(result);
//        }
//    }
//
//    List<AppInfo> getStaticData() {
//        List<AppInfo> datas = new ArrayList<AppInfo>();
//        AppInfo app1 = new AppInfo(1, 1, Constants.APPLICATION_TITLE, Constants.APPLICATION_CONTENT, new Date());
//        AppInfo app2 = new AppInfo(2, 2, Constants.APPLICATION_TITLE, Constants.APPLICATION_CONTENT, new Date());
//        AppInfo app3 = new AppInfo(3, 3, Constants.APPLICATION_TITLE, Constants.APPLICATION_CONTENT, new Date());
//        AppInfo app4 = new AppInfo(4, 4, Constants.APPLICATION_TITLE, Constants.APPLICATION_CONTENT, new Date());
//
//        datas.clear();
//        datas.add(app1);
//        datas.add(app2);
//        datas.add(app3);
//        datas.add(app4);
//
//        return datas;
//    }
//
////    public static Fragment newInstance(String content) {
////        NewsFragment fragment = new NewsFragment();
////        return fragment;
////    }
//
// class ApplicationAdapter extends BaseAdapter {
//
//        LayoutInflater mInflater;
//        List<AppInfo> store = new ArrayList<AppInfo>();
//
//        public ApplicationAdapter(Context context, List<AppInfo> store) {
//            mInflater = LayoutInflater.from(context);
//            this.store = store;
//        }
//
//        @Override
//        public int getCount() {
//            return store == null ? 0 : store.size();
//        }
//
//        @Override
//        public AppInfo getItem(int position) {
//            return store.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return getItem(position).getId();
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            convertView = mInflater.inflate(R.layout.appinfo_list_item, null);
//
//            TextView typeView = (TextView) convertView.findViewById(R.id.lbl_appinfo_type);
//            TextView titleView = (TextView) convertView.findViewById(R.id.lbl_appinfo_title);
//            TextView contentView = (TextView) convertView.findViewById(R.id.lbl_appinfo_content);
//            TextView timeView = (TextView) convertView.findViewById(R.id.lbl_appinfo_time);
//
//            titleView.setText(getItem(position).getTitle());
//            typeView.setText("[" + getItem(position).getTypeName() + "]  ");
//            contentView.setText(getItem(position).getContent());
//            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
//            timeView.setText(dateformat.format(getItem(position).getCreateDate()));
//
//            return convertView;
//        }
//
//    }
//}
