package com.liwei.activity;

import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imlib.model.Conversation;

import java.util.Locale;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.liwei.application.MyApplation;
import com.liwei.teachsystem.R;

public class ConversationActivity extends FragmentActivity{
	/**
	   * 目标 Id
	   */
	    private String mTargetId;

	    /**
	     * 刚刚创建完讨论组后获得讨论组的id 为targetIds，需要根据 为targetIds 获取 targetId
	     */
	    private String mTargetIds;

	    /**
	     * 会话类型
	     */
	    private Conversation.ConversationType mConversationType;

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.conversation);
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
	        Intent intent = getIntent();

	        getIntentDate(intent);
	    }

	    /**
	     * 展示如何从 Intent 中得到 融云会话页面传递的 Uri
	     */
	    private void getIntentDate(Intent intent) {

	        mTargetId = intent.getData().getQueryParameter("targetId");
	        mTargetIds = intent.getData().getQueryParameter("targetIds");
	        //intent.getData().getLastPathSegment();//获得当前会话类型
	        mConversationType = Conversation.ConversationType.valueOf(intent.getData().getLastPathSegment().toUpperCase(Locale.getDefault()));

	        enterFragment(mConversationType, mTargetId);
	    }

	    /**
	     * 加载会话页面 ConversationFragment
	     *
	     * @param mConversationType 会话类型
	     * @param mTargetId 目标 Id
	     */
	    private void enterFragment(Conversation.ConversationType mConversationType, String mTargetId) {

	        ConversationFragment fragment = (ConversationFragment) getSupportFragmentManager().findFragmentById(R.id.conversation);

	        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
	                .appendPath("conversation").appendPath(mConversationType.getName().toLowerCase())
	                .appendQueryParameter("targetId", mTargetId).build();

	        fragment.setUri(uri);
	    }
}
