package com.xc.plugin.easemob;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.controller.EaseUI;
import com.hyphenate.easeui.ui.EaseBaseActivity;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.xc.smemobile.R;

public class ChatActivity extends EaseBaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
//        EMOptions options = new EMOptions();
//        // 默认添加好友时，是不需要验证的，改成需要验证
//        options.setAcceptInvitationAlways(false);
//        EaseUI.getInstance().init(this.getApplication(),options);
        
//        Intent intent = getIntent();  
//        String extraChatType = intent.getStringExtra(EaseConstant.EXTRA_CHAT_TYPE);  
//        String extraUserId = intent.getStringExtra(EaseConstant.EXTRA_USER_ID);  
//        int extraChatTypeInt = Integer.parseInt(extraChatType);
        
        //new出EaseChatFragment或其子类的实例
        EaseChatFragment chatFragment = new EaseChatFragment();
        //传入参数
//        Bundle args = new Bundle();
//        args.putInt(EaseConstant.EXTRA_CHAT_TYPE, extraChatTypeInt);
//        args.putString(EaseConstant.EXTRA_USER_ID, extraUserId);
//        chatFragment.setArguments(args);
        
        chatFragment.setArguments(getIntent().getExtras());
        
        getSupportFragmentManager().beginTransaction().add(R.id.container, chatFragment).commit();
    }
}
