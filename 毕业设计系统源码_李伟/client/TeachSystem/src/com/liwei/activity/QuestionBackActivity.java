package com.liwei.activity;

import java.lang.reflect.Type;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.liwei.adapter.QuestionBackAdapter;
import com.liwei.application.MyApplation;
import com.liwei.model.bean.QuestionAndAnswer;
import com.liwei.teachsystem.R;
import com.liwei.utils.ToastUtils;
import com.liwei.utils.UrlUtils;

public class QuestionBackActivity  extends Activity{
	private ListView lv;
	private String sno;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.questionback);
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
		lv=(ListView) findViewById(R.id.questionback);
		sno=getIntent().getStringExtra("sno");
		Toast.makeText(QuestionBackActivity.this, sno, Toast.LENGTH_LONG).show();
		HttpUtils utils=new HttpUtils();
        RequestParams params=new RequestParams();
        params.addBodyParameter("sno",sno);
		utils.send(HttpMethod.POST, UrlUtils.url+"/QuestionBackServlet", params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				ToastUtils.getToast(QuestionBackActivity.this, "请求失败");
				
			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String list=responseInfo.result;
				Gson gson=new Gson();
				Type classOfT=new TypeToken<List<QuestionAndAnswer>>(){}.getType();
				List<QuestionAndAnswer> list1=gson.fromJson(list, classOfT);
				QuestionBackAdapter adapter=new QuestionBackAdapter(QuestionBackActivity.this, list1);
				lv.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				ToastUtils.getToast(QuestionBackActivity.this, "请求成功");
				
			}
		});
		
	}

}
