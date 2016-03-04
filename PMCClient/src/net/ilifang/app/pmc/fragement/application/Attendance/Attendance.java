package net.ilifang.app.pmc.fragement.application.Attendance;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import net.ilifang.app.commons.utils.ViewUtils;
import net.ilifang.app.pmc.MainActivity;
import net.ilifang.app.pmc.R;
public class Attendance extends Fragment implements OnClickListener{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.attendance, container,false );
		view.findViewById(R.id.Layout_punchcard).setOnClickListener(this);
		view.findViewById(R.id.Layout_time).setOnClickListener(this);
		view.findViewById(R.id.Layout_leave).setOnClickListener(this);
		view.findViewById(R.id.Layout_out).setOnClickListener(this);

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
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.Layout_punchcard:
			ViewUtils.forward(getContext(), Punchcard.class, false);//跳转的方法
			break;
		case R.id.Layout_time:
			ViewUtils.forward(getContext(), Realtimeattendance.class, false);//跳转的方法
			break;
		case R.id.Layout_leave:
//			ViewUtils.forward(getContext(), , false);
			break;
		case R.id.Layout_out:

			break;
		}
	}
}