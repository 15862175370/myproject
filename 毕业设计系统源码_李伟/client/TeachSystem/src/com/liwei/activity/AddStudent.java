package com.liwei.activity;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.liwei.application.MyApplation;
import com.liwei.teachsystem.R;
import com.liwei.utils.ToastUtils;
import com.liwei.utils.UrlUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class AddStudent  extends Activity{
	private EditText ssex;
	private EditText sno;
	private EditText sname;
	private EditText sage;
	private EditText phone;
	private EditText dno;
	private EditText user;
	private EditText pass;
	private Button add;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.addstudent);
		MyApplation.setActivity(this);
		initView();
		
	}
	public void initView(){
		ssex=(EditText) findViewById(R.id.mystudent_sex);
		sno=(EditText) findViewById(R.id.mystudent_sno);
		sname=(EditText) findViewById(R.id.mystudent_sname);
		sage=(EditText) findViewById(R.id.mystudent_age);
		phone=(EditText) findViewById(R.id.mystudent_phone);
		dno=(EditText) findViewById(R.id.mystudent_dno);
		user=(EditText) findViewById(R.id.mystudent_user);
		pass=(EditText) findViewById(R.id.mystudent_pass);
		add=(Button) findViewById(R.id.mystudent_add);
		add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			if(sno.getText().toString().equals("")
				||sname.getText().toString().equals("")	
				||sage.getText().toString().equals("")
				||phone.getText().toString().equals("")
				||dno.getText().toString().equals("")
				||user.getText().toString().equals("")
				||pass.getText().toString().equals("")
				||ssex.getText().toString().equals("")
					){
				ToastUtils.getToast(AddStudent.this, "请确保所填信息都不为空");
			}else{
				HttpUtils utils=new HttpUtils();
				RequestParams params=new RequestParams();
				params.addBodyParameter("sno",sno.getText().toString());
				params.addBodyParameter("sname",sname.getText().toString());
				params.addBodyParameter("sage",sage.getText().toString());
				params.addBodyParameter("phone",phone.getText().toString());
				params.addBodyParameter("dno",dno.getText().toString());
				params.addBodyParameter("user",user.getText().toString());
				params.addBodyParameter("pass",pass.getText().toString());
				params.addBodyParameter("ssex",ssex.getText().toString());
				utils.send(HttpMethod.POST, UrlUtils.url+"/AddStudentServlet", params, new RequestCallBack<String>() {
					@Override
					public void onFailure(HttpException arg0, String arg1) {
				          ToastUtils.getToast(AddStudent.this, "请求网络数据失败");
						
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
					String flag=responseInfo.result;
					if(flag.equals("true")){
						 ToastUtils.getToast(AddStudent.this, "添加成功");
						 finish();
					}else{
						 ToastUtils.getToast(AddStudent.this, "添加失败");
					}
						
					}
				});
			}
				
			}
		});
	}

}
