(function() {
	'use strict';

	/*** get app to regist config state ***/
	angular
		.module('app')
		.config(aboutState);

	/**
	 * 
	 * @param $stateProvider
	 * @returns
	 */
	function aboutState($stateProvider) {
		
		$stateProvider
			.state('about', {
				url : '/about',
				views: {
			        nav: {
			        	templateUrl: 'pages/shared/header/navbar.tpl.html',
			        	controller : 'NavbarCtrl'
			        },
			        content: {
			        	templateUrl: 'pages/about/about.tpl.html'
			        },
		            resolve: {
		                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
		                    $translatePartialLoader.addPart('about');
		                    return $translate.refresh();
		                }]
		            }
			    }
			});
	}
	
})();