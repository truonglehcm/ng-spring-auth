(function() {
    'use strict';

    /*** get app to regist service ***/
    angular
        .module('app')
        .factory('ManageProfileService', ManageProfileService);

    /*** @inject service ***/
    ManageProfileService.$inject = ['$http', '$q', '$log', '$auth', 'CONFIG'];
    
    /**
     * define service
     * @param $http
     * @param $q
     * @param $log
     * @param $auth
     * @param CONFIG
     * @returns
     */
    function ManageProfileService($http, $q, $log, $auth, CONFIG) {
    	
    	var service = {
    		getProfile	: getProfile
        	// saveProfile : saveProfile, // todo
        	// savePassword: savePassword // todo
        };

        return service;

        /**
         * get profile
         */
        function getProfile(id) {
        	var deferred	= $q.defer();
        	
        	var reqConfig = {
	    			method	: 'GET',
		            url		: CONFIG.USER_PROFILE,
		            headers	: { 'Authorization': $auth.getToken() }
        	}
        	
        	$http(reqConfig)
	    		.then(getProfileComplete)
		    	.catch(getProfileFailed);
	    	
	    	function getProfileComplete(response) {
	    		deferred.resolve(response.data);
	        }
	
	        function getProfileFailed(error) {
	        	$log.error('XHR Failed for get profile' + error.message);
	            deferred.reject(error);
	        }
	        
	        return deferred.promise;
        }
    }
})();
