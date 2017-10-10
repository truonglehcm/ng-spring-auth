(function() {
    'use strict';

    /*** get app to regist controller ***/
    angular
        .module('app')
        .controller('ErrorCtrl', ErrorCtrl);

    /*** @inject services ***/
    ErrorCtrl.$inject = ['$scope', '$stateParams'];
    
    /**
     * define controller
     * @param $scope
     * @param $routeParams
     * @returns
     */
    function ErrorCtrl($scope, $stateParams) {
        $scope.code = $stateParams.code;

        switch ($scope.code) {
            case "403" :
                $scope.message = "Oops! you have come to unauthorised page."
                break;
            case "404" :
                $scope.message = "Page not found."
                break;
            default:
                $scope.code = 500;
                $scope.message = "Oops! unexpected error"
        }

    }
})();
