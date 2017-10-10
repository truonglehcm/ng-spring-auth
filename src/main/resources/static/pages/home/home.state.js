(function() {
	'use strict';

	/*** get app to regist state ***/
	angular
		.module('app')
		.config(homeState);

	/**
	 * define config state
	 * @param $stateProvider
	 * @returns
	 */
	function homeState($stateProvider) {
		
		$stateProvider
			.state('home', {
				url : '/',
				views: {
			        nav: {
			        	templateUrl: 'pages/shared/header/navbar.tpl.html',
			        	controller : 'NavbarCtrl'
			        },
			        content: {
			        	templateUrl: 'pages/home/home.tpl.html',
			        	controller : 'HomeCtrl'
			        },
		            resolve: {
		                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
		                    $translatePartialLoader.addPart('home');
		                    return $translate.refresh();
		                }]
		            }
			    }
			});
	}
	
})();