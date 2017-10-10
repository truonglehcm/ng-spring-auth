(function() {
    'use strict';

    /*** get app to regist controller ***/
    angular
        .module('app')
        .controller('ManageProfileCtrl', ManageProfileCtrl);

    /*** @inject service ***/
    ManageProfileCtrl.$inject = ['$scope', '$auth', 'toastr', 'ManageProfileService'];
    
    /**
     * define controller
     * @param $scope
     * @param $auth
     * @param toastr
     * @param ManageProfileService
     * @returns
     */
    function ManageProfileCtrl($scope, $auth, toastr, ManageProfileService) {
    	// properties
    	$scope.profile	= {};
    	$scope.isAuthenticated 	= $auth.isAuthenticated();
		
    	// init
		if ($scope.isAuthenticated) {
			getProfile();
		}
		    	
    	/**
		 * function get profile
		 */
		function getProfile() {
			ManageProfileService.getProfile()
			.then(function(data) {
				$scope.profile = data;
			}, function(errResponse) {
	            console.error('Error while get profile');
	        });
	    }
    }
})();
