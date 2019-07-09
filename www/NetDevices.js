var exec = cordova.require('cordova/exec');

var NetDevices = function() {
    alert('NetDevices instanced');
};

NetDevices.prototype.obtenerips = function(msg, onSuccess, onError) {
	try{
		alert("entre a net");
		var errorCallback = function(obj) {
			alert("error en net");
	        onError(obj);
	    };

	    var successCallback = function(obj) {
	    	alert("success net");
	        onSuccess(obj);
	    };

	    exec(successCallback, errorCallback, 'NetDevices', 'obtenerips', [msg]);
	}
	catch(error){
		alert(error);
	}
    
};

if (typeof module != 'undefined' && module.exports) {
    module.exports = NetDevices;
}