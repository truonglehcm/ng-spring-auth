(function() {
    'use strict';

    /*** get app to regist services ***/
    angular
        .module('app')
        .factory('ManageUserService', ManageUserService);

    /*** @inject services ***/
    ManageUserService.$inject = ['$http', '$q', '$log', '$auth', 'CONFIG'];
    
    /**
     * define services
     * @param $http
     * @param $q
     * @param $log
     * @param $auth
     * @param CONFIG
     * @returns
     */
    function ManageUserService($http, $q, $log, $auth, CONFIG) {
    	var service = {
        	getUsers 	: getUsers,
        	saveUser	: saveUser,
        	deleteUser	: deleteUser
        };

        return service;

        
        /**
         * get users
         */
        function getUsers() {
        	var deferred = $q.defer();
        	var reqConfig = {
	    			method	: 'GET',
		            url		: CONFIG.MANAGE_USERS,
		            headers	: {
		                'Content-Type':'application/json',
		                'Authorization': $auth.getToken()
		            }
        	}
        	
	    	$http(reqConfig)
	    		.then(getUsersComplete)
		    	.catch(getUsersFailed);
	    	
	    	function getUsersComplete(response) {
	    		deferred.resolve(response.data);
	        }
	
	        function getUsersFailed(error) {
	        	$log.error('XHR Failed for get getUsers' + error.message);
	            deferred.reject(error);
	        }
	        
	        return deferred.promise;
        }
        
        /**
         * save or update user
         */
        function saveUser(user, addNew) {
        	var deferred = $q.defer();
        	var reqConfig = {
	    			method	: (addNew ? 'POST' : 'PUT'),
		            url		: CONFIG.MANAGE_USERS,
		            headers	: {
		                'Content-Type':'application/json',
		                'Authorization': $auth.getToken()
		            },
		            data: user
        	}
        	
	    	$http(reqConfig)
	    		.then(saveUserComplete)
		    	.catch(saveUserFailed);
	    	
	    	function saveUserComplete(response) {
	    		deferred.resolve(response.data);
	        }
	
	        function saveUserFailed(error) {
	        	$log.error('XHR Failed for get saveUser' + error.message);
	            deferred.reject(error);
	        }
	        
	        return deferred.promise;
        }
        
        /**
         * delete user
         */
        function deleteUser(id) {
        	var deferred = $q.defer();
        	var reqConfig = {
	    			method	: 'DELETE',
		            url		: CONFIG.MANAGE_USERS + "/" + id,
		            headers	: {
		                'Content-Type':'application/json',
		                'Authorization': $auth.getToken()
		            }
        	}
        	
	    	$http(reqConfig)
	    		.then(deleteUserComplete)
		    	.catch(deleteUserFailed);
	    	
	    	function deleteUserComplete(response) {
	    		deferred.resolve(response.data);
	        }
	
	        function deleteUserFailed(error) {
	        	$log.error('XHR Failed for delete user' + error.message);
	            deferred.reject(error);
	        }
	        
	        return deferred.promise;
        }
        
    }
})();
