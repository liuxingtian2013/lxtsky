package net.ilifang.app.pmc.fragement;

import com.special.ResideMenu.ResideMenu;

import net.ilifang.app.pmc.MainActivity;
import net.ilifang.app.pmc.R;
import net.ilifang.app.pmc.fragement.chat.ContactFragment;
import net.ilifang.app.pmc.fragement.chat.MsgFragment;
import net.ilifang.app.pmc.fragement.chat.VedioFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class ChatFragment extends Fragment {

    private Fragment msg, contact, vedio;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_chat, container, false);
        MainActivity parentActivity = (MainActivity) getActivity();

        ResideMenu resideMenu = parentActivity.getResideMenu();
        resideMenu.clearIgnoredViewList();

        msg = new MsgFragment();
        getChildFragmentManager().beginTransaction().replace(R.id.main_chat_fragment, msg).commit();

        RadioGroup tabBarMenuGroup = parentActivity.getTabBarMenuGroup();
        tabBarMenuGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                case R.id.bar_item_msg:
                    msg = new MsgFragment();
                    getChildFragmentManager().beginTransaction().replace(R.id.main_chat_fragment, msg).commit();
                    break;
                case R.id.bar_item_contact:
                    contact = new ContactFragment();
                    getChildFragmentManager().beginTransaction().replace(R.id.main_chat_fragment, contact).commit();
                    break;
                case R.id.bar_item_video_conf:
                    vedio = new VedioFragment();
                    getChildFragmentManager().beginTransaction().replace(R.id.main_chat_fragment, vedio).commit();
                    break;
                default:
                    break;
                }
            }
        });

        return contentView;
    }
}
