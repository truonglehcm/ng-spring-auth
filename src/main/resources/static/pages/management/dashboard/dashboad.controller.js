(function() {
    'use strict';

    /*** get app to regist controller ***/
    angular
        .module('app')
        .controller('DashboardCtrl', DashboardCtrl);

    /*** @inject services ***/
    DashboardCtrl.$inject = ['$scope', 'DashboardService'];
    
    /**
     * define controller
     * @param $scope
     * @param DashboardService
     * @returns
     */
    function DashboardCtrl($scope, DashboardService) {
    	// todo
    }
})();
