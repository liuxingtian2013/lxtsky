package net.ilifang.app.pmc.fragement.home;

import net.ilifang.app.pmc.R;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.viewpagerindicator.IconPagerAdapter;

public class ImageViewFragmentAdapter extends FragmentPagerAdapter implements IconPagerAdapter {

    protected static final int[] ICONS = new int[] { R.drawable.banner1, R.drawable.banner2, R.drawable.banner3 };

    public ImageViewFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ImageViewFragment.newInstance(ICONS[position]);
    }

    @Override
    public int getCount() {
        return ICONS.length;
    }

    @Override
    public int getIconResId(int index) {
        return ICONS[index % ICONS.length];
    }

}