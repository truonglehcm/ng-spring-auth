(function() {
    'use strict';

    /*** get app to regist controller ***/
    angular
        .module('app')
        .controller('EditUserCtrl', EditUserCtrl);

    /*** @inject services ***/
    EditUserCtrl.$inject = ['$scope', '$http', '$state', '$stateParams', 'toastr', '$uibModalInstance', 'ManageUserService', 'grid', 'user'];
    
    /**
     * define controller
     * @param $scope
     * @param $http
     * @param $state
     * @param $stateParams
     * @param toastr
     * @param $uibModalInstance
     * @param ManageUserService
     * @param grid
     * @param user
     * @returns
     */
	function EditUserCtrl($scope, $http, $state, $stateParams, toastr, $uibModalInstance, ManageUserService, grid, user) {
		
		// properties
		$scope.isNew	= (user == null);
		$scope.entity 	= ($scope.isNew ? {} : angular.copy(user.entity));
		$scope.title	= ($scope.isNew  ? "Add User" : "Edit User");
		
		// functions
		$scope.saveUser 	= saveUser;
		$scope.deleteUser	= deleteUser;
		
		/**
		 * save user
		 */
		function saveUser() {
			ManageUserService.saveUser($scope.entity, $scope.isNew)
			.then(function(data) {
				toastr.info(
                    'Create user success!',
                    {closeButton: true}
                );
				
				if($scope.isNew) {
					grid.data.push(data);
				} else {
					user.entity.userName 	= $scope.entity.userName;
					user.entity.firstName 	= $scope.entity.firstName;
					user.entity.lastName 	= $scope.entity.lastName;
					user.entity.email 		= $scope.entity.email;
					user.entity.enabled 	= $scope.entity.enabled;
					user.entity.expired 	= $scope.entity.expired;
					user.entity.locked 		= $scope.entity.locked;
				}
				
				$state.go('management.users');
				$uibModalInstance.close();
			}, function(errResponse) {
				toastr.error(
                    'Error while save users!',
                    {closeButton: true}
	            );
			})
		}
		
		/**
		 * delete user
		 */
		function deleteUser(user) {
			ManageUserService.deleteUser(user.id)
			.then(function(data) {
				toastr.info(
                    'Delete user success!',
                    {closeButton: true}
                );
				
				var i = grid.data.indexOf(user);
				grid.data.splice(i, 1);
				$state.go('management.users');
				$uibModalInstance.close();
			}, function(errResponse) {
				toastr.error(
                    'Error while save users!',
                    {closeButton: true}
	            );
			})
		}
	}
	
})();