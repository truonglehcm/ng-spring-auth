(function() {
	'use strict';

	/*** get module to regist run config ***/
	angular
		.module('app')
		.run(run);

	/*** @inject services ***/
	run.$inject = [ '$rootScope', '$state', '$auth', 'cfpLoadingBar' ];

	/**
	 * define run config
	 * @param $rootScope
	 * @param $state
	 * @param $auth
	 * @param cfpLoadingBar
	 * @returns
	 */
	function run($rootScope, $state, $auth, cfpLoadingBar) {
		$rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams, options) {
			
			// required admin
			if (toState.data && toState.data.requiredAdmin) {
				if ($auth.isAuthenticated()) {
					var payLoad = $auth.getPayload();
		            if(payLoad == null || payLoad.roles == null || !payLoad.roles.ROLE_ADMIN) {
		            	event.preventDefault();
		            	$state.go("error", {code: "500"});
		            }
				} else {
					// goto page login
					event.preventDefault();
					$state.go('signin');
				}
				
			} 
			
			// required login
			if (toState.data && toState.data.requiredLogin) {
				if (!$auth.isAuthenticated()) {
					event.preventDefault();
					$state.go('signin');
				}
			}

			cfpLoadingBar.start();

		})

		// close loading bar
		$rootScope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams, options) {
			cfpLoadingBar.complete();
		})
	}
	
})();
