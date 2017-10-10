(function() {
    'use strict';

    /*** get app to regist service ***/
    angular
        .module('app')
        .factory('ManagePostService', ManagePostService);

    /*** @inject services ***/
    ManagePostService.$inject = ['$http', '$q', '$log', '$auth', 'CONFIG'];
    
    /**
     * define services
     * @param $http
     * @param $q
     * @param $log
     * @param $auth
     * @param CONFIG
     * @returns
     */
    function ManagePostService($http, $q, $log, $auth, CONFIG) {
    	
    	var service = {
    		getPost		: getPost,
        	getPosts 	: getPosts,
        	savePost	: savePost,
        	deletePost	: deletePost
        };

        return service;

        /**
         * get one post
         */
        function getPost(id) {
        	var deferred	= $q.defer();
        	
        	var reqConfig = {
	    			method	: 'GET',
		            url		: CONFIG.MANAGE_POSTS + CONFIG.SLASH + id,
		            headers	: { 'Authorization': $auth.getToken() }
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
        
        /**
         * get list post
         */
        function getPosts() {
        	var deferred	= $q.defer();
        	
        	var reqConfig = {
	    			method: 'GET',
		            url: CONFIG.MANAGE_POSTS,
		            headers: {
		                'Content-Type' :'application/json',
		                'Authorization': $auth.getToken()
		            }
        	}
        	
        	$http(reqConfig)
	    		.then(getPostsComplete)
		    	.catch(getPostsFailed);
	    	
	    	function getPostsComplete(response) {
	    		deferred.resolve(response.data);
	        }
	
	        function getPostsFailed(error) {
	        	$log.error('XHR Failed for get getPosts' + error.message);
	            deferred.reject(error);
	        }
	        
	        return deferred.promise;
        }
        
        /**
         * save post
         */
        function savePost(post, id) {
        	var deferred = $q.defer();
        	
        	var reqConfig = {
        			data	: post,
		            headers	: { 
		            	'Content-Type' :'application/json', 
		            	'Authorization': $auth.getToken()
		            }
        	}
        	
        	if (id != null && id != "") {
        		reqConfig.method 	= 'PUT';
        		reqConfig.url 		= CONFIG.MANAGE_POSTS + CONFIG.SLASH + id;
        	} else {
        		reqConfig.method 	= 'POST';
        		reqConfig.url 		= CONFIG.MANAGE_POSTS;
        	}
        	
	    	$http(reqConfig)
	    		.then(savePostComplete)
		    	.catch(savePostFailed);
	    	
	    	function savePostComplete(response) {
	    		deferred.resolve(response.data);
	        }
	
	        function savePostFailed(error) {
	        	$log.error('XHR Failed for savePost' + error.message);
	            deferred.reject(error);
	        }
	        
	        return deferred.promise;
        }
        
        /**
         * delete post
         */
        function deletePost(id) {
            var deferred = $q.defer();
            
            var reqConfig = {
	    			method: 'DELETE',
		            url: CONFIG.MANAGE_POSTS + CONFIG.SLASH + id,
		            headers: { 
		            	'Content-Type' :'application/json', 
		            	'Authorization': $auth.getToken()
		            }
        	}
            
            $http(reqConfig)
                .then(deletePostComplete)
		    	.catch(deletePostFailed);
            
            function deletePostComplete(response) {
	    		deferred.resolve(response.data);
	        }
	
	        function deletePostFailed(error) {
	        	$log.error('XHR Failed for deletePost' + error.message);
	            deferred.reject(error);
	        }
            
            return deferred.promise;
        }
        
        
    }
})();
