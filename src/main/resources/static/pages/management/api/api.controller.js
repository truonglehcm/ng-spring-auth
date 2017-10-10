(function() {
    'use strict';

    /*** get app to regist controller ***/
    angular
        .module('app')
        .controller('ApiDocController', ApiDocController);

    /*** @inject services ***/
    ApiDocController.$inject = ['$scope'];

    /**
     * define controller
     * @param $scope
     * @returns
     */
    function ApiDocController($scope) {
    	// properties
        $scope.isLoading = false;
        $scope.url = $scope.swaggerUrl = 'v2/api-docs';
        $scope.infos = false;
        
        // error management function
        $scope.myErrorHandler = function (data, status) {
            console.log('failed to load swagger: ' + status + '   ' + data);
        };
    }
})();
