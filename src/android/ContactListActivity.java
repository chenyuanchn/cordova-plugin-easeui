package com.xc.plugin.easemob;


import java.util.HashMap;
import java.util.Map;

import org.apache.cordova.CordovaActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.controller.EaseUI;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.ui.EaseBaseActivity;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.ui.EaseContactListFragment;
import com.hyphenate.easeui.ui.EaseContactListFragment.EaseContactListItemClickListener;
import com.xc.smemobile.R;

public class ContactListActivity extends EaseBaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
//        EMOptions options = new EMOptions();
//        // 默认添加好友时，是不需要验证的，改成需要验证
//        options.setAcceptInvitationAlways(false);
//        EaseUI.getInstance().init(this.getApplication(),options);
        
        //new出EaseChatFragment或其子类的实例
        EaseContactListFragment contactListFragment= new EaseContactListFragment();
        //需要设置联系人列表才能启动fragment
        contactListFragment.setContactsMap(getContacts());
        //设置item点击事件
        contactListFragment.setContactListItemClickListener(new EaseContactListItemClickListener() {
         
                    @Override
                    public void onListItemClicked(EaseUser user) {
                        startActivity(new Intent(ContactListActivity.this, ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, user.getUsername()));
                    }
                });
        getSupportFragmentManager().beginTransaction().add(R.id.container, contactListFragment).commit();
    }

    /**
     * 临时生成的数据，密码皆为123456，可以登录测试接发消息
     * @return
     */
    private Map<String, EaseUser> getContacts(){
        Map<String, EaseUser> contacts = new HashMap<String, EaseUser>();
        for(int i = 1; i <= 10; i++){
            EaseUser user = new EaseUser("easeuitest" + i);
            contacts.put("easeuitest" + i, user);
        }
        return contacts;
    }
}
