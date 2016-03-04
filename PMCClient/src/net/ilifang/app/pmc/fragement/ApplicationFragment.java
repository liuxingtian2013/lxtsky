package net.ilifang.app.pmc.fragement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.ilifang.app.commons.utils.ViewUtils;
import net.ilifang.app.pmc.MainActivity;
import net.ilifang.app.pmc.R;
import net.ilifang.app.pmc.fragement.application.Announcement.Announcement;
import net.ilifang.app.pmc.fragement.application.Attendance.Attendance;
import net.ilifang.app.pmc.fragement.application.Institution.Institution;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.special.ResideMenu.ResideMenu;
import com.viewpagerindicator.TabPageIndicator;

public class ApplicationFragment extends Fragment {

	View rootView;
	Context context;
	PopupWindow projectPopupWindow;
	private static final String[] CONTENT = new String[] { "考勤","公告", "制度"};
	//	,"审批", "邮箱", "任务", "云盘","财务","PMC" 
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.CustomTabPageIndicator);
		LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);

		if (rootView != null) {
			ViewGroup parent = (ViewGroup) rootView.getParent();
			parent.removeAllViews();
		}
		View view = localInflater.inflate(R.layout.fragment_application, container, false);
		List<Fragment> fragments = new ArrayList<Fragment>(); //将fragments添加到list中  以便适配器适配  
		fragments.add(new Attendance()); 
		fragments.add(new Announcement());
		fragments.add(new Institution());
		FragmentPagerAdapter adapter = new GoogleMusicAdapter(getChildFragmentManager(),fragments);
		ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
		pager.setAdapter(adapter);
		pager.setCurrentItem(0);
		TabPageIndicator indicator = (TabPageIndicator) view.findViewById(R.id.indicator);
		indicator.setViewPager(pager);
		context = (Context) getActivity().getApplicationContext();
		MainActivity parentActivity = (MainActivity) getActivity();
		ImageView plusBtn = parentActivity.getPlusBtn();
		plusBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				showProjectPopupWindow(view);
			}
		});

		ResideMenu resideMenu = parentActivity.getResideMenu();
		resideMenu.addIgnoredView(pager);

		return view;
	}

	private void showProjectPopupWindow(View view) {
		if (projectPopupWindow == null) {
			View contentView = LayoutInflater.from(context).inflate(R.layout.left_point_popup_window, null, false);
			ListView popupMenuItemListView = (ListView) contentView.findViewById(R.id.popupMenuItemListview);

			String[] arrays = getResources().getStringArray(R.array.app_item_title);
			int[] icons = { R.drawable.ic_notice, R.drawable.ic_apply, R.drawable.ic_project, R.drawable.ic_file };
			List<Map<String, Object>> menuStore = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < arrays.length; i++) {
				Map<String, Object> menuItem = new HashMap<String, Object>();
				menuItem.put("title", arrays[i]);
				menuItem.put("icon", icons[i]);
				menuStore.add(menuItem);
			}

			SimpleAdapter menuItemAdapter = new SimpleAdapter(context, menuStore, R.layout.simple_list_item_with_icon, new String[] { "icon", "title" },
					new int[] { R.id.simple_list_item_icon, R.id.simple_list_item_text });
			popupMenuItemListView.setAdapter(menuItemAdapter);
			projectPopupWindow = new PopupWindow(contentView, ViewUtils.dip2px(context, 185), WindowManager.LayoutParams.WRAP_CONTENT);
		}
		projectPopupWindow.setFocusable(true);
		projectPopupWindow.setOutsideTouchable(true);
		projectPopupWindow.setBackgroundDrawable(new BitmapDrawable());
		projectPopupWindow.showAsDropDown(view);
	}

	class GoogleMusicAdapter extends FragmentPagerAdapter {
		private List<Fragment>  fragments; 
		public GoogleMusicAdapter(FragmentManager fm) {
			super(fm);
		}
		public GoogleMusicAdapter (FragmentManager fm,List<Fragment>  fragments){
			super(fm);
			this.fragments = fragments;
		}
		@Override
		public Fragment getItem(int position) {
			//			return NewsFragment.newInstance(CONTENT[position % CONTENT.length]);
			return fragments.get(position);
		}
		@Override
		public int getItemPosition(Object object) {
			return POSITION_NONE;
		};
		@Override
		public CharSequence getPageTitle(int position) {
			return CONTENT[position % CONTENT.length].toUpperCase();
		}

		@Override
		public int getCount() {
			return CONTENT.length;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			super.destroyItem(container, position, object);
			// container.removeViewInLayout(getView());
		}

	}
}
