var localDatabase = {};
var dbName = "weiboDB";
localDatabase.indexedDB = window.indexedDB || window.mozIndexedDB || window.webkitIndexedDB || window.msIndexedDB;
localDatabase.IDBKeyRange = window.IDBKeyRange || window.webkitIDBKeyRange;
localDatabase.IDBTransaction = window.IDBTransaction || window.webkitIDBTransaction;

localDatabase.indexedDB.onerror = function (e) {
	console.log("Database error: " + e.target.errorCode);
};
		
function openDatabase() {
	var openRequest = localDatabase.indexedDB.open(dbName);
	openRequest.onerror = function(e) {
		console.log("Database error: " + e.target.errorCode);
	};
	openRequest.onsuccess = function(event) {
		localDatabase.db = openRequest.result;
	};	
}

function createDatabase() {
	console.log('Deleting local database');
	var deleteDbRequest = localDatabase.indexedDB.deleteDatabase(dbName);
	deleteDbRequest.onsuccess = function (event) {
   		console.log('Database deleted');
   		var openRequest = localDatabase.indexedDB.open(dbName,1);
		
		openRequest.onerror = function(e) {
			console.log("Database error: " + e.target.errorCode);
		};
		openRequest.onsuccess = function(event) {
			console.log("Database created");
			localDatabase.db = openRequest.result;
		};	
		openRequest.onupgradeneeded = function (evt) {   
			console.log('Creating object stores');
	    	var messageStore = evt.currentTarget.result.createObjectStore
				("messages", {keyPath: "mid"});
	    	messageStore.createIndex("UalaisIndex", "Ualais", { unique: false });        
        };
        deleteDbRequest.onerror = function (e) {
        	console.log("Database error: " + e.target.errorCode);
        };
    
	};
}

function populateDatabase(obj) {
	console.log("populating database");
	var transaction = localDatabase.db.transaction("messages", "readwrite");
	var store = transaction.objectStore("messages");                    
  
	if (localDatabase != null && localDatabase.db != null) {
		for(var i=0;i<obj.length;i++){
				var request = store.put({
					"mid":obj[i].Mid,
					"cid":obj[i].Cid,
					"Ualais":obj[i].user.Ualais,
					"Uimage":obj[i].user.Uimage,
					"Mcontent":obj[i].Mcontent,
					"Mdatetime":obj[i].Mdatetime
				});
				request.onsuccess = function(e) {
					console.log("Added success");
				};
				
				request.onerror = function(e) {
					console.log(e.value);
				};
			
		}
	}
}







function fetchUalaisMessage(Ualais) {
	try {
		$("#friends").css("visibility","hidden");
		$("#searchfriend").val(Ualais);
		if (localDatabase != null && localDatabase.db != null) {
			var range = IDBKeyRange.only(Ualais);
			 
			var store = localDatabase.db.transaction("messages").objectStore("messages");
			var index = store.index("UalaisIndex");
			var html='';
			index.openCursor(range).onsuccess = function(evt) {  
			    var cursor = evt.target.result;    
			    if (cursor) {
			    	var messages = cursor.value;
			    	
			    	var jsonStr = JSON.stringify(messages);
			    	var jsonObj = eval('(' + jsonStr + ')');
			    	//console.log(jsonObj.mid);
			    	html+='<div Mid="'+jsonObj.Mid+'" Cid="'+jsonObj.Cid+'" class="stateShow" onmouseout="stateMouseOut(this)" onmouseover="stateMouseOver(this)" style="background-color: rgb(255, 255, 255);">';
					html+='<div class="stateShowWord"><table class="stateTable" width="450" cellspacing="0" cellpadding="0" border="0">';
					html+='<tbody><tr><td width="70" valign="top" align="center"><a href="#"><img width="48" height="48" alt="" src="'+jsonObj.Uimage+'">';
					html+='</a></td><td width="380"><a href="#">'+jsonObj.Ualais+'</a><img align="absmiddle" style="border: none;" src="images/1.gif">';
					html+=jsonObj.Mcontent+'</td></tr></tbody></table></div><div class="stateShowtime">'+jsonObj.Mdatetime+'</div><div class="stateOp">';
					html+='<a class="opState" onclick="reform(this)">回复</a><a class="opState">转发</a>';
					html+='</div><div class="huifu"></div></div></div>';  
			        cursor.continue();  
			    }  
			    $("#mainBannerContent").html(html);
			};
		}
	}
	catch(e){
		console.log(e);
	}
}