package com.xc.plugin.easemob;


import java.util.List;

import org.apache.cordova.CordovaActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.hyphenate.EMConversationListener;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.chat.EMConversation.EMConversationType;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.controller.EaseUI;
import com.hyphenate.easeui.ui.EaseBaseActivity;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.hyphenate.easeui.ui.EaseConversationListFragment.EaseConversationListItemClickListener;
import com.xc.smemobile.R;

public class ConversationListActivity extends EaseBaseActivity{

	private EaseConversationListFragment conversationListFragment;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	 super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_chat);
         
    	 conversationListFragment = new EaseConversationListFragment();
         conversationListFragment.setConversationListItemClickListener(new EaseConversationListItemClickListener() {
          
         	@Override
         	public void onListItemClicked(EMConversation conversation) {
                String username = conversation.getUserName();
                if (username.equals(EMClient.getInstance().getCurrentUser()))
                    Toast.makeText(ConversationListActivity.this, R.string.Cant_chat_with_yourself, 0).show();
                else {
                    // 进入聊天页面
                    Intent intent = new Intent(ConversationListActivity.this, ChatActivity.class);
                    if(conversation.isGroup()){
                        if(conversation.getType() == EMConversationType.ChatRoom){
                            // it's group chat
                            intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_CHATROOM);
                        }else{
                            intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_GROUP);
                        }
                        
                    }
                    // it's single chat
                    intent.putExtra(EaseConstant.EXTRA_USER_ID, username);
                    startActivity(intent);
                }
//         		startActivity(new Intent(ConversationListActivity.this, ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, conversation.getUserName()));
         	}
         });
         getSupportFragmentManager().beginTransaction().add(R.id.container, conversationListFragment).commit();
    }
    
    EMMessageListener messageListener = new EMMessageListener() {
		
		@Override
		public void onMessageReceived(List<EMMessage> messages) {
			if (conversationListFragment != null) {
				conversationListFragment.refresh();
			}
		}
		
		@Override
		public void onCmdMessageReceived(List<EMMessage> messages) {
		}
		
		@Override
		public void onMessageReadAckReceived(List<EMMessage> messages) {
		}
		
		@Override
		public void onMessageDeliveryAckReceived(List<EMMessage> message) {
		}
		
		@Override
		public void onMessageChanged(EMMessage message, Object change) {}
	};
	@Override
	protected void onResume() {
		super.onResume();
		
		EMClient.getInstance().chatManager().addMessageListener(messageListener);
	}

	@Override
	protected void onStop() {
		EMClient.getInstance().chatManager().removeMessageListener(messageListener);

		super.onStop();
	}
	
}
