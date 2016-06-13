var exec = require('cordova/exec');

exports.init = function(arg0, success, error) {
    exec(success, error, "Easemob", "init", [arg0]);
};

exports.login = function(userName, password, success, error) {
    exec(success, error, "Easemob", "login", [userName, password]);
};

exports.chat = function(extraUserId,extraChatType, success, error) {
    exec(success, error, "Easemob", "chat", [extraUserId, extraChatType]);
};
exports.contactList = function(arg0, success, error) {
    exec(success, error, "Easemob", "contactList", [arg0]);
};

exports.conversationList = function(arg0, success, error) {
    exec(success, error, "Easemob", "conversationList", [arg0]);
};

exports.addMessageListener = function(arg0, success, error) {
    exec(success, error, "Easemob", "addMessageListener", [arg0]);
};

exports.receiveMessageInAndroidCallback = function(data) {
	try{
		console.log("cordova.plugins.Easemob.receiveMessageInAndroidCallback");
		data = JSON.stringify(data);
		var bToObj = JSON.parse(data);
		//this.receiveNotification = bToObj;
		//cordova.fireDocumentEvent('cordova.plugins.Easemob.receiveNotification', null);
	} catch(exception) {
		console.log(exception);
	}
};