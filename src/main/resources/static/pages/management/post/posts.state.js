(function() {
    'use strict';

    /*** get app to regist state ***/
    angular
        .module('app')
        .config(managePostConfig)
        .config(editPostConfig)
        .config(addPostConfig);

    /*** inject services ***/
    managePostConfig.$inject = ['$stateProvider'];
    
    /**
     * define config state
     * @param $stateProvider
     * @returns
     */
    function managePostConfig($stateProvider) {
    	$stateProvider.state('management.posts', {
        	url			: '/posts',
            templateUrl	: 'pages/management/post/posts.tpl.html',
            controller	: 'ManagePostsCtrl',
			data 		: { requiredLogin : true }
        });
    }
    
    /*** inject services ***/
    editPostConfig.$inject = ['$stateProvider'];
    
    /**
     * define config state
     * @param $stateProvider
     * @returns
     */
    function editPostConfig($stateProvider) {
    	$stateProvider.state('management.editPosts', {
        	url			: '/posts/{postId}',
            templateUrl	: 'pages/management/post/add/posts.add.tpl.html',
            controller	: 'AddPostCtrl',
			data 		: { requiredLogin : true }
        });
    }
    
    /*** inject services ***/
    addPostConfig.$inject = ['$stateProvider'];
    
    /**
     * define config state
     * @param $stateProvider
     * @returns
     */
    function addPostConfig($stateProvider) {
    	$stateProvider.state('management.addPosts', {
        	url			: '/posts',
            templateUrl	: 'pages/management/post/add/posts.add.tpl.html',
            controller	: 'AddPostCtrl',
			data 		: { requiredLogin : true }
        });
    }
    
})();
