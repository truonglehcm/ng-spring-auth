(function() {
	'use strict';
	
	/*** get app to regist controller ***/
	angular
		.module('app')
		.controller('HomeCtrl', HomeCtrl);
	
	/*** @inject service ***/
	HomeCtrl.inject = ['$scope', '$state', 'HomeService'];
	
	/**
	 * define controller
	 * @param $scope
	 * @param $state
	 * @param HomeService
	 * @returns
	 */
	function HomeCtrl($scope, $state, HomeService) {
		// properties
		$scope.posts = [];
		
		
		// init
		getPosts();
		
		/**
		 * function get posts
		 */
		function getPosts() {
			HomeService.getPosts()
			.then(function(data) {
				$scope.posts = data;
			}, function(errResponse) {
	            console.error('Error while getting posts');
	        });
	    }
		
	}
}());