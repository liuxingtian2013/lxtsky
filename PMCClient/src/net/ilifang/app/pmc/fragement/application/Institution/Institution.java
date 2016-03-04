package net.ilifang.app.pmc.fragement.application.Institution;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import net.ilifang.app.pmc.R;

public class Institution extends Fragment{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.institution, container,false );
		return view;
	};
			@Override
			public void onActivityCreated(Bundle savedInstanceState) {
				// TODO Auto-generated method stub
				super.onActivityCreated(savedInstanceState);
			}
			@Override
			public void onPause() {
				// TODO Auto-generated method stub
				super.onPause();
			}
}
