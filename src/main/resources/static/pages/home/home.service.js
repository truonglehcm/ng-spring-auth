(function() {
	'use strict';

	/*** get app to regist service ***/
	angular
    	.module('app')
    	.factory('HomeService', HomeService);

	/*** @inject service ***/
	HomeService.$inject = ['$http', '$q', '$log', '$auth', 'CONFIG'];
	
	/**
	 * define service
	 * @param $http
	 * @param $q
	 * @param $log
	 * @param $auth
	 * @param CONFIG
	 * @returns
	 */
	function HomeService($http, $q, $log, $auth, CONFIG) {
		
		var service = {
        	getPosts 	: getPosts
        };

        return service;
		
		/**
         * get post
         */
        function getPosts() {
        	var deferred	= $q.defer();
        	var reqUrl 		= CONFIG.GET_POSTS;
        	
	    	$http.get(reqUrl)
	    		.then(getPostsComplete)
		    	.catch(getPostsFailed);
	    	
	    	function getPostsComplete(response) {
	    		deferred.resolve(response.data);
	        }
	
	        function getPostsFailed(error) {
	        	$log.error('XHR Failed for getPosts' + error.message);
	            deferred.reject(error);
	        }
	        
	        return deferred.promise;
        }
	}
})();