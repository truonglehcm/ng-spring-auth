(function() {
	'use strict';

	/*** get app to regist control ***/
	angular
		.module('app')
		.config(signinState);
	
	/**
	 * define state
	 * @param $stateProvider
	 * @param $urlRouterProvider
	 * @returns
	 */
	function signinState($stateProvider, $urlRouterProvider) {
		var signin = {
			url : '/auth/login',
			views: {
		        content: {
		        	templateUrl: 'pages/auth/signin/signin.tpl.html',
		        	controller : 'SignInCtrl'
		        },
	            resolve: {
	                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
	                    $translatePartialLoader.addPart('signin');
	                    return $translate.refresh();
	                }]
	            }
		    }
		}

		$stateProvider.state('signin', signin);
	}
	
})();
