(function() {
	'use strict';
	
	/*** get app to regist control ***/
	angular
		.module('app')
		.controller('SignUpCtrl', SignUpCtrl);

	/*** @inject service ***/
	SignUpCtrl.$inject = [ '$rootScope', '$scope', '$state', '$auth', 'toastr', 'CONFIG', 'SignUpService'];
	
	/**
	 * define controller detail
	 * @param $rootScope
	 * @param $scope
	 * @param $state
	 * @param $auth
	 * @param toastr
	 * @returns
	 */
	function SignUpCtrl($rootScope, $scope, $state, $auth, toastr, CONFIG, SignUpService) {
		
		$scope.captchaConfig = {};
		
		getRecaptchaSiteKey();
		
		$scope.signup = function(isValid) {
	    	if (isValid) {
	    		var user = {
	    			  firstName			: $scope.firstname,
	    			  lastName			: $scope.lastname,
	    			  email				: $scope.email,
	    			  password			: $scope.password,
	    			  matchingpassword	: $scope.matchingpassword,
	    			  recaptcha			: $scope.recaptcha
	    		};
	    
	    		$auth.signup(user)
	    		  .then(function(response) {
	    			  toastr.info(
    	                    'Please verify email to active your account',
    	                    {closeButton: true}
    	              );
	    			  
	    			  $state.go('signin');
	    		  })
	    		  .catch(function(response) {
	    			  var message = angular.fromJson(response.data.message);
	    			  toastr.error(message, {closeButton: true});
	    		  });
	    	}
	    }
		
		function getRecaptchaSiteKey() {
			SignUpService.getRecaptchaSiteKey()
	    		  .then(function(response) {
	    			  $scope.captchaConfig.key = response.siteKey;
	    		  })
	    		  .catch(function(response) {
	    			  console.log('can not find site key google captcha');
	    		  });
	    }
	}
}());