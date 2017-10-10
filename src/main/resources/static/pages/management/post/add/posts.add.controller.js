(function() {
    'use strict';

    /*** get app to regist controller ***/
    angular
        .module('app')
        .controller('AddPostCtrl', AddPostCtrl);

    /*** @inject service ***/
    AddPostCtrl.$inject = ['$scope', '$state', '$stateParams', '$sce', 'ManagePostService'];
    
    /**
     * define controller
     * @param $scope
     * @param $state
     * @param $stateParams
     * @param $sce
     * @param ManagePostService
     * @returns
     */
	function AddPostCtrl($scope, $state, $stateParams, $sce, ManagePostService) {
		// properties
		$scope.post = {};
		$scope.tinymceDescOptions = {
	        theme: "modern",
	        plugins: [
	            "advlist autolink lists link image charmap print preview hr anchor pagebreak",
	            "searchreplace wordcount visualblocks visualchars code fullscreen",
	            "insertdatetime media nonbreaking save table contextmenu directionality",
	            "emoticons template paste textcolor"
	        ],
	        toolbar1: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image",
	        toolbar2: "print preview media | forecolor backcolor emoticons",
	        image_advtab: true,
	        height: "200px"
	    };
		
		$scope.tinymceContentOptions = {
	        theme: "modern",
	        plugins: [
	            "advlist autolink lists link image charmap print preview hr anchor pagebreak",
	            "searchreplace wordcount visualblocks visualchars code fullscreen",
	            "insertdatetime media nonbreaking save table contextmenu directionality",
	            "emoticons template paste textcolor", "codesample"
	        ],
	        codesample_languages: [
	            {text: 'HTML/XML', value: 'markup'},
	            {text: 'JavaScript', value: 'javascript'},
	            {text: 'CSS', value: 'css'},
	            {text: 'PHP', value: 'php'},
	            {text: 'Ruby', value: 'ruby'},
	            {text: 'Python', value: 'python'},
	            {text: 'Java', value: 'java'},
	            {text: 'C', value: 'c'},
	            {text: 'C#', value: 'csharp'},
	            {text: 'C++', value: 'cpp'}
	        ],
	        toolbar1: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image",
	        toolbar2: "print preview media | forecolor backcolor emoticons | codesample",
	        image_advtab: true,
	        height: "800px"
	    };
		
		// functions
		$scope.save = save;
		$scope.cancelPost = cancelPost;
		$scope.deletePost = deletePost;

		// init
		getPost();
		
		/**
		 * function save post
		 * @param post
		 */
		function save(post) {
			var postId = $stateParams.postId;
			
			ManagePostService.savePost(post, postId)
			.then(function(dataResponse) {
				$state.go('management.posts');
			}, function(errResponse){
                console.error('Error while creating post');
            });
		}
		
		/**
		 * function get post
		 */
		function getPost() {
			var postId = $stateParams.postId;
			
			if (postId != null) {
				ManagePostService.getPost(postId)
				.then(function(dataResponse) {
					$scope.post = dataResponse;
				}, function(errResponse){
	                console.error('Error while get post');
	            });
			}
		}
		
		/**
		 * function cancel post
		 */
		function cancelPost() {
			$state.go('management.posts');
		}
		
		/**
		 * function delete post
		 */
		function deletePost(post) {
			ManagePostService.deletePost(post.id)
			.then(function(dataResponse) {
				$state.go('management.posts');
			}, function(errResponse){
                console.error('Error while creating post');
            });
		}
	}
	
})();