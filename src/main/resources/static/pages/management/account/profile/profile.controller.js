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
    	$scope.passwordForm	= {};
    	$scope.isAuthenticated 	= $auth.isAuthenticated();
    	
    	// action
    	$scope.updateProfile = updateProfile;
    	$scope.changePassword = changePassword;
		
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
				$scope.passwordForm.username = $scope.profile.username;
			}, function(errResponse) {
	            console.error('Error while get profile');
	        });
	    }
		
		/**
		 * function update profile
		 */
		function updateProfile(isValid) {
			ManageProfileService.updateProfile($scope.profile)
			.then(function(data) {
				toastr.info(
                    'Update profile success!',
                    {closeButton: true}
                );
			}, function(errResponse) {
				toastr.error(
                    'Error while update profile',
                    {closeButton: true}
	            );
	        });
	    }
		
		/**
		 * function change password
		 */
		function changePassword(isValid) {
			ManageProfileService.changePassword($scope.passwordForm)
			.then(function(data) {
				toastr.info(
                    'Change password success!',
                    {closeButton: true}
                );
			}, function(errResponse) {
				toastr.error(
                    'Error while change password',
                    {closeButton: true}
	            );
	        });
	    }
    }
})();
