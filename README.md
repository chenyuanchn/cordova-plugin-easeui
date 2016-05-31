## cordova-plugin-easeui ##

### 本cordova插件是基于easeui和环信sdk3.0的简单demo

###感谢
		本代码是根据evicord的Easemob和EasemobPlugin插件进行的修改
		有需要的同学直接调用这两个插件，本插件仅测试
		主要功能是：聊天界面，会话界面，联系人列表
	
###准备工作

 - 这里我默认环境已经安装完毕，只需要进行插件安装即可
  
 - APP_KEY:注册环信的appkey
		

###Cordova/Phonegap 安装 （现仅支持Android。ios版本正在开发）

   在线：cordova plugin add https://github.com/chenyuanchn/cordova-plugin-easeui.git --variable APP_KEY=value
    
   因为插件包很大，推荐下载后再进行安装cordova plugin add 插件目录 --variable APP_KEY=value
    
   安装完毕后修改包里所有的.R 改为自己项目路径
    
   如果还不能使用，请调整android:targetSdkVersion为20以下
   
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

  
```