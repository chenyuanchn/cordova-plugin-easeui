Cordova easeui和环信sdk3.0（无实时语音、实时视频功能的SDK包）的简单插件
======

## 最新更新

1. 增加退出
2. 修改监听返回对象(四种类型：文字、语音、图片、定位)
3. 使用了hyphenatechat_3.1.3.jar 替换了hyphenatechat_3.1.2.jar

## 支持的系统

* 仅Android（iOS正在开发，敬请期待）

## 自动安装（Cordova > v5.1.1）

	cordova plugin add https://github.com/chenyuanchn/cordova-plugin-easeui.git --variable APP_KEY=注册环信的appkey
	
	**注意**：安装完毕后修改包里所有的.R 改为自己项目路径
	请调整android:targetSdkVersion为21及以下
	需要去掉ui里的title，请在AndroidManifest.xml里的application中加入android:theme="@android:style/Theme.NoTitleBar" 

## 主要功能
		
   聊天界面，会话界面，联系人列表，监听接收信息
   		
## 使用方法
```
		//初始化easeui(ui初始化就不需要sdk初始化了)
  	cordova.plugins.Easemob.init(null,function(e){console.log(e);},function(e){console.log(e);});
		//登录 参数：用户名、密码
    cordova.plugins.Easemob.login('1','11',function(e){console.log(e);},function(e){console.log(e);});
		//聊天界面 参数：聊天对象id、类型(现阶段先不使用这个参数)
  	cordova.plugins.Easemob.chat('200492083448906152',null,function(e){console.log(e);},function(e){console.log(e);});
		//联系人列表(因为联系人是自己服务器维护，这里只增加测试数据测试使用)
  	cordova.plugins.Easemob.contactList(null,function(e){console.log(e);},function(e){console.log(e);});
		//会话列表
  	cordova.plugins.Easemob.conversationList(null,function(e){console.log(e);},function(e){console.log(e);});
		
		
		//以下是无ui 仅仅sdk的一些方法
	
		//注册接收消息监听
		cordova.plugins.Easemob.addMessageListener(null,function(e){console.log(e);},function(e){console.log(e);});
		cordova.plugins.Easemob.receiveMessageInAndroidCallback = function(data){
    	//对取回的消息信息进行处理   	对象四种返回类型：IMAGE TXT VOICE LOCATION
    }
    cordova.plugins.Easemob.onMessageReadAckReceivedInAndroidCallback = function(data){
      //对方已读回执消息    
    }
     cordova.plugins.Easemob.onActivityResultInAndroidCallback = function(data){
      //从聊天返回web界面触发   
    }
```