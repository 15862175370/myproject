package com.liwei.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.ImageView;
import android.widget.Toast;
import com.liwei.application.MyApplation;
import com.liwei.teachsystem.R;

public class CalendarActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.calendar);
		MyApplation.setActivity(this);
		ImageView close=(ImageView)findViewById(R.id.bjmgf_sdk_closeAboutBtnId);
		/**
		 * 返回按钮
		 */
		close.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			finish();
				
			}
		});
		 CalendarView calendarView=(CalendarView) findViewById(R.id.calendarView1);
	        calendarView.setOnDateChangeListener(new OnDateChangeListener() {
	            @Override
	            public void onSelectedDayChange(CalendarView view, int year, int month,
	                    int dayOfMonth) {
	                // TODO Auto-generated method stub
	                String date = year + "年" +( month+1) + "月" + dayOfMonth + "日";
	                
	                Toast.makeText(CalendarActivity.this, date, Toast.LENGTH_SHORT).show();
	            }
	        });
	}

}
