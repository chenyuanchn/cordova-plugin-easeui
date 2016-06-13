## cordova-plugin-easeui ##

### 本cordova插件是基于easeui和环信sdk3.0（无实时语音、实时视频功能的SDK包）的简单demo

###主要功能
		
 - 主要功能是：聊天界面，会话界面，联系人列表
	
###准备工作

 - 这里我默认环境已经安装完毕，只需要进行插件安装即可
  
 - APP_KEY:注册环信的appkey
		

###Cordova/Phonegap 安装 （现仅支持Android。ios版本正在开发）

   在线：cordova plugin add https://github.com/chenyuanchn/cordova-plugin-easeui.git --variable APP_KEY=value
    
   因为插件包很大，推荐下载后再进行安装cordova plugin add 插件目录 --variable APP_KEY=value
    
   安装完毕后修改包里所有的.R 改为自己项目路径
    
   需要去掉ui里的title，请在AndroidManifest.xml里的application中加入android:theme="@android:style/Theme.NoTitleBar" 
    
   注意：请调整android:targetSdkVersion为19及以下
   
### 支持平台

		Android only
		
### Android API

+ 环信插件 API
    		
1.js调用插件方法
```js
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
    	//对取回的消息信息进行处理   	
    }
		
	
		  
```