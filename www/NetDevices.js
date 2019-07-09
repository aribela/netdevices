var exec = cordova.require('cordova/exec');

var NetDevices = function() {
    console.log('NetDevices instanced');
};

NetDevices.prototype.obtenerips = function(msg, onSuccess, onError) {
	try{
		var errorCallback = function(obj) {
	        onError(obj);
	    };

	    var successCallback = function(obj) {
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