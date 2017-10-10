(function (angular, _) {
    var isolate_ = angular.copy(_);

    angular.module('underscorejs', [])
        .factory('_', function () {
            return isolate_;
        })
    ;
})(angular, _);