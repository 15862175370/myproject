package com.liwei.activity;

import java.lang.reflect.Type;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.liwei.application.MyApplation;
import com.liwei.model.bean.AllList;
import com.liwei.model.bean.TestCourse;
import com.liwei.teachsystem.R;
import com.liwei.utils.ToastUtils;
import com.liwei.utils.UrlUtils;

public class CourseTestActivity extends Activity{
	private String sno;
	private int cno;
	private Button submitTest;
	private AllList lv;
	int lastscore=0;
	int c=0;
	 int a=0;
public  int score=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.test_course);
		MyApplation.setActivity(this);
		sno=getIntent().getStringExtra("sno");
		cno=getIntent().getIntExtra("cno", 0);
		
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
		submitTest=(Button) findViewById(R.id.submittest);
		
		submitTest.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(CourseTestActivity.this, "您的得分为"+score, Toast.LENGTH_LONG).show();
			   new AlertDialog.Builder(CourseTestActivity.this)
			   .setTitle("提交试卷")
			   .setMessage("你确定要提交吗")
			   .setPositiveButton("确定", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					HttpUtils utils=new HttpUtils();
					RequestParams params=new RequestParams();
					params.addBodyParameter("sno",sno);
					params.addBodyParameter("cno",String.valueOf(cno));
					params.addBodyParameter("score",String.valueOf(score));
					utils.send(HttpMethod.POST, UrlUtils.url+"/InsertScoreServlet", params, new RequestCallBack<String>() {

						@Override
						public void onFailure(HttpException arg0, String arg1) {
							Toast.makeText(CourseTestActivity.this, "请求网络数据失败", Toast.LENGTH_LONG).show();
							
						}

						@Override
						public void onSuccess(ResponseInfo<String> responseInfo) {
							String flag=responseInfo.result;
							if(flag.equals("true")){
								ToastUtils.getToast(CourseTestActivity.this, "提交成功");
							}else{
								ToastUtils.getToast(CourseTestActivity.this, "提交失败");
							}
							
							
						}
					});
					
					
				}
					
					
				
			})
			.setNegativeButton("取消", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					
				}
			}).create().show();
			}
		});
		
		
		lv=(AllList) findViewById(R.id.coursetestlist);
		HttpUtils utils=new HttpUtils();
		RequestParams params=new RequestParams();
		params.addBodyParameter("cno",String.valueOf(cno));
		utils.send(HttpMethod.POST, UrlUtils.url+"/getTestCourseDataServlet", params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				Toast.makeText(CourseTestActivity.this, "请求网络数据失败", Toast.LENGTH_LONG).show();
				
			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String list=responseInfo.result;
				Gson gson=new Gson();
				Type classOfT=new TypeToken<List<TestCourse>>(){}.getType();
				List<TestCourse> list1=gson.fromJson(list, classOfT);
				CourseTestAdapterT dAdapter=new CourseTestAdapterT(list1);
				lv.setAdapter(dAdapter);
				dAdapter.notifyDataSetChanged();
				
				
			}
		});
		
	}
	
	class CourseTestAdapterT extends BaseAdapter{
		
		 boolean flag=false;
		List<TestCourse> list;
		 public CourseTestAdapterT(List<TestCourse> list) {
			 this.list=list;
			
		}
		@Override
		public int getCount() {
		
			return list.size();
		}

		@Override
		public Object getItem(int position) {
		
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {

			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
		
		
			final TestCourse tCourse=list.get(position);
			
		
		final	ViewHolder holder;
			if(convertView==null){
				holder=new ViewHolder();
				convertView=View.inflate(CourseTestActivity.this, R.layout.test_course_item, null);
				holder.question=(TextView) convertView.findViewById(R.id.question_test);
				holder.rGroup=(RadioGroup) convertView.findViewById(R.id.radiogroup_test);
				holder.radioButtonA=(RadioButton) convertView.findViewById(R.id.radiobutton1_test);
				holder.radioButtonB=(RadioButton) convertView.findViewById(R.id.radiobutton2_test);
				holder.radioButtonZ=(RadioButton) convertView.findViewById(R.id.radiobutton3_test);
				convertView.setTag(holder);
			}else{
				holder=(ViewHolder) convertView.getTag();
			}
			holder.question.setText(tCourse.getQuestion_content());
			holder.radioButtonA.setText(tCourse.getAnswer_content_a());
			holder.radioButtonB.setText(tCourse.getAnswer_content_b());
			holder.radioButtonZ.setText(tCourse.getAnswer_correct());
			
	
			holder.rGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {
				
					
					if(checkedId==holder.radioButtonZ.getId()){
						
					
					
						
						c=score;
						
							score=score+4;
							a=score;
						
					
						
							
					}else {
						
						if(a>c){
						
							score=score-4;
							a=score;
							
						}
					}
					
					
					//lastscore=lastscore+score;
				}
			});
			
			return convertView;
			
		}
		
		class ViewHolder{
			public TextView question;
			public RadioGroup rGroup;
			public RadioButton radioButtonA;
			public RadioButton radioButtonB;
			public RadioButton radioButtonZ;
		}
		
		
		public void reset(){
			a=0;
			c=0;
		}

	}
	
}
