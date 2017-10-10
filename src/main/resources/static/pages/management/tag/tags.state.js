(function() {
    'use strict';

    /*** get app to regist config state ***/
    angular
        .module('app')
        .config(manageTagConfig);

    /*** @inject service ***/
    manageTagConfig.$inject = ['$stateProvider'];
    
    /**
     * define config
     * @param $stateProvider
     * @returns
     */
    function manageTagConfig($stateProvider) {
    	$stateProvider.state('management.tags', {
        	url			: '/tags',
            templateUrl	: 'pages/management/tag/tags.tpl.html',
            controller	: 'ManageTagCtrl',
			data 		: { requiredAdmin : true }
        });
    }
})();
