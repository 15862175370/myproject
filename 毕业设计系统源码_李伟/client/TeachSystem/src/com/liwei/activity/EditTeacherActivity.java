package com.liwei.activity;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.liwei.model.bean.MyTeacher;
import com.liwei.teachsystem.R;
import com.liwei.utils.ToastUtils;
import com.liwei.utils.UrlUtils;

import android.R.string;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class EditTeacherActivity  extends Activity {
	private MyTeacher myTeacher;
	private EditText tno;
	private EditText tname;
	private EditText tsex;
	private EditText tage;
	private EditText prof;
	private EditText phone;
	private EditText dept;
	private Button change;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	      setContentView(R.layout.edit_teacher);
	      initView();
	}
	public void initView(){
		tno=(EditText) findViewById(R.id.myteacher_tno);
		tname=(EditText) findViewById(R.id.myteacher_tname);
		tsex=(EditText) findViewById(R.id.myteacher_sex);
		tage=(EditText) findViewById(R.id.myteacher_age);
		prof=(EditText) findViewById(R.id.teacher_prof);
		phone=(EditText) findViewById(R.id.myteacher_phone);
		dept=(EditText) findViewById(R.id.myteacher_dept);
		change=(Button) findViewById(R.id.myteacher_add);
		myTeacher=(MyTeacher) getIntent().getSerializableExtra("myteacher");
		tno.setText(String.valueOf(myTeacher.getTno()));
		tname.setText(myTeacher.getTname());
		tsex.setText(myTeacher.getTsex());
		tage.setText(String.valueOf(myTeacher.getTage()));
		prof.setText(myTeacher.getProf());
		phone.setText(myTeacher.getPhone());
		dept.setText(myTeacher.getDname());
		change.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(tno.getText().toString().equals(String.valueOf(myTeacher.getTno()))
				&&tname.getText().toString().equals(myTeacher.getTname())
				&&tsex.getText().toString().equals(myTeacher.getTsex())
				&&tage.getText().toString().equals(String.valueOf(myTeacher.getTage()))
				&&prof.getText().toString().equals(myTeacher.getProf())	
				&&phone.getText().toString().equals(myTeacher.getPhone())
				&&dept.getText().toString().equals(myTeacher.getDname())){
					ToastUtils.getToast(EditTeacherActivity.this, "不能提交未修改的内容");
				}else{
				HttpUtils utils=new HttpUtils();
				RequestParams params=new RequestParams();
				params.addBodyParameter("tno",tno.getText().toString());
				params.addBodyParameter("tname",tname.getText().toString());
				params.addBodyParameter("tsex",tsex.getText().toString());
				params.addBodyParameter("tage",tage.getText().toString());
				params.addBodyParameter("prof",prof.getText().toString());
				params.addBodyParameter("phone",phone.getText().toString());
				params.addBodyParameter("dept",dept.getText().toString());
				utils.send(HttpMethod.POST, UrlUtils.url+"/ChangTeacherInfo", params, new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						ToastUtils.getToast(EditTeacherActivity.this, "请求网络数据失败");
						
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						String flag=responseInfo.result;
						if(flag.equals("true")){
							ToastUtils.getToast(EditTeacherActivity.this, "修改成功");
							finish();
						}else{
							ToastUtils.getToast(EditTeacherActivity.this, "修改失败");
						}
						
						
					}
				});
				}
						
						
				
			}
		});
		
	}

}
