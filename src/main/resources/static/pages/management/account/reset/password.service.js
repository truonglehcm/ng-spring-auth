(function() {
    'use strict';

    /*** get app to regist service ***/
    angular
        .module('app')
        .factory('ResetPasswordService', ResetPasswordService);

    /*** @inject services ***/
    ResetPasswordService.$inject = ['$http', '$q', '$log', 'CONFIG'];
    
    /**
     * define service
     * @param $http
     * @param $q
     * @param $log
     * @param CONFIG
     * @returns
     */
    function ResetPasswordService($http, $q, $log, CONFIG) {

        var service = {
    		sendEmailReset 		: sendEmailReset,
    		sendPasswordReset 	: sendPasswordReset
        };

        return service;

        function sendEmailReset(email) {
        	var deferred = $q.defer();
        	var reqConfig = {
	    			method	: 'POST',
		            url		: CONFIG.RESET_PASSWORD + email,
		            headers	: { 'Content-Type':'application/json' }
        	}
        	
	    	$http(reqConfig)
	    		.then(sendEmailResetComplete)
		    	.catch(sendEmailResetFailed);
	    	
	    	function sendEmailResetComplete(response) {
	    		deferred.resolve(response.data);
	        }
	
	        function sendEmailResetFailed(error) {
	        	$log.error('XHR Failed for get sendEmailReset' + error.message);
	            deferred.reject(error);
	        }
	        
	        return deferred.promise;
        }
        
        
        function sendPasswordReset(newPassword, token) {
        	var deferred = $q.defer();
        	var reqConfig = {
	    			method	: 'POST',
		            url		: CONFIG.RESET_PASSWORD_CONFIRM + newPassword + "&token=" + token
        	}
        	
	    	$http(reqConfig)
	    		.then(sendPasswordResetComplete)
		    	.catch(sendPasswordResetFailed);
	    	
	    	function sendPasswordResetComplete(response) {
	    		deferred.resolve(response.data);
	        }
	
	        function sendPasswordResetFailed(error) {
	        	$log.error('XHR Failed for get sendPasswordReset' + error.message);
	            deferred.reject(error);
	        }
	        
	        return deferred.promise;
        }
    }
    
})();
