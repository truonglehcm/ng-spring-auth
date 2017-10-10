(function() {
	'use strict';

	/*** get module to regist run config ***/
	angular
		.module('app')
		.config(routeState)
		.config(localLogin)
		//.config(httpProvider)
		.config(cfpLoadingBar);
	
	/**
	 * goto home page
	 */
	function routeState($stateProvider, $urlRouterProvider) {
		$urlRouterProvider.otherwise("/");
		
		// $locationProvider.html5Mode(true).hashPrefix('!');
	}

	/**
	 * @override satellizer config
	 */
	localLogin.$inject = [ '$authProvider' ];
	function localLogin($authProvider) {
		$authProvider.httpInterceptor 	= false;
		$authProvider.baseUrl 			= '/';
	    $authProvider.loginUrl 			= '/auth/login';
	    $authProvider.signupUrl 		= '/auth/signup';
		$authProvider.tokenPrefix 		= 'demo';
		$authProvider.tokenName 		= 'token';
		$authProvider.tokenHeader 		= 'Authorization';
		$authProvider.tokenType 		= 'Bearer';
	}

	/**
	 * config default header
	 */
//	httpProvider.$inject = ['$httpProvider'];
//	function httpProvider($httpProvider) {
//		$httpProvider.defaults.useXDomain = true;
//		delete $httpProvider.defaults.headers.common['X-Requested-With'];
//	}
	
	/**
	 * config loading bar
	 */
	cfpLoadingBar.$inject = [ 'cfpLoadingBarProvider' ];
	function cfpLoadingBar(cfpLoadingBarProvider) {
	    cfpLoadingBarProvider.includeSpinner = false;
	}
	
})();
