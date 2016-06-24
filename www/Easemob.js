var exec = require('cordova/exec');

module.exports = {
    init: function(arg0, success, error) {
        exec(success, error, "Easemob", "init", [arg0]);
    },
    login : function(userName, password, success, error) {
    		exec(success, error, "Easemob", "login", [userName, password]);
		},
		chat : function(extraUserId,extraChatType, success, error) {
    		exec(success, error, "Easemob", "chat", [extraUserId, extraChatType]);
		},
		contactList : function(arg0, success, error) {
    		exec(success, error, "Easemob", "contactList", [arg0]);
		},
		conversationList : function(arg0, success, error) {
    		exec(success, error, "Easemob", "conversationList", [arg0]);
		},
		logout : function(arg0, success, error) {
    		exec(success, error, "Easemob", "logout", [arg0]);
		},
		addMessageListener : function(arg0, success, error) {
   			exec(success, error, "Easemob", "addMessageListener", [arg0]);
		},
		receiveMessageInAndroidCallback : function(data) {
				try{
					console.log("cordova.plugins.Easemob.receiveMessageInAndroidCallback");
					data = JSON.stringify(data);
					var bToObj = JSON.parse(data);
					//this.receiveNotification = bToObj;
					//cordova.fireDocumentEvent('cordova.plugins.Easemob.receiveNotification', null);
				} catch(exception) {
					console.log(exception);
				}
		},
		onMessageReadAckReceivedInAndroidCallback : function(data) {
				try{
					console.log("cordova.plugins.Easemob.onMessageReadAckReceivedInAndroidCallback");
					data = JSON.stringify(data);
					var bToObj = JSON.parse(data);
					//this.receiveNotification = bToObj;
					//cordova.fireDocumentEvent('cordova.plugins.Easemob.receiveNotification', null);
				} catch(exception) {
					console.log(exception);
				}
		},
		onActivityResultInAndroidCallback : function(data) {
				try{
					console.log("cordova.plugins.Easemob.onMessageReadAckReceivedInAndroidCallback");
					data = JSON.stringify(data);
					var bToObj = JSON.parse(data);
					//this.receiveNotification = bToObj;
					//cordova.fireDocumentEvent('cordova.plugins.Easemob.receiveNotification', null);
				} catch(exception) {
					console.log(exception);
				}
		}
};
