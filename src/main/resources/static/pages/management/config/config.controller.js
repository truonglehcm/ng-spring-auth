(function() {
    'use strict';

    /*** get app to regist controller ***/
    angular
        .module('app')
        .controller('ManagementConfigCtrl', ManagementConfigCtrl);

    /*** @inject services ***/
    ManagementConfigCtrl.$inject = ['$scope', 'ManagementConfigService'];
    
    /**
     * define controller
     * @param $scope
     * @param ManagementConfigService
     * @returns
     */
    function ManagementConfigCtrl($scope, ManagementConfigService) {
    	// todo
    }
})();
