package net.ilifang.app.pmc.fragement.chat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.ilifang.app.pmc.R;
import net.ilifang.app.pmc.entity.MsgInfo;
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

public class MsgFragment extends Fragment {

    ListView msglv;
    List<MsgInfo> msgInfos = new ArrayList<MsgInfo>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_msg, container, false);
        msglv = (ListView) view.findViewById(R.id.chat_listview);

        MsgInfo msg0 = new MsgInfo(0, getResources().getDrawable(R.drawable.ic_special), "企业代表", "我们这么年轻，做喜欢的事情，爱所爱的人。关于未来，不迎接不纠结，活在当下，珍惜当下好时光", new Date());
        MsgInfo msg1 = new MsgInfo(1, getResources().getDrawable(R.drawable.ic_non_mainstream_avatar), "汉宫秋月", "我们这么年轻，做喜欢的事情，爱所爱的人。关于未来，不迎接不纠结，活在当下，珍惜当下好时光",
                new Date());
        MsgInfo msg2 = new MsgInfo(2, getResources().getDrawable(R.drawable.ic_avatar_xbobby), "碧海大仙", "我们这么年轻，做喜欢的事情，爱所爱的人。关于未来，不迎接不纠结，活在当下，珍惜当下好时光",
                new Date());
        MsgInfo msg3 = new MsgInfo(3, getResources().getDrawable(R.drawable.ic_bobby), "云平台团队", "我们这么年轻，做喜欢的事情，爱所爱的人。关于未来，不迎接不纠结，活在当下，珍惜当下好时光", new Date());
        MsgInfo msg4 = new MsgInfo(4, getResources().getDrawable(R.drawable.ic_avatar_uplus), "U+团队", "我们这么年轻，做喜欢的事情，爱所爱的人。关于未来，不迎接不纠结，活在当下，珍惜当下好时光",
                new Date());

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
        ListAdapter adapter = new MsgInfoAdapter(context, msgInfos);
        msglv.setAdapter(adapter);

        return view;
    }

    class MsgInfoAdapter extends BaseAdapter {

        LayoutInflater mInflater;
        List<MsgInfo> store = new ArrayList<MsgInfo>();

        public MsgInfoAdapter(Context context, List<MsgInfo> store) {
            super();
            mInflater = LayoutInflater.from(context);
            this.store = store;
        }

        @Override
        public int getCount() {
            return store == null ? 0 : store.size();
        }

        @Override
        public MsgInfo getItem(int position) {
            return store.get(position);
        }

        @Override
        public long getItemId(int position) {
            return getItem(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = mInflater.inflate(R.layout.msg_list_item, null);

            ImageView iconView = (ImageView) convertView.findViewById(R.id.ic_msg_avatar);
            TextView titleView = (TextView) convertView.findViewById(R.id.txtv_title);
            TextView descView = (TextView) convertView.findViewById(R.id.txtv_desc);
            TextView timeView = (TextView) convertView.findViewById(R.id.txtv_time);

            titleView.setText(getItem(position).getTitle());
            descView.setText(getItem(position).getDesc());
            iconView.setImageDrawable(getItem(position).getIcon());
            timeView.setText(getItem(position).getFormatedTime());

            return convertView;
        }
    }

}
