package com.xc.plugin.easemob;


import java.io.File;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMImageMessageBody;
import com.hyphenate.chat.EMLocationMessageBody;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.chat.EMVoiceMessageBody;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.controller.EaseUI;
import com.hyphenate.easeui.ui.EaseBaiduMapActivity;
import com.xc.smemobile.R;

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
    	callback = callbackContext;
    	if (action.equals("init")) {
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
        }else if (action.equals("addMessageListener")) {
            PluginResult result = null;

            EMMessageListener msgListener = new EMMessageListener() {

				@Override
				public void onCmdMessageReceived(List<EMMessage> arg0) {
					// TODO Auto-generated method stub
				}
				@Override
				public void onMessageChanged(EMMessage arg0, Object arg1) {
					// TODO Auto-generated method stub
				}
				@Override
				public void onMessageDeliveryAckReceived(List<EMMessage> arg0) {
					// TODO Auto-generated method stub
				}
				@Override
				public void onMessageReadAckReceived(List<EMMessage> arg0) {
//					 JSONObject data = getMessageObject(message, extras);
			        String format = "cordova.plugins.Easemob.onMessageReadAckReceivedInAndroidCallback(%s);";
			        JSONArray jSONArray = new JSONArray();
			        packageJSONArray(arg0,jSONArray);
			        final String js = String.format(format, jSONArray);
			        cordova.getActivity().runOnUiThread(new Runnable() {
			            @Override
			            public void run() {
			                webView.loadUrl("javascript:" + js);
			            }
			        });
			}
				@Override
				public void onMessageReceived(List<EMMessage> arg0) {
//					 JSONObject data = getMessageObject(message, extras);
				        String format = "cordova.plugins.Easemob.receiveMessageInAndroidCallback(%s);";
				        JSONArray jSONArray = new JSONArray();
				        packageJSONArray(arg0,jSONArray);
				        final String js = String.format(format, jSONArray);
				        cordova.getActivity().runOnUiThread(new Runnable() {
				            @Override
				            public void run() {
				                webView.loadUrl("javascript:" + js);
				            }
				        });
				}
       		 
    		};
    		//通过注册消息监听来接收消息
    		EMClient.getInstance().chatManager().addMessageListener(msgListener);
            
            result = new PluginResult(PluginResult.Status.OK, "注册消息监听成功");
            callback.sendPluginResult(result);

            return true;
        }else if (action.equals("chat")) {//聊天页面，最主要的 fragment
            
            PluginResult result = null;

            String extraUserId = args.getString(0);
            int extraChatTypeInt = args.getInt(1);
            String extraChatType = extraChatTypeInt+"";

            
            Intent intent = new Intent();
            
            if(extraUserId==null||extraUserId.equals("")||extraUserId.equals("null")){
            	 result = new PluginResult(PluginResult.Status.ERROR, "请输入对方聊天id");
                 callback.sendPluginResult(result);
                 return false;
            }
            
            if(extraChatType!=null&&!extraChatType.equals("")&&!extraChatType.equals("null")){
            	intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, extraChatTypeInt);  
           }
            intent.putExtra(EaseConstant.EXTRA_USER_ID, extraUserId);  
            intent.setClass(this.cordova.getActivity().getApplication(), ChatActivity.class);
//            this.cordova.getActivity().startActivity(intent);
            cordova.startActivityForResult((CordovaPlugin) this,intent, 1);  

            result = new PluginResult(PluginResult.Status.OK, "chat");
            callback.sendPluginResult(result);

            return true;
        }else if (action.equals("conversationList")) {//会话列表页面
        	 Intent intent = new Intent();
             intent.setClass(this.cordova.getActivity().getApplication(), ConversationListActivity.class);
             this.cordova.getActivity().startActivity(intent);

             PluginResult result = new PluginResult(PluginResult.Status.OK, "ConversationList");
             callback.sendPluginResult(result);
           

            return true;
        	
        	
        }else if (action.equals("contactList")) {//联系人页面

            Intent intent = new Intent();
            intent.setClass(this.cordova.getActivity().getApplication(), ContactListActivity.class);
            this.cordova.getActivity().startActivity(intent);

            PluginResult result = new PluginResult(PluginResult.Status.OK, "contactList");
            callback.sendPluginResult(result);

            return true;
        	
        	
        }else if (action.equals("logout")) {//退出聊天登录

        	EMClient.getInstance().logout(true);//此方法为同步方法，里面的参数 true 表示退出登录时解绑 GCM 或者小米推送的 token
        	
            PluginResult result = new PluginResult(PluginResult.Status.OK, "logout");
            callback.sendPluginResult(result);

            return true;
        	
        	
        }
        return false;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == Activity.RESULT_OK) { 
            if (requestCode == 1) { //chat回来
            	

//				 JSONObject data = getMessageObject(message, extras);
			        String format = "cordova.plugins.Easemob.onActivityResultInAndroidCallback(%s);";
			        JSONObject jExtras = new JSONObject();
			        try {
						jExtras.put("data", "从聊天返回web界面");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        final String js = String.format(format, jExtras);
			        cordova.getActivity().runOnUiThread(new Runnable() {
			            @Override
			            public void run() {
			                webView.loadUrl("javascript:" + js);
			            }
			        });
			
            }
//        }
    }
    
    public void packageJSONArray(List<EMMessage> arg0,JSONArray jSONArray){
    	JSONObject jExtras;
        for(int i=0;i<arg0.size();i++){
        	try {
        		jExtras = new JSONObject();
        		//arg0.get(i).getType()    IMAGE   TXT VOICE LOCATION
        		//body内容根据类型选择，这里用EMTextMessageBody测试
        		if(arg0!=null&&arg0.get(i)!=null&&arg0.get(i).getType()!=null&&arg0.get(i).getType().name()!=null){
        			
        			if(arg0.get(i).getType().name().equals("TXT")){
        				jExtras.put("body", ((EMTextMessageBody)arg0.get(i).getBody()).getMessage());
        			}else if(arg0.get(i).getType().name().equals("IMAGE")){
        				String filePath = ((EMImageMessageBody)arg0.get(i).getBody()).getLocalUrl();
        	            if (filePath != null) {
        	                File file = new File(filePath);
        	                if (!file.exists()) {
        	                    // send thumb nail if original image does not exist
        	                    filePath = ((EMImageMessageBody)arg0.get(i).getBody()).thumbnailLocalPath();
        	                }
        	                jExtras.put("body", filePath);
        	            }
        			}else if(arg0.get(i).getType().name().equals("VOICE")){
        				jExtras.put("body", ((EMVoiceMessageBody)arg0.get(i).getBody()).getLocalUrl());
        			}else if(arg0.get(i).getType().name().equals("LOCATION")){
        				jExtras.put("body", ((EMLocationMessageBody)arg0.get(i).getBody()).getAddress());
        			}
        			
        		}
				jExtras.put("from", arg0.get(i).getFrom());
				jExtras.put("to", arg0.get(i).getTo());
				jExtras.put("type", arg0.get(i).getType());
				jExtras.put("userName", arg0.get(i).getUserName());
				jExtras.put("chatType", arg0.get(i).getChatType());
				jExtras.put("msgId", arg0.get(i).getMsgId());
				jExtras.put("msgTime", arg0.get(i).getMsgTime());
				jSONArray.put(i, jExtras);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    	
    	
    }
    
    
}
