(function() {
	'use strict';

	// get module to regist config state
	angular
		.module('app')
		.config(managementState);
	
	/**
	 * define state
	 * @param $stateProvider
	 * @param $urlRouterProvider
	 * @returns
	 */
	function managementState($stateProvider, $urlRouterProvider) {
		
		$urlRouterProvider.when('/management', '/management/dashboard');
		
		// abstract template
		var management = {
			abstract: true,
			url : '/management',
			views: {
		        content: {
		          templateUrl: 'pages/management/management.tpl.html',
		          controller : 'ManagementCtrl'
		        },
	            resolve: {
	                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
	                    $translatePartialLoader.addPart('management');
	                    return $translate.refresh();
	                }]
	            }
		    },
			data : { requiredLogin: true }
		}

		// registration state
		$stateProvider.state('management', management);
	}
	
})();
