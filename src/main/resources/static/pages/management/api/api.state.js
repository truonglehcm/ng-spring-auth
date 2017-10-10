(function() {
    'use strict';

    /*** get app to regist config ***/
    angular
        .module('app')
        .config(apiConfig);

    /*** @inject services ***/
    apiConfig.$inject = ['$stateProvider'];

    /**
     * define config
     * @param $stateProvider
     * @returns
     */
    function apiConfig($stateProvider) {
        $stateProvider.state('management.api', {
            url: '/api',
            templateUrl: 'pages/management/api/api.tpl.html',
            controller: 'ApiDocController',
            data : { requiredAdmin: true  }
        });
    }
    
})();
