package com.cb.potatoclock;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class LaunchFragment extends Fragment {

	private Dialog dialogSetDuration;
	private LinearLayout linearLayoutWork, linearLayoutShortTime,linearLayoutLongTime;
	private Button dialogCancel, dialogConfirm;
	private ImageButton launch;
	private FragmentCallBack fragmentCallback;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try{
			fragmentCallback = (FragmentCallBack)activity;
		}catch(ClassCastException e){
			throw new ClassCastException(activity.toString() + "must implements FragmentCallback Interface");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.launch,container,false);
		// 生成设置时间的Dialog
		myDialog();
		//绑定开始页面的3个LinearLayout
		linearLayoutWork = (LinearLayout) view.findViewById(R.id.linearlayout_work);
		linearLayoutShortTime = (LinearLayout) view.findViewById(R.id.linearlayout_shortrest);
		linearLayoutLongTime = (LinearLayout) view.findViewById(R.id.linearlayout_longrest);
		//监听这3个LinearLayout
		linearLayoutWork.setOnClickListener(new LinearLayoutListener());
		linearLayoutShortTime.setOnClickListener(new LinearLayoutListener());
		linearLayoutLongTime.setOnClickListener(new LinearLayoutListener());
		//开始番茄工作法，监听launch按钮
		setLaunch((ImageButton)view.findViewById(R.id.ready_go));
		fragmentCallback.launchOnClickListener(null);
		
		return view;
	}
	
	class LinearLayoutListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// 得到Dialog所在的当前窗口
			Window window = dialogSetDuration.getWindow();
			// 得到屏幕的尺寸
			WindowManager.LayoutParams layoutParam = window.getAttributes();
			DisplayMetrics dm = new DisplayMetrics();
			getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
			// 将dialog的宽设为，屏幕宽的90%
			layoutParam.width = (int) (dm.widthPixels * 0.9);
			// 设置自定义好的dialog窗口属性
			window.setAttributes(layoutParam);
			// 在当前dialog window 下绑定Button
			// 设置Dialog 的取消按钮
			dialogCancel = (Button) window.findViewById(R.id.dialog_button_cancel);
			dialogCancel.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialogSetDuration.dismiss();
				}
			});
			// 设置Dialog的确定按钮
			dialogConfirm = (Button) window.findViewById(R.id.dialog_button_confirm);
			dialogConfirm.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialogSetDuration.dismiss();
				}
			});
			// 显示Dialog
			dialogSetDuration.show();
		}
	}

	// 设置工作时间等的Dialog
	private void myDialog() {
		dialogSetDuration = new Dialog(getActivity(),R.style.dialogSetDuration);
		dialogSetDuration.setContentView(R.layout.dialog_setduration);
	}

	public ImageButton getLaunch() {
		return launch;
	}

	public void setLaunch(ImageButton launch) {
		this.launch = launch;
	}
	

}
