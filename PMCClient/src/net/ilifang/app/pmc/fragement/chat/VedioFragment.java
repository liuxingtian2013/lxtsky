package net.ilifang.app.pmc.fragement.chat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.ilifang.app.pmc.R;
import net.ilifang.app.pmc.entity.VedioInfo;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class VedioFragment extends Fragment {
    ListView msglv;
    List<VedioInfo> msgInfos = new ArrayList<VedioInfo>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_msg, container, false);
        msglv = (ListView) view.findViewById(R.id.chat_listview);

        VedioInfo msg0 = new VedioInfo(0, getResources().getDrawable(R.drawable.ic_special_vedio), "数据中心V2电话会议", "001号办公室,需要八爪鱼", new Date());
        VedioInfo msg1 = new VedioInfo(1, getResources().getDrawable(R.drawable.ic_special_vedio), "数据中心V2电话会议", "001号办公室,需要八爪鱼", new Date());
        VedioInfo msg2 = new VedioInfo(2, getResources().getDrawable(R.drawable.ic_special_vedio), "数据中心V2电话会议", "001号办公室,需要八爪鱼", new Date());
        VedioInfo msg3 = new VedioInfo(3, getResources().getDrawable(R.drawable.ic_special_vedio), "数据中心V2电话会议", "001号办公室,需要八爪鱼", new Date());
        VedioInfo msg4 = new VedioInfo(4, getResources().getDrawable(R.drawable.ic_special_vedio), "数据中心V2电话会议", "001号办公室,需要八爪鱼", new Date());

        msgInfos.clear();
        msgInfos.add(msg0);
        msgInfos.add(msg1);
        msgInfos.add(msg2);
        msgInfos.add(msg3);
        msgInfos.add(msg4);
        msgInfos.add(msg1);
        msgInfos.add(msg2);
        msgInfos.add(msg3);
        msgInfos.add(msg4);

        Context context = (Context) getActivity().getApplicationContext();
        ListAdapter adapter = new VedioInfoAdapter(context, msgInfos);
        msglv.setAdapter(adapter);

        return view;
    }

    class VedioInfoAdapter extends BaseAdapter {

        LayoutInflater mInflater;
        List<VedioInfo> store = new ArrayList<VedioInfo>();

        public VedioInfoAdapter(Context context, List<VedioInfo> store) {
            super();
            mInflater = LayoutInflater.from(context);
            this.store = store;
        }

        @Override
        public int getCount() {
            return store == null ? 0 : store.size();
        }

        @Override
        public VedioInfo getItem(int position) {
            return store.get(position);
        }

        @Override
        public long getItemId(int position) {
            return getItem(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = mInflater.inflate(R.layout.vedio_list_item, null);

            ImageView iconView = (ImageView) convertView.findViewById(R.id.ic_msg_avatar);
            TextView titleView = (TextView) convertView.findViewById(R.id.txtv_title);
            TextView descView = (TextView) convertView.findViewById(R.id.txtv_desc);
            TextView timeView = (TextView) convertView.findViewById(R.id.txtv_time);

            titleView.setText(getItem(position).getTitle());
            descView.setText(getItem(position).getDesc());
            iconView.setImageDrawable(getItem(position).getIcon());
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            timeView.setText(dateformat.format(getItem(position).getCreateDate()));

            return convertView;
        }
    }

}
