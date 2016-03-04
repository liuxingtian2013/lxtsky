package net.ilifang.app.pmc;

import net.ilifang.app.commons.utils.ViewUtils;
import net.ilifang.app.commons.widget.PathView;
import net.ilifang.app.commons.widget.PathView.OnItemClickListener;
import net.ilifang.app.pmc.fragement.ApplicationFragment;
import net.ilifang.app.pmc.fragement.ChatFragment;
import net.ilifang.app.pmc.fragement.HomeFragment;
import net.ilifang.app.pmc.fragement.ProjectFragment;
import net.ilifang.app.pmc.fragement.SettingsFragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

@SuppressWarnings("deprecation")
public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private ResideMenu resideMenu;
    private MainActivity mContext;
    private ResideMenuItem myHome, myFile, slideMenuItem1, slideMenuItem2;

    private RadioGroup menuGroup;
    private Fragment home, app, project, chat;
private RadioButton menuapplication,menuhome;
    private ImageView searchBtn, plusBtn, avatarBtn;
    private RadioGroup tabBarMenuGroup;
    private TextView actbarUserMsg;

    int[] drawableIds = { R.drawable.ic_path_singin, R.drawable.ic_path_task, R.drawable.ic_path_sms, R.drawable.ic_path_finance };
    String[] pathTxts = { "签到", "任务", "消息", "财务" };

    PathView mPathView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initActionBar();

        mContext = this;
        setUpMenu();
        initView();
        setupPopupMenuView();
