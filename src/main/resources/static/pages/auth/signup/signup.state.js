(function() {
	'use strict';

	/*** get app to regist state ***/
	angular
		.module('app')
		.config(signUpState)
		.config(signUpConfirmState);
	
	/**
	 * define state sign up
	 * @param $stateProvider
	 * @param $urlRouterProvider
	 * @returns
	 */
	function signUpState($stateProvider, $urlRouterProvider) {
		var signUp = {
			url : '/auth/signup',
			views: {
		        content: {
		        	templateUrl: 'pages/auth/signup/signup.tpl.html',
		        	controller : 'SignUpCtrl'
		        },
	            resolve: {
	                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
	                    $translatePartialLoader.addPart('signup');
	                    return $translate.refresh();
	                }]
	            }
		    }
		}

		$stateProvider.state('signup', signUp);
	}
	
	/**
	 * define sign up confirm state
	 * @param $stateProvider
	 * @param $urlRouterProvider
	 * @returns
	 */
	function signUpConfirmState($stateProvider, $urlRouterProvider) {
		var signUpConfirm = {
			url : '/auth/signup/confirm/:token',
			views: {
		        content: {
		           templateUrl: 'pages/auth/signup/confirm.tpl.html',
		           controller: function($scope, $state, $stateParams, $http, toastr, CONFIG){
		        	   var req = {
					            method	: 'POST',
					            url		: CONFIG.SIGNUP_CONFIRM + CONFIG.SLASH + $stateParams.token,
					            headers	: { 'Content-Type':'application/json' }
				        }
						
			            $http(req).then(function(response) {
			            	toastr.info(
	    	                    'Active succcessful',
	    	                    {closeButton: true}
	    	                );
			            	$state.go("signin");
		                })
		                .catch(function (response) {
		                	$state.go("error", {code: response.status});
		                	$scope.success = false;
		                })
	        	   }
		        },
	            resolve: {
	                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
	                    $translatePartialLoader.addPart('signupconfirm');
	                    return $translate.refresh();
	                }]
	            }
		    }	
		}

		$stateProvider.state('signupconfirm', signUpConfirm);
	}
	
})();
