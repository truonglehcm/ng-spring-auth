(function() {
	'use strict';
	
	/*** get app to regist controller ***/
	angular
		.module('app')
		.controller('ManagementCtrl', ManagementCtrl);
	
	/*** @inject service ***/
	ManagementCtrl.inject = [ '$scope', '$state', '$auth', 'ManagementService'];
	
	/**
	 * define controlle
	 * @param $scope
	 * @param $state
	 * @param $auth
	 * @param ManagementService
	 * @returns
	 */
	function ManagementCtrl($scope, $state, $auth, ManagementService) {
		
		// properties
		$scope.isAuthenticated  = $auth.isAuthenticated();
		$scope.username			= "";
		
		// functions
		$scope.logout = logout;
			
		
		
		/**
		 * init to get username from payload
		 */
	    function init() {
	    	if (!$scope.isAuthenticated) {
				$state.go('signin');
			} else {
	            var payLoad = $auth.getPayload();
	            if(payLoad != null && payLoad.sub != null) {
	            	$scope.username = payLoad.sub;
	            }
			}
	    }
	    
	    /**
	     * logout
	     */
	    function logout() {
	        $auth.logout();
	        $state.go("signin");
	    };
		
	}
	
}());