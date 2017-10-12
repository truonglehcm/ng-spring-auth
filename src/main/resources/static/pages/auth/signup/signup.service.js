(function() {
	'use strict';

	/*** get app to sign up service ***/
	angular
    	.module('app')
    	.factory('SignUpService', SignUpService);

	/*** @inject service ***/
	SignUpService.$inject = ['$http', '$q', '$log', 'CONFIG'];
	
	/**
	 * define service
	 * @param $http
	 * @param $q
	 * @param $log
	 * @param CONFIG
	 * @returns
	 */
	function SignUpService($http, $q, $log, CONFIG) {
		
		var service = {
        	getRecaptchaSiteKey : getRecaptchaSiteKey
        };

        return service;

        function getRecaptchaSiteKey() {
        	var deferred	= $q.defer();
        	var reqUrl 		= CONFIG.SIGNUP;
        	
	    	$http.get(reqUrl)
	    		.then(getRecaptchaSiteKeyComplete)
		    	.catch(getRecaptchaSiteKeyFailed);
	    	
	    	function getRecaptchaSiteKeyComplete(response) {
	    		deferred.resolve(response.data);
	        }
	
	        function getRecaptchaSiteKeyFailed(error) {
	        	$log.error('XHR Failed for getRecaptchaSiteKey' + error.message);
	            deferred.reject(error);
	        }
	        
	        return deferred.promise;
        }
	}
})();