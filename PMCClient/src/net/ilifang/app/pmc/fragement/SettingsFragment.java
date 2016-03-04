package net.ilifang.app.pmc.fragement;

import com.special.ResideMenu.ResideMenu;

import net.ilifang.app.pmc.MainActivity;
import net.ilifang.app.pmc.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SettingsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        MainActivity parentActivity = (MainActivity) getActivity();
        ResideMenu resideMenu = parentActivity.getResideMenu();
        resideMenu.clearIgnoredViewList();
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

}
