(function() {
    'use strict';

    /*** get app to regist service ***/
    angular
        .module('app')
        .factory('ManagementConfigService', ManagementConfigService);

    /*** @inject services ***/
    ManagementConfigService.$inject = ['$http', '$q', '$log', '$auth', 'CONFIG'];
    
    /**
     * define service
     * @param $http
     * @param $q
     * @param $log
     * @param $auth
     * @param CONFIG
     * @returns
     */
    function ManagementConfigService($http, $q, $log, $auth, CONFIG) {
        var service = {
        	// todo
        };

        return service;
    }
})();
