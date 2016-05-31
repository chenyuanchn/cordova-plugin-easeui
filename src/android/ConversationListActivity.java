package com.xc.plugin.easemob;


import org.apache.cordova.CordovaActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.controller.EaseUI;
import com.hyphenate.easeui.ui.EaseBaseActivity;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.hyphenate.easeui.ui.EaseConversationListFragment.EaseConversationListItemClickListener;
import com.xc.test.R;

public class ConversationListActivity extends EaseBaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	 super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_chat);
         
    	 EaseConversationListFragment conversationListFragment = new EaseConversationListFragment();
         conversationListFragment.setConversationListItemClickListener(new EaseConversationListItemClickListener() {
          
         	@Override
         	public void onListItemClicked(EMConversation conversation) {
         		startActivity(new Intent(ConversationListActivity.this, ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, conversation.getUserName()));
         	}
         });
         getSupportFragmentManager().beginTransaction().add(R.id.container, conversationListFragment).commit();
    }
}
