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
    		getProfile	: getProfile,
        	updateProfile : updateProfile,
        	changePassword: changePassword
        };

        return service;

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
        
        function updateProfile(profileForm) {
        	var deferred	= $q.defer();
        	
        	var reqConfig = {
	    			method	: 'PUT',
		            url		: CONFIG.USER_PROFILE,
		            headers	: { 
		            	'Content-Type' :'application/json', 
		            	'Authorization': $auth.getToken() 
		            },
        			data	: profileForm
        	}
        	
        	$http(reqConfig)
	    		.then(updateProfileComplete)
		    	.catch(updateProfileFailed);
	    	
	    	function updateProfileComplete(response) {
	    		deferred.resolve(response.data);
	        }
	
	        function updateProfileFailed(error) {
	        	$log.error('XHR Failed for update profile' + error.message);
	            deferred.reject(error);
	        }
	        
	        return deferred.promise;
        }
        
        function changePassword(passwordForm) {
        	var deferred	= $q.defer();
        	
        	var reqConfig = {
	    			method	: 'PUT',
		            url		: CONFIG.USER_CHANGE_PASSWORD,
		            headers	: { 
		            	'Content-Type' :'application/json', 
		            	'Authorization': $auth.getToken()
		            },
        			data	: passwordForm
        	}
        	
        	$http(reqConfig)
	    		.then(changePasswordComplete)
		    	.catch(changePasswordFailed);
	    	
	    	function changePasswordComplete(response) {
	    		deferred.resolve(response.data);
	        }
	
	        function changePasswordFailed(error) {
	        	$log.error('XHR Failed for change password' + error.message);
	            deferred.reject(error);
	        }
	        
	        return deferred.promise;
        }
    }
})();
