(function() {
    'use strict';

    /*** get app to regist state ***/
    angular
        .module('app')
        .config(manageProfileConfig);

    /*** @inject service ***/
    manageProfileConfig.$inject = ['$stateProvider'];
    
    /**
     * define state
     * @param $stateProvider
     * @returns
     */
    function manageProfileConfig($stateProvider) {
    	$stateProvider.state('management.setting', {
        	url			: '/profile',
            templateUrl	: 'pages/management/account/profile/profile.tpl.html',
            controller	: 'ManageProfileCtrl',
			data 		: { requiredLogin : true }
        });
    }
    
})();
