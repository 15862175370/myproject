package com.liwei.activity;

import com.liwei.application.MyApplation;
import com.liwei.teachsystem.MainActivity;
import com.liwei.teachsystem.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class Administrator  extends Activity implements OnClickListener{
	private Button exit;
	private Button addTeacher;
	private Button searchTeacher;
	private Button addStudent;
	private Button searchStudent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.administrator_center);
		MyApplation.setActivity(this);
		initView();
	}

	public void initView(){
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
		exit=(Button) findViewById(R.id.exit);
		addTeacher=(Button) findViewById(R.id.addteacher);
		searchTeacher=(Button) findViewById(R.id.serchteacher);
		addStudent=(Button) findViewById(R.id.addstudent);
		searchStudent=(Button) findViewById(R.id.serchstudent);
		addStudent.setOnClickListener(this);
		searchStudent.setOnClickListener(this);
		addTeacher.setOnClickListener(this);
		exit.setOnClickListener(this);
		searchTeacher.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.exit:
			Intent intent=new Intent(Administrator.this,MainActivity.class);
		startActivity(intent);
		finish();
			break;
          case R.id.addteacher:
        	  Intent intent1=new Intent(Administrator.this,ChangeTeacher.class);
      		startActivity(intent1);
        	  break;
        	  case R.id.serchteacher:
        		  Intent intent2=new Intent(Administrator.this,AdministratorSerch.class);
            		startActivity(intent2);
        		  break;
        	  case R.id.addstudent :
        		  Intent intent3=new Intent(Administrator.this,AddStudent.class);
          		startActivity(intent3);
        		  break;
        	  case R.id.serchstudent :
        		  Intent intent4=new Intent(Administrator.this,SearchStudentActivity.class);
            		startActivity(intent4);
        		  break;
		default:
			break;
		}
		
	}
}
