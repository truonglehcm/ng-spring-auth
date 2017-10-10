(function() {
	'use strict';

	/*** get app to regist config state ***/
	angular
		.module('app')
		.config(postDetailState);

	/**
	 * 
	 * @param $stateProvider
	 * @returns
	 */
	function postDetailState($stateProvider) {
		
		$stateProvider
			.state('postDetail', {
				url : '/posts/{postId}',
				views: {
			        nav: {
			        	templateUrl: 'pages/shared/header/navbar.tpl.html',
			        	controller : 'NavbarCtrl'
			        },
			        content: {
			        	templateUrl: 'pages/post-detail/post-detail.tpl.html',
			        	controller : 'PostDetailCtrl'
			        },
		            resolve: {
		                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
		                    $translatePartialLoader.addPart('postDetail');
		                    return $translate.refresh();
		                }]
		            }
			    }
			});
	}
	
})();