//        Intent intent = getIntent();
//        if(intent.hasExtra("sign")){
//		String position = intent.getStringExtra("sign");
//		if(position.equals("Punchcard")){
//			searchBtn.setVisibility(View.GONE);
//            plusBtn.setVisibility(View.VISIBLE);
//            actbarUserMsg.setVisibility(View.VISIBLE);
//            tabBarMenuGroup.setVisibility(View.GONE);
//            menuapplication.setChecked(true);
//            app = new ApplicationFragment();
//            getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, app).commit();
//		}else{}}
    }

    /**
     * 自定义ActionBar
     */
    private void initActionBar() {
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.actionbar);// 自定义ActionBar布局

        searchBtn = (ImageView) actionBar.getCustomView().findViewById(R.id.actbar_search);
        plusBtn = (ImageView) actionBar.getCustomView().findViewById(R.id.actbar_plus);
        avatarBtn = (ImageView) actionBar.getCustomView().findViewById(R.id.actbar_avatar);
        actbarUserMsg = (TextView) actionBar.getCustomView().findViewById(R.id.actbar_user_msg);
        tabBarMenuGroup = (RadioGroup) actionBar.getCustomView().findViewById(R.id.tabbar_intera_menu);

    }

    private void setupPopupMenuView() {
        mPathView = (PathView) findViewById(R.id.mPathView);
        ImageButton startMenu = new ImageButton(this);
        startMenu.setBackgroundResource(R.drawable.start_menu_btn);
        mPathView.setStartMenu(startMenu);

        View[] items = new View[drawableIds.length];
        for (int i = 0; i < drawableIds.length; i++) {

            Drawable drawable = getResources().getDrawable(drawableIds[i]);
            drawable.setBounds(0, 0, drawable.getMinimumWidth()-20, drawable.getMinimumHeight()-20);

            TextView txtBtn = new TextView(this);
            ViewUtils.setMargins(txtBtn, ViewUtils.dip2px(mContext, 10), ViewUtils.dip2px(mContext, 10), ViewUtils.dip2px(mContext, 10),
                    ViewUtils.dip2px(mContext, 5));
            txtBtn.setPadding(ViewUtils.dip2px(mContext, 10), ViewUtils.dip2px(mContext, 10), ViewUtils.dip2px(mContext, 10), ViewUtils.dip2px(mContext, 10));
            txtBtn.setText(pathTxts[i]);
            txtBtn.setGravity(Gravity.CENTER);
            txtBtn.setCompoundDrawables(null, null, drawable, null);
            txtBtn.setTextColor(getResources().getColor(android.R.color.white));
            txtBtn.setTextSize(ViewUtils.dip2px(mContext, 6));
            items[i] = txtBtn;

        }
        mPathView.setItems(items);
        mPathView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mPathView.setBackgroundColor(Color.TRANSPARENT);
                Toast.makeText(mContext, "点击了pathview", Toast.LENGTH_SHORT).show();
            }
        });
        startMenu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPathView.isExpand()) {
                    mPathView.shrink(mPathView.DURATION);
                } else {
                    //mPathView.setBackgroundColor(getResources().getColor(R.color.headColorTransparent));
                    mPathView.expand(mPathView.DURATION);
                }
            }
        });

    }

    private void initView() {
    	menuapplication = (RadioButton) findViewById(R.id.menu_application);
        menuhome = (RadioButton) findViewById(R.id.menu_home);
        menuGroup = (RadioGroup) findViewById(R.id.tab_menu);
        avatarBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
        home = new HomeFragment();
        menuhome.setChecked(true);
        plusBtn.setVisibility(View.GONE);
        tabBarMenuGroup.setVisibility(View.GONE);
        searchBtn.setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, home).commit();
        menuGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                case R.id.menu_home:

                    // home = new HomeFragment();
                    plusBtn.setVisibility(View.GONE);
                    actbarUserMsg.setVisibility(View.VISIBLE);
                    tabBarMenuGroup.setVisibility(View.GONE);
                    searchBtn.setVisibility(View.VISIBLE);
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, home).commit();
                    break;

                case R.id.menu_application:

                    searchBtn.setVisibility(View.GONE);
                    plusBtn.setVisibility(View.VISIBLE);
                    actbarUserMsg.setVisibility(View.VISIBLE);
                    tabBarMenuGroup.setVisibility(View.GONE);
                    app = new ApplicationFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, app).commit();
                    break;

                case R.id.menu_project:

                    plusBtn.setVisibility(View.VISIBLE);
                    actbarUserMsg.setVisibility(View.VISIBLE);
                    searchBtn.setVisibility(View.GONE);
                    tabBarMenuGroup.setVisibility(View.GONE);
                    project = new ProjectFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, project).commit();
                    break;

                case R.id.menu_chat:

                    plusBtn.setVisibility(View.GONE);
                    searchBtn.setVisibility(View.GONE);
                    actbarUserMsg.setVisibility(View.GONE);
                    tabBarMenuGroup.setVisibility(View.VISIBLE);
                    chat = new ChatFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, chat).commit();
                    break;

                default:
                    break;

                }
            }
        });
    }

    private void setUpMenu() {

        View headerView = LayoutInflater.from(MainActivity.this).inflate(R.layout.reside_menu_item_header, null, false);

        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.menu_background);
        resideMenu.attachToActivity(this);
        resideMenu.setLeftMenuHeader(headerView);
        resideMenu.setMenuListener(menuListener);
        resideMenu.requestDisallowInterceptTouchEvent(true);

        myHome = new ResideMenuItem(this, R.drawable.ic_my_home, "我的详情");
        myFile = new ResideMenuItem(this, R.drawable.ic_my_file, "我的文件");
        slideMenuItem1 = new ResideMenuItem(this, R.drawable.ic_menu_item, "导航菜单1");
        slideMenuItem2 = new ResideMenuItem(this, R.drawable.ic_menu_item, "导航菜单2");

        myHome.setOnClickListener(this);
        myFile.setOnClickListener(this);
        slideMenuItem1.setOnClickListener(this);
        slideMenuItem2.setOnClickListener(this);

        resideMenu.addMenuItem(myHome, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(myFile, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(slideMenuItem1, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(slideMenuItem2, ResideMenu.DIRECTION_LEFT);

        resideMenu.setDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        resideMenu.getSettingBtn().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewUtils.forward(mContext, SettingsActivity.class, false);
            }
        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {

        if (view == myHome) {
            changeFragment(new SettingsFragment());
        } else if (view == myFile) {
            changeFragment(new SettingsFragment());
        } else if (view == slideMenuItem1) {
            changeFragment(new SettingsFragment());
        } else if (view == slideMenuItem1) {
            changeFragment(new SettingsFragment());
        }

        resideMenu.closeMenu();
    }

    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
            Toast.makeText(mContext, "Menu is opened!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void closeMenu() {
            Toast.makeText(mContext, "Menu is closed!", Toast.LENGTH_SHORT).show();
        }
    };
    private void changeFragment(Fragment targetFragment) {
        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
    }

    /*-
     * Gettings
     */
    public ResideMenu getResideMenu() {
        return resideMenu;
    }

    public ImageView getSearchBtn() {
        return searchBtn;
    }

    public ImageView getPlusBtn() {
        return plusBtn;
    }

    public RadioGroup getTabBarMenuGroup() {
        return tabBarMenuGroup;
    }

}
