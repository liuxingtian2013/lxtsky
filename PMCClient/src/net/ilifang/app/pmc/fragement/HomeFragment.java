package net.ilifang.app.pmc.fragement;

import java.util.ArrayList;
import java.util.List;

import net.ilifang.app.commons.utils.ViewUtils;
import net.ilifang.app.pmc.MainActivity;
import net.ilifang.app.pmc.R;
import net.ilifang.app.pmc.entity.Active;
import net.ilifang.app.pmc.fragement.home.ImageViewFragmentAdapter;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.special.ResideMenu.ResideMenu;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

public class HomeFragment extends Fragment {

    ListView activelv;
    List<Active> actives = new ArrayList<Active>();

    ViewPager mPager;
    PageIndicator mIndicator;
    ImageViewFragmentAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);
        activelv = (ListView) view.findViewById(R.id.active_listview);

        Active act1 = new Active(1, getResources().getDrawable(R.drawable.ic_chat), "这么年轻何必纠结于未来", "人生,励志", "我们这么年轻，做喜欢的事情，爱所爱的人。关于未来，不迎接不纠结，活在当下，珍惜当下好时光",
                null);
        Active act2 = new Active(2, getResources().getDrawable(R.drawable.ic_alert), "这么年轻何必纠结于未来", "人生,成长,励志", "我们这么年轻，做喜欢的事情，爱所爱的人。关于未来，不迎接不纠结，活在当下，珍惜当下好时光",
                null);
        Active act3 = new Active(3, getResources().getDrawable(R.drawable.ic_voice), "这么年轻何必纠结于未来", "人生,成长", "我们这么年轻，做喜欢的事情，爱所爱的人。关于未来，不迎接不纠结，活在当下，珍惜当下好时光",
                null);
        Active act4 = new Active(4, getResources().getDrawable(R.drawable.ic_sms), "这么年轻何必纠结于未来", "成长,励志", "我们这么年轻，做喜欢的事情，爱所爱的人。关于未来，不迎接不纠结，活在当下，珍惜当下好时光", null);
        actives.clear();
        actives.add(act1);
        actives.add(act2);
        actives.add(act3);
        actives.add(act4);

        Context context = (Context) getActivity().getApplicationContext();
        ListAdapter adapter = new ActiveAdapter(context, actives);
        activelv.setAdapter(adapter);

        mAdapter = new ImageViewFragmentAdapter(getChildFragmentManager());
        mPager = (ViewPager) view.findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);
        CirclePageIndicator indicator = (CirclePageIndicator) view.findViewById(R.id.indicator);
        mIndicator = indicator;
        indicator.setViewPager(mPager);
        indicator.setSnap(true);

        // viewPager上忽略resideMenu
        MainActivity parentActivity = (MainActivity) getActivity();
        ResideMenu resideMenu = parentActivity.getResideMenu();
        resideMenu.addIgnoredView(mPager);

        return view;
    }

    class ActiveAdapter extends BaseAdapter {

        LayoutInflater mInflater;
        List<Active> store = new ArrayList<Active>();

        public ActiveAdapter(Context context, List<Active> store) {
            super();
            mInflater = LayoutInflater.from(context);
            this.store = store;
        }

        @Override
        public int getCount() {
            return store == null ? 0 : store.size();
        }

        @Override
        public Active getItem(int position) {
            return store.get(position);
        }

        @Override
        public long getItemId(int position) {
            return getItem(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = mInflater.inflate(R.layout.active_list_item, null);

            ImageView iconView = (ImageView) convertView.findViewById(R.id.ic_view_active);
            TextView titleView = (TextView) convertView.findViewById(R.id.txtv_title);
            TextView descView = (TextView) convertView.findViewById(R.id.descTextView);
            LinearLayout labelView = (LinearLayout) convertView.findViewById(R.id.active_labeles);

            titleView.setText(getItem(position).getTitle());
            descView.setText(getItem(position).getDesc());
            iconView.setImageDrawable(getItem(position).getIcon());

            String[] labels = getItem(position).getTags();
            for (int i = 0; i < labels.length; i++) {
                LayoutParams lparms = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                lparms.setMargins(0, 0, ViewUtils.px2dip(getContext(), 10), 0);

                TextView label = new TextView(getContext());
                label.setText(labels[i]);
                label.setBackgroundResource(R.drawable.label_rectangle_with_border);
                label.setLayoutParams(lparms);
                label.setTextColor(getResources().getColor(R.color.dark_green));

                labelView.addView(label);
            }

            return convertView;
        }
    }

}
