(function() {
    'use strict';

    /*** get app to regist controller ***/
    angular
        .module('app')
        .controller('ManageUserCtrl', ManageUserCtrl);

    /*** @inject controller ***/
    ManageUserCtrl.$inject = ['$scope', '$auth', 'uiGridConstants', 'toastr', 'EditUserService', 'ManageUserService'];
    
    /**
     * define controller
     * @param $scope
     * @param $auth
     * @param uiGridConstants
     * @param toastr
     * @param EditUserService
     * @param ManageUserService
     * @returns
     */
    function ManageUserCtrl($scope, $auth, uiGridConstants, toastr, EditUserService, ManageUserService) {
    	
    	// properties
    	$scope.addUser 	= addUser;
    	$scope.editUser = EditUserService.editUser;
    	$scope.isAuthenticated = $auth.isAuthenticated();
		$scope.gridUsersOptions = {
			enableCellEditOnFocus: true,	
		    enableColumnMenus: false,
		    onRegisterApi: function(gridApi) {
		    	$scope.gridApi = gridApi;
		    	gridApi.edit.on.afterCellEdit($scope, editUserEvent);
		    },
		    columnDefs: [
		    	{ 
		    		name: 'userName', displayName: 'User Name', width: '15%',
		    		cellTemplate:'<a href class="ui-grid-cell-contents" ng-click="grid.appScope.editUser(grid.appScope.gridUsersOptions, row)" >{{COL_FIELD CUSTOM_FILTERS}}</a>'
		    	},
		    	{ name: 'firstName', displayName: 'First Name', width: '14%'  },
		    	{ name: 'lastName', displayName: 'Last Name', width: '14%'  },
		    	{ name: 'email', displayName: 'Email', width: '20%' },
		    	{ name: 'enabled', displayName: 'Enabled', type: 'boolean', width: '7%' },
		    	{ name: 'expired', displayName: 'Expired', type: 'boolean', width: '7%' },
		    	{ name: 'locked', displayName: 'Locked', type: 'boolean', width: '7%' },
		    	{ 
		    	  name: 'lastPasswordReset', displayName: 'Last Password Reset',  
		    	  type: 'date', enableCellEdit: false, cellFilter: 'date: "dd/MM/yyyy HH:mm:ss"'
		    	}
		    ]
		};
		
		$scope.toggleFilter = function() {
		    $scope.gridUsersOptions.enableFiltering = !$scope.gridUsersOptions.enableFiltering;
		    $scope.gridApi.core.notifyDataChange( uiGridConstants.dataChange.COLUMN );
		};
		
		
		if ($scope.isAuthenticated) {
			getUsers();
		}
		
		/**
		 * function edit user
		 */
		function editUserEvent(rowEntity, colDef, newValue, oldValue) {
    		if(newValue !== oldValue) {
    			ManageUserService.saveUser(rowEntity, false)
    			.then(function(data) {
    				getUsers();
    				toastr.info(
	                    'Save success!',
	                    {closeButton: true}
	                );
    			}, function(errResponse) {
    				getUsers();
    				toastr.error(
	                    'Error while save user!',
	                    {closeButton: true}
    	            );
    	        });
    			
    			$scope.$apply();
    		}
    	}
    	
    	/**
		 * function get users
		 */
		function getUsers() {
			ManageUserService.getUsers()
			.then(function(data) {
				$scope.gridUsersOptions.data = data;
			}, function(errResponse) {
	            console.error('Error while getting users');
	        });
	    }
		
		/**
		 * add user
		 */
		function addUser() {
			$scope.editUser($scope.gridUsersOptions, null);
		};
    }
})();
