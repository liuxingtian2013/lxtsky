package net.ilifang.app.pmc.fragement.home;

import net.ilifang.app.pmc.R;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public final class ImageViewFragment extends Fragment {

    Context context;
    static int resId;

    public static ImageViewFragment newInstance(int icons) {
        ImageViewFragment.resId = icons;
        ImageViewFragment fragment = new ImageViewFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getContext();

        View view = inflater.inflate(R.layout.viewpager_image_fragment, container, false);
        ImageView image = (ImageView) view.findViewById(R.id.image_item);
        image.setImageResource(resId);

        return view;
    }
}
