package net.ilifang.app.pmc.fragement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.ilifang.app.commons.utils.ViewUtils;
import net.ilifang.app.pmc.MainActivity;
import net.ilifang.app.pmc.R;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.special.ResideMenu.ResideMenu;

public class ProjectFragment extends Fragment {

    Context context;
    TextView appInfoType;
    PopupWindow typePopupWindow, projectPopupWindow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_project, container, false);
        context = (Context) getActivity().getApplicationContext();

        GridView gridview = (GridView) contentView.findViewById(R.id.gridview);
        ArrayList<HashMap<String, Object>> items = new ArrayList<HashMap<String, Object>>();
        String[] projectDataItems = getResources().getStringArray(R.array.project_item_title);
        int[] projectIconItems = { R.drawable.pro1, R.drawable.pro2, R.drawable.pro3, R.drawable.pro4, R.drawable.pro5, R.drawable.pro6, R.drawable.pro7,
                R.drawable.pro8, R.drawable.pro9, R.drawable.pro10, R.drawable.pro11 };
        for (int i = 0; i < projectDataItems.length; i++) {
            HashMap<String, Object> varMap = new HashMap<String, Object>();
            varMap.put("title", projectDataItems[i]);
            varMap.put("icon", projectIconItems[i]);
            items.add(varMap);
        }
        SimpleAdapter gridAdapter = new SimpleAdapter(context, items, R.layout.grid_item, new String[] { "icon", "title" }, new int[] { R.id.icon_item,
                R.id.txt_item });
        gridview.setAdapter(gridAdapter);

        appInfoType = (TextView) contentView.findViewById(R.id.lbl_appinfo_type);
        appInfoType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTypePopupWindow(view);
            }
        });

        MainActivity parentActivity = (MainActivity) getActivity();
        ImageView plusBtn = parentActivity.getPlusBtn();
        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProjectPopupWindow(view);
            }
        });

        ResideMenu resideMenu = parentActivity.getResideMenu();
        resideMenu.clearIgnoredViewList();

        return contentView;
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

    private void showTypePopupWindow(View view) {
        if (typePopupWindow == null) {
            View contentView = LayoutInflater.from(context).inflate(R.layout.middle_point_popup_window, null, false);
            ListView popupMenuItemListView = (ListView) contentView.findViewById(R.id.popupMenuItemListview);
            String[] types = getResources().getStringArray(R.array.app_item_type);
            List<Map<String, String>> menuStore = new ArrayList<Map<String, String>>();
            for (int i = 0; i < types.length; i++) {
                Map<String, String> menuItem = new HashMap<String, String>();
                menuItem.put("name", types[i]);
                menuStore.add(menuItem);
            }
            SimpleAdapter menuItemAdapter = new SimpleAdapter(context, menuStore, R.layout.simple_list_item, new String[] { "name" },
                    new int[] { R.id.simple_list_item_text });
            popupMenuItemListView.setAdapter(menuItemAdapter);
            // 创建一个PopuWidow对象
            typePopupWindow = new PopupWindow(contentView, ViewUtils.dip2px(context, 132), WindowManager.LayoutParams.WRAP_CONTENT);
        }
        typePopupWindow.setFocusable(true);
        typePopupWindow.setOutsideTouchable(true);
        typePopupWindow.setBackgroundDrawable(new BitmapDrawable());
        typePopupWindow.showAsDropDown(view);
    }
}
