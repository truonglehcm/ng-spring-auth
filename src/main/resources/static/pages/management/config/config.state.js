(function() {
    'use strict';

    /*** get app to regist state ***/
    angular
        .module('app')
        .config(managementConfig);

    /*** @inject services ***/
    managementConfig.$inject = ['$stateProvider'];
    
    /**
     * define config state
     * @param $stateProvider
     * @returns
     */
    function managementConfig($stateProvider) {
        $stateProvider.state('management.config', {
            url: '/config',
            templateUrl: 'pages/management/config/config.tpl.html',
            controller: 'ManagementConfigCtrl',
            data : { requiredAdmin: true }
        });
    }
})();
