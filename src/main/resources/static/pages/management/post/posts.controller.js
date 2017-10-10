(function() {
    'use strict';

    /*** get app to regist controller ***/
    angular
        .module('app')
        .controller('ManagePostsCtrl', ManagePostsCtrl);

    /*** @inject services ***/
    ManagePostsCtrl.$inject = ['$scope', '$auth', 'uiGridConstants', 'toastr', 'ManagePostService'];
    
    /**
     * define controller
     * @param $scope
     * @param $auth
     * @param uiGridConstants
     * @param toastr
     * @param ManagePostService
     * @returns
     */
    function ManagePostsCtrl($scope, $auth, uiGridConstants, toastr, ManagePostService) {
    	// properties
    	$scope.post		= {};
    	$scope.posts	= [];
    	$scope.isAuthenticated 	= $auth.isAuthenticated();
		$scope.gridPostsOptions = {
			enableCellEditOnFocus	: true,	
		    enableColumnMenus		: false,
		    onRegisterApi			: function(gridApi) {
		    	$scope.gridApi = gridApi;
		    },
		    columnDefs: [
		    	{ 
		    		name: 'title', displayName: 'Title', width: '20%',
		    		cellTemplate:'<a href class="ui-grid-cell-contents" ui-sref="management.editPosts({postId: row.entity.id, postEdit: row.entity})" >{{COL_FIELD CUSTOM_FILTERS}}</a>'
		    	},
		    	{ name: 'description', displayName: 'Description' },
		    	{ 
		    		name: 'createAt', displayName: 'Create Date', width: '14%', 
		    		type: 'date', cellFilter: 'date:\'MM/dd/yyyy hh:mm a\'' 
		    	},
		    	{ 
		    		name: 'createBy', displayName: 'Create By', width: '14%'
		    	},
		    	{ name: 'logoUrl', displayName: 'Logo', width: '14%' },
		    	{ name: 'visible', displayName: 'Visible', type: 'boolean', width: '8%' }
		    ]
		};
		
		$scope.toggleFilter = function() {
		    $scope.gridPostsOptions.enableFiltering = !$scope.gridPostsOptions.enableFiltering;
		    $scope.gridApi.core.notifyDataChange(uiGridConstants.dataChange.COLUMN);
		};
		
		$scope.addPost 	= addPost;
		
		if ($scope.isAuthenticated) {
			getPosts();
		}
		
    	/**
		 * function get posts
		 */
		function getPosts() {
			ManagePostService.getPosts()
			.then(function(data) {
				$scope.gridPostsOptions.data = data;
			}, function(errResponse) {
	            console.error('Error while getting posts');
	        });
	    }
		
		/**
		 * add post
		 */
		function addPost() {
			var post = {
				entity : {
					"id" 					: 0,
					"username" 				: "",
					"firstname" 			: "",
					"lastname" 				: "",
					"email"					: "",
					"expired" 				: "",
					"locked" 				: "",
					"lastPasswordReset" 	: ""
				}
			};
			
			$scope.editPost($scope.gridPostsOptions, post, true);
		};
    }
})();
