(function() {
    'use strict';

    /*** get app to regist controller ***/
    angular
        .module('app')
        .controller('EditTagCtrl', EditTagCtrl);

    /*** @inject services ***/
    EditTagCtrl.$inject = ['$scope', '$http', '$state', '$stateParams', 'toastr', '$uibModalInstance', 'ManageTagService', 'grid', 'tag'];
    
    /**
     * define controller
     * @param $scope
     * @param $http
     * @param $state
     * @param $stateParams
     * @param toastr
     * @param $uibModalInstance
     * @param ManageTagService
     * @param grid
     * @param tag
     * @returns
     */
	function EditTagCtrl($scope, $http, $state, $stateParams, toastr, $uibModalInstance, ManageTagService, grid, tag) {
		
		// properties
		$scope.isNew	= (tag == null);
		$scope.entity 	= ($scope.isNew ? {} : angular.copy(tag.entity));
		$scope.title	= ($scope.isNew  ? "Add Tag" : "Edit Tag");
		
		// functions
		$scope.saveTag = saveTag;
		
		function saveTag() {
			ManageTagService.saveTag($scope.entity, $scope.isNew)
			.then(function(data) {
				toastr.info(
                    'Create tag success!',
                    {closeButton: true}
                );
				grid.data.push(data);
				$uibModalInstance.close();
				$state.go('management.tags');
			}, function(errResponse) {
				toastr.error(
                    'Error while save tags!',
                    {closeButton: true}
	            );
			})
		}
	}
	
})();