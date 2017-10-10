(function() {
    'use strict';

    /*** get app to regist services ***/
    angular
        .module('app')
        .factory('DashboardService', DashboardService);

    /*** get app to regist services ***/
    DashboardService.$inject = ['$http', '$q', '$log', '$auth', 'CONFIG'];
    
    /**
     * 
     * @param $http
     * @param $q
     * @param $log
     * @param $auth
     * @param CONFIG
     * @returns
     */
    function DashboardService($http, $q, $log, $auth, CONFIG) {
        var services= {
    		// todo
        }
        
        return services;
    }
})();
