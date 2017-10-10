(function() {
    'use strict';

    /*** get app to regist controller ***/
    angular
        .module('app')
        .controller('ManageTagCtrl', ManageTagCtrl);

    /*** @inject service ***/
    ManageTagCtrl.$inject = ['$scope', '$auth', 'uiGridConstants', 'toastr', 'EditTagService', 'ManageTagService'];
    
    /**
     * define controller
     * @param $scope
     * @param $auth
     * @param uiGridConstants
     * @param toastr
     * @param EditTagService
     * @param ManageTagService
     * @returns
     */
    function ManageTagCtrl($scope, $auth, uiGridConstants, toastr, EditTagService, ManageTagService) {
    	
    	// properties
    	$scope.addTag 		= addTag;
    	$scope.editTag 		= EditTagService.editTag;
    	$scope.deleteTag 	= deleteTag;
    	$scope.isAuthenticated = $auth.isAuthenticated();
		$scope.gridTagsOptions = {
			enableCellEditOnFocus: true,	
		    enableColumnMenus: false,
		    onRegisterApi: function(gridApi) {
		    	$scope.gridApi = gridApi;
		    	gridApi.edit.on.afterCellEdit($scope, editTagEvent);
		    },
		    columnDefs: [
		    	{ name: 'name', displayName: 'Tag Name' },
		    	{ name: 'visible', displayName: 'Visible', type: 'boolean', width: '8%' },
		    	{ 
		    		name: 'id', displayName: '', width: '8%', cellClass: 'text-center', enableCellEdit: false, enableFiltering: false,
		    		cellTemplate:'<i class="btn-action fa fa-trash-o ui-grid-cell-contents" ng-click="grid.appScope.deleteTag(row.entity)" ></i>'
		    	}
		    ]
		};
		
		$scope.toggleFilter = function() {
		    $scope.gridTagsOptions.enableFiltering = !$scope.gridTagsOptions.enableFiltering;
		    $scope.gridApi.core.notifyDataChange( uiGridConstants.dataChange.COLUMN );
		};
		
		
		if ($scope.isAuthenticated) {
			getTags();
		}
		
		/**
		 * function edit Tag
		 */
		function editTagEvent(rowEntity, colDef, newValue, oldValue) {
    		if(newValue !== oldValue) {
    			ManageTagService.saveTag(rowEntity, false)
    			.then(function(data) {
    				getTags();
    				toastr.info(
	                    'Save success!',
	                    {closeButton: true}
	                );
    			}, function(errResponse) {
    				getTags();
    				toastr.error(
	                    'Error while save Tag!',
	                    {closeButton: true}
    	            );
    	        });
    			
    			$scope.$apply();
    		}
    	}
    	
    	/**
		 * function get Tags
		 */
		function getTags() {
			ManageTagService.getTags()
			.then(function(data) {
				$scope.gridTagsOptions.data = data;
			}, function(errResponse) {
	            console.error('Error while getting Tags');
	        });
	    }
		
		/**
		 * add Tag
		 */
		function addTag() {
			$scope.editTag($scope.gridTagsOptions, null);
		};
		
		/**
		 * delete tag
		 */
		function deleteTag(tag) {
			ManageTagService.deleteTag(tag.id)
			.then(function(data) {
				var i = $scope.gridTagsOptions.data.indexOf(tag);
				$scope.gridTagsOptions.data.splice(i, 1);
				toastr.info(
                    'Delete success!',
                    {closeButton: true}
                );
			}, function(errResponse) {
	            console.error('Error while detele tag');
	        });
		}
    }
})();
