(function() {
    'use strict';

    /*** get app to regist service ***/
    angular
        .module('app')
        .config(DashBoardConfig);

    /*** @inject services ***/
    DashBoardConfig.$inject = ['$stateProvider'];
    
    /**
     * define services
     * @param $stateProvider
     * @returns
     */
    function DashBoardConfig($stateProvider) {
        $stateProvider.state('management.dashboard', {
        	url: '/dashboard',
            templateUrl: 'pages/management/dashboard/dashboad.tpl.html',
            controller: 'DashboardCtrl',
			data : { requiredAdmin: true  }
        });
    }
})();
