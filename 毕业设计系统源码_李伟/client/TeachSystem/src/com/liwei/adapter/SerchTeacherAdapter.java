package com.liwei.adapter;

import java.util.List;












import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.liwei.activity.AdministratorSerch;
import com.liwei.activity.EditTeacherActivity;
import com.liwei.activity.StudentSendMessageToTeacherActivity;
import com.liwei.adapter.MyTeacherAdapter.ViewHolder;
import com.liwei.model.bean.CircleImageView;
import com.liwei.model.bean.MyTeacher;
import com.liwei.teachsystem.R;
import com.liwei.utils.ToastUtils;
import com.liwei.utils.UrlUtils;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class SerchTeacherAdapter extends BaseAdapter {
	private AdministratorSerch administratorSerch;
	private Context context;
	private List<MyTeacher> list;
	private String tno;
	 public SerchTeacherAdapter(Context context,List<MyTeacher>list,String tno,AdministratorSerch administratorSerch){
		this.context=context;
		this.list=list;
		this.tno=tno;
		this.administratorSerch=administratorSerch;
		
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final MyTeacher myTeacher=list.get(position);
		ViewHolder holder;
		BitmapUtils utils=new BitmapUtils(context);
			if(convertView==null){
				holder=new ViewHolder();
				convertView=View.inflate(context, R.layout.serchteacher_item, null);
			    holder.tno=(TextView) convertView.findViewById(R.id.myteacher_tno);
				holder.tsex=(TextView) convertView.findViewById(R.id.myteacher_sex);
				holder.prof=(TextView) convertView.findViewById(R.id.teacher_prof);
				holder.phone=(TextView) convertView.findViewById(R.id.myteacher_phone);
				holder.tname=(TextView) convertView.findViewById(R.id.myteacher_tname);
				holder.dname=(TextView) convertView.findViewById(R.id.myteacher_dept);
				holder.tage=(TextView) convertView.findViewById(R.id.myteacher_age);
				holder.ticon=(CircleImageView) convertView.findViewById(R.id.mytecher_icon);
				holder.delete=(Button) convertView.findViewById(R.id.myteacher_delete);
				holder.change=(Button) convertView.findViewById(R.id.myteacher_change);
				convertView.setTag(holder);
			}else{
				holder=(ViewHolder) convertView.getTag();
			}
			holder.tno.setText(myTeacher.getTno()+"");
			holder.tname.setText(myTeacher.getTname());
			holder.tsex.setText(myTeacher.getTsex());
			holder.phone.setText(myTeacher.getPhone());
			holder.prof.setText(myTeacher.getProf());
			holder.dname.setText(myTeacher.getDname());
			holder.tage.setText(myTeacher.getTage()+"");
			
			if(myTeacher.getTicon()!=null){
			utils.display(holder.ticon, myTeacher.getTicon());
			}
			//修改老师
			holder.change.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent=new Intent(context,EditTeacherActivity.class);
					intent.putExtra("myteacher", myTeacher);
					context.startActivity(intent);
				}
			});
			//删除教师
			holder.delete.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					HttpUtils utils=new HttpUtils();
					RequestParams params=new RequestParams();
					params.addBodyParameter("tno",String.valueOf(myTeacher.getTno()));
					utils.send(HttpMethod.POST, UrlUtils.url+"/DeleteTeacherServlet", params, new RequestCallBack<String>() {

						@Override
						public void onFailure(HttpException arg0, String arg1) {
							ToastUtils.getToast(context, "请求网络数据失败");
							
						}

						@Override
						public void onSuccess(ResponseInfo<String> responseInfo) {
							String flag=responseInfo.result;
							if(flag.equals("true")){
								ToastUtils.getToast(context, "删除成功");
								administratorSerch.finish();
							}else{
								ToastUtils.getToast(context, "删除失败");
							}
							
						}
					});
					
				}
			});
			
		return convertView;
	}
	class ViewHolder{
		public TextView cno;
		public  TextView cname;
		public TextView tno;
		public TextView tname;
	    public TextView tsex;
	    public TextView prof;
	    public CircleImageView ticon;
	    public TextView phone;
	    public TextView dname;
	    public TextView tage;
	    public Button change;
	    public Button delete;
	}

}
