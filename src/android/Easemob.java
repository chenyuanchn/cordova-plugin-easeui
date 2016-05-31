package com.xc.plugin.easemob;


import android.content.Intent;
import android.util.Log;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.controller.EaseUI;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class Easemob extends CordovaPlugin {

    CallbackContext callback;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("init")) {
            callback = callbackContext;
            EMOptions options = new EMOptions();
         // 默认添加好友时，是不需要验证的，改成需要验证
         options.setAcceptInvitationAlways(false);
          //初始化
//            EMClient.getInstance().init(this.cordova.getActivity().getApplication(), options);
//            //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
//            EMClient.getInstance().setDebugMode(true);
            
            // 默认添加好友时，是不需要验证的，改成需要验证
            EaseUI.getInstance().init(this.cordova.getActivity().getApplication(),options);

            PluginResult result = new PluginResult(PluginResult.Status.OK, "init");
            callback.sendPluginResult(result);

            return true;
        }else if (action.equals("login")) {
            callback = callbackContext;
            PluginResult result = null;

            String userName = args.getString(0);
            String password = args.getString(1);

            if(userName==null||userName.equals("")||userName.equals("null")||
               password==null||password.equals("")||password.equals("null")){
            	 result = new PluginResult(PluginResult.Status.ERROR, "请输入用户名或密码");
                 callback.sendPluginResult(result);
                 return false;
            }
    		// 调用sdk登陆方法登陆聊天服务器
    		EMClient.getInstance().login(userName, password, new EMCallBack() {

    			@Override
    			public void onSuccess() {

    				Log.e("onSuccess", "登陆聊天服务器成功！");
    			}

    			@Override
    			public void onProgress(int progress, String status) {
    				 Log.e("onProgress", "progress:"+progress);
    			}

    			@Override
    			public void onError(final int code, final String message) {
    				Log.e("onError", "登陆聊天服务器失败,code=" + code + ",message=" + message);
    	               
    			}
    		});
            
            result = new PluginResult(PluginResult.Status.OK, "userName: " + userName + "/" + "password: " + password);
            callback.sendPluginResult(result);

            return true;
        }else if (action.equals("chat")) {//聊天页面，最主要的 fragment
            callback = callbackContext;

            
            PluginResult result = null;

            String extraUserId = args.getString(0);
            String extraChatType = args.getString(1);

            
            Intent intent = new Intent();
            
            if(extraUserId==null||extraUserId.equals("")||extraUserId.equals("null")){
            	 result = new PluginResult(PluginResult.Status.ERROR, "请输入对方聊天id");
                 callback.sendPluginResult(result);
                 return false;
            }
            
            if(extraChatType!=null&&!extraChatType.equals("")&&!extraChatType.equals("null")){
            	intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, extraChatType);  
           }
            intent.putExtra(EaseConstant.EXTRA_USER_ID, extraUserId);  
            intent.setClass(this.cordova.getActivity().getApplication(), ChatActivity.class);
            this.cordova.getActivity().startActivity(intent);

            result = new PluginResult(PluginResult.Status.OK, "chat");
            callback.sendPluginResult(result);

            return true;
        }else if (action.equals("conversationList")) {//会话列表页面
        	callback = callbackContext;
        	 Intent intent = new Intent();
             intent.setClass(this.cordova.getActivity().getApplication(), ConversationListActivity.class);
             this.cordova.getActivity().startActivity(intent);

             PluginResult result = new PluginResult(PluginResult.Status.OK, "ConversationList");
             callback.sendPluginResult(result);
           

            return true;
        	
        	
        }else if (action.equals("contactList")) {//联系人页面
        	callback = callbackContext;

            Intent intent = new Intent();
            intent.setClass(this.cordova.getActivity().getApplication(), ContactListActivity.class);
            this.cordova.getActivity().startActivity(intent);

            PluginResult result = new PluginResult(PluginResult.Status.OK, "contactList");
            callback.sendPluginResult(result);

            return true;
        	
        	
        }
        return false;
    }
}
