(function() {
    'use strict';

    /*** get app to regist serice ***/
    angular
        .module('app')
        .factory('PostDetailService', PostDetailService);

    /*** @inject services ***/
    PostDetailService.$inject = ['$http', '$q', '$log', 'CONFIG'];
    
    /**
     * define service
     * @param $http
     * @param $q
     * @param $log
     * @param CONFIG
     * @returns
     */
    function PostDetailService($http, $q, $log, CONFIG) {
    	
    	var service = {
    		getPost		: getPost
        };

        return service;

        /**
         * get one post
         */
        function getPost(id) {
        	var deferred	= $q.defer();
        	
        	var reqConfig = {
	    			method	: 'GET',
		            url		: CONFIG.GET_POSTS + CONFIG.SLASH + id
        	}
        	
        	$http(reqConfig)
	    		.then(getPostComplete)
		    	.catch(getPostFailed);
	    	
	    	function getPostComplete(response) {
	    		deferred.resolve(response.data);
	        }
	
	        function getPostFailed(error) {
	        	$log.error('XHR Failed for get one post' + error.message);
	            deferred.reject(error);
	        }
	        
	        return deferred.promise;
        }
    }
})();
