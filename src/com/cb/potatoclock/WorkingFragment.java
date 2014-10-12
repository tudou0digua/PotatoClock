package com.cb.potatoclock;

import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WorkingFragment extends Fragment {
	private TextView timer_minute,timer_second;
	private ImageButton stop_timer,timer_done;
	private LinearLayout timer_linearlayout;
	private int TOTAL_TIME = 1;
	private CountDownTimer timer;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_work,container,false);
		
		timer_minute = (TextView)view.findViewById(R.id.timer_minute);
		timer_second = (TextView)view.findViewById(R.id.timer_second);
		timer_linearlayout = (LinearLayout)view.findViewById(R.id.timer_linearlayout);
		stop_timer = (ImageButton)view.findViewById(R.id.stop_timer);
		timer_done = (ImageButton)view.findViewById(R.id.timer_done);
		
		
		setTimer();
		timer.start();
		
		stop_timer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				timer.cancel();
			}
		});
		
		return view;
	}

	public void setTimer() {
		timer = new CountDownTimer(TOTAL_TIME * 60 * 1000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				int minute = (int) millisUntilFinished / 60000;
				int second = (int) (int) (millisUntilFinished / 1000) % 60;
				if (minute < 10) {
					timer_minute.setText("0" + minute + " : ");
				} else {
					timer_minute.setText(minute + " : ");
				}
				if (second < 10) {
					timer_second.setText("0" + second);
				} else {
					timer_second.setText(second + "");
				}
			};

			@Override
			public void onFinish() {
				timer_linearlayout.setVisibility(View.GONE);
				timer_done.setVisibility(View.VISIBLE);
			}
			
		};
	}
	
}
