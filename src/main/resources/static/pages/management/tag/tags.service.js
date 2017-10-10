(function() {
    'use strict';

    /*** get app to regist service ***/
    angular
        .module('app')
        .factory('ManageTagService', ManageTagService);

    /*** @inject services ***/
    ManageTagService.$inject = ['$http', '$q', '$log', '$auth', 'CONFIG'];
    
    /**
     * define services
     * @param $http
     * @param $q
     * @param $log
     * @param $auth
     * @param CONFIG
     * @returns
     */
    function ManageTagService($http, $q, $log, $auth, CONFIG) {
    	var service = {
        	getTags 	: getTags,
        	saveTag		: saveTag,
        	deleteTag 	: deleteTag
        };

        return service;

        
        /**
         * get Tags
         */
        function getTags() {
        	var deferred = $q.defer();
        	var reqConfig = {
	    			method	: 'GET',
		            url		: CONFIG.MANAGE_TAGS,
		            headers	: {
		                'Content-Type':'application/json',
		                'Authorization': $auth.getToken()
		            }
        	}
        	
	    	$http(reqConfig)
	    		.then(getTagsComplete)
		    	.catch(getTagsFailed);
	    	
	    	function getTagsComplete(response) {
	    		deferred.resolve(response.data);
	        }
	
	        function getTagsFailed(error) {
	        	$log.error('XHR Failed for get getTags' + error.message);
	            deferred.reject(error);
	        }
	        
	        return deferred.promise;
        }
        
        /**
         * save or update Tag
         */
        function saveTag(tag, addNew) {
        	var deferred = $q.defer();
        	var reqConfig = {
	    			method	: (addNew ? 'POST' : 'PUT'),
		            url		: CONFIG.MANAGE_TAGS,
		            headers	: {
		                'Content-Type':'application/json',
		                'Authorization': $auth.getToken()
		            },
		            data: tag
        	}
        	
	    	$http(reqConfig)
	    		.then(saveTagComplete)
		    	.catch(saveTagFailed);
	    	
	    	function saveTagComplete(response) {
	    		deferred.resolve(response.data);
	        }
	
	        function saveTagFailed(error) {
	        	$log.error('XHR Failed for get saveTag' + error.message);
	            deferred.reject(error);
	        }
	        
	        return deferred.promise;
        }
        
        /**
         * delete Tag
         */
        function deleteTag(id) {
        	var deferred = $q.defer();
        	var reqConfig = {
	    			method	: 'DELETE',
		            url		: CONFIG.MANAGE_TAGS + "/" + id,
		            headers	: {
		                'Content-Type':'application/json',
		                'Authorization': $auth.getToken()
		            }
        	}
        	
	    	$http(reqConfig)
	    		.then(deleteTagComplete)
		    	.catch(deleteTagFailed);
	    	
	    	function deleteTagComplete(response) {
	    		deferred.resolve(response.data);
	        }
	
	        function deleteTagFailed(error) {
	        	$log.error('XHR Failed for delete Tag' + error.message);
	            deferred.reject(error);
	        }
	        
	        return deferred.promise;
        }
    }
})();
