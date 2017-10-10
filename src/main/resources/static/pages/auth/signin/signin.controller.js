(function() {
	'use strict';
	
	/*** get app to regist controller ***/
	angular
		.module('app')
		.controller('SignInCtrl', SignInCtrl);
	
	/*** @inject services ***/
	SignInCtrl.$inject = ['$rootScope', '$scope', '$state', '$auth', 'toastr'];
	
	/**
	 * define controller
	 * @param $rootScope
	 * @param $scope
	 * @param $state
	 * @param $auth
	 * @param toastr
	 * @returns
	 */
	function SignInCtrl($rootScope, $scope, $state, $auth, toastr) {
		$scope.login = function (username, password) {
	        $auth
	        	.login({username: username, password: password})
	            .then(function (response) {
	                $auth.setToken(response.data.token);
	                $state.go('home');
	                toastr.info(
	                    'Welcome back!',
	                    {closeButton: true}
	                );
	            })
	            .catch(function (response) {
	                toastr.error(
	                    'Email or password not correct!',
	                    {closeButton: true}
	                );
	            })
	    };
	}
}());