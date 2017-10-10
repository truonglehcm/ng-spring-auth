(function() {
	'use strict';
	
	/*** get app to regist controller ***/
	angular
		.module('app')
		.controller('PostDetailCtrl', PostDetailCtrl);
	
	/*** @inject services ***/
	PostDetailCtrl.inject = ['$scope', '$state', '$stateParams', 'PostDetailService'];
	
	/**
	 * define controller
	 * @param $scope
	 * @param $state
	 * @param $stateParams
	 * @param PostDetailService
	 * @returns
	 */
	function PostDetailCtrl($scope, $state, $stateParams, PostDetailService) {
		$scope.post = {};
		
		getPost();
		
		/**
		 * function get post
		 */
		function getPost() {
			var postId = $stateParams.postId;
			
			if (postId != null) {
				PostDetailService.getPost(postId)
				.then(function(dataResponse) {
					$scope.post = dataResponse;
				}, function(errResponse){
	                console.error('Error while get post');
	            });
			}
		}
		
	}
}());