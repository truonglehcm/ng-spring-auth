(function() {
	'use strict';

	/*** get app to regist state ***/
	angular
		.module('app')
		.config(resetPasswordState)
		.config(resetPasswordConfirmState);
	
	/**
	 * define config state
	 * @param $stateProvider
	 * @param $urlRouterProvider
	 * @returns
	 */
	function resetPasswordState($stateProvider, $urlRouterProvider) {
		var resetPassword = {
			url : '/reset/password',
			views: {
		        content: {
		          templateUrl: 'pages/management/account/reset/password.tpl.html',
		          controller : 'ResetPasswordCtrl'
		        },
	            resolve: {
	                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
	                    $translatePartialLoader.addPart('resetpassword');
	                    return $translate.refresh();
	                }]
	            }
		    }
		}

		$stateProvider.state('resetpassword', resetPassword);
	}
	
	/**
	 * define config state
	 * @param $stateProvider
	 * @param $urlRouterProvider
	 * @returns
	 */
	function resetPasswordConfirmState($stateProvider, $urlRouterProvider) {
		var resetPasswordConfirm = {
			url : '/reset/password/confirm/:token',
			views: {
		        content: {
		          templateUrl: 'pages/management/account/reset/password_confirm.tpl.html',
		          controller : 'ResetPasswordConfirmCtrl'
		        },
	            resolve: {
	                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
	                    $translatePartialLoader.addPart('resetpasswordconfirm');
	                    return $translate.refresh();
	                }]
	            }
		    }
		}

		$stateProvider.state('resetpasswordconfirm', resetPasswordConfirm);
	}
	
})();
