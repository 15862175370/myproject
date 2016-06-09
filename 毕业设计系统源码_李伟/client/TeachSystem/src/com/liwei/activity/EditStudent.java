package com.liwei.activity;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.liwei.model.bean.MyStudentBean;
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

public class EditStudent extends Activity{
	private MyStudentBean myStudentBean;
	private EditText ssex;
	private EditText sno;
	private EditText sname;
	private EditText sage;
	private EditText phone;
	private EditText dno;
	private EditText user;
	private EditText pass;
	private Button change;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.editstudent);
		initView();
	}
            public void initView(){
            	myStudentBean=(MyStudentBean) getIntent().getSerializableExtra("mystudent");
            	ssex=(EditText) findViewById(R.id.mystudent_sex);
        		sno=(EditText) findViewById(R.id.mystudent_sno);
        		sname=(EditText) findViewById(R.id.mystudent_sname);
        		sage=(EditText) findViewById(R.id.mystudent_age);
        		phone=(EditText) findViewById(R.id.mystudent_phone);
        		dno=(EditText) findViewById(R.id.mystudent_dno);
        		change=(Button) findViewById(R.id.mystudent_change);
        		ssex.setText(myStudentBean.getSsex());
        		sno.setText(myStudentBean.getSno());
        		sname.setText(myStudentBean.getSname());
        		sage.setText(myStudentBean.getSage());
        		phone.setText(myStudentBean.getPhone());
        		dno.setText(myStudentBean.getDname());
        		change.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
					if(ssex.getText().toString().equals(myStudentBean.getSsex())
					&&sno.getText().toString().equals(R.id.mystudent_sno)
					&&sname.getText().toString().equals(myStudentBean.getSname())
					&&sage.getText().toString().equals(myStudentBean.getSage())
					&&phone.getText().toString().equals(myStudentBean.getPhone())
					&&dno.getText().toString().equals(myStudentBean.getDname())
					){
						ToastUtils.getToast(EditStudent.this, "请修改后在提交");
					}else{
						HttpUtils utils=new HttpUtils();
						RequestParams params=new RequestParams();
						params.addBodyParameter("ssex",ssex.getText().toString());
						params.addBodyParameter("sno",sno.getText().toString());
						params.addBodyParameter("sname",sname.getText().toString());
						params.addBodyParameter("sage",sage.getText().toString());
						params.addBodyParameter("phone",phone.getText().toString());
						params.addBodyParameter("dno",dno.getText().toString());
						utils.send(HttpMethod.POST, UrlUtils.url+"/ChangeStudentServlet", params, new RequestCallBack<String>() {

							@Override
							public void onFailure(HttpException arg0,
									String arg1) {
								
								ToastUtils.getToast(EditStudent.this, "请求网络数据失败");
							}

							@Override
							public void onSuccess(ResponseInfo<String> responseInfo) {
								String flag=responseInfo.result;
								if(flag.equals("true")){
									ToastUtils.getToast(EditStudent.this, "修改成功");
									finish();
								}else{
									ToastUtils.getToast(EditStudent.this, "修改失败");	
								}
								
							}
						});
					}
						
					}
				});
            }
}
