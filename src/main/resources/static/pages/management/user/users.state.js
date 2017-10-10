(function() {
    'use strict';

    /*** get app to regist config state ***/
    angular
        .module('app')
        .config(manageUserConfig);

    /*** @inject services ***/
    manageUserConfig.$inject = ['$stateProvider'];
    
    /**
     * define config state
     * @param $stateProvider
     * @returns
     */
    function manageUserConfig($stateProvider) {
    	$stateProvider.state('management.users', {
        	url: '/users',
            templateUrl: 'pages/management/user/users.tpl.html',
            controller: 'ManageUserCtrl',
			data : { requiredAdmin : true }
        });
    }
})();
