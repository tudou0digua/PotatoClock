package com.cb.potatoclock;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;


public class MainActivity extends Activity implements FragmentCallBack{

	private LaunchFragment launchFragment;
	private FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		fragmentManager = getFragmentManager();
		
		launchFragment = new LaunchFragment();
		FragmentTransaction ft = fragmentManager.beginTransaction();
		ft.add(R.id.launchFrameLayout, launchFragment);
		ft.commit();
		
	}

	@Override
	public void launchOnClickListener(View view) {
		
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FragmentTransaction ft = fragmentManager.beginTransaction();
				ft.add(R.id.launchFrameLayout,new WorkingFragment());
				ft.addToBackStack(null);
				ft.commit();
			}
		});
		
//		LaunchFragment launchFrag = (LaunchFragment)fragmentManager.findFragmentById(R.id.launchFrameLayout);
//		if(launchFrag != null){
//			ImageButton launch = launchFrag.getLaunch();
//			launch.setOnClickListener(new OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					FragmentTransaction ft = fragmentManager.beginTransaction();
//					ft.add(R.id.launchFrameLayout,new WorkingFragment());
//					ft.addToBackStack(null);
//					ft.commit();
//				}
//			});
//		}
	}


}
