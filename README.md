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

   cordova plugin add https://github.com/chenyuanchn/cordova-plugin-easeui.git
    
   安装完毕后修改包里所有的.R 改为自己项目路径
    
   如果还不能使用，请调整android:targetSdkVersion为20以下
   
### 支持平台

		Android only
		
### Android API

+ 检测更新软件 API
    		
1.js调用插件方法
```js

  	cordova.plugins.Easemob.init(null,function(e){console.log(e);},function(e){console.log(e);});

    cordova.plugins.Easemob.login('1','11',function(e){console.log(e);},function(e){console.log(e);});

  	cordova.plugins.Easemob.chat('200492083448906152','2',function(e){console.log(e);},function(e){console.log(e);});

  	cordova.plugins.Easemob.contactList(null,function(e){console.log(e);},function(e){console.log(e);});

  	cordova.plugins.Easemob.conversationList(null,function(e){console.log(e);},function(e){console.log(e);});

  
```