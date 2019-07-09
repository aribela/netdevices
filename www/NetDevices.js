var exec = cordova.require('cordova/exec');

var NetDevices = function() {
    console.log('NetDevices instanced');
};

NetDevices.prototype.show = function(msg, onSuccess, onError) {
    var errorCallback = function(obj) {
        onError(obj);
    };

    var successCallback = function(obj) {
        onSuccess(obj);
    };

    exec(successCallback, errorCallback, 'NetDevices', 'show', [msg]);
};

if (typeof module != 'undefined' && module.exports) {
    module.exports = NetDevices;
}