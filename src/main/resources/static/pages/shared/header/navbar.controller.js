(function() {
	'use strict';
	
	/*** get app to regist controller ***/
	angular
		.module('app')
		.controller('NavbarCtrl', NavbarCtrl);
	
	/*** @inject services ***/
	NavbarCtrl.$inject = ['$scope', '$auth', '$state', '$http', '_'];
	
	/**
	 * define controller
	 * @param $scope
	 * @param $auth
	 * @param $state
	 * @param $http
	 * @param _
	 * @returns
	 */
	function NavbarCtrl($scope, $auth, $state, $http, _) {
		
		// properties
		$scope.isAuthenticated = $auth.isAuthenticated();
		$scope.roles = '';
		
		$scope.logout = function () {
	        $auth.logout();
	        $state.go("signin");
	    };
		
		if ($scope.isAuthenticated) {
            var payLoad = $auth.getPayload();
            if(payLoad != null && payLoad.sub != null) {
            	$scope.username = payLoad.sub;
            	$scope.roles = payLoad.roles;
            }
		}
	}
}());