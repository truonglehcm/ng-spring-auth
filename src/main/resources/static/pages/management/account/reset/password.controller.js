(function() {
	'use strict';
	
	/*** get app to regist controller ***/
	angular
		.module('app')
		.controller('ResetPasswordCtrl', ResetPasswordCtrl)
		.controller('ResetPasswordConfirmCtrl', ResetPasswordConfirmCtrl);
	
	/*** @inject service ***/
	ResetPasswordCtrl.$inject = ['$scope', '$state', 'toastr', 'ResetPasswordService'];
	
	/**
	 * define controller
	 * @param $scope
	 * @param $state
	 * @param toastr
	 * @param ResetPasswordService
	 * @returns
	 */
	function ResetPasswordCtrl($scope, $state, toastr, ResetPasswordService) {
		$scope.sendEmailReset = function(isValid) {
			if (isValid) {
				ResetPasswordService.sendEmailReset($scope.email)
				.then(function(data) {
					$state.go('home');
	                toastr.info(
	                    'Please verify email to reset your account!',
	                    {closeButton: true}
	                );
				}, function(errResponse) {
		            toastr.error(
	                    'Error while sending email reset password!',
	                    {closeButton: true}
	                );
		        });
			}
		}
	}
	
	/*** @inject services ***/
	ResetPasswordConfirmCtrl.$inject = ['$scope', '$state', '$stateParams', 'toastr', 'ResetPasswordService'];
	
	/**
	 * define controller
	 * @param $scope
	 * @param $state
	 * @param $stateParams
	 * @param toastr
	 * @param ResetPasswordService
	 * @returns
	 */
	function ResetPasswordConfirmCtrl($scope, $state, $stateParams, toastr, ResetPasswordService) {
		$scope.sendPasswordReset = function(isValid) {
			if (isValid) {
				ResetPasswordService.sendPasswordReset($scope.newPassword, $stateParams.token)
				.then(function(data) {
					$state.go('signin');
	                toastr.info(
	                    'Reset password success!',
	                    {closeButton: true}
	                );
				}, function(errResponse) {
					toastr.error(
	                    'Reset password error!',
	                    {closeButton: true}
	                );
		        });
			}
		}
	}
}());