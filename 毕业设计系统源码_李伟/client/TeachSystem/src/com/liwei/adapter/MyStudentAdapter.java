package com.liwei.adapter;

import java.util.List;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.liwei.activity.TeacherProvideAdviceActivity;
import com.liwei.model.bean.CircleImageView;
import com.liwei.model.bean.MyStudentBean;
import com.liwei.teachsystem.R;



public class MyStudentAdapter extends BaseAdapter{
	private String tno;
	private List<MyStudentBean> list;
	private Context context;
	public  MyStudentAdapter(Context context,List<MyStudentBean> list,String tno) {
		this.context=context;
		this.list=list;
		this.tno=tno;
		
		
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
	public View getView(int position, View containView, ViewGroup viewGroup) {
		final MyStudentBean myStudentBean=list.get(position);
		BitmapUtils utils=new BitmapUtils(context);
		ViewHolder holder;
		if(containView==null){
			holder=new ViewHolder();
			containView=View.inflate(context,R.layout.mystudent_item, null);
			holder.sno=(TextView) containView.findViewById(R.id.mystudent_sno);
			holder.sname=(TextView) containView.findViewById(R.id.mystudent_sname);
			holder.sicon=(CircleImageView) containView.findViewById(R.id.mystudent_icon);
			holder.ssex=(TextView) containView.findViewById(R.id.mystudent_sex);
			holder.sage=(TextView) containView.findViewById(R.id.mystudent_age);
			holder.dname=(TextView) containView.findViewById(R.id.mystudent_dept);
			holder.phone=(TextView) containView.findViewById(R.id.mystudent_phone);
			holder.advise=(Button) containView.findViewById(R.id.mystudent_message);
			containView.setTag(holder);
		}else{
			holder=(ViewHolder) containView.getTag();
		}
		
		holder.sno.setText(myStudentBean.getSno()+"");
		holder.sname.setText(myStudentBean.getSname());
		holder.ssex.setText(myStudentBean.getSsex());
		holder.sage.setText(myStudentBean.getSage()+"");
		holder.phone.setText(myStudentBean.getPhone());
		holder.dname.setText(myStudentBean.getDname());
		
		utils.display(holder.sicon, myStudentBean.getSicon());
		
		holder.advise.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(context,TeacherProvideAdviceActivity.class);
				intent.putExtra("tno", tno);
				intent.putExtra("sno", String.valueOf(myStudentBean.getSno()));
				context.startActivity(intent);
				
				
				
			}
		});
		return containView;
	}
	
	class ViewHolder{
		public TextView sno;
		public TextView sname;
		public TextView ssex;
		public CircleImageView sicon;
		public TextView sage;
		public TextView dname;
		public TextView phone;
	    public Button advise;
		
	}

}
