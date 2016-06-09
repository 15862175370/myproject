package com.liwei.activity;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.liwei.adapter.SerchTeacherAdapter;
import com.liwei.application.MyApplation;
import com.liwei.model.bean.MyTeacher;
import com.liwei.teachsystem.R;
import com.liwei.utils.ToastUtils;
import com.liwei.utils.UrlUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

public class AdministratorSerch  extends Activity{
	private EditText serach;
	private ImageView iv;
	private ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.serchteacher);
		MyApplation.setActivity(this);
		initView();
	}
             public void initView(){
            	 //搜索按钮
            	 serach=(EditText) findViewById(R.id.myteacher_searchView);
            	 iv=(ImageView) findViewById(R.id.iv);
            	 lv=(ListView) findViewById(R.id.mycourse);
            	 iv.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						if(serach.getText().toString().equals("")){
							ToastUtils.getToast(AdministratorSerch.this, "请输入内容");
						}else{
							HttpUtils utils=new HttpUtils();
							RequestParams params=new RequestParams();
							params.addBodyParameter("tno",serach.getText().toString());
							utils.send(HttpMethod.POST, UrlUtils.url+"/SearchTeacherServlet", params, new RequestCallBack<String>() {

								@Override
								public void onFailure(HttpException arg0,
										String arg1) {
									ToastUtils.getToast(AdministratorSerch.this, "请求网络失败");
									
								}

								@Override
								public void onSuccess(ResponseInfo<String> responseInfo) {
									String list=responseInfo.result;
									if(list.equals("[]")||list==null||list.equals("")){
										ToastUtils.getToast(AdministratorSerch.this, "你查找内容不存在");
									}
									Gson gson=new Gson();
									Type classOfT=new TypeToken<List<MyTeacher>>(){}.getType();
									List<MyTeacher> list1=gson.fromJson(list, classOfT);
									SerchTeacherAdapter adapter=new SerchTeacherAdapter(AdministratorSerch.this, list1,serach.getText().toString(),AdministratorSerch.this);
									lv.setAdapter(adapter);
									adapter.notifyDataSetChanged();
									
								}
							});
						}
						
					}
				});
             }
}
