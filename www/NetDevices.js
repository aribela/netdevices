var exec = cordova.require('cordova/exec');

var NetDevices = function() {
    alert('NetDevices instanced');
};

NetDevices.prototype.obtenerips = function(msg, onSuccess, onError) {
	// try{
		// alert("entre a net");
		var errorCallback = function(obj) {
			// alert("error en net");
	        onError(obj);
	    };

	    var successCallback = function(obj) {
	    	// alert("success net");
	        onSuccess(obj);
	    };
	    try{
	    	// alert("exec");
	    	cordova.exec(successCallback, errorCallback, 'NetDevices', 'obtenerips', [msg]);
	    }
	    catch(error){
	  //   	alert("error exec");
			// alert(error);
		}
	// }
	// catch(error){
	// 	alert(error);
	// }
    
};

NetDevices.prototype.urlValidator = function(msg, onSuccess, onError) {
	
		var errorCallback = function(obj) {
			onError(obj);
	    };

	    var successCallback = function(obj) {
	    	onSuccess(obj);
	    };
	    try{
	    	cordova.exec(successCallback, errorCallback, 'NetDevices', 'urlValidator', [msg]);
	    }
	    catch(error){
	  
		}
	
    
};

if (typeof module != 'undefined' && module.exports) {
    module.exports = NetDevices;
}