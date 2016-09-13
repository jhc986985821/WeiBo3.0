var client;

function showchat() {
	client = new Messaging.Client('localhost', Number("61614"), "example-"
			+ (Math.floor(Math.random() * 100000)));
	client.onConnect = onConnect;
	client.onMessageArrived = onMessageArrived;
	client.onConnectionLost = onConnectionLost;

	client.connect({
		userName : "100",
		password : "100",
		onSuccess : onConnect,
		onFailure : onFailure
	});
}

var onConnect = function() {
	console.log("connected to MQTT");
	var uidarr=sessionStorage.getItem("uidarr");
	uidarr = uidarr.split(",");
	for(var i=0;i<uidarr.length;i++){
		client.subscribe(uidarr[i].toString());
	}
};

// this allows to display debug logs directly on the web page

/*
 * $('#disconnect').click(function() { client.disconnect();
 * $("#message").html("") return false; });
 */

function onFailure(failure) {
	console.log("failure");
	console.log(failure.errorMessage);
}

function onMessageArrived(message) {
	var divmessage = document.getElementById("message");
	
	//解决string字符转换为html代码的问题
	
	divmessage.innerHTML=message.payloadString;
}

function onConnectionLost(responseObject) {
	if (responseObject.errorCode !== 0) {
		console.log(client.clientId + ": " + responseObject.errorCode + "\n");
	}
